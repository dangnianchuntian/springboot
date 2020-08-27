/*
 * Copyright (c) 2020. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：Spring Boot实战:Redis批量操作轻松实现百倍性能提升
 * 类名称：ListMultiGetResponse.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhredisbatch.controller.response;


import com.sun.istack.internal.NotNull;
import com.zhanghan.zhredisbatch.dto.BatchRedisDto;
import lombok.Data;

import java.util.List;

@Data
public class ListMultiGetResponse {

    @NotNull
    private List<BatchRedisDto> batchRedisDtoList;

    public ListMultiGetResponse() {
    }
}
