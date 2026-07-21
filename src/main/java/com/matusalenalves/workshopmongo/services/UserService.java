package com.matusalenalves.workshopmongo.services;

import com.matusalenalves.workshopmongo.domain.User;
import com.matusalenalves.workshopmongo.dto.UserDTO;
import com.matusalenalves.workshopmongo.repository.UserRepository;
import com.matusalenalves.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Object not found."));
    }

    public User insert(User user) {
        return userRepository.insert(user);
    }

    public void delete(String id) {
        userRepository.delete(findById(id));
    }

    public User update(User data) {
        User user = findById(data.getId());
        updateData(user, data);
        return userRepository.save(user);
    }

    public User fromDTO(UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
    }

    private void updateData(User user, User data) {
        user.setName(data.getName());
        user.setEmail(data.getEmail());
    }
}