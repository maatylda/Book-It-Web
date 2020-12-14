package pl.book.it.web.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    //@Autowired
    //JsonbHttpMessageConverter jsonbHttpMessageConverter;
    //logowanie powinno być zrobione na tockenie, w ty momencoe hasło przesyłane jest restem co nie jest idealne rozwiązanie
    @Bean
    public RestTemplate restTemplate(ApiUserConfig apiUserConfig) {
        final RestTemplate restTemplate = new RestTemplateBuilder()
                .basicAuthentication(apiUserConfig.getUserName(), apiUserConfig.getPassword())
                .messageConverters(new MappingJackson2HttpMessageConverter())
                .build();
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        stringHttpMessageConverter.setWriteAcceptCharset(true);
        for (int i = 0; i < restTemplate.getMessageConverters().size(); i++) {
            if (restTemplate.getMessageConverters().get(i) instanceof StringHttpMessageConverter) {
                restTemplate.getMessageConverters().remove(i);
                restTemplate.getMessageConverters().add(i, stringHttpMessageConverter);
                break;
            }
        }
        return restTemplate;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/test").setViewName("test");
        registry.addViewController("/hello").setViewName("hello");
        //       registry.addViewController("/login").setViewName("login_page");
//        registry.addViewController("/api/bia/users").setViewName("users");
        //      registry.addViewController("/places").setViewName("places");
//       registry.addViewController("/index").setViewName("index");
    }

    @Bean
    public BookingApiClientConfiguration bookingApiClientConfiguration() {
        return new BookingApiClientConfiguration();
    }
}
