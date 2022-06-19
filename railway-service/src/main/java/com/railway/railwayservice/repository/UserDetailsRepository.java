package com.railway.railwayservice.repository;

import com.railway.railwayservice.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface User details repository.
 */
@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long>{
    /**
     * Find by username optional.
     *
     * @param username the username
     * @return the optional
     */
    Optional<UserDetails> findByUsername(String username);

    /**
     * Exists by username boolean.
     *
     * @param username the username
     * @return the boolean
     */
    Boolean existsByUsername(String username);

    /**
     * Exists by email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    Boolean existsByEmail(String email);
}
