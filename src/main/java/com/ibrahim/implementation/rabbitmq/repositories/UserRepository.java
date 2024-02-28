package com.ibrahim.implementation.rabbitmq.repositories;

import com.ibrahim.implementation.rabbitmq.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select * from user where balance>:balance", nativeQuery = true)
    public List<User> getUserByBalance(@Param("balance") Long balance);

    public User findByUsername(@Param("username") String username);

}
