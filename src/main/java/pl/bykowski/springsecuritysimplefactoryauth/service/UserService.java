package pl.bykowski.springsecuritysimplefactoryauth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.bykowski.springsecuritysimplefactoryauth.entity.AppUser;
import pl.bykowski.springsecuritysimplefactoryauth.entity.Token;
import pl.bykowski.springsecuritysimplefactoryauth.repository.AppUserRepo;
import pl.bykowski.springsecuritysimplefactoryauth.repository.TokenRepository;
import pl.bykowski.springsecuritysimplefactoryauth.utils.Role;

import javax.mail.MessagingException;
import java.util.UUID;

@Service
public class UserService {

    private AppUserRepo appUserRepo;
    private PasswordEncoder passwordEncoder;
    private TokenRepository tokenRepository;
    private MailService mailService;

    public UserService(AppUserRepo appUserRepo, PasswordEncoder passwordEncoder, TokenRepository tokenRepository, MailService mailService) {
        this.appUserRepo = appUserRepo;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepository = tokenRepository;
        this.mailService = mailService;
    }

    public void addUser(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUser.setRole(Role.ROLE_USER.name());
        appUserRepo.save(appUser);
        sendToken(appUser);
    }

    private void sendToken(AppUser user) {
        String tokenValue = UUID.randomUUID().toString();
        Token token = new Token();
        token.setValue(tokenValue);
        token.setUser(user);
        tokenRepository.save(token);

        String url = "http://localhost:8080/token?value=" + tokenValue;
        try {
            mailService.sendMail(user.getLogin(), "Token", "Wbij na: " + url, false);
        } catch (MessagingException messagingException) {
            messagingException.printStackTrace();
        }

    }
}
