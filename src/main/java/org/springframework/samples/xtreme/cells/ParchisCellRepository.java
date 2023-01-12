package org.springframework.samples.xtreme.cells;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ParchisCellRepository extends CrudRepository<ParchisCell, Integer>{

	@Query("SELECT DISTINCT cell FROM ParchisCell cell WHERE cell.position = :position")
	ParchisCell findByPosition(Integer position);

}
