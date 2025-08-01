package kr.co.wikibook.gallery_jwt_jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class GalleryJwtJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(GalleryJwtJpaApplication.class, args);
    }

}
