// package com.zhengqi.wiki03.interceptor;
//
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.stereotype.Component;
// import org.springframework.web.servlet.HandlerInterceptor;
// import org.springframework.web.servlet.ModelAndView;
//
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
//
// /**
//  * 拦截器拦截， Spring 框架特有， 常用语登录校验， 请求日志打印等
//  */
// @Component
// public class LogInterceptor implements HandlerInterceptor {
//     private final static Logger LOG = LoggerFactory.getLogger(LogInterceptor.class);
//
//     @Override
//     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//         LOG.info("----------LogInterceptor 开始---------");
//         LOG.info("请求地址{} {}", request.getRequestURI().toString(), request.getMethod());
//         LOG.info("请求远程地址：{}", request.getRemoteAddr());
//
//         long startTime = System.currentTimeMillis();
//         request.setAttribute("requestStartTime", startTime);
//         return true;
//     }
//
//     @Override
//     public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//         long startTime = System.currentTimeMillis();
//         request.setAttribute("requestStartTime", startTime);
//         LOG.info("----------LogInterceptor 耗时{} ms", System.currentTimeMillis() - startTime);
//     }
// }
