package com.mem.model;

import java.util.List;

public interface MemDAO_interface {
	public MemVO insert(MemVO memVo);
	public void update (MemVO memVo);
	public void updatePhoto (MemVO memVo);
	public void updateMemStatus (String memNo);
	public void updateMom (String memNo);
	public void delete(String memNo);
	public MemVO findByPrimaryKey(String memNo);
	public List<MemVO> getAll();
	public MemVO checkAcc(String memAcc);
	public void updateByEmp(MemVO memVO);
}
