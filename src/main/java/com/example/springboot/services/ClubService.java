package com.example.springboot.services;

import com.example.springboot.dto.ClubDto;
import com.example.springboot.models.Club;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClub();
    Club saveClub(ClubDto clubDto);
    ClubDto findClubById(long clubId);

    void updateClub(ClubDto clubDto);

    void delete(long clubId);

    List<ClubDto> searchClubs(String query);
}
