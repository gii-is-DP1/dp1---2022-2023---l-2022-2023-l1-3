package org.springframework.samples.xtreme.chat;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.samples.xtreme.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="chat")
public class Chat extends BaseEntity{
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chat")
    private List<Mensaje> mensaje;

}
