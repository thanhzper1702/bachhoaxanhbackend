package com.bachhoaxanh.user;

import java.util.List;
import java.util.Optional;

/**
 * Business logic service skeleton class for User operations with VARCHAR(50) PK.
 */
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserById(String maNguoiDung) {
        return userRepository.findById(maNguoiDung);
    }

    public Optional<User> getUserByPhone(String soDienThoai) {
        return userRepository.findBySoDienThoai(soDienThoai);
    }

    public List<User> getAllUsers() {
        return userRepository.findAllActive();
    }

    public User updateUser(String maNguoiDung, User user) {
        return null;
    }

    public void softDeleteUser(String maNguoiDung) {
        userRepository.softDeleteById(maNguoiDung);
    }
}
