package debit.card.application.configs;


import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.ResourceBundle;

@EnableWs
@Configuration
public class DebitCardServiceConfiguration {
    ResourceBundle resourceBundle = ResourceBundle.getBundle("cards");
    @Bean
    public ServletRegistrationBean servletRegistrationBean(ApplicationContext applicationContext){
        MessageDispatcherServlet servlet=new MessageDispatcherServlet(); //responsible for dispatching incoming SOAP messages to the appropriate endpoint.
        servlet.setTransformWsdlLocations(true);
        servlet.setApplicationContext(applicationContext);
        return new ServletRegistrationBean(servlet,"/debitcardrepo/*"); //to register the servlet with specific URL mappings ("/debitcardrepo/
    }

    // wsdl properties
    @Bean(name = "debitcard")
    public DefaultWsdl11Definition convertToWsdl(XsdSchema xsdSchema){
        DefaultWsdl11Definition defaultWsdl11Definition=new DefaultWsdl11Definition();
        defaultWsdl11Definition.setPortTypeName("DebitCardPort");
        defaultWsdl11Definition.setTargetNamespace(resourceBundle.getString("url"));
        defaultWsdl11Definition.setLocationUri("/debitcardrepo");
        defaultWsdl11Definition.setSchema(xsdSchema);
        return defaultWsdl11Definition;
    }

    @Bean
    public XsdSchema debitCardSchema(){
        return new SimpleXsdSchema(new ClassPathResource("debitcard.xsd"));
    }

}
