package edu.est.library.domain.interfaces.repository;

import edu.est.library.domain.interfaces.ICreate;
import edu.est.library.domain.interfaces.IDeleted;
import edu.est.library.domain.interfaces.IUpdate;
import java.util.List;
import java.util.TreeSet;

public interface ICrudStudent <Entity> extends ICreate<Entity>, IDeleted<Entity>, IUpdate<Entity> {
    TreeSet<Entity> ToList();
    List<Entity> TreeSetToList();
}
