package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fosmon.model.FosmService;
import com.fosmon.model.FosmVO;
import com.mem.model.MemVO;

public class FosmFilter implements Filter{
	
	private FilterConfig config;

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

	public void destroy() {
		config = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		HttpSession se = req.getSession();
		MemVO memVO = (MemVO)se.getAttribute("LoginMem");
		FosmService fmSvc = new FosmService();
		FosmVO fosmVO = fmSvc.getOneFosm(memVO.getMemNo());
		
		if(fosmVO==null) {
			se.setAttribute("isFosm", "你還沒成為保母!可以在此頁下方點選申請!");
			res.sendRedirect(req.getContextPath()+"/front-end/foscare/foscareHome.jsp");
			return;
		}else{
			se.setAttribute("LoginFosm", fosmVO);
		}
		chain.doFilter(req, res);
		
	}

}
