package com.railway.railwayservice.repository;

import com.railway.railwayservice.entity.Role;
import com.railway.railwayservice.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface Role repository.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    /**
     * Find by name optional.
     *
     * @param name the name
     * @return the optional
     */
    Optional<Role> findByName(ERole name);
}
