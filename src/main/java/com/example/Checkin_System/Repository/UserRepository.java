package com.example.Checkin_System.Repository;

import com.example.Checkin_System.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    // Example custom query to find a User by userName
    User findByUserName(String userName);

    User findByUserNameAndPasswordHash(String username, String passhash);

    @Query(value = "SELECT * FROM users WHERE user_name IN (SELECT user_name FROM checkins WHERE google_place_id = :googlePlaceId AND sysdate() >= checkin_time AND sysdate() <= checkout_time)", nativeQuery = true)
    List<User> findCurrentCheckInsByGooglePlaceId(@Param("googlePlaceId") String googlePlaceId);

    User findByEmail(String email);

}
