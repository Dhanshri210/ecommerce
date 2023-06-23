package com.bikkadit.ecommerce.repository;

import com.bikkadit.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    Optional<User> findByUserEmail(String userEmail);

    List<User> findByUserNameContaining(String keyword);


}
