package cn.com.test.user.interceptor;

import cn.com.test.user.entity.SysUser;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author mazhiqiang
 * @Description //TODO 拦截器，拦截到登陆页面
 * @Date 10:33 2019\3\14 0014
 * @Param
 * @return 
 **/
public class LoginInterceptor extends HandlerInterceptorAdapter {
    // @Autowired
    // private JedisClient jedisClient;

    /**
     * This implementation always returns {@code true}.
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("请求地址：" + request.getRequestURI());
        SysUser sysUser = (SysUser) request.getSession().getAttribute("system_user");

        if (sysUser != null) {
            System.out.println("用户名：" + sysUser.getUser_name());
            // if(!jedisClient.exists(sysUser.getUser_name())){
            //     response.sendRedirect(request.getContextPath() + "/cms/login.html");
            //     return false;
            // }
            return true;
        } else {
            if (request.getHeader("x-requested-with") != null
                    && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/xml");
                response.setDateHeader("Expires", 0);
                response.setHeader("sessionstatus", "timeout");
            } else {
                response.sendRedirect(request.getContextPath() + "/cms/login.html");
            }
            return false;
        }
    }

    /**
     * This implementation is empty.
     */
    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
    }

    /**
     * This implementation is empty.
     */
    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}
