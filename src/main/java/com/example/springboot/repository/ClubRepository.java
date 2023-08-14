package com.example.springboot.repository;

import com.example.springboot.models.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClubRepository extends JpaRepository<Club,Long> {
    @Query("SELECT c from Club c WHERE c.Title like concat('%',:query,'%')")
    List<Club> searchClucb(String query);

}
