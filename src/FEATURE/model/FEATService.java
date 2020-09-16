package FEATURE.model;

import java.util.List;

public class FEATService {

	private FEATDAO_interface dao;

	public FEATService() {
		dao = new FEATDAO();
	}

	public FEATVO addFEAT(
			String featname
			) {

		FEATVO featVO = new FEATVO();
		featVO.setFeatname(featname);
		dao.insert(featVO);
		return featVO;
	}

	//�w�d�� Struts 2 �Ϊ�
	public void addFEAT(FEATVO featVO) {
		dao.insert(featVO);
	}
	
	public FEATVO updateFEAT(
			String featno,
			String featname
			) {

		FEATVO featVO = new FEATVO();

		featVO.setFeatno(featno);
		featVO.setFeatname(featname);
		
		dao.update(featVO);

		return dao.findByPrimaryKey(featno);
	}
	
	//�w�d�� Struts 2 �Ϊ�
	public void updateFEAT(FEATVO featVO) {
		dao.update(featVO);
	}

	public void deleteFEAT(String featno) {
		dao.delete(featno);
	}

	public FEATVO getOneFEAT(String featno) {
		return dao.findByPrimaryKey(featno);
	}

	public List<FEATVO> getAll() {
		return dao.getAll();
	}
}
