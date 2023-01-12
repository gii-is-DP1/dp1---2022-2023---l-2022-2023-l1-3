package org.springframework.samples.xtreme.pieces;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ParchisPieceService {
    
    private ParchisPieceRepository parchisPieceRepository;

    @Autowired
    public ParchisPieceService(ParchisPieceRepository parchisPieceRepository) {
        this.parchisPieceRepository = parchisPieceRepository;
    }

    @Transactional
    public void save(ParchisPiece piece) {
        parchisPieceRepository.save(piece);
    }

    @Transactional
    public void delete(ParchisPiece piece) {
        parchisPieceRepository.delete(piece);
    }

    @Transactional
    public Collection<ParchisPiece> findPiecesByGameAndPlayer(Integer gameId, Integer playerId) {
        return parchisPieceRepository.findPiecesByGameAndPlayer(gameId, playerId);
    }

    @Transactional(readOnly = true)
    public ParchisPiece findById(Integer id){
        return this.parchisPieceRepository.findById(id).get();
    }
}
