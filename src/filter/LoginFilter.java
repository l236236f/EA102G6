package filter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mem.model.MemVO;

public class LoginFilter implements Filter {
	
	ServletContext context=null;
	
	public void init(FilterConfig config){
		context=config.getServletContext();
	}

	public void destroy(){
		context=null;
	}

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
        throws ServletException, IOException {
    	
    	HttpServletRequest httpReq = (HttpServletRequest) req;
    	HttpSession session = httpReq.getSession();
    	MemVO memVO = (MemVO) session.getAttribute("LoginMem");
    	
    	if(memVO == null) {
			RequestDispatcher successView = req.getRequestDispatcher("/front-end/mem/loginMem.jsp");
			successView.forward(req, res);
			return;
    	} 
		   
		chain.doFilter(req,res);
		
    }
}
