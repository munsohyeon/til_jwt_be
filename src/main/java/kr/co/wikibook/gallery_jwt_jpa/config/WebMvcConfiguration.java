package kr.co.wikibook.gallery_jwt_jpa_jwt_jpa.common;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration // 빈등록 스프링 컨테이너의 객체 생성을 대리로 해주는거..?
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final String uploadPath;

    public WebMvcConfiguration(@Value("${constants.file.directory}") String uploadPath) {
        this.uploadPath = uploadPath;
        log.info("uploadPath: {}", uploadPath);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pic/**")
                .addResourceLocations("file:" + uploadPath);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*"); // 쿠키, 세션 허용
    }
}
