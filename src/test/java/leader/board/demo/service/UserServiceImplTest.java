package leader.board.demo.service;

import leader.board.demo.entity.User;
import leader.board.demo.exception.AlreadyExists;
import leader.board.demo.exception.NoUserPresent;
import leader.board.demo.exception.UserNotFoundException;
import leader.board.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void testGetAllUsers_NoUsers() throws Exception {
        List<User> userList = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(userList);

        assertThrows(NoUserPresent.class, () -> userService.getAllUsers());
    }

    @Test
    public void testGetAllUsers_UsersPresent() throws Exception {
        List<User> userList = new ArrayList<>();
        userList.add(new User("1", "Harsh", 0, new ArrayList<>()));
        userList.add(new User("2", "Rohit", 0, new ArrayList<>()));
        when(userRepository.findAll()).thenReturn(userList);

        List<User> returnedList = userService.getAllUsers();

        assertEquals(2, returnedList.size());
        assertEquals("1", returnedList.get(0).getUserId());
        assertEquals(0, returnedList.get(0).getScore());
    }

    @Test
    public void testGetUserById_UserFound() throws Exception {
        String userId = "1";
        User user = new User(userId, "Harsh", 0, new ArrayList<>());
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        User returnedUser = userService.getUserById(userId);

        assertEquals(user, returnedUser);
        assertEquals(userId, returnedUser.getUserId());
        assertEquals("Harsh", returnedUser.getUsername());
        assertEquals(0, returnedUser.getScore());
    }
    @Test
    public void testGetUserById_UserNotFound() throws Exception {
        String userId = "1";
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserById(userId));
    }
    @Test
    public void testRegisterUser_NewUser() throws Exception {

        User newUser = new User("1", "Harsh", 0, new ArrayList<>());
        when(userRepository.findById(newUser.getUserId())).thenReturn(Optional.empty());
        when(userRepository.save(newUser)).thenReturn(newUser);

        User returnedUser = userService.registerUser(newUser);

        assertEquals(newUser, returnedUser);
        assertEquals(0, returnedUser.getScore());
        assertEquals(0, returnedUser.getBadges().size());
    }

    @Test
    public void testRegisterUser_ExistingUser() throws Exception {

        User newUser = new User("1", "Harsh", 0, new ArrayList<>());
        when(userRepository.findById(newUser.getUserId())).thenReturn(Optional.of(newUser));

        assertThrows(AlreadyExists.class, () -> userService.registerUser(newUser));
    }






    @Test
    public void testUpdateUserScore_UserFound() throws Exception {
        User newUser = new User("1", "Harsh", 0, new ArrayList<>());
        int newScore = 20;
        when(userRepository.findById(newUser.getUserId())).thenReturn(Optional.of(newUser));
        when(userRepository.save(newUser)).thenReturn(newUser);

        User returnedUser = userService.updateUserScore("1", newScore);

        assertEquals(newUser, returnedUser);
        assertEquals(newScore, returnedUser.getScore());
    }

    @Test
    public void testUpdateUserScore_UserNotFound() throws Exception {
        String userId = "1";
        int newScore = 20;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.updateUserScore(userId, newScore));
    }
    @Test
    public void testUpdateUserScore_AwardBadge() throws Exception {

        User user = new User("1", "username", 0, new ArrayList<>()); // Initial score
        int newScore = 10;
        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        User returnedUser = userService.updateUserScore(user.getUserId(), newScore);
        assertEquals(newScore, returnedUser.getScore());

        assertTrue(returnedUser.getBadges().contains("Code Ninja"));
    }

}