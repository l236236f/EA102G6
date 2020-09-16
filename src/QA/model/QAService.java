package QA.model;

import java.sql.Timestamp;
import java.util.List;

public class QAService {

	private QADAO_interface dao;

	public QAService() {
		dao = new QADAO();
	}

	public QAVO addQA(
			String qachangeman,
			String qatext,
			Timestamp qachangedate,
			String qastatus,
			String qakind,
			String qakind2,
			String qakind3) {

		QAVO qaVO = new QAVO();

		qaVO.setQachangeman(qachangeman);
		qaVO.setQatext(qatext);
		qaVO.setQachangedate(qachangedate);
		qaVO.setQastatus(qastatus);
		qaVO.setQakind(qakind);
		qaVO.setQakind2(qakind2);
		qaVO.setQakind3(qakind3);
		dao.insert(qaVO);
		return qaVO;
	}

	//預留給 Struts 2 用的
	public void addQA(QAVO qaVO) {
		dao.insert(qaVO);
	}
	
	public QAVO updateQA(
			String qano,
			String qachangeman,
			String qatext,
			Timestamp qachangedate,
			String qastatus,
			String qakind,
			String qakind2,
			String qakind3) {

		QAVO qaVO = new QAVO();

		qaVO.setQano(qano);
		qaVO.setQachangeman(qachangeman);
		qaVO.setQatext(qatext);
		qaVO.setQachangedate(qachangedate);
		qaVO.setQastatus(qastatus);
		qaVO.setQakind(qakind);
		qaVO.setQakind2(qakind2);
		qaVO.setQakind3(qakind3);
		dao.update(qaVO);

		return dao.findByPrimaryKey(qano);
	}
	
	//預留給 Struts 2 用的
	public void updateQA(QAVO qaVO) {
		dao.update(qaVO);
	}

	public void deleteQA(String qano) {
		dao.delete(qano);
	}

	public QAVO getOneQA(String qano) {
		return dao.findByPrimaryKey(qano);
	}

	public List<QAVO> getAll() {
		return dao.getAll();
	}
}
