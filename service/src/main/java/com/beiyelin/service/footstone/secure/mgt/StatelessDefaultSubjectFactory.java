package com.beiyelin.service.footstone.secure.mgt;


import org.apache.shiro.mgt.DefaultSubjectFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xinsheng.hu on 2016/10/2.
 */
public class StatelessDefaultSubjectFactory extends DefaultWebSubjectFactory {
    private static Logger logger = LoggerFactory.getLogger(StatelessDefaultSubjectFactory.class);
    @Override
    public Subject createSubject(SubjectContext context) {
        logger.debug("开始创建Subject，不创建session...");
        //不创建session
        context.setSessionCreationEnabled(false);

        return super.createSubject(context);
    }
}
