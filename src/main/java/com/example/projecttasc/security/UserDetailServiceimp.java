package com.example.projecttasc.security;

import com.example.projecttasc.database.entity.User;
import com.example.projecttasc.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceimp implements UserDetailsService {
    @Autowired UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         User user = userRepository.findUserByUserName(username);
         if (user!=null){
             System.out.println("da tim thay");
         }else {
             System.out.println("kong "+ username);
         }
          UserDetailimp userDetailimp = UserDetailimp.build(user);
         if (userDetailimp==null){
             System.out.println("tao khong duoc");
         }
         return userDetailimp;
    }
}
