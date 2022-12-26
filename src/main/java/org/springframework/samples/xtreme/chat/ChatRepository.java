package org.springframework.samples.xtreme.chat;
import org.springframework.stereotype.Repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


@Repository
public interface ChatRepository extends CrudRepository<Chat,Integer>{
    
}
