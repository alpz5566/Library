package com.book.library.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.book.library.mapper.SysDictionaryMapper;
import com.book.library.po.SysDictionary;
import com.book.library.po.SysDictionaryExample;
import com.book.library.service.SysDictionaryService;

@Component
@Transactional
public class SysDictionaryServiceImpl implements SysDictionaryService{
	
	@Autowired
	private SysDictionaryMapper sysDictionaryMapper;

	@Override
	public List<SysDictionary> findAll() {
		SysDictionaryExample example = new SysDictionaryExample();
		List<SysDictionary> dictionaries = sysDictionaryMapper.selectByExample(example);
		return dictionaries;
	}

	@Override
	public void save(SysDictionary sysDictionary) {
		String id = UUID.randomUUID().toString();
		sysDictionary.setId(id);
		sysDictionaryMapper.insert(sysDictionary);
	}

	@Override
	public SysDictionary findDictionaryById(String id) {
		SysDictionary sysDictionary = sysDictionaryMapper.selectByPrimaryKey(id);
		return sysDictionary;
	}

	@Override
	public void update(SysDictionary sysDictionary) {
		sysDictionaryMapper.updateByPrimaryKey(sysDictionary);
	}

	@Override
	public void delete(String id) {
		sysDictionaryMapper.deleteByPrimaryKey(id);
	}
	
}
