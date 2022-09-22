package com.apiexample.springapiexample;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class SpringAPIController {

    HashMap<String,String>log=new HashMap<>();
    @PostMapping("/Users")
    public ResponseEntity<Object> create(@RequestBody UserInfo post){
        log.put(post.Name,post.Password);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(log);
    }

    @GetMapping("/Users/{userName}")
    public ResponseEntity<Object> getpass(@PathVariable String userName){
        ArrayList user=new ArrayList(log.keySet());
        if(user.contains(userName)){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("The password is "+log.get(userName));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not found");
    }

    @DeleteMapping("/Users/{userName}")
    public ResponseEntity<Object> del(@PathVariable String userName){
        if(log.containsKey(userName)){
            log.remove(userName);
            return ResponseEntity.status(HttpStatus.FOUND).body("It is deleted"+"\n"+log);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not Found");
    }

    @PutMapping("/Users/{userName}")
    public ResponseEntity<Object> upd(@PathVariable String userName,@RequestBody UserInfo n){
        if(log.containsKey(userName)){
            log.put(userName,n.Password);
            return ResponseEntity.status(HttpStatus.OK).body("Password is Updated"+"\n"+log);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not Found");
    }
}
