package filter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mem.model.MemVO;

public class GetURIFilter implements Filter {
	
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
    	
    	if(!"/front-end/mem/loginMem.jsp".equals(httpReq.getServletPath()) 
    	&& !"/front-end/mem/registerMem.jsp".equals(httpReq.getServletPath())
    	&& !"/front-end/vendor/loginVendor.jsp".equals(httpReq.getServletPath()) 
    	&& !"/front-end/vendor/registerVendor.jsp".equals(httpReq.getServletPath())) {
    		session.setAttribute("whereUFrom",httpReq.getContextPath() + httpReq.getServletPath());
    	}
		
		chain.doFilter(req,res);
    }
}
