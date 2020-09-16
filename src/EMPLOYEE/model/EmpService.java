package EMPLOYEE.model;

import java.sql.Timestamp;
import java.util.List;


public class EmpService {

	private EmpDAO_interface dao;

	public EmpService() {
		dao = new EmpDAO();
	}

	public EmpVO addEmp(
			String empname,
			String emptel,
			String empemail,
			String empid,
			String emppsw,
			byte[] empphoto,
			String empposition,
			Timestamp emphiredate,
			Timestamp empchangedate,
			String empchangeman,
			String empstatus,
			String empnotes) {

		EmpVO empVO = new EmpVO();

		empVO.setEmpname(empname);
		empVO.setEmptel(emptel);
		empVO.setEmpemail(empemail);
		empVO.setEmpid(empid);
		empVO.setEmppsw(emppsw);
		empVO.setEmpphoto(empphoto);
		empVO.setEmpposition(empposition);
		empVO.setEmphiredate(emphiredate);
		empVO.setEmpchangedate(empchangedate);
		empVO.setEmpchangeman(empchangeman);
		empVO.setEmpstatus(empstatus);
		empVO.setEmpnotes(empnotes);
		dao.insert(empVO);

		return empVO;
	}

	public EmpVO updateEmp(
			String empno,
			String empname,
			String emptel,
			String empemail,
			String empid,
			String emppsw,
			byte[] empphoto,
			String empposition,
			Timestamp emphiredate,
			Timestamp empchangedate,
			String empchangeman,
			String empstatus,
			String empnotes) {

		EmpVO empVO = new EmpVO();
		empVO.setEmpno(empno);
		empVO.setEmpname(empname);
		empVO.setEmptel(emptel);
		empVO.setEmpemail(empemail);
		empVO.setEmpid(empid);
		empVO.setEmppsw(emppsw);
		empVO.setEmpphoto(empphoto);
		empVO.setEmpposition(empposition);
		empVO.setEmphiredate(emphiredate);
		empVO.setEmpchangedate(empchangedate);
		empVO.setEmpchangeman(empchangeman);
		empVO.setEmpstatus(empstatus);
		empVO.setEmpnotes(empnotes);
		dao.update(empVO);

		return empVO;
	}

	public void deleteEmp(String empno) {
		dao.delete(empno);
	}

	public EmpVO getOneEmp(String empno) {
		return dao.findByPrimaryKey(empno);
	}

	public List<EmpVO> getAll() {
		return dao.getAll();
	}
	public EmpVO getOneEmpLogin(String empid) {
		return dao.findByPrimaryKeyLogin(empid);

	}
	public String checkEmpid(String empid) {
		
		return dao.checkid(empid);
		
	}
	
	
}
