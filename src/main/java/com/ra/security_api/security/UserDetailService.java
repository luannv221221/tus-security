package com.ra.security_api.security;

import com.ra.security_api.model.entity.User;
import com.ra.security_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getByUsername(username);

        // conver User ==> UserPrinciple
        UserPrinciple userPrinciple = new UserPrinciple();
        userPrinciple.setUser(user);
        // gan danh sach quyen cua user
        userPrinciple.setGrantedAuthorities(user.getRoles()
                .stream().map(role-> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet()));
        return userPrinciple;
    }
}
