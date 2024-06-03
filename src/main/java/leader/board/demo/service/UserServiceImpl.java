package leader.board.demo.service;

import leader.board.demo.entity.User;
import leader.board.demo.exception.AlreadyExists;
import leader.board.demo.exception.NoUserPresent;
import leader.board.demo.exception.UserNotFoundException;
import leader.board.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class  UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
            try {
                List<User> userList= userRepository.findAll();
                if(userList.size()==0 ||userList.isEmpty()){
                    throw new NoUserPresent("No Users are present");
                }
                if(userList.size()!=0){
                    return userList.stream()
                            .sorted(Comparator.comparingInt(User::getScore).reversed())
                            .collect(Collectors.toList());
                }
                return userList;
            } catch (Exception e){
                throw new NoUserPresent("String is empty "+e.getMessage());
            }


    }

    @Override
    public User getUserById(String userId) {

           try {
               Optional<User> user = userRepository.findById(userId);
               if (user.isPresent()) {
                   return user.get();
               } else {
                   throw new UserNotFoundException(userId);
               }
           }catch (UserNotFoundException e){
               throw new UserNotFoundException("user is not Present "+e.getMessage());
           }catch (Exception e){
               throw  new UserNotFoundException("user is not Present "+e.getMessage());
           }

    }

    @Override
    public User registerUser(User newUser) {
        try {
            // Check if user already exists by their unique identifier (e.g., email)
            Optional<User> existingUser = userRepository.findById(newUser.getUserId());

            if (existingUser.isPresent()) {
                // If user already exists, return the existing user
                throw new AlreadyExists(newUser.getUserId());
            } else {
                // If user does not exist, set default properties and save the new user
                newUser.setScore(0);
                newUser.setBadges(new ArrayList<>());
                return userRepository.save(newUser);
            }
        } catch (Exception e) {
            throw new AlreadyExists("already exists,UserId :-"+e.getMessage());
        }
    }

    @Override
    public User updateUserScore(String userId, int newScore) {
        try {
            Optional<User> optionalUser = userRepository.findById(userId);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                user.setScore(newScore);
                awardBadges(user);
                return userRepository.save(user);
            } else {
                throw new UserNotFoundException("User not found with id: " + userId);
            }
        } catch (Exception e) {
            throw new UserNotFoundException("Error updating user score"+e.getMessage());
        }
    }

    @Override
    public void deleteUser(String userId) {
        try {
            if (userRepository.existsById(userId)) {
                userRepository.deleteById(userId);
            } else {
                throw new UserNotFoundException("User not found with id: " + userId);
            }
        } catch (DataAccessException e) {
            throw new UserNotFoundException("Error deleting user"+e.getMessage());
        }
    }

    private void awardBadges(User user) {
        int score = user.getScore();
        List<String> badges = user.getBadges();
        if (score >= 60 && !badges.contains("Code Master")) {
            badges.add("Code Master");
        } else if (score >= 30 && !badges.contains("Code Champ")) {
            badges.add("Code Champ");
        } else if (score >= 1 && !badges.contains("Code Ninja")) {
            badges.add("Code Ninja");
        }
        if (badges.size() > 3) {
            badges.remove(0);
        }
        user.setBadges(badges);
    }

    // Custom exception classes



}
