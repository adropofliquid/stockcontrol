package com.dami.stockcontrol.service;

import com.dami.stockcontrol.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    PersonService personService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Person person = checkUserExist(s);

        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getPassword() {
                return person.getPassword();
            }

            @Override
            public String getUsername() {
                return person.getUsername();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return person.getEnabled();
            }
        };
    }

    private Person checkUserExist(String username){
        return personService.getPersonByUsername(username);
    }


}
