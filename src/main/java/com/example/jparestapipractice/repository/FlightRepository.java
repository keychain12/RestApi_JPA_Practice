package com.example.jparestapipractice.repository;

import com.example.jparestapipractice.domain.Airport;
import com.example.jparestapipractice.domain.Flight;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> , FlightRepositoryCustom{


}
