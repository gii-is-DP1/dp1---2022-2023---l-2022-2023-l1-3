package org.springframework.samples.xtreme.turn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ParchisTurnService {

    @Autowired
    private ParchisTurnRepository parchisTurnRepository;

    @Transactional
    public void save(ParchisTurn t){
        this.parchisTurnRepository.save(t);
    }

    @Transactional(readOnly = true)
    public ParchisTurn findByGame(Integer gameId){
        return this.parchisTurnRepository.findByGame(gameId);
    }
}
