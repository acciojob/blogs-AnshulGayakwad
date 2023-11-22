package com.driver.repositories;

import com.driver.models.Blog;
import com.driver.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    @Modifying
    @Query("Update User u SET u.password =: password WHERE u.id =: id")
    public User updatePassword(@Param("id") int id, @Param("password") String password);
}
