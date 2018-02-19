package com.book.console.web.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * ServletRequest 中 getReader() 和 getInputStream() 只能调用一次的解决办法
 * Created by zhengfeng on 2016-09-18 11:39.
 */
@Component
public class BodyReaderFilter implements Filter {

    private final static Logger logger = LoggerFactory.getLogger(BodyReaderFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("BodyReaderFilter started ...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(new BodyReaderHttpServletRequestWrapper((HttpServletRequest) request), response);
    }

    @Override
    public void destroy() {
        logger.info("BodyReaderFilter destroy ...");
    }
}
