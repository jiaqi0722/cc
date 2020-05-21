package com.cc.api.common.config;

import com.cc.api.common.util.CopyrightUtil;
import com.cc.api.common.util.SpringUtil;
import com.cc.api.common.response.ResponseBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * * * * * * * * * * *
 * Here  be  dragons *
 * * * * * * * * * * *
 *
 * @author teangtang
 */
@Component
@Order
public class CopyrightInspector implements ApplicationRunner {
    public CopyrightInspector() {
    }

    @Override
    public void run(ApplicationArguments applicationArguments) {
        ResponseBean responseBean = CopyrightUtil.checkCopyright();
        if (responseBean.hasError()) {
            this.shutdownServer();
        }

    }

    private void shutdownServer() {
        ApplicationContext ac = SpringUtil.getApplicationContext();
        SpringApplication.exit(ac, new ExitCodeGenerator[0]);
    }
}
