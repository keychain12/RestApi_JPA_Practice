package com.example.jparestapipractice.testdata;

import com.example.jparestapipractice.domain.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends JpaRepository<Seat, Long> {

}
