package com.beiyelin.service.footstone.secure.filter;

import com.beiyelin.service.footstone.secure.realm.StatelessToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by xinsheng.hu on 2016/10/2.
 */

public class NoSesstionHttpMethodPermissionFilter extends HttpMethodPermissionFilter {

    private static Logger logger = LoggerFactory.getLogger(NoSesstionHttpMethodPermissionFilter.class);

    /**
     * 当访问被拒绝时，调用这个方法，这里返回401页面
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request,
                                     ServletResponse response) throws IOException {
        logger.debug("执行onAccessDenied...");
        HttpServletResponse res = (HttpServletResponse) response;
        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }

    @Override
    public boolean isAccessAllowed(ServletRequest request,
                                   ServletResponse response, Object mappedValue) throws IOException {
        logger.debug("执行isAccessAllowed过程...");
        HttpServletRequest req = (HttpServletRequest) request;

//        String name = req.getHeader("name");
//        String pass = req.getHeader("pass");
//
//        StatelessToken token = new StatelessToken();

        // 如果是带验证的，则进行验证，否则没有验证，只能进行一般的请求
//        if (name != null && pass != null) {
//            token.setPrincipal(new UserPrincipal(name, UserPrincipal.PrincipType.USER));
//            token.setPassword(pass);
//            try {
//                getSubject(request, response).login(token);
//                // 如果认证成功，则增加request的属性，用于@CurrentUser注解使用
//                User user = token.getUser();
//                request.setAttribute(Contants.CURRENT_USER, user);
//
//            } catch (AuthenticationException e) {
//                logger.info(.g"认证失败! "+eetClass().getSimpleName());
//            } catch (Exception e) {
//                logger.info("其他认证失败! "+e.getClass().getSimpleName());
//                e.printStackTrace();
//            }

//        }

        boolean result = super.isAccessAllowed(request, response, mappedValue);
        logger.info("restult:"+result);

        return super.isAccessAllowed(request, response, mappedValue);
    }


}
