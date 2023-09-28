package edu.est.library.domain.interfaces.repository;

import edu.est.library.domain.interfaces.ICreate;
import edu.est.library.domain.interfaces.IDeleted;
import edu.est.library.domain.interfaces.ITolist;
import edu.est.library.domain.interfaces.IUpdate;

public interface ICrud<Entity> extends ITolist<Entity>, ICreate<Entity>, IDeleted<Entity>, IUpdate<Entity> {
}
