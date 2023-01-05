package com.example.projecttasc.controller.user;

import com.example.projecttasc.database.entity.Role;
import com.example.projecttasc.database.entity.User;
import com.example.projecttasc.model.request.UserRequest;
import com.example.projecttasc.services.UserServices;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
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
    public ResponseEntity<User> saveuser(@RequestBody UserRequest request){
        log.info(request.toString());
        return new ResponseEntity(userServices.saveUser(request), HttpStatus.OK);
    }
    @PostMapping("/saverole")
    public ResponseEntity<Role> saverole(@RequestBody Role role){
        return new ResponseEntity<Role>(userServices.saveRole(role),HttpStatus.OK);
    }
    @PostMapping("/addroletouser")
    public ResponseEntity<?> addroletouser (@RequestParam(required = false) String rolename,@RequestParam(required = false) String username){
        userServices.addRoletoUser(rolename,username);
        return ResponseEntity.ok().build();
    }

}
