package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public User findById(Long id) {
    return userRepository.findById(id).orElse(null);
  }

  public User save(User user) {
    return userRepository.save(user);
  }

  public void deleteById(Long id) {
    userRepository.deleteById(id);
  }
}