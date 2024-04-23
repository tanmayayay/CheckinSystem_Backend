package com.example.Checkin_System.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "given_name", nullable = false)
    private String givenName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "military_id", unique = true, nullable = false)
    private String militaryId;

    @Column(name = "military_rank", nullable = false)
    private String militaryRank;

    @Column(name = "user_name", unique = true, nullable = false)
    private String userName;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "profile_photo")
    private String profilePhoto;

    @Column(name = "email", nullable = false, unique = true)  // Added this line
    private String email;

    @Column(name = "otp") // OTP field, can be null when not in use
    private String otp;

    @Column(name = "otp_expiration") // OTP expiration time
    private LocalDateTime otpExpiration;

    // Getters and Setters
    // Getters and setters for all fields including the new email field

    public int getId() {
        return id;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMilitaryId() {
        return militaryId;
    }

    public String getMilitaryRank() {
        return militaryRank;
    }

    public String getUserName() {
        return userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public String getEmail() {  // Getter for email
        return email;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMilitaryId(String militaryId) {
        this.militaryId = militaryId;
    }

    public void setMilitaryRank(String militaryRank) {
        this.militaryRank = militaryRank;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public void setEmail(String email) {  // Setter for email
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public LocalDateTime getOtpExpiration() {
        return otpExpiration;
    }

    public void setOtpExpiration(LocalDateTime otpExpiration) {
        this.otpExpiration = otpExpiration;
    }
}
