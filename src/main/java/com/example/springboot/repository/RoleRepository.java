package com.example.springboot.repository;


import com.example.springboot.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository  extends JpaRepository<Roles,Long> {
    Roles findByName(String name);

}
