package org.springframework.samples.xtreme.cells;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.samples.xtreme.util.Tuple;


public class ParchisPositions {
    
    private Map<String, Tuple<Integer,Integer>> parchisPositions = new HashMap<>() {{
        put("1", new Tuple<Integer,Integer>(175,10));
        put("2", new Tuple<Integer,Integer>(175,32));
        put("3", new Tuple<Integer,Integer>(175,55));
        put("4", new Tuple<Integer,Integer>(175,76));
        put("5", new Tuple<Integer,Integer>(175,97));
    }};

    
}
