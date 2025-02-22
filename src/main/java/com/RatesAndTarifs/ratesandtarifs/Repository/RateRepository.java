package com.RatesAndTarifs.ratesandtarifs.Repository;

import com.RatesAndTarifs.ratesandtarifs.DTO.RateDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RateRepository extends JpaRepository<RateDTO, Long> {

    List<RateDTO> findByBrandIdAndProductId(Long brandId, Long productId);

    @Query("SELECT r FROM RateDTO r WHERE r.brandId = :brandId " +
            "AND r.productId = :productId " +
            "AND :date BETWEEN r.startDate AND r.endDate " +
            "ORDER BY r.price DESC")
    Optional<RateDTO> findCurrentRate(
            @Param("brandId") Long brandId,
            @Param("productId") Long productId,
            @Param("date") LocalDateTime date);

    @Query("SELECT r FROM RateDTO r WHERE " +
            "r.brandId = :brandId AND r.productId = :productId " +
            "AND ((r.startDate BETWEEN :startDate AND :endDate) OR " +
            "(r.endDate BETWEEN :startDate AND :endDate))")
    List<RateDTO> findOverlappingRates(
            @Param("brandId") Long brandId,
            @Param("productId") Long productId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);
}
