// Repository package: com.amar.repository

package com.amar.repository;

import com.amar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
