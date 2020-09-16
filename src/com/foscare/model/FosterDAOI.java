package com.foscare.model;

import java.util.*;
import com.pet.model.*;

public interface FosterDAOI {
	//新增寄養單
	public void insert(FosterVO fosterVO); 
	//修改寄養單
    public void update(FosterVO fosterVO); 
    //上傳簽名檔
    public void updateSign(byte[] sign,String fosNo,String AorB); 
    //查詢單筆寄養單
    public FosterVO findByPrimaryKey(String fosNo);
    //會員編號查詢寄養單
    public List<FosterVO> getAllByMemNo(String memNo);
    //保母編號查詢寄養單
    public List<FosterVO> getAllByFosmNo(String fosmNo);
    //查詢全部寄養單
    public List<FosterVO> getAll();
    //修改寄養單狀態
    public void updateStatus(String fosNo,String fosStatus);
    //評價星等及評論
    public void evaluation(String memNo,Integer i,String eva);
    //保母回覆評論
    public void updateFosmres(String fosNo, String fosmEvares);
    //取得寵物名稱編號
    public List<PetVO> findPetByMemNo(String memNo);
    //查詢當日結束的寄養單
    public void getAlltimeUp();
    
}
