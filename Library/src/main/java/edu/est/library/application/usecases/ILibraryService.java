package edu.est.library.application.usecases;

import edu.est.library.domain.interfaces.ICreate;
import edu.est.library.domain.interfaces.ITolist;

public interface ILibraryService <Entity> extends ITolist<Entity>, ICreate<Entity> {

}
