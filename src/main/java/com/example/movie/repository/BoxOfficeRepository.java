package com.example.movie.repository;

import com.example.movie.domain.BoxOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface BoxOfficeRepository extends JpaRepository<BoxOffice, Long> {
    Optional<BoxOffice> findByRank(Long rank);
}
