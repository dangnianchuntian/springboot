package com.zhanghan.zhboot.config;

import com.zhanghan.zhboot.properties.RedisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;


@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    @Autowired
    private RedisProperties redisProperties;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration rf = new RedisStandaloneConfiguration();
        rf.setDatabase(redisProperties.getDatabase());
        rf.setHostName(redisProperties.getHost());
        rf.setPort(redisProperties.getPort());
        rf.setPassword(RedisPassword.of(redisProperties.getPassword()));
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpb =
                (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(redisProperties.getMaxIdle());
        jedisPoolConfig.setMinIdle(redisProperties.getMinIdle());
        jedisPoolConfig.setMaxTotal(redisProperties.getMaxActive());
        jedisPoolConfig.setMaxWaitMillis(redisProperties.getMaxWait());
        jpb.poolConfig(jedisPoolConfig);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(rf, jpb.build());
        return jedisConnectionFactory;
    }

//    @Bean
//    public RedisTemplate redisTemplate() {
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//        RedisTemplate rt = new RedisTemplate();
//        rt.setConnectionFactory(jedisConnectionFactory);
//        System.out.println(jedisConnectionFactory.getPoolConfig().getMaxIdle());
//        System.out.println(jedisConnectionFactory.getPoolConfig().getMinIdle());
//        System.out.println(jedisConnectionFactory.getPoolConfig().getMaxTotal());
//        RedisSerializer rs = new StringRedisSerializer();
//        rt.setKeySerializer(rs);
//        rt.setValueSerializer(jackson2JsonRedisSerializer);
//        rt.setHashKeySerializer(rs);
//        rt.setHashValueSerializer(jackson2JsonRedisSerializer);
//        rt.afterPropertiesSet();
//        return rt;
//
//    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

//    @Bean
//    JedisConnectionFactory jedisConnectionFactory() {
//        JedisConnectionFactory factory = new JedisConnectionFactory();
//        factory.setHostName(redisProperties.getHost());
//        factory.setPort(redisProperties.getPort());
//        factory.setDatabase(redisProperties.getDatabase());
//        factory.setPassword(redisProperties.getPassword());
//        factory.setUsePool(true);
//        //factory.setTimeout(redisProperties.getTimeout());
//        return factory;
//    }

    @Bean
    RedisTemplate<Object, Object> redisTemplate() {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }

//    @Bean
//    RedisCacheManager cacheManager() {
//        return RedisCacheManager.create(jedisConnectionFactory());
//    }


    @Bean
    RedisTemplate<String, String> strRedisTemplate() {
        final RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericToStringSerializer<>(String.class));
        template.setValueSerializer(new GenericToStringSerializer<>(String.class));
        return template;
    }

    @Bean
    RedisTemplate<String, Long> longRedisTemplate() {
        final RedisTemplate<String, Long> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericToStringSerializer<>(Long.class));
        template.setValueSerializer(new GenericToStringSerializer<>(Long.class));
        return template;
    }

    @Bean
    RedisTemplate<String, Boolean> booleanRedisTemplate() {
        final RedisTemplate<String, Boolean> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericToStringSerializer<>(Boolean.class));
        template.setValueSerializer(new GenericToStringSerializer<>(Boolean.class));
        return template;
    }
}