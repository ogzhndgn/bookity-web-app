package org.bookity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.SpringVersion;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
@EnableAsync
public class BookityWebAppApplication {

    public static void main(String[] args) {
        System.out.println("****************************************");
        System.out.println("Bookity!");
        System.out.println("Spring Version is: " + SpringVersion.getVersion());
        System.out.println("****************************************");
        SpringApplication.run(BookityWebAppApplication.class, args);
    }


    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(new Locale("tr"));
        return sessionLocaleResolver;
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
        reloadableResourceBundleMessageSource.setBasename("classpath:locale/messages");
        return reloadableResourceBundleMessageSource;
    }
}