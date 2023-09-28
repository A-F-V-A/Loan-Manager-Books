package edu.est.library.domain.interfaces;

import java.util.HashSet;
import java.util.List;

public interface ITolist <Entity>{
    HashSet<Entity> ToList();
    List<Entity> HashSetToList();
}
