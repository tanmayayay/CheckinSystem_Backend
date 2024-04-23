package com.example.Checkin_System.Controller;

import com.example.Checkin_System.Model.*;
import com.example.Checkin_System.Service.CheckInService;
import com.example.Checkin_System.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class controller {


    @Autowired
    private UserService userService;
    @Autowired
    private CheckInService checkInService;


    @GetMapping("/")
    public String home() {
        return "Welcome to the application!";
    }
    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<CheckIn>> getAllCheckIns() {
        List<CheckIn> checkIns = checkInService.findAllCheckIns();
        if (checkIns.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(checkIns, HttpStatus.OK);
    }

//    @GetMapping("/activeReservations/{username}")
//    public ResponseEntity<List<CheckIn>> getActiveCheckInsByUser(@PathVariable String username) {
//        List<CheckIn> checkIns = checkInService.findActiveCheckInsByUserName(username);
//        if (checkIns.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(checkIns, HttpStatus.OK);
//    }

    @GetMapping("/reservations/{username}")
    public ResponseEntity<List<CheckIn>> getCheckInsByUser(@PathVariable String username) {
        List<CheckIn> checkIns = checkInService.findCheckInsByUserName(username);
        if (checkIns.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(checkIns, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User registeredUser = userService.registerUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public User loginUser(@Validated @RequestBody Login login_credentials) {
        System.out.println(login_credentials);
            System.out.println(login_credentials.getUsername() + " & " + login_credentials.getPassword());
        return userService.login(login_credentials.getUsername(),login_credentials.getPassword());
    }
    @PostMapping("/loginu")
    public void loginUsers(@RequestBody Object login_credentials) {
        System.out.println(login_credentials.toString());
//        System.out.println(login_credentials.getUsername() + " & " + login_credentials.getPassword());
//        return userService.login(login_credentials.getUsername(),login_credentials.getPassword());
    }

    @PostMapping("/checkin")
    public CheckIn createCheckIn(@RequestBody CheckIn checkIn) {
        System.out.println("hereeee");
        System.out.println(checkIn.toString());
        return checkInService.saveCheckIn(checkIn);
    }

    // Additional endpoints for update, delete, etc., can be added here


    @GetMapping("/profile/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        System.out.println(username);
        User user = userService.findByUserName(username);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/activeReservations/{googlePlaceId}")
    public ResponseEntity<List<User>> getCurrentCheckInsByGooglePlaceId(@PathVariable String googlePlaceId) {
        List<User> checkIns = userService.findCurrentCheckInsByGooglePlaceId(googlePlaceId);
        if (checkIns.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(checkIns, HttpStatus.OK);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        boolean result = userService.processForgotPassword(request.getEmail());
        if (result) {
            return ResponseEntity.ok("OTP sent to email");
        } else {
            return ResponseEntity.badRequest().body("User not found");
        }
    }


    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest request) {
        boolean result = userService.resetPassword(request.getEmail(), request.getOtp(), request.getNewPassword());
        if (result) {
            return ResponseEntity.ok("Password updated successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid OTP or OTP expired");
        }
    }

}
