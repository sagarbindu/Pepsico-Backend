package com.casestudy.authentication.controller;

import com.casestudy.authentication.model.User;
import com.casestudy.authentication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@CrossOrigin(allowedHeaders = "*"  ,  origins = "*")
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    
    

    public UserController(UserService userService) {
		super();
		this.userService = userService;
	}


    
	@RequestMapping(value = "/users",method= RequestMethod.GET)
    public ResponseEntity<List<User>> getUsers(){

        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String home(){
        return "User Dashboard";
    }

    @RequestMapping(value = "/{userid}",method= RequestMethod.GET)
    public ResponseEntity<User> getUserById(@PathVariable int userid){

        return new ResponseEntity<>(userService.getUserById(userid), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }

}
