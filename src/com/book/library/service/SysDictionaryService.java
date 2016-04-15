package com.book.library.service;

import java.util.List;

import com.book.library.po.SysDictionary;

public interface SysDictionaryService {

	List<SysDictionary> findAll();

	void save(SysDictionary sysDictionary);

	SysDictionary findDictionaryById(String id);

	void update(SysDictionary sysDictionary);

	void delete(String id);

}
