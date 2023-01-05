package com.example.projecttasc.services;

import com.example.projecttasc.database.entity.Role;
import com.example.projecttasc.database.entity.User;
import com.example.projecttasc.database.entity.UserRole;
import com.example.projecttasc.database.repository.RoleRepository;
import com.example.projecttasc.database.repository.UserRepository;
import com.example.projecttasc.database.repository.UserRoleRepository;
import com.example.projecttasc.model.request.UserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServices implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    UserRoleRepository userRoleRepository;

    public User saveUser (UserRequest request){
        log.info(request.getFullName());
        log.info("save new user to data base");
        log.info(request.getUserName());
        request.setPassword((passwordEncoder.encode(request.getPassword())));
        User find= userRepository.findByUserName(request.getUserName());
        if (find !=null){
            log.info("user name duplicate");
            return find;
        }else {
            User user = new User();
            user.setUserName(request.getUserName());
            user.setFullName(request.getFullName());
            user.setPassword(request.getPassword());
            user.setAddress(request.getAddress());
            user.setEmail(request.getEmail());
            user.setPhone(request.getPhone());
            user.setBrithday(request.getBrithday());
            return userRepository.save(user);
        }

    }

    public Role saveRole(Role role){
        log.info("save new role to data base");
        return roleRepository.save(role);
    }
    public void addRoletoUser(String rolename,String username){
        User user = userRepository.findByUserName(username);
        Role role = roleRepository.findByName(rolename);
        UserRole userRole = new UserRole();
        userRole.setRoleid(role.getId());
        userRole.setUserid(user.getId());
        userRoleRepository.save(userRole);
        log.info("add role to user data base");
    }
    public User getUser (String  username){
        return userRepository.findByUserName(username);
    }
    public List<User> getUsers(){
        log.info("get list user in data base");
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null){
            log.error("khong the tim thay user");
           throw  new UsernameNotFoundException("user not fond");
        }else {
            log.info("da tim thay user");
        }
        Collection<SimpleGrantedAuthority> authorities =  new ArrayList<>();
        user.getRoles().forEach(role ->{
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),authorities);
    }
}
