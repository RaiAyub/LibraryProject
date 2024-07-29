package com.library.management.repo;

import com.library.management.entity.role.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username= :username")
    public  User getUserByUsername(@Param("username") String username);
}
