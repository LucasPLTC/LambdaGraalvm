package br.com.itau.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.adapter.aws.FunctionInvoker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
@EnableFeignClients
public class LambdaGraalvmApplication {

    public static void main(String[] args) {
        // Referência explícita para evitar eliminação
        Class<?> clazz = FunctionInvoker.class;
        SpringApplication.run(LambdaGraalvmApplication.class, args);
    }

    @Bean
    public Function<String, String> uppercase() {
        return value -> value.toUpperCase();
    }
}
