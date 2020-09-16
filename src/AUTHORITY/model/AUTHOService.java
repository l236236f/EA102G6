package AUTHORITY.model;


import java.util.List;

public class AUTHOService {

	private AUTHODAO_interface dao;

	public AUTHOService() {
		dao = new AUTHODAO();
	}

	public AUTHOVO addAUTHO(
			String featno,
			String empno
			) {
		
		AUTHOVO authoVO = new AUTHOVO();
		authoVO.setFeatno(featno);
		authoVO.setEmpno(empno);

		dao.insert(authoVO);
		
		return authoVO;
	}

	//預留給 Struts 2 用的
	public void addAUTHO(AUTHOVO featVO) {
		dao.insert(featVO);
	}
	public void deleteAUTHO(String featno,String empno) {
		dao.delete(featno,empno);
	}
	public void deleteAUTHOByEmpno(String empno) {
		dao.deleteByEmpno(empno);
	}
	public List<AUTHOVO> getAUTHOByFeatno(String featno) {
		return dao.findByFeatno(featno);
	}
	public List<AUTHOVO> getAUTHOByEmpno(String empno) {
		return dao.findByEmpno(empno);
	}
	public List<AUTHOVO> getAll() {
		return dao.getAll();
	}
}
