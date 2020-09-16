package EMPLOYEE.model;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import AUTHORITY.model.AUTHOService;
import AUTHORITY.model.AUTHOVO;

public class LoginFilter implements Filter{
	private FilterConfig config;

	public void init(FilterConfig config) {
		this.setConfig(config);
	}

	public void destroy() {
		setConfig(null);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		Object account = session.getAttribute("EmpVO");
		
		
		
		
		if(account == null) {
			session.setAttribute("location",  req.getRequestURI());;
			res.sendRedirect(req.getContextPath()+"/back-end/emp/loginEmp.jsp");
			return;
		} else {
			String requrl=req.getRequestURI();
			boolean feat0001=false,feat0002=false,feat0003=false,feat0004=false,feat0005=false;
			//0001員工 0002 權限 0003檢舉 0004前台0005基本資料
			String empno=((EmpVO) account).getEmpno();
			AUTHOService authoSvc = new AUTHOService();
			List<AUTHOVO> list = authoSvc.getAUTHOByEmpno(empno);
			
			for(AUTHOVO authVO : list) {
				if("FEAT0001".equals(authVO.getFeatno())) {
					feat0001=true;
				}
				if("FEAT0002".equals(authVO.getFeatno())) {
					feat0002=true;
				}
				if("FEAT0003".equals(authVO.getFeatno())) {
					feat0003=true;
				}
				if("FEAT0004".equals(authVO.getFeatno())) {
					feat0004=true;
				}
				if("FEAT0005".equals(authVO.getFeatno())) {
					feat0005=true;
				}	
			}

			if("/EA102G6/back-end/emp/empmanage.jsp".equals(requrl)) {
				if(feat0001) {
					chain.doFilter(request, response);
				}else {
					backIndex(req,res);
				}
			}else if("/EA102G6/back-end/emp/authoritymanage.jsp".equals(requrl)) {
				if(feat0002) {
					chain.doFilter(request, response);
				}else {
					backIndex(req,res);
				}
			}else if("/EA102G6/back-end/emp/qamanage.jsp".equals(requrl)) {
				if(feat0004) {
					chain.doFilter(request, response);
				}else {
					backIndex(req,res);
				}
			}else if("/EA102G6/back-end/emp/annmanage.jsp".equals(requrl)) {
				if(feat0004) {
					chain.doFilter(request, response);
				}else {
					backIndex(req,res);
				}
			}else if("/EA102G6/back-end/emp/articleReport.jsp".equals(requrl)) {
				if(feat0003) {
					chain.doFilter(request, response);
				}else {
					backIndex(req,res);
				}
			}else if("/EA102G6/back-end/emp/getherReport.jsp".equals(requrl)) {
				if(feat0003) {
					chain.doFilter(request, response);
				}else {
					backIndex(req,res);
				}
			}else if("/EA102G6/back-end/emp/memReport.jsp".equals(requrl)) {
				if(feat0003) {
					chain.doFilter(request, response);
				}else {
					backIndex(req,res);
				}
			}else if("/EA102G6/back-end/emp/shopReport.jsp".equals(requrl)) {
				if(feat0003) {
					chain.doFilter(request, response);
				}else {
					backIndex(req,res);
				}
			}else if("/EA102G6/back-end/emp/usedReport.jsp".equals(requrl)) {
				if(feat0003) {
					chain.doFilter(request, response);
				}else {
					backIndex(req,res);
				}
			}else if("/EA102G6/back-end/emp/memmanage.jsp".equals(requrl)) {
				if(feat0005) {
					chain.doFilter(request, response);
				}else {
					backIndex(req,res);
				}
			}else if("/EA102G6/back-end/emp/venmanage.jsp".equals(requrl)) {
				if(feat0005) {
					chain.doFilter(request, response);
				}else {
					backIndex(req,res);
				}
			}else if("/EA102G6/back-end/emp/fosQualify.jsp".equals(requrl)) {
				if(feat0005) {
					chain.doFilter(request, response);
				}else {
					backIndex(req,res);
				}
			}else if("/EA102G6/back-end/emp/venQualify.jsp".equals(requrl)) {
				if(feat0005) {
					chain.doFilter(request, response);
				}else {
					backIndex(req,res);
				}
			}else {
				chain.doFilter(request, response);	
			}
			
				
		
		}
	}
public void backIndex(HttpServletRequest req,HttpServletResponse res) throws IOException {
	res.sendRedirect(req.getContextPath()+"/back-end/emp/empbackindex.jsp");
	return;
}

public FilterConfig getConfig() {
	return config;
}

public void setConfig(FilterConfig config) {
	this.config = config;
}
}