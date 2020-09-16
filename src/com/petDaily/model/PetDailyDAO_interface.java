package com.petDaily.model;

import java.util.*;

public interface PetDailyDAO_interface {
	public PetDailyVO insert(PetDailyVO petDailyVO);
	public void update(PetDailyVO petDailyVO);
	public void delete(String petDailyNo);
	public PetDailyVO findByPrimaryKey(String petDailyNo);
	public List<PetDailyVO> getAll();
	public List<PetDailyVO> findByPetNo(String petNo);
}
