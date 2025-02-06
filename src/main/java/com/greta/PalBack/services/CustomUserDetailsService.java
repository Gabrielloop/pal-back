package com.greta.PalBack.services;

import com.greta.PalBack.daos.UserDao;
import com.greta.PalBack.entities.CustomUserDetails;
import com.greta.PalBack.entities.User;
import com.greta.PalBack.exceptions.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDao userDao;

    public CustomUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByMail(username);
        return new CustomUserDetails(user);
    }
}