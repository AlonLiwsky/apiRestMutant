package com.example.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.main.entities.Humano;

@Repository
public interface HumanoRepository extends JpaRepository<Humano, Integer>{

}
