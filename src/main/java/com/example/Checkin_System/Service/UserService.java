package com.example.Checkin_System.Service;

import com.example.Checkin_System.Model.User;
import com.example.Checkin_System.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    private EmailService emailService;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }


    public User registerUser(User user) {
        // Additional logic can be added here (e.g., validation, encoding passwords)
        return userRepository.save(user);
    }

    public User login(String Username, String Password) {
        return userRepository.findByUserNameAndPasswordHash(Username,Password);
    }

    public List<User> findCurrentCheckInsByGooglePlaceId(String googlePlaceId) {
        return userRepository.findCurrentCheckInsByGooglePlaceId(googlePlaceId);
    }

    public boolean processForgotPassword(String email) {
        System.out.println(email);
        User user = userRepository.findByEmail(email);
        if (user == null) {
            System.out.println("here");
            return false;
        }

        String otp = generateOtp(); // Implement this method to generate a random OTP
        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(10); // 10 minutes from now

        user.setOtp(otp);
        user.setOtpExpiration(expirationTime);
        userRepository.save(user);

        emailService.sendOtpEmail(user.getEmail(), otp); // Implement this method to send the email
        return true;
    }

    private String generateOtp() {
        SecureRandom random = new SecureRandom();
        int num = random.nextInt(1000000); // Generates a number between 0 - 999999
        return String.format("%06d", num); // Formats the number to ensure it is 6 digits
    }

    public boolean resetPassword(String email, String otp, String newPassword) {
        User user = userRepository.findByEmail(email);
        if (user == null || !otp.equals(user.getOtp()) || LocalDateTime.now().isAfter(user.getOtpExpiration())) {
            return false;
        }

        user.setPasswordHash(newPassword); // Implement password hashing
        user.setOtp(null); // Clear the OTP fields
        user.setOtpExpiration(null);
        userRepository.save(user);
        return true;
    }

    private String hashPassword(String password) {
        // Implement password hashing (e.g., using BCrypt)
        return password; // Placeholder
    }



    // Additional service methods as needed
}
