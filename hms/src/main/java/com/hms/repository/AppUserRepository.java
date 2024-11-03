package com.hms.repository;

import com.hms.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
  Optional<AppUser> findByUsername(String username);//first finder method
  Optional<AppUser>findByEmail(String Email);
} //this finder method job is to check database ,is this username is present in database.if yes do not allow to sing up.