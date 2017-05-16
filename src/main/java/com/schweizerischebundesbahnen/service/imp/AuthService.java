package com.schweizerischebundesbahnen.service.imp;

import com.schweizerischebundesbahnen.model.User;
import com.schweizerischebundesbahnen.repository.UserRepository;
import com.schweizerischebundesbahnen.service.api.AttendanceService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by aleksandrprendota on 26.03.17.
 *
 * Spring security class for authorization
 */

@Service
@Log4j
public class AuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AttendanceService attendanceService;

    /**
     * @param username - user Email
     * @return
     * @throws UsernameNotFoundException when eMail isn't correct
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails loadedUser;
        if (username.equals("")) {
            log.info("Empty username in login form");
            throw new UsernameNotFoundException(username);

        }

        if ((userRepository.findByEmail(username)) == null){
            log.info("No found username in database");
            throw new UsernameNotFoundException(username);
        }

        try {
            User client = userRepository.findByEmail(username);
            loadedUser = new org.springframework.security.core.userdetails.User(
                    client.getEmail(), client.getPassword(),
                    client.getRoles());
            attendanceService.updateAttendance(new Date());
        } catch (Exception repositoryProblem) {
            throw new InternalAuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
        }
        return loadedUser;
    }

    static class DummyAuthority implements GrantedAuthority {
        static Collection<GrantedAuthority> getAuth()
        {
            List<GrantedAuthority> res = new ArrayList<>(1);
            res.add(new DummyAuthority());
            return res;
        }
        @Override
        public String getAuthority() {
            return "USER";
        }
    }

}
