package com.anna.beadando.repository;

import com.anna.beadando.entity.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser,Long> {

    ApplicationUser findByUsername(String username);
}
