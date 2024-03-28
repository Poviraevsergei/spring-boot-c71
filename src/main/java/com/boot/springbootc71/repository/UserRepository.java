package com.boot.springbootc71.repository;

import com.boot.springbootc71.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM users")  //Query work only for read!!!
    List<User> customGetAllUsers();


}
