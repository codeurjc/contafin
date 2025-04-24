package com.daw.contafin.service;

import com.daw.contafin.config.jwt.UserDetailsImpl;
import com.daw.contafin.entity.User;
import com.daw.contafin.mapper.UserMapper;
import com.daw.contafin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Resource
    UserMapper userMapper;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            User user = userRepository.findByEmail(username);
            if (user == null) {
                user = userRepository.findByName(username);
            }
            return UserDetailsImpl.build(userMapper.UserToUserDto(user));
        }catch (Exception e){
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }
    }
}
