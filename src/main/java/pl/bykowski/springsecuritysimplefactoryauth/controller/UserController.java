package pl.bykowski.springsecuritysimplefactoryauth.controller;

import org.apache.catalina.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.bykowski.springsecuritysimplefactoryauth.entity.AppUser;
import pl.bykowski.springsecuritysimplefactoryauth.entity.Token;
import pl.bykowski.springsecuritysimplefactoryauth.exception.UserNotFoundException;
import pl.bykowski.springsecuritysimplefactoryauth.repository.AppUserRepo;
import pl.bykowski.springsecuritysimplefactoryauth.repository.TokenRepository;
import pl.bykowski.springsecuritysimplefactoryauth.service.UserService;

import java.security.Principal;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

@Controller
public class UserController {

    private UserService userService;
    private TokenRepository tokenRepository;
    private AppUserRepo appUserRepo;

    public UserController(UserService userService, TokenRepository tokenRepository, AppUserRepo appUserRepo) {
        this.userService = userService;
        this.tokenRepository = tokenRepository;
        this.appUserRepo = appUserRepo;
    }

//    @GetMapping("/hello")
//    @ResponseBody
//    public String hello() {
//        return "hello";
//    }

    @GetMapping("/hello")
    public String forUser(Principal principal, Model model) {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        model.addAttribute("name", principal.getName());
        model.addAttribute("authorities", authorities);
        return "hello";
    }

    @GetMapping("/register")
    public String signUp(Model model) {
        model.addAttribute("user", new AppUser());
        return "sign-up";
    }

    @PostMapping("/register")
    public String register(AppUser appUser) {
        userService.addUser(appUser);
        return "sign-up";
    }

    @GetMapping("/token")
    @ResponseBody
    public String token(@RequestParam String value){
        Optional<Token> optionalToken = tokenRepository.findByValue(value);
        if(optionalToken.isPresent()){
            AppUser user = optionalToken.get().getUser();
            user.setEnabled(true);
            appUserRepo.save(user);
            return "Weryfikacja udana!";
        } else {
            return "Zly token - weryfikacja nieudana!";
        }

    }


}
