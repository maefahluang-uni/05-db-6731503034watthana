package th.mfu.boot;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users") // กำหนด base path
public class UserController {

    @Autowired
    public UserRepository repo;
   
    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        // TODO: check if user with the username exists
        List<User> existingUsers = repo.findByUsername(user.getUsername());
        if (!existingUsers.isEmpty()) {
            return new ResponseEntity<>("Username already exists.", HttpStatus.CONFLICT);
        }
       
        // TODO: save the user
        repo.save(user);

        // TODO: remove below and return proper status
        return new ResponseEntity<>("User created successfully.", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> list() {
        // TODO: remove below and return proper result
        List<User> users = (List<User>) repo.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        // TODO: check if user with the id exists
        Optional<User> userOptional = repo.findById(id);
        if (userOptional.isPresent()) {
            // TODO: delete the user
            repo.deleteById(id);
            return new ResponseEntity<>("User deleted.", HttpStatus.OK);
        }
       
        // TODO: remove below and return proper status
        return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
    }
}