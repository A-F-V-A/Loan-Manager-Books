package edu.est.library.domain.interfaces.repository;

public interface ICrudBook <Entity> extends ICrud<Entity>{
    int lendBook(Entity entity) throws Exception;
}

