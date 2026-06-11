package com.task1.user_management_api.service;
import com.task1.user_management_api.entity.User;
import com.task1.user_management_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public User save(User user) {
        return userRepository.save(user);
    }
    public User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }
    public User updateUser(Long id, User updatedUser) {
        User user = getUser(id);
        user.setName(updatedUser.getName());
        user.setEmployeeType(updatedUser.getEmployeeType());
        return userRepository.save(user);
    }
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}