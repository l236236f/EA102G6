package com.fosmphoto.model;

import java.util.List;

public interface FosmPhotoDAOI {
	public void insert(String fosmNo,byte[] phoCon);
	
	public void delete(String phoNo);
	
	//�Ӥ��s���^�ǳ�@�Ӥ�VO
	public FosmPhotoVO getPhoto(String phoNo);
			
	//�ΫO���s��������Ӥ����s��
	public List<String> getAll(String fosmNo);

	
}
