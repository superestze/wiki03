package com.zhengqi.wiki03.interceptor;

import com.zhengqi.wiki03.util.LoginUserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private final static Logger LOG = LoggerFactory.getLogger(LoginInterceptor.class);

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOG.info("--------LOG interceptor 开始");
        long startTime = System.currentTimeMillis();
        request.setAttribute("requestStartTime", startTime);

        if (request.getMethod().toLowerCase().endsWith("OPTIONS")) {
            return true;
        }

        String path = request.getRequestURL().toString();
        LOG.info("登录接口拦截:, path {}", path);

        String token = request.getHeader("token");
        if (token == null || token.isEmpty()) {
            LOG.info("token 为空， 请求被拦截");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        Object object = redisTemplate.opsForValue().get(token);
        if (object == null) {
            LOG.warn("token 无效， 请求被拦截");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        } else {
            LOG.info("已登录:{}", object);
            // LoginUserContext.setUser(object);
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long startTime = System.currentTimeMillis();
        LOG.info("------------- LoginInterceptor 结束 耗时：{} ms -------------", System.currentTimeMillis() - startTime);

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // LOG.info("LogInterceptor 结束");
    }


}
