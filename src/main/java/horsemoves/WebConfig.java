package horsemoves;

import horsemoves.controller.servlet.HorseMovesServletController;
import javax.servlet.http.HttpServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Texhnolyze
 */

@Configuration
public class WebConfig {
    
    @Bean
    public ServletRegistrationBean<HttpServlet> horseMovesServletController() {
        HttpServlet servlet = new HorseMovesServletController();
        ServletRegistrationBean<HttpServlet> bean = new ServletRegistrationBean<>();
        bean.setServlet(servlet);
        bean.setLoadOnStartup(1);
        bean.addUrlMappings("/hourse/servlet/count/");
        return bean;
    }
    
}
