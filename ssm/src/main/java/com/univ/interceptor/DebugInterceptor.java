package com.univ.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/** 
 * @author univ 
 * @date 2016年1月12日 下午4:58:50 
 * @version v1.0
 * @Description: 调试时输出请求路径，响应方法，返回视图等等
 */
public class DebugInterceptor implements HandlerInterceptor{
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        return true;//返回为true后面的postHandle方法和afterCompletion方法才会被执行
    }
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
 
        String requestUri = request.getRequestURI();//请求路径
        System.out.println("请求: " + request.getMethod() + " " + requestUri);
 
        //具体能得到什么，最好的方法是使用debug模式查看handler的值
        if (handler instanceof HandlerMethod) {//需要判断
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            String controllerName = handlerMethod.getBean().getClass().getName();//响应请求的控制器            
            String methodName = handlerMethod.getMethod().getName();//响应请求的方法
            System.out.println("响应: " + controllerName + " " + methodName);
        }
        if (modelAndView != null) {//比如异步请求时modelAndView可能会为null
            String view = modelAndView.getViewName();//返回的视图名
            System.out.println("返回的视图名: " + view);
        }
    }
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex)
            throws Exception {       
    }
}

