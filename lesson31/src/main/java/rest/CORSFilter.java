package rest;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import rest.util.servlet.HttpMethod;

import java.io.IOException;

@WebFilter(asyncSupported = true, urlPatterns = {"/*"})
public class CORSFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Headers", "*");
        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Methods","*");

        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if (request.getMethod().equals(HttpMethod.OPTIONS.name())) {
            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            return;
        }

        chain.doFilter(request, servletResponse);
    }
}
