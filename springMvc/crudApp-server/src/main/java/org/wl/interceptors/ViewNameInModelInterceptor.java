package org.wl.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.wl.constants.ModelAttrs;

public class ViewNameInModelInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

        if (modelAndView != null) {
        	modelAndView.addObject(ModelAttrs.CURRENT_PAGE.toString(), modelAndView.getViewName());
        	modelAndView.addObject("CURRENT_REQUEST_URI", request.getRequestURI());
        	modelAndView.addObject("CURRENT_CONTEXT_PATH", request.getContextPath());
        	modelAndView.addObject("CURRENT_URL", request.getRequestURI().substring(request.getContextPath().length()));
        }
        
        super.postHandle(request, response, handler, modelAndView);
    }

}
