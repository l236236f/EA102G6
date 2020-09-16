package com.fosmon.model;

import java.util.List;


public interface FosmDAOI {
	
	//�s�W�O������
	public void insert(FosmVO fosmVO);
	//�ק�O������
	public void update(FosmVO fosmVO);
	//�d�߳浧�O������
	public FosmVO findByMemNo(String memNo);
	
	public FosmVO findByFosmNo(String fosmNo);
	//�d�ߥ����O��
    public List<FosmVO> getAll();
    //
    public void updateStar(FosmVO fosmVO);
    //�^�е��ׯd��
//    public void resEva();
    //�R���c�N�d��
//    public void delmsg();
	
}
