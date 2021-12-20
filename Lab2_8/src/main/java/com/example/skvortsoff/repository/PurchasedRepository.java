package com.example.skvortsoff.repository;

import com.example.skvortsoff.entity.Purchased;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchasedRepository extends JpaRepository<Purchased, Long> { }
