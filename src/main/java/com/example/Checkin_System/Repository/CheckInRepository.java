package com.example.Checkin_System.Repository;

import com.example.Checkin_System.Model.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CheckInRepository extends JpaRepository<CheckIn, Long> {
    // Find CheckIns by username using a join

    @Query(value = "SELECT c FROM CheckIn c WHERE CURRENT_TIMESTAMP >= c.checkinTime AND CURRENT_TIMESTAMP <= c.checkoutTime AND c.userName = :username")
    List<CheckIn> findActiveCheckInsByUserName(String username);

    @Query(value = "SELECT c FROM CheckIn c WHERE c.userName = :username")
    List<CheckIn> findCheckInsByUserName(String username);




}

