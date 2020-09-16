package com.shop_product.model;

import java.io.IOException;
import java.util.*;

import com.sprod_photo.model.Sprod_photoVO;


public interface Shop_productDAO_interface {
	 public void insert(Shop_productVO shopProductVO);
     public void update(Shop_productVO shopProductVO);
     public void delete(String prodNo);
     public Shop_productVO findByPrimaryKey(String prodNo);
     public List<Shop_productVO> getAll();
     public byte[] writePhoto(String path) throws IOException;
     public void insertPhoto(Shop_productVO shopProductVO); 
     //搜尋上架商品
     public List<Shop_productVO> selectShophomeProduct(Integer sprodStatus);
     //利用商品編號搜尋商品名稱
     public String selectShopProductName(String sprodNo);
     //爬蟲資料新增(少圖片)
     public void shopCrawlerInsert(Shop_productVO shopProductVO);
}