package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
	List<T> list();
	int save(T t);
	Optional<T> get(int id);
	int update(T t, int id);
	int delete(int id);
	

}
