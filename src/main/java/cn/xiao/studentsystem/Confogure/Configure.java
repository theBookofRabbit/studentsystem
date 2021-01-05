package cn.xiao.studentsystem.Confogure;


import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.google.common.base.Predicates;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Configuration
@EnableSwagger2
public class Configure implements WebMvcConfigurer {

    //===============下面为借鉴网上的代码，目的是配置swagger和fastjson等，经测试有效。====================

    //Fastjson
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 创建 FastJsonHttpMessageConverter 对象
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        // 自定义 FastJson 配置
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setCharset(Charset.defaultCharset()); // 设置字符集
        fastJsonConfig.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect.WriteMapNullValue);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        // 设置支持的 MediaType
        fastJsonHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON,
                MediaType.APPLICATION_JSON_UTF8));
        // 添加到 converters 中
        converters.add(1, fastJsonHttpMessageConverter);
    }


    //Cors跨域，不然postman请求被拒绝、Vue某些请求报错：http://www.iocoder.cn/Spring-Boot/SpringMVC/?github
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        // 创建 UrlBasedCorsConfigurationSource 配置源，类似 CorsRegistry 注册表
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 创建 CorsConfiguration 配置，相当于 CorsRegistration 注册信息
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Collections.singletonList("*")); // 允许所有请求来源
        config.setAllowCredentials(true); // 允许发送 Cookie
        config.addAllowedMethod("*"); // 允许所有请求 Method
        config.setAllowedHeaders(Collections.singletonList("*")); // 允许所有请求 Header
        // config.setExposedHeaders(Collections.singletonList("*")); // 允许所有响应 Header
        config.setMaxAge(1800L); // 有效期 1800 秒，2 小时
        source.registerCorsConfiguration("/**", config);
        // 创建 FilterRegistrationBean 对象
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(
                new CorsFilter(source)); // 创建 CorsFilter 过滤器
        bean.setOrder(0); // 设置 order 排序。这个顺序很重要哦，为避免麻烦请设置在最前
        return bean;
    }

    //添加swagger支持
    @Bean
    public Docket createRestApi() {
        // 创建 Docket 对象
        return new Docket(DocumentationType.SWAGGER_2) // 文档类型，使用 Swagger2
                .apiInfo(this.apiInfo()) // 设置 API 信息
                // 扫描 Controller 包路径，获得 API 接口
                .select()
                .apis(Predicates.or(
                        RequestHandlerSelectors.basePackage("cn.xiao.studentsystem.Controller"),//此行不要打错，会影响swagger-ui页面的接口列表显示
                        RequestHandlerSelectors.basePackage("cn.xiao.studentsystem.ClientCrashLogJsonController")//删了
                ))
                .paths(PathSelectors.any())
                // 构建出 Docket 对象
                .build();
    }


    /**
     * 创建 API 信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("数据库课设之学生信息管理系统接口文档")
                .description("正如你所见，这是一份简陋的swagger接口文档，我把他当做学生和教师的用户界面了OvO")
                .version("1.0.0(也是最终版)") // 版本号
                .contact(new Contact("小小", "https://www.cnblogs.com/tuzidewangblog/", "3172864829@qq.com")) // 联系人
                .build();
    }


}

