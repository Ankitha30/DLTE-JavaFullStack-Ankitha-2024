package element.spring.boot.springjdbctemplate.configs;


import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;


@EnableWs
@Configuration
public class SoapServiceConfiguration {

    @Bean
    public ServletRegistrationBean servletRegistrationBean(ApplicationContext applicationContext){
        MessageDispatcherServlet servlet=new MessageDispatcherServlet();
        servlet.setTransformWsdlLocations(true);
        servlet.setApplicationContext(applicationContext);
        return new ServletRegistrationBean(servlet,"/transactionrepo/*");
    }

    // wsdl properties
    @Bean(name = "transaction")
    public DefaultWsdl11Definition convertToWsdl(XsdSchema xsdSchema){
        DefaultWsdl11Definition defaultWsdl11Definition=new DefaultWsdl11Definition();
        defaultWsdl11Definition.setPortTypeName("TransactionPort");
        defaultWsdl11Definition.setTargetNamespace("http://services.transaction");
        defaultWsdl11Definition.setLocationUri("/transactionrepo");
        defaultWsdl11Definition.setSchema(xsdSchema);
        return defaultWsdl11Definition;
    }

    // identify the xsd
    @Bean
    public XsdSchema transactionSchema(){

        return new SimpleXsdSchema(new ClassPathResource("transactions.xsd"));
//        String xsdFilePath = "C:/TransactionXSD/transactions.xsd";
//        FileSystemResource xsdFileResource = new FileSystemResource(xsdFilePath);
//        return new SimpleXsdSchema(xsdFileResource);
    }
}
