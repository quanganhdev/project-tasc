package com.example.projecttasc.controller.user;

import com.example.projecttasc.database.entity.Role;
import com.example.projecttasc.database.entity.User;
import com.example.projecttasc.services.UserServices;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cl/user")
@RequiredArgsConstructor
public class UseronUserController {
    private final UserServices userServices;

    @GetMapping
    public ResponseEntity<List<User>>getUsers(){
        return ResponseEntity.ok(userServices.getUsers());
    }
    @PostMapping
    public ResponseEntity<User> saveuser(@RequestBody User user){
        return new ResponseEntity(userServices.saveUser(user), HttpStatus.OK);
    }
    @PostMapping("/saverole")
    public ResponseEntity<Role> saverole(@RequestBody Role role){
        return new ResponseEntity<Role>(userServices.saveRole(role),HttpStatus.OK);
    }
    @PostMapping("/addroletouser")
    public ResponseEntity<?> addroletouser (@RequestBody RoleToUser from){
        userServices.addRoletoUser(from.getUsername(),from.getRolename());
        return ResponseEntity.ok().build();
    }
    @Data
    class RoleToUser{
        private String username;
        private String rolename;

    }

}
