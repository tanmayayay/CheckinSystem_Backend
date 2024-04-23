package com.example.Checkin_System.Service;

import com.example.Checkin_System.Model.CheckIn;
import com.example.Checkin_System.Repository.CheckInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckInService {

    private final CheckInRepository checkInRepository;

    @Autowired
    public CheckInService(CheckInRepository checkInRepository) {
        this.checkInRepository = checkInRepository;
    }




    public List<CheckIn> findAllCheckIns() {
        return checkInRepository.findAll();
    }


    public CheckIn saveCheckIn(CheckIn checkIn) {
        return checkInRepository.save(checkIn);
    }

    // Additional service methods as needed

    public List<CheckIn> findActiveCheckInsByUserName(String username) {
        return checkInRepository.findActiveCheckInsByUserName(username);
    }


    public List<CheckIn> findCheckInsByUserName(String username) {
        return checkInRepository.findCheckInsByUserName(username);
    }


}
