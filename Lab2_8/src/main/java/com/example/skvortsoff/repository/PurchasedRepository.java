package com.example.skvortsoff.repository;

import com.example.skvortsoff.entity.Purchased;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PurchasedRepository extends JpaRepository<Purchased, Long> {
    @Query(value = "select * from PURCHASED where USER = :id", nativeQuery = true)
    Collection<Purchased> findByUserId( @Param("id")Long id);
}
