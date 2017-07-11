package com.zxh.entity.dto;

/**
 * Created by zhengxh on 2017/7/3.
 */
public class MenuDto {
    private String name;
    private Long id;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
