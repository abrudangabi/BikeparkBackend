package com.gabi.backend.bikeparkend.repository;

import com.gabi.backend.bikeparkend.model.Role;
import com.gabi.backend.bikeparkend.model.RoleString;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleStringEquals(RoleString roleString);
}
