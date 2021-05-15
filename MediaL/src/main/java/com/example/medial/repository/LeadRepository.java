package com.example.medial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.medial.entity.Lead;

public interface LeadRepository extends JpaRepository<Lead, Integer>{

}
