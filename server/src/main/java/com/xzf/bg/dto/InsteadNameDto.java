package com.xzf.bg.dto;

import java.util.List;

/**
 * 需要把上传的excel表明传给 下载和作为instead单号使用
 */
public class InsteadNameDto {
    private List<InsteadPayDto> list;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<InsteadPayDto> getList() {
        return list;
    }

    public void setList(List<InsteadPayDto> list) {
        this.list = list;
    }
}
