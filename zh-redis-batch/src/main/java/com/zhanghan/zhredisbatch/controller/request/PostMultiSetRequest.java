/*
 * Copyright (c) 2020. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：Spring Boot实战:Redis批量操作轻松实现百倍性能提升
 * 类名称：PostMultiSetRequest.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/springboot
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhredisbatch.controller.request;


import javax.validation.constraints.NotNull;
import com.zhanghan.zhredisbatch.dto.BatchRedisDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PostMultiSetRequest {

    @NotNull
    private List<BatchRedisDto> batchRedisDtoList;

}
