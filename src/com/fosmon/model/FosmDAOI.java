package com.fosmon.model;

import java.util.List;


public interface FosmDAOI {
	
	//新增保母頁面
	public void insert(FosmVO fosmVO);
	//修改保母頁面
	public void update(FosmVO fosmVO);
	//查詢單筆保母頁面
	public FosmVO findByMemNo(String memNo);
	
	public FosmVO findByFosmNo(String fosmNo);
	//查詢全部保母
    public List<FosmVO> getAll();
    //
    public void updateStar(FosmVO fosmVO);
    //回覆評論留言
//    public void resEva();
    //刪除惡意留言
//    public void delmsg();
	
}
