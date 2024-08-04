package com.calculator.config;

import com.calculator.interceptor.CalculatorInterceptor;
import com.calculator.service.OperationService;
import com.calculator.service.RecordService;
import com.calculator.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class AppConfig implements WebMvcConfigurer {

    private final UserService userService;
    private final RecordService recordService;
    private final OperationService operationService;
    private final Environment environment;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // Interceptor configured for the /calculator path in order to manage requests and responses
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CalculatorInterceptor(userService, recordService, operationService, environment)).addPathPatterns("/calculator/**");
    }
}
