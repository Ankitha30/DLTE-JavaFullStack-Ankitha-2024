package debit.card.application.security;

import debits.cards.dao.services.CardSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class CardSecurityConfiguration {
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    CardSecurityService cardSecurityServices;

    AuthenticationManager authenticationManager;

    @Autowired
    CardFailure cardFailureHandler;
    @Autowired
    CardSuccessHandler cardSuccessHandler;



    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("http://127.0.0.1:5500"));

        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }




    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.httpBasic();
        httpSecurity.authorizeRequests().antMatchers("/web/").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/images/**").permitAll();
        httpSecurity.logout().permitAll();



        httpSecurity.formLogin().loginPage("/web/")
                .usernameParameter("username")
                .failureHandler(cardFailureHandler)
                .successHandler(cardSuccessHandler);
        httpSecurity.csrf().disable();
        httpSecurity.cors();
        httpSecurity.authorizeRequests().antMatchers("/bank/register").permitAll();
//        httpSecurity.authorizeRequests().antMatchers("/debitcardrepo/debitcard.wsdl").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/v3/api-docs").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/css/**").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/images/**").permitAll();
        httpSecurity.authorizeRequests().anyRequest().authenticated();


        AuthenticationManagerBuilder builder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(cardSecurityServices);
        authenticationManager = builder.build();
        httpSecurity.authenticationManager(authenticationManager);
        return httpSecurity.build();
    }
}
