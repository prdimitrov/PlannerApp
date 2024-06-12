package com.plannerapp.service;

import com.plannerapp.model.bindingModels.UserLoginModel;
import com.plannerapp.model.bindingModels.UserRegisterModel;
import com.plannerapp.model.entity.User;
import com.plannerapp.repo.UserRepository;
import com.plannerapp.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;

    public UserService(ModelMapper modelMapper, UserRepository userRepository, PasswordEncoder passwordEncoder, CurrentUser currentUser) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
    }

    public void registerUser(UserRegisterModel userModel) {

        User user = modelMapper.map(userModel, User.class);
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));

        userRepository.save(user);
//        userRepository.saveAndFlush(user);
    }

    public void login(UserLoginModel loginModel) {
        User user = findByUsername(loginModel.getUsername()).get();
        currentUser.setUsername(loginModel.getUsername());
        currentUser.setLoggedIn(true);
    }

    public void logout() {
        currentUser.clear();
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
