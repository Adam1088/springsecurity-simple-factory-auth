package pl.bykowski.springsecuritysimplefactoryauth.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.bykowski.springsecuritysimplefactoryauth.entity.AppUser;
import pl.bykowski.springsecuritysimplefactoryauth.repository.AppUserRepo;

@Component
public class Start {


    public Start(BCryptPasswordEncoder passwordEncoder, AppUserRepo appUserRepo) {
            AppUser user1 = new AppUser();
            user1.setLogin("123");
            user1.setPassword(passwordEncoder.encode("123"));
            user1.setRole(Role.ROLE_USER.name());
            user1.setEnabled(true);
            appUserRepo.save(user1);

            AppUser user2 = new AppUser();
            user2.setLogin("124");
            user2.setPassword(passwordEncoder.encode("124"));
            user2.setRole(Role.ROLE_ADMIN.name());
            user2.setEnabled(true);
            appUserRepo.save(user2);
    }

}
