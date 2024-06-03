package leader.board.demo.controller;

import leader.board.demo.entity.User;
import leader.board.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/User")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/User/{userId}")
    public User getUserById(@PathVariable String userId) {
               return userService.getUserById(userId);
    }

    @PostMapping("/User")
    public ResponseEntity<User> registerUser(@RequestBody User newUser) {
        User savedUser = userService.registerUser(newUser);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("/User/{userId}")
    public ResponseEntity<User> updateUserScore(@PathVariable String userId, @RequestParam int newScore) {
        User updatedUser = userService.updateUserScore(userId, newScore);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/User/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
