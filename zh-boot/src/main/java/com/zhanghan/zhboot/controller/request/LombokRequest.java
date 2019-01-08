package com.zhanghan.zhboot.controller.request;


import lombok.Data;

@Data
public class LombokRequest {

    private Integer intLombok;
    private String strLombok;
    private Boolean boleanLombok;
    private Person personLombok;

    @Data
    private static class Person {
        private Integer age;
        private String name;
    }


}
