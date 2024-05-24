package egovframework.breeze.core.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FilterServlet implements Filter {

	
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) 
    		throws IOException, ServletException {
    	//System.out.println("===== before(filter) =====");

		HttpServletRequest request = (HttpServletRequest) req ;
		String sUri = request.getRequestURI().toString().trim();

		if(!sUri.startsWith("/_") && !sUri.contains(".")) {	// _(언더바)로 시작하지 않는다면 체크해서 _breeze 붙여줘서 forward
			RequestDispatcher dispatcher = request.getRequestDispatcher("/_breeze"+sUri);
			dispatcher.forward(request, res);
			return;
		}else{	// _(언더바)로 시작한다면
			String arrUri[] = sUri.split("/");
			if(arrUri[1].equals("_breeze")) {
				HttpServletResponse response = (HttpServletResponse) res;
				response.sendRedirect(sUri.replace("/_breeze", ""));
				return;
			}else {
				chain.doFilter(request, res);
			}
		}

		//System.out.println("===== after(filter) =====");
	}
 
	@Override
	public void init(FilterConfig config) throws ServletException {

    }
    
}
