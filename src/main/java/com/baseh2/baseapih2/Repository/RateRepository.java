package com.baseh2.baseapih2.Repository;

import com.baseh2.baseapih2.DTO.RateDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateRepository extends JpaRepository<RateDTO, Long> {
    List<RateDTO> findByBrandIdAndProductId(Long brandId, Long productId);
}