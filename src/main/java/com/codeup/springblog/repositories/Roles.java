package com.codeup.springblog.repositories;

import com.codeup.springblog.models.UserWithRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Roles extends JpaRepository<UserWithRoles, Long> {
    @Query("select ur.id from User ur, User u where u.username=?1 and ur.id = u.id")
    List<String> ofUserWith(String username);
}
