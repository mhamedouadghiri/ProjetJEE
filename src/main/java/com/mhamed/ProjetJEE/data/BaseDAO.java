package com.mhamed.ProjetJEE.data;

public interface BaseDAO<T> {

    T get(Long id);

    Long save(T entity);

    Boolean delete(Long id);
}
