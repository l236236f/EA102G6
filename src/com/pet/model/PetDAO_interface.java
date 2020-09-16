package com.pet.model;

import java.util.*;

import com.mem.model.MemVO;

public interface PetDAO_interface {
	public void insert(PetVO petVO);
	public void update(PetVO petVO);
	public void delete(String petNo);
	public PetVO findByPrimaryKey(String petNo);
	public List<PetVO> getAll();
	public List<PetVO> findByMemNo(String memNo);
}
