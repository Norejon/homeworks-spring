package com.example.homework1.dao;

import com.example.homework1.models.ClientUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientUserDAO extends JpaRepository<ClientUser,Integer> {
    ClientUser findByEmail(String email);
}
