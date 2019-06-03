package com.sieyuan.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
//@EnableFeignClientsa
@EnableDiscoveryClient
//@EnableHystrix
public class ApiGateWayApplication {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder){
        return builder.routes().route(
                r->r.path("/api/**").uri("www.baidu.com")
        ).build();
    }
	public static void main(String[] args) {
		SpringApplication.run(ApiGateWayApplication.class, args);
	}

}
