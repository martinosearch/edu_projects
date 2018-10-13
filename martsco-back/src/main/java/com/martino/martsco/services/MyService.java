package com.martino.martsco.services;

import java.util.List;

public interface MyService<T> {

	void delete(Long id);

	List<T> findAll();

	T findOne(Long id);

	T save(T obj);
}
