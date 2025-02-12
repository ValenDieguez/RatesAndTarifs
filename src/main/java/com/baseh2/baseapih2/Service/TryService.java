package com.baseh2.baseapih2.Service;

import com.baseh2.baseapih2.DTO.TryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TryService {


    TryDTO createTry(TryDTO tryDTO);
    List<TryDTO> getAllTries();
    TryDTO getOneTry(Integer id);
    TryDTO modifyTry(TryDTO tryDTO);
    Boolean deleteTry(Integer id);
}
