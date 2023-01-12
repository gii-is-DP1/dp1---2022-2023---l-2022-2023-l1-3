package org.springframework.samples.xtreme.cells;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ParchisCellRepository extends CrudRepository<ParchisCell, Integer> {

    @Query("SELECT c FROM ParchisCell c WHERE c.position = ?1")
    ParchisCell findByPosition(Integer position);
        
}
