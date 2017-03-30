package com.inkdrop.infrastructure.repositories;

import com.inkdrop.domain.models.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  User findByUid(Integer uid);

  @Cacheable(cacheNames = "userByBackendToken")
  User findByBackendAccessToken(String token);
}