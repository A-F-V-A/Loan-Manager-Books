package edu.est.library.domain.interfaces;

public interface IUpdate<Entity> {
    Entity Update( Entity entity, Entity entityNew) throws Exception;
}
