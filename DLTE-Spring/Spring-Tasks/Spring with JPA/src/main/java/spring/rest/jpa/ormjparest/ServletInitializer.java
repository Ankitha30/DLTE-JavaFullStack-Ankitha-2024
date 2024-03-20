package spring.rest.jpa.ormjparest;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {
    protected SpringApplicationBuilder configurw(SpringApplicationBuilder applicationBuilder){
        return applicationBuilder.sources(SpringWithJpaApplication.class);
    }
}
