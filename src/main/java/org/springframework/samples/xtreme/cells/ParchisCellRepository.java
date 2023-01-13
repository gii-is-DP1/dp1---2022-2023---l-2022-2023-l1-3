package org.springframework.samples.xtreme.cells;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ParchisCellRepository extends CrudRepository<ParchisCell, Integer>{

	@Query("SELECT DISTINCT cell FROM ParchisCell cell WHERE cell.position = :position")
	ParchisCell findByPosition(Integer position);

	@Query(value = "SELECT p FROM ParchisCell p WHERE  p.color = 'YELLOW' ORDER BY p.position ASC")
    public List<ParchisCell> findCellYellow();
	@Query(value = "SELECT p FROM ParchisCell p WHERE  p.color = 'GREEN' ORDER BY p.position ASC")
    public List<ParchisCell> findCellGreen();
	@Query(value = "SELECT p FROM ParchisCell p WHERE  p.color = 'RED' ORDER BY p.position ASC")
    public List<ParchisCell> findCellRed();
	@Query(value = "SELECT p FROM ParchisCell p WHERE  p.color = 'BLUE' ORDER BY p.position ASC")
    public List<ParchisCell> findCellBlue();
}
