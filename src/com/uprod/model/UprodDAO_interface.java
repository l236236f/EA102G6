package com.uprod.model;

import java.util.*;

public interface UprodDAO_interface {
	     
          public void insert(UprodVO uprodVO);
          public void update(UprodVO uprodVO);
          public void buy(UprodVO uprodVO);
          public void shipment(UprodVO uprodVO);
          public void receive(UprodVO uprodVO);
          public UprodVO findByPrimaryKey(String ProdNo);
          public List<UprodVO> getAll();
          public List<UprodVO> getAllOnShelf();
          public List<UprodVO> getAllBySeller(String SellerNo);
          public List<UprodVO> getAllBySellerDetail(String SellerNo);
          public List<UprodVO> getAllByCustomer(String CustNo);
          public List<UprodVO> getAllOnShelfByA();
          public List<UprodVO> getAllOnShelfByB();
          public List<UprodVO> getAllOnShelfByC();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
