/*
 * Copyright (c) 2020. zhanghan_java@163.com All Rights Reserved.
 * 项目名称：Spring Boot实战分页查询附近的人: Redis+GeoHash+Lua
 * 类名称：RedisLuaUtil.java
 * 创建人：张晗
 * 联系方式：zhanghan_java@163.com
 * 开源地址: https://github.com/dangnianchuntian/redis-to-db
 * 博客地址: https://zhanghan.blog.csdn.net
 */

package com.zhanghan.zhnearbypeople.util;


import org.springframework.data.redis.core.script.DigestUtils;
import org.springframework.data.redis.core.script.RedisScript;

public class RedisLuaUtil {

    private static final RedisScript<Long> GEO_RADIUS_STOREDIST_SCRIPT;

    public static RedisScript<Long> GEO_RADIUS_STOREDIST_SCRIPT() {
        return GEO_RADIUS_STOREDIST_SCRIPT;
    }

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("return redis.call('georadiusbymember',KEYS[1],KEYS[2],KEYS[3],KEYS[4],KEYS[5],KEYS[6],KEYS[7])");
        GEO_RADIUS_STOREDIST_SCRIPT = new RedisScriptImpl<>(sb.toString(), Long.class);
    }

    private static class RedisScriptImpl<T> implements RedisScript<T> {
        private final String script;
        private final String sha1;
        private final Class<T> resultType;

        public RedisScriptImpl(String script, Class<T> resultType) {
            this.script = script;
            this.sha1 = DigestUtils.sha1DigestAsHex(script);
            this.resultType = resultType;
        }

        @Override
        public String getSha1() {
            return sha1;
        }

        @Override
        public Class<T> getResultType() {
            return resultType;
        }

        @Override
        public String getScriptAsString() {
            return script;
        }
    }


}
