package com.sanedge.netflixclone.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sanedge.netflixclone.dto.response.StatResponse;
import com.sanedge.netflixclone.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query(value = "SELECT MONTH(createdAt) as month, COUNT(id) as total FROM User WHERE createdAt >= ?1 GROUP BY MONTH(createdAt)", nativeQuery = true)
    List<StatResponse> getStats(Date oneYearAgo);
}
