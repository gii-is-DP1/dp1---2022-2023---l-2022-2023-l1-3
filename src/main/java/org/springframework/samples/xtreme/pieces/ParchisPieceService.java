package org.springframework.samples.xtreme.pieces;

import java.util.Collection;
import java.util.List;

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
    public ParchisPiece findById(Integer gameId) {
        return parchisPieceRepository.findById(gameId).get();
    }

    @Transactional
    public void save(ParchisPiece piece) {
        parchisPieceRepository.save(piece);
    }

    @Transactional
    public void delete(ParchisPiece piece) {
        parchisPieceRepository.delete(piece);
    }

    @Transactional(readOnly = true)
    public List<ParchisPiece> findPieceByBoardAndPlayer(Integer playerId, Integer boardId) {
        return parchisPieceRepository.findPieceByBoardAndPlayer(playerId, boardId);
    }
    @Transactional(readOnly = true)
    public List<ParchisPiece> findPieceByCellAndBoard(Integer position, Integer boardId) {
        return parchisPieceRepository.findPieceByCellAndBoard(position, boardId);
    }

}
