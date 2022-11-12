package org.springframework.samples.xtreme.cells;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.samples.xtreme.model.BaseEntity;
import org.springframework.samples.xtreme.board.ParchisBoard;
import org.springframework.samples.xtreme.pieces.Color;
import org.springframework.samples.xtreme.pieces.ParchisPiece;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ParchisCell extends BaseEntity{

    @Column(name="position")
    @Size(min=1,max=104)
    private Integer position;

    @OneToMany(cascade = CascadeType.ALL)
    @Size(min=0, max=2)
    List<ParchisPiece> pieces;

    private Color color;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="board_id")
    ParchisBoard board;

    public  Boolean isSafe() {
        List<Integer>safe=List.of(12,17,29,34,46,51,63,68);
        return safe.contains(this.position);

    }

    public  Boolean isHouse() {
        List<Integer>house=List.of(101,102,103,104);
        return house.contains(this.position);

    }

    public Boolean isStart() {
        List<Integer>start=List.of(5,22,39,56);//5 amarillo 22 azul 39 rojo 56 verde
        return start.contains(this.position);
    }

    public  Boolean isBloqueo() {
        return pieces.size()==2;
    }

    public  Boolean isStair() {
        List<Integer>stair=IntStream.rangeClosed(69, 100).boxed().collect(Collectors.toList()); 
        return stair.contains(this.position);
    }

    public void colocarFicha(ParchisPiece piece) {
        pieces.add(piece);
    }

    public void quitarFicha(ParchisPiece piece) {
        pieces.remove(piece);
    }
    
    public Boolean isFinalSquare() {
        List<Integer>finalSquare=List.of(76,84,92,100);
        return finalSquare.contains(this.position);
    }








}
