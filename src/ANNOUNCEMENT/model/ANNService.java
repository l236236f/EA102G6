package ANNOUNCEMENT.model;

import java.sql.Timestamp;
import java.util.List;

public class ANNService {

	private ANNDAO_interface dao;

	public ANNService() {
		dao = new ANNDAO();
	}

	public ANNVO addANN(
			String annchangeman,
			String anntext,
			Timestamp annchangedate,
			String annstatus,
			byte[] annimg,
			String anntitle) {

		ANNVO annVO = new ANNVO();
		annVO.setAnnchangeman(annchangeman);
		annVO.setAnntext(anntext);
		annVO.setAnnchangedate(annchangedate);
		annVO.setAnnstatus(annstatus);
		annVO.setAnnimg(annimg);
		annVO.setAnntitle(anntitle);
	
		dao.insert(annVO);
	
		return annVO;
	}

	//預留給 Struts 2 用的
	public void addANN(ANNVO annVO) {
		dao.insert(annVO);
	}
	
	public ANNVO updateANN(
			String annno,
			String annchangeman,
			String anntext,
			Timestamp annchangedate,
			String annstatus,
			byte[] annimg,
			String anntitle) {

		ANNVO annVO = new ANNVO();

		annVO.setAnnno(annno);
		annVO.setAnnchangeman(annchangeman);
		annVO.setAnntext(anntext);
		annVO.setAnnchangedate(annchangedate);
		annVO.setAnnstatus(annstatus);
		annVO.setAnnimg(annimg);
		annVO.setAnntitle(anntitle);
		dao.update(annVO);

		return dao.findByPrimaryKey(annno);
	}
	
	//預留給 Struts 2 用的
	public void updateANN(ANNVO annVO) {
		dao.update(annVO);
	}

	public void deleteANN(String annno) {
		dao.delete(annno);
	}

	public ANNVO getOneANN(String annno) {
		return dao.findByPrimaryKey(annno);
	}

	public List<ANNVO> getAll() {
		return dao.getAll();
	}
}
