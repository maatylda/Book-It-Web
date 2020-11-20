package pl.book.it.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Bean
    public RestTemplate restTemplate() {

        return new RestTemplate();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/test").setViewName("test");
        registry.addViewController("/login").setViewName("login_page");
        registry.addViewController("/api/bia/users").setViewName("users");
        registry.addViewController("/api/bia/places").setViewName("places");
    }

    @Bean
    public BookingApiClientConfiguration bookingApiClientConfiguration() {
        return new BookingApiClientConfiguration();
    }
}
