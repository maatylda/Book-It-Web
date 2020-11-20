package pl.book.it.web.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Getter
@Setter
@ConfigurationProperties(prefix = "api.address")
public class BookingApiClientConfiguration {
    private String protocol;
    private String host;
    private String port;

    public String getBaseUrl() {
        return protocol + "://" + host + ":" + port;
    }

}
