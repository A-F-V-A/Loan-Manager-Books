package edu.est.library.application.usecases;

import edu.est.library.domain.interfaces.ICreate;
import edu.est.library.domain.interfaces.ITolist;
import edu.est.library.domain.interfaces.repository.ICrud;
import edu.est.library.domain.interfaces.repository.ICrudBook;

public interface IBookService <Entity> extends ICrudBook<Entity> {
}
