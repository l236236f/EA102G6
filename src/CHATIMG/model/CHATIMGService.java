package CHATIMG.model;
import java.util.List;

public class CHATIMGService {

	private CHATIMGDAO_interface dao;

	public CHATIMGService() {
		dao = new CHATIMGDAO();
	}

	public CHATIMGVO addCHATIMG(
			String chatimgno,
			byte[] chatimg
			) {

		CHATIMGVO chatimgVO = new CHATIMGVO();
		chatimgVO.setChatimgno(chatimgno);
		chatimgVO.setChatimg(chatimg);
	
		dao.insert(chatimgVO);
		return chatimgVO;
	}

	//預留給 Struts 2 用的
	public void addCHATIMG(CHATIMGVO chatimgVO) {
		dao.insert(chatimgVO);
	}
	
	public CHATIMGVO updateCHATIMG(
			String chatimgno,
			byte[] chatimg ) {

		CHATIMGVO chatimgVO = new CHATIMGVO();

		chatimgVO.setChatimgno(chatimgno);
		chatimgVO.setChatimg(chatimg);
		
		dao.update(chatimgVO);

		return dao.findByPrimaryKey(chatimgno);
	}
	
	//預留給 Struts 2 用的
	public void updateCHATIMG(CHATIMGVO chatimgVO) {
		dao.update(chatimgVO);
	}

	public void deleteCHATIMG(String chatimgno) {
		dao.delete(chatimgno);
	}

	public CHATIMGVO getOneCHATIMG(String chatimgno) {
		return dao.findByPrimaryKey(chatimgno);
	}

	public List<CHATIMGVO> getAll() {
		return dao.getAll();
	}
}
