package org.springframework.samples.xtreme.cells;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.xtreme.pieces.Color;
import org.springframework.stereotype.Service;

@Service
public class ParchisCellService {

    private ParchisCellRepository parchisCellRepository;

    @Autowired
    public ParchisCellService(ParchisCellRepository parchisCellRepository) {
        this.parchisCellRepository = parchisCellRepository;
    }

    @Transactional
    public void save(ParchisCell parchisCell) {
        parchisCellRepository.save(parchisCell);
    }

    @Transactional
    public void delete(ParchisCell parchisCell) {
        parchisCellRepository.delete(parchisCell);
    }

    @Transactional
    public ParchisCell findByPosition(Integer id) {
        return parchisCellRepository.findByPosition(id);
    }

    @Transactional
    public void generateCells() {
        for(int i = 1;i <= 104;i++) {
            if (69 <= i && i <= 76) {
                ParchisCell c = new ParchisCell(null,i,Color.YELLOW,null);
                parchisCellRepository.save(c);
            } else if (77 <= i && i <= 84) {
                ParchisCell c = new ParchisCell(null, i, Color.GREEN, null);
                parchisCellRepository.save(c);
            } else if (85 <= i && i <= 92) {
                ParchisCell c = new ParchisCell(null, i, Color.RED, null);
                parchisCellRepository.save(c);
            } else if (93 <= i && i <= 100) {
                ParchisCell c = new ParchisCell(null, i, Color.BLUE, null);
                parchisCellRepository.save(c);
            } else {
                ParchisCell c = new ParchisCell(null, i, null, null);
                parchisCellRepository.save(c);
            }
        }
    }
}
