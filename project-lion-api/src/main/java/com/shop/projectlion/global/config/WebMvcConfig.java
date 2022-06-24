package com.shop.projectlion.global.config;

import com.shop.projectlion.api.interceptor.PermissionInterceptor;
import com.shop.projectlion.api.interceptor.TokenCheckInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${file.upload.path}")
    private String uploadPath;

    private final PermissionInterceptor permissionInterceptor;
    private final TokenCheckInterceptor tokenCheckInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**") // "/images/**" url로 요청이 올 경우 uploadPath로부터 파일을 찾음
                .addResourceLocations("file:///"+uploadPath);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("*")
                .allowedMethods(
                        HttpMethod.GET.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.PATCH.name(),
                        HttpMethod.DELETE.name()
                );
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(permissionInterceptor).addPathPatterns("/api/admin/**");
        registry.addInterceptor(tokenCheckInterceptor).addPathPatterns("/api/**")
                .excludePathPatterns("/api/oauth-login", "/api/logout", "/api/token", "/api/health");
    }
}
