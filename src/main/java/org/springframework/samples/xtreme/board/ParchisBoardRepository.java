package org.springframework.samples.xtreme.board;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParchisBoardRepository extends CrudRepository<ParchisBoard, Integer> {

}
