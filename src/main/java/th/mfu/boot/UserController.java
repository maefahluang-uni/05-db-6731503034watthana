package th.mfu.boot;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class UserController {

    //TODO: add userrepository as `public` with @Autowired
    @Autowired 
    public UserRepository repo;
   
    @PostMapping("/users")
    public ResponseEntity<String> registerUser(@RequestBody User user) {

        //TODO: check if user with the username exists
       User existingUser = repo.findByUsername(user.getUsername());
       if (existingUser != null){
        return new ResponseEntity<>(HttpStatus.CONFLICT);
       }
        //TODO: save the user
        repo.save(user);
        //TODO: remove below and return proper status
        return new ResponseEntity<String>( HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> list() {
        
        //TODO: remove below and return proper result
        List<User> users = repo.findAll();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        
        //TODO: check if user with the id exists
       if (!repo.existsById(id)){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
        //TODO: delete the user
        repo.deleteById(id);
        //TODO: remove below and return proper status
        return new ResponseEntity<String>( HttpStatus.NO_CONTENT);
    }


}
