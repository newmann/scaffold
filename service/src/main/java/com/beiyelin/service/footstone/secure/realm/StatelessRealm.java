package com.beiyelin.service.footstone.secure.realm;

import com.beiyelin.service.footstone.secure.codec.HmacSHA256Utils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xinsheng.hu on 2016/10/1.
 */
public class StatelessRealm extends AuthorizingRealm {
    private static Logger logger = LoggerFactory.getLogger(StatelessRealm.class);

    @Override
    public boolean supports(AuthenticationToken token) {
        logger.debug("判断是否为StatelessToken...");
        //仅支持StatelessToken类型的Token
        return token instanceof StatelessToken;

    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.debug("开始处理验证信息...");
        StatelessToken statelessToken = (StatelessToken) token;

        String userName = statelessToken.getUsername();
        String key = getKey(userName);//根据用户名获取密钥（和客户端的一样）
        //在服务器端生成客户端参数消息摘要
        String serverDigest = HmacSHA256Utils.digest(key, statelessToken.getParams());
        System.out.println(statelessToken.getClientDigest());
        System.out.println(serverDigest);
        //然后进行客户端消息摘要和服务器端消息摘要的匹配
        return new SimpleAuthenticationInfo(
                userName,
                serverDigest,
                getName());

    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.debug("开始返回用户权限...");
        //根据用户名查找角色，请根据需求实现
        String userName = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        authorizationInfo.addRole("admin");

        return authorizationInfo;
    }

    private String getKey(String username) {//得到密钥，此处硬编码一个
        if("admin".equals(username)) {
            return "dadadswdewq2ewdwqdwadsadasd";
        }
        return null;
    }

}
