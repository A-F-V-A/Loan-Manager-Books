package edu.est.library.domain.interfaces.repository;

import edu.est.library.domain.interfaces.ICreate;
import edu.est.library.domain.interfaces.IDeleted;
import edu.est.library.domain.interfaces.IUpdate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

public interface ICrudLoanBook <Entity> extends ICreate<Entity>, IDeleted<Entity> {
    HashMap<String,Entity> ToList();
    List<Entity> HashMapToList();
    Entity Update(Entity entity) throws Exception;
}
