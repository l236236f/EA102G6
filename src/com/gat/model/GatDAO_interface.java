package com.gat.model;

import java.util.List;

public interface GatDAO_interface {
	
	public void insert(GatVO gatVO);
	public void update(GatVO gatVO);
	public void updateStatus(String gatStatus, String gatNo);
	public void delete(String gatNo);
	public GatVO findByPrimaryKey(String gatNo);
	public List<GatVO> getAll();
	public List<GatVO> getAllByEndtime();
	public List<GatVO> getAllLike(String gatName);
	public List<GatVO> getAllForUpdateList();
	public void autoStatus();
	
//  ¿À¡|
    public void update_report(String gatNo);	
	
}
