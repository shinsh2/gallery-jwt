package kr.co.wikibook.gallery.common.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class P6spyConfig {
	@Bean
    public P6SpyEventListener p6SpyCustomEventListener() {
        return new P6SpyEventListener();
    }
	
	@Bean
    public P6spyPrettySqlFormatter p6SpyCustomFormatter() {
        return new P6spyPrettySqlFormatter();
    }
}