package com.mem.model;

import java.sql.Date;
import java.util.List;

public class MemService {

	private MemDAO_interface dao;

	public MemService() {
		dao = new MemDAO();
	}

	public MemVO addMem(String memAcc,String memPw,
			String memName,Date memBirth,String memID,String memTel,
			String memGender,String memAddr,String memEmail,String memMoney,
			byte[] memPhoto,String memIntro) {

		MemVO memVO = new MemVO();

		memVO.setMemAcc(memAcc);
		memVO.setMemPw(memPw);
		memVO.setMemName(memName);
		memVO.setMemBirth(memBirth);
		memVO.setMemID(memID);
		memVO.setMemTel(memTel);
		memVO.setMemGender(memGender);
		memVO.setMemAddr(memAddr);
		memVO.setMemEmail(memEmail);
		memVO.setMemMoney(memMoney);
		memVO.setMemPhoto(memPhoto);
		memVO.setMemIntro(memIntro);
		
		memVO = dao.insert(memVO);

		return memVO;
	}
	

	public MemVO updateMem(String memNo,String memPw,
			String memName,Date memBirth,String memID,String memTel,
			String memGender,String memAddr,String memEmail,String memMoney,
			String memIntro) {

		MemVO memVO = new MemVO();

		memVO.setMemNo(memNo);
		memVO.setMemPw(memPw);
		memVO.setMemName(memName);
		memVO.setMemBirth(memBirth);
		memVO.setMemID(memID);
		memVO.setMemTel(memTel);
		memVO.setMemGender(memGender);
		memVO.setMemAddr(memAddr);
		memVO.setMemEmail(memEmail);
		memVO.setMemMoney(memMoney);
		memVO.setMemIntro(memIntro);
		
		dao.update(memVO);
		
		return memVO;
	}
	
	public void updatePhoto(String memNo, byte[] memPhoto) {

		MemVO memVO = new MemVO();

		memVO.setMemNo(memNo);
		memVO.setMemPhoto(memPhoto);
		
		dao.updatePhoto(memVO);
		
		return;
	}
	
	public void updateMemStatus(String memNo) {
		dao.updateMemStatus(memNo);
	}
	 
	
	public void updateMom(String memNo) {
		dao.updateMom(memNo);
	}

	public void deleteMem(String memNo) {
		dao.delete(memNo);
	}

	public MemVO getOneMem(String memNo) {
		return dao.findByPrimaryKey(memNo);
	}

	public List<MemVO> getAll() {
		return dao.getAll();
	}
	
	public MemVO checkAcc(String memAcc) {
		return dao.checkAcc(memAcc);
	}
	
	public MemVO updateMemByEmp(String memNo, String memPw, String memEmail, String memMoney, String memStatus,
			String mom, int bonus) {

		MemVO memVO = new MemVO();

		memVO.setMemNo(memNo);
		memVO.setMemPw(memPw);
		memVO.setMemEmail(memEmail);
		memVO.setMemMoney(memMoney);
		memVO.setMemStatus(memStatus);
		memVO.setMom(mom);
		memVO.setBonus(bonus);

		dao.updateByEmp(memVO);
		return memVO;
	}
	
	
}

