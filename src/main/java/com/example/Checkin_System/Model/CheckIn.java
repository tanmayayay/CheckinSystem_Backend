package com.example.Checkin_System.Model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "checkins") // Ensure table name matches the actual table name, case-sensitive
public class CheckIn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Google_place_id", nullable = false)
    private String googlePlaceId;

    @Column(name = "Place_name", nullable = false) // Added field for place name
    private String placeName;

    @Column(name = "Checkout_time", nullable = false)
    private Timestamp checkoutTime;

    @Column(name = "checkin_time", nullable = false)
    private Timestamp checkinTime;

    @Column(name = "user_name", nullable = false)
    @JoinColumn(name = "user_name", referencedColumnName = "user_name", foreignKey = @ForeignKey(name = "FK_CheckIns_Users"))
    private String userName;

    public CheckIn() {
        // Set checkin time to current time
        this.checkinTime = Timestamp.valueOf(LocalDateTime.now());

        // Set checkout time to 24 hours later
        LocalDateTime checkoutDateTime = LocalDateTime.now().plusHours(24);
        this.checkoutTime = Timestamp.valueOf(checkoutDateTime);
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public String getGooglePlaceId() {
        return googlePlaceId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public Timestamp getCheckoutTime() {
        return checkoutTime;
    }

    public Timestamp getCheckinTime() {
        return checkinTime;
    }

    public String getUserName() {
        return userName;
    }

    // Setters

    public void setId(int id) {
        this.id = id;
    }

    public void setGooglePlaceId(String googlePlaceId) {
        this.googlePlaceId = googlePlaceId;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public void setCheckoutTime(Timestamp checkoutTime) {
        this.checkoutTime = checkoutTime;
    }

    public void setCheckinTime(Timestamp checkinTime) {
        this.checkinTime = checkinTime;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
