package com.bank.resources;

import com.bank.config.JwtTokenUtil;
import com.bank.dto.UserDTO;
import com.bank.repository.UserRepository;
import com.bank.services.JwtUserDetailService;
import com.bank.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

@RestController
public class JwtAuthenticationResources {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailService jwtUserDetailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/authenticate")
    public Map<String, String> createAuthenticationToken(@RequestBody UserDTO userDto, HttpServletRequest req) throws Exception {
        this.authenticate(userDto.getUsername(), userDto.getPassword(), req);
        UserDetails userDetails = this.jwtUserDetailService.loadUserByUsername(userDto.getUsername());
        String token = this.jwtTokenUtil.generateToken(userDetails);
        return Collections.singletonMap("token", token);
    }

    private void authenticate(String username, String password, HttpServletRequest req) throws Exception {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch(BadCredentialsException e) {
            req.setAttribute("badCredentials", e.getMessage());
            throw new Exception("BAD_CREDENTIALS", e);
        } catch(DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerNewUser(@RequestBody UserDTO userDto) {
        String username = userDto.getUsername();
        String email = userDto.getEmail();
        if(this.userRepository.findByUsername(username) != null) {
            return new ResponseEntity<>("Username exists", HttpStatus.CONFLICT);
        }
        if(this.userRepository.findByEmail(email) != null) {
            return new ResponseEntity<>("Email exists", HttpStatus.CONFLICT);
        }
        try {
            this.userService.registerNewUser(userDto);
            return new ResponseEntity<>("New user saved", HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.CONFLICT);
        }
    }
}
