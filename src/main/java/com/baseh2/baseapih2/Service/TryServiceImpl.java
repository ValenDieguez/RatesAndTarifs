package com.baseh2.baseapih2.Service;

import com.baseh2.baseapih2.DTO.TryDTO;
import com.baseh2.baseapih2.Repository.TryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@Component
public class TryServiceImpl implements TryService {

    @Autowired
    TryRepository tryRepository;


    @Override
    public TryDTO createTry(TryDTO tryDTO) {
        try{
        return tryRepository.save(tryDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TryDTO> getAllTries() {
        List<TryDTO> tryDTOS = tryRepository.findAll();
        return tryDTOS;
    }

    @Override
    public TryDTO getOneTry(Integer id) {
        return tryRepository.getOne(id);
    }

    @Override
    public TryDTO modifyTry(TryDTO tryDTO) {
        TryDTO completedTryDTO = tryRepository.save(tryDTO);
        return completedTryDTO;
    }


    @Override
    public Boolean deleteTry(Integer id) {
        tryRepository.deleteById(id);
        return true;
    }
}
