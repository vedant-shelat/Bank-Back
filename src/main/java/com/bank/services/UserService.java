package com.bank.services;

import com.bank.dto.UserDTO;
import com.bank.model.Role;
import com.bank.model.User;
import com.bank.repository.UserRepository;
import com.bank.utility.MailConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private MailConstructor mailConstructor;
    private JavaMailSender javaMailSender;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       MailConstructor mailConstructor, JavaMailSender javaMailSender) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailConstructor = mailConstructor;
        this.javaMailSender = javaMailSender;
    }

    @Transactional
    public void registerNewUser(UserDTO userDTO)  {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setRole(Role.ROLE_USER);
        user.setPassword(this.passwordEncoder.encode(userDTO.getPassword()));
        user.setCreationDate(new Date().getTime());
        this.userRepository.save(user);
    }

    @Transactional
    public UserDTO getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = this.userRepository.findByUsername(authentication.getName());
        return new UserDTO(user);
    }

    @Transactional
    public ResponseEntity<?> forgotPassword(UserDTO userDTO) {
        String email = userDTO.getEmail();
        if(this.userRepository.findByEmail(email) == null) {
            return new ResponseEntity<>("Email doesn't exists", HttpStatus.CONFLICT);
        }
        try {
            SimpleMailMessage mail = this.mailConstructor.sendForgotPwdMail(userDTO);
            this.javaMailSender.send(mail);
            return new ResponseEntity<>("Mail send", HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.CONFLICT);
        }
    }

    @Transactional
    public ResponseEntity<?> updateUserInfo(UserDTO userDTO) {
        User user = this.userRepository.findById(userDTO.getId()).orElse(null);
        user.setPassword(this.passwordEncoder.encode(userDTO.getPassword()));
        this.userRepository.save(user);
        return ResponseEntity.ok(new UserDTO(user));
    }
}
