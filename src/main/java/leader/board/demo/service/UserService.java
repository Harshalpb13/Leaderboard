package leader.board.demo.service;

import leader.board.demo.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(String userId);
    User registerUser(User newUser);
    User updateUserScore(String userId, int newScore);
    void deleteUser(String userId);
}
