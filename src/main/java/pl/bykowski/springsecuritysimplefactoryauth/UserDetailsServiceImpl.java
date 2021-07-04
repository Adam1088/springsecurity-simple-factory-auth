package pl.bykowski.springsecuritysimplefactoryauth;

import pl.bykowski.springsecuritysimplefactoryauth.exception.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.bykowski.springsecuritysimplefactoryauth.repository.AppUserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private AppUserRepo appUserRepo;

    public UserDetailsServiceImpl(AppUserRepo appUserRepo) {
        this.appUserRepo = appUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        //todo throw if not exists
        return appUserRepo.findByLogin(login).orElseThrow(UserNotFoundException::new);
    }

}
