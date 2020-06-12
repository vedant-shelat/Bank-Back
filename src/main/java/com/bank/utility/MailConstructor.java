package com.bank.utility;

import com.bank.dto.UserDTO;
import com.bank.model.User;
import com.bank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MailConstructor {

    @Value("${app.default.mail}")
    private String from;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public SimpleMailMessage sendMailToNewUser(UserDTO userDTO) {
        SimpleMailMessage mail = new SimpleMailMessage();
        String content = "Hello " + userDTO.getUsername() + "," + "\n" +
                "Your account has been created succesfully." + "\n" + "\n" +
                "Regards," + "\n" +
                "SG Bank";
        mail.setTo(userDTO.getEmail());
        mail.setFrom(from);
        mail.setSubject("SG Bank: Account Created");
        mail.setText(content);
        return mail;
    }

    public SimpleMailMessage sendForgotPwdMail(UserDTO userDTO) {
        User user = this.userRepository.findByEmail(userDTO.getEmail());
        String SALT_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder pwd = new StringBuilder();
        Random randomNumber = new Random();
        while(pwd.length() < 18) {
            int index = (int) (randomNumber.nextFloat() * SALT_CHARS.length());
            pwd.append(SALT_CHARS.charAt(index));
        }
        String randomGeneratedPwd = pwd.toString();
        user.setPassword(this.passwordEncoder.encode(randomGeneratedPwd));
        this.userRepository.save(user);

        SimpleMailMessage mail = new SimpleMailMessage();
        String content = "Hello " + user.getUsername() + "," + "\n" +
                "Here is your randomly generated password : " + randomGeneratedPwd + "\n" +
                "You can change your password later in my profile section." + "\n" + "\n" +
                "Have a good day!" + "\n" + "\n" +
                "SG Bank !";
        mail.setFrom(from);
        mail.setTo(userDTO.getEmail());
        mail.setSubject("SG Bank: Password Forgot");
        mail.setText(content);
        return mail;
    }
}
