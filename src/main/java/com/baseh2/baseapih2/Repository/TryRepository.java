package com.baseh2.baseapih2.Repository;

import com.baseh2.baseapih2.DTO.TryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TryRepository extends JpaRepository<TryDTO, Integer> {

}
