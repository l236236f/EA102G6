package com.foscare.model;

import java.util.*;
import com.pet.model.*;

public interface FosterDAOI {
	//�s�W�H�i��
	public void insert(FosterVO fosterVO); 
	//�ק�H�i��
    public void update(FosterVO fosterVO); 
    //�W��ñ�W��
    public void updateSign(byte[] sign,String fosNo,String AorB); 
    //�d�߳浧�H�i��
    public FosterVO findByPrimaryKey(String fosNo);
    //�|���s���d�߱H�i��
    public List<FosterVO> getAllByMemNo(String memNo);
    //�O���s���d�߱H�i��
    public List<FosterVO> getAllByFosmNo(String fosmNo);
    //�d�ߥ����H�i��
    public List<FosterVO> getAll();
    //�ק�H�i�檬�A
    public void updateStatus(String fosNo,String fosStatus);
    //�����P���ε���
    public void evaluation(String memNo,Integer i,String eva);
    //�O���^�е���
    public void updateFosmres(String fosNo, String fosmEvares);
    //���o�d���W�ٽs��
    public List<PetVO> findPetByMemNo(String memNo);
    //�d�߷�鵲�����H�i��
    public void getAlltimeUp();
    
}
