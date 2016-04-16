package com.book.library.base;

import java.util.List;

public interface BaseServiceSupport<T> {
	
	void save(T entity);

	void delete(Long id);
	
	void delete(String id);
	
	void update(T entity);
	
	T getEntityById(Long id);
	
	T getEntityById(String id);
	
	List<T> getEntityByIds(Long[] ids);
	
	List<T> getEntityByIds(String[] ids);
	
	List<T> findAll();
}
