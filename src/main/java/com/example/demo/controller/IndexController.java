package com.example.demo.controller;

import com.example.demo.model.BlogProperties;
import com.example.demo.model.ConfigBean;
import com.example.demo.model.TestConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author xiaohu
 * @Date 2019/11/27 11:32
 */
@RestController
public class IndexController {

    @Autowired
    private BlogProperties blogProperties;

    @Autowired
    private ConfigBean configBean;

    @Autowired
    private TestConfigBean testConfigBean;

    @RequestMapping("/test")
    String test() {
        return blogProperties.getName()+"——"+blogProperties.getTitle();
    }


    @RequestMapping("/test2")
    String test2() {
        return configBean.getName()+"——"+configBean.getTitle()+"——"+configBean.getWholeTitle();
    }

    @RequestMapping("/test3")
    String test3() {
        return testConfigBean.getName()+"——"+testConfigBean.getAge();
    }
}
