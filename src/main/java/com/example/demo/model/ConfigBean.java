package com.example.demo.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author xiaohu
 * @Date 2019/11/27 11:34
 */
@ConfigurationProperties(prefix="mrbird.blog")
@Component
public class ConfigBean {
    private String name;
    private String title;
    private String wholeTitle;

    public String getWholeTitle() {
        return wholeTitle;
    }

    public void setWholeTitle(String wholeTitle) {
        this.wholeTitle = wholeTitle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
