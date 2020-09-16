package com.art_response.model;

import java.sql.Timestamp;
import java.util.List;

public class ArtResponseService {

	private ArtResponseDAO_interface dao;

	public ArtResponseService() {
		dao = new ArtResponseDAO();
//		dao = new ArtResponseJNDIDAO();
	}

	public ArtResponseVO addArtResponse(String artno, String memno, String rescontent, 
									    Timestamp restime, String resstatus) {

		ArtResponseVO artResponseVO = new ArtResponseVO();

		artResponseVO.setArtno(artno);
		artResponseVO.setMemno(memno);
		artResponseVO.setRescontent(rescontent);
		artResponseVO.setRestime(restime);
		artResponseVO.setResstatus(resstatus);
		dao.insert(artResponseVO);

		return artResponseVO;
	}

	public ArtResponseVO updateArtResponse(String resno, String artno, String memno, 
										   String rescontent, Timestamp restime, String resstatus) {

		ArtResponseVO artResponseVO = new ArtResponseVO();

		artResponseVO.setResno(resno);
		artResponseVO.setArtno(artno);
		artResponseVO.setMemno(memno);
		artResponseVO.setRescontent(rescontent);
		artResponseVO.setRestime(restime);
		artResponseVO.setResstatus(resstatus);
		dao.update(artResponseVO);

		return artResponseVO;
	}

	public void deleteArtResponse(String resno) {
		dao.delete(resno);
	}

	public ArtResponseVO getOneArtResponse(String resno) {
		return dao.findByPrimaryKey(resno);
	}

	public List<ArtResponseVO> getAll() {
		return dao.getAll();
	}
	
	public List<ArtResponseVO> getOneResByArtno(String artno) {
		return dao.getResByArticle(artno);
	}

}
