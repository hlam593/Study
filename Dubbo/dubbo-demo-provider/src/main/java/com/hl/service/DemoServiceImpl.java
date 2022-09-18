package com.hl.service;

import org.apache.dubbo.config.annotation.DubboService;
import org.hl.DemoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author hlam
 * @date 2022/9/18
 */
@DubboService(version = "1.0.0")
@Service
public class DemoServiceImpl implements DemoService {
    @Value("${dubbo.application.name}")
    private String serviceName;

    @Override
    public String sayHello(String name) {
        return String.format("[%s] : Hello, %s", serviceName, name);
    }
}
