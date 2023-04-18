package server.bodyhealth.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        String url = "https://bodyhealth-web.netlify.app"; //SE CAMBIA EN PRODUCCION

        registry.addMapping("/login").allowedOrigins(url).allowedOrigins(url);

//        registry.addMapping("/login")
//                .allowedOrigins("http://localhost:5173")
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                .allowedHeaders("authorization", "content-type")
//                .allowCredentials(true)
//                .maxAge(3600);
    }

}
