package com.matusalenalves.workshopmongo.resources;

import com.matusalenalves.workshopmongo.domain.User;
import com.matusalenalves.workshopmongo.dto.UserDTO;
import com.matusalenalves.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResources {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> users = userService.findAll();
        List<UserDTO> userDTOS = users.stream().map(user -> new UserDTO(user)).toList();
        return ResponseEntity.ok().body(userDTOS);
    }
}