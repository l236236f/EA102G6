package com.fav_sprod.model;

import java.util.*;

public interface Fav_sprodDAO_interface {
	public void insert(Fav_sprodVO favSprodVO);
	public void delete(String memNo, String prodNo);
	public Fav_sprodVO findByPrimaryKey(String memNo, String prodNo);
	public List<Fav_sprodVO> getAll();
}
