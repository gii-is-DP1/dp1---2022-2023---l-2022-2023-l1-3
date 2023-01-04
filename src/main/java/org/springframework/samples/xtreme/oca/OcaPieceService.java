package org.springframework.samples.xtreme.oca;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OcaPieceService {
	
	private OcaPieceRepository ocaPieceRepository;
	
	@Autowired
	public OcaPieceService(OcaPieceRepository ocaPieceRepo) {
		super();
		this.ocaPieceRepository = ocaPieceRepo;
	}
	
	@Transactional
	public OcaPiece findPieceById(int id){
		return ocaPieceRepository.findById(id).get();
	}
	@Transactional
	public OcaPiece save(OcaPiece piece){
		return ocaPieceRepository.save(piece);
	}
	
	@Transactional
	public void delete(OcaPiece piece){
		ocaPieceRepository.delete(piece);
	}
	

	@Transactional
	public OcaPiece findByPlayerId(Integer playerId) {
		return ocaPieceRepository.findByPlayerId(playerId).iterator().next();
	}
	
	@Transactional
	public OcaPiece findPiecebyGameAndPlayer(Integer playerId,Integer gameId) {
		return ocaPieceRepository.findPiecebyGameAndPlayer(playerId,gameId);
	}
}
