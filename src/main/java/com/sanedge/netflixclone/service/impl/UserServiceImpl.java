package com.sanedge.netflixclone.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanedge.netflixclone.dto.request.UserRequest;
import com.sanedge.netflixclone.dto.response.MessageResponse;
import com.sanedge.netflixclone.dto.response.StatResponse;
import com.sanedge.netflixclone.exception.NotFoundException;
import com.sanedge.netflixclone.models.User;
import com.sanedge.netflixclone.repository.UserRepository;
import com.sanedge.netflixclone.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return userRepository.findByUsername(authentication.getName())
                .orElseThrow(
                        () -> new UsernameNotFoundException("User name not found - " + authentication.getName()));

    }

    @Override
    public MessageResponse findAll() {
        List<User> myUsers = this.userRepository.findAll();

        if (myUsers.isEmpty()) {
            return MessageResponse.builder().message("users kosong").data(null).statusCode(400).build();
        }

        return MessageResponse.builder().message("Berhasil mendapatkan data").data(myUsers).build();
    }

    @Override
    public MessageResponse findById(Long id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("not found user by id"));

        return MessageResponse.builder().message("Berhasil mendapatkan data").data(user).statusCode(200).build();
    }

    @Override
    public List<StatResponse> statResponse() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, -1);
        Date oneYearAgo = calendar.getTime();

        List<StatResponse> data = userRepository.getStats(oneYearAgo);

        return data;
    }

    @Override
    public MessageResponse create(UserRequest userRequest) {
        User user = new User();

        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(this.passwordEncoder.encode(userRequest.getPassword()));
        user.setProfilePic(userRequest.getProfilePic());
        user.setAdmin(userRequest.getAdmin());

        this.userRepository.save(user);

        return MessageResponse.builder().message("Berhasil mendapatkan data").data(user).statusCode(200).build();
    }

    @Override
    public MessageResponse findByIdUpdate(Long id, UserRequest userRequest) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("not found user by id"));
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(this.passwordEncoder.encode(userRequest.getPassword()));
        user.setProfilePic(userRequest.getProfilePic());
        user.setAdmin(userRequest.getAdmin());

        this.userRepository.save(user);

        return MessageResponse.builder().message("Berhasil mendapatkan data").data(user).statusCode(200).build();
    }

    @Override
    public MessageResponse deleteById(Long id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("not found user by id"));

        this.userRepository.delete(user);

        return MessageResponse.builder().message("Berhasil mendelete user").data(null).statusCode(200).build();
    }
}
