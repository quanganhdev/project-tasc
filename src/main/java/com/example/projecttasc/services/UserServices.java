package com.example.projecttasc.services;

import com.example.projecttasc.database.entity.Role;
import com.example.projecttasc.database.entity.User;
import com.example.projecttasc.database.repository.RoleRepository;
import com.example.projecttasc.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServices {
    private final UserRepository userRepository;
    private  final RoleRepository roleRepository;
    public User saveUser (User user){
        log.info("save new user to data base");
        return userRepository.save(user);
    }
    public Role saveRole(Role role){
        log.info("save new role to data base");
        return roleRepository.save(role);
    }
    public void addRoletoUser(String rolename,String username){
        User user = userRepository.findByUserName(username);
        Role role = roleRepository.findByName(rolename);
        user.getRoles().add(role);
        log.info("add role to user data base");
    }
    public User getUser (String  username){
        return userRepository.findByUserName(username);
    }
    public List<User> getUsers(){
        log.info("get list user in data base");
        return userRepository.findAll();
    }
}
