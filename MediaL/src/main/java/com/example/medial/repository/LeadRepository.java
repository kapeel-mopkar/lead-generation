package com.example.medial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.medial.entity.Lead;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Integer>{

}
