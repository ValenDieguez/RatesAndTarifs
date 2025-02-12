package com.baseh2.baseapih2.Repository;

import com.baseh2.baseapih2.DTO.PricesDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PricesRepository extends JpaRepository<PricesDTO, Long> {

    List<PricesDTO> findByBrandIdAndProductId(Long brandId, Long productId);
}
