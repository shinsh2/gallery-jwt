package kr.co.wikibook.gallery.common.config;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

@Configuration
//@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

//    private final ApiInterceptor apiInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173")
                .allowedMethods("*")
                .allowCredentials(true);
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        // Request 정보 로깅
        registry.addInterceptor(new LoggerInterceptor());

        // 특정 리소스에 대한 cache control
        WebContentInterceptor interceptor = new WebContentInterceptor();
        interceptor.addCacheMapping(CacheControl
                .maxAge(60, TimeUnit.SECONDS)
                .noTransform()
                .mustRevalidate(), "/vi/api/*");
        
        registry.addInterceptor(interceptor);
    }
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(apiInterceptor)
//                .addPathPatterns("/v1/api/**")
//                .excludePathPatterns("/v1/api/account/**", "/v1/api/items/**"); // 예외
//    }
}