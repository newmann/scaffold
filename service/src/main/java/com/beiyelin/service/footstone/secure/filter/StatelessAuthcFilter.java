package com.beiyelin.service.footstone.secure.filter;

import com.beiyelin.service.footstone.constant.SecureCST;
import com.beiyelin.service.footstone.secure.realm.StatelessToken;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xinsheng.hu on 2016/10/2.
 */
public class StatelessAuthcFilter extends AccessControlFilter {
    private static Logger logger = LoggerFactory.getLogger(StatelessAuthcFilter.class);

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        logger.debug("执行onAccessDenied...");

        //1、客户端生成的消息摘要
        String clientDigest = request.getParameter(SecureCST.PARAM_DIGEST);
        //2、客户端传入的用户身份
        String username = request.getParameter(SecureCST.PARAM_USERNAME);
        //3、客户端请求的参数列表
        Map<String, String[]> params = new HashMap<String, String[]>(request.getParameterMap());
        params.remove(SecureCST.PARAM_DIGEST);

        //4、生成无状态Token
        StatelessToken token = new StatelessToken(username, params, clientDigest);

        try {
            //5、委托给Realm进行登录
            getSubject(request, response).login(token);
        } catch (Exception e) {
            e.printStackTrace();
            onLoginFail(response); //6、登录失败
            return false;
        }
        return true;

    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        logger.debug("isAccessAllowed, return false.");
        return false;
    }

    //登录失败时默认返回401状态码
    private void onLoginFail(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.getWriter().write("login error");
    }

}
