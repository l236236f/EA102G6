package com.rep_sprod.model;

import java.util.*;

public interface Rep_sprodDAO_interface {
	
	public void insert(Rep_sprodVO repSprod);
	public void update(Rep_sprodVO repSprod);
	public void delete(String repNo);
	public Rep_sprodVO findByPrimaryKey(String repNo);
	public List<Rep_sprodVO> getAll();
}
