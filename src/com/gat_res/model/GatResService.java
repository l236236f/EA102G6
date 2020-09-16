package com.gat_res.model;

import java.util.List;

public class GatResService {

	private GatResDAO_interface dao;
	
	public GatResService() {
		dao = new GatResJDBCDAO();
	}
	
	public GatResVO addRes(String gatNo, String memNo, String resCont) {
		GatResVO gatResVO = new GatResVO();
		gatResVO.setGatNo(gatNo);
		gatResVO.setMemNo(memNo);
		gatResVO.setResCont(resCont);
		dao.insert(gatResVO);
		return gatResVO;
	}
	
	public GatResVO updateRes(String resNo, String resCont) {
		GatResVO gatResVO = new GatResVO();
		gatResVO.setResNo(resNo);
		gatResVO.setResCont(resCont);
		dao.update(gatResVO);
		return gatResVO;		
	}
	
	public GatResVO replyRes(String resNo, String resReply) {
		GatResVO gatResVO = new GatResVO();
		gatResVO.setResNo(resNo);
		gatResVO.setResReply(resReply);
		dao.reply(gatResVO);
		return gatResVO;
	}
	
	public void deleteRes(String resNo) {
		dao.delete(resNo);
	}
	public GatResVO getOneGat(String resNo) {
		return dao.findByPrimaryKey(resNo);
	} 
	public List<GatResVO> getAll(){
		return dao.getAll();
	}
	
	public static void main(String[] args) {
		GatResService resSvc = new GatResService();
		resSvc.addRes("G001", "M002", "frage ich.");
		System.out.println("�s�W���\");		
		resSvc.updateRes("RS006", "du fragst.");
		System.out.println("�ק令�\");
		resSvc.replyRes("RS006", "er fragt.");
		System.out.println("�^�Ц��\");
		resSvc.deleteRes("RS006");
		System.out.println("�R�����\");
		GatResVO r1 = resSvc.getOneGat("RS001");
		System.out.println("RS001���e: " + r1.getResCont());
		List<GatResVO> rall = resSvc.getAll();
		for(GatResVO result : rall) {
			System.out.println(result.getResNo() + "�����e: " + result.getResReply());
		}		
	}	
}
