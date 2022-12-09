package org.springframework.samples.xtreme.board;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParchisBoardService {

    @Autowired
    ParchisBoardRepository boardRepo;

    public Optional<ParchisBoard> findById(Integer id) {
        return boardRepo.findById(id);
    }
    
}
