package me.anisimov.agency.service;

import me.anisimov.agency.domain.User;
import me.anisimov.agency.persistance.repository.UserRepository;
import me.anisimov.agency.util.UserDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserDetailsMapper userDetailsMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByPhoneOrEmail(username);
        UserDetails userDetails = userDetailsMapper.convertToUserDetails(user);
        return userDetails;
    }
}
