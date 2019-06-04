package com.microservices.hystrixdashboardexample;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class RestResource {

    @HystrixCommand(fallbackMethod = "fallBackHello" ,
                    groupKey = "hello",
                    commandKey = "hello")
    @GetMapping("/hello")
    public String hello(){
        if(RandomUtils.nextBoolean()){
            throw new RuntimeException("Failed");
        }
        return "Hystrix Hello World";
    }
    @HystrixCommand(fallbackMethod = "fallBackHello" ,
            groupKey = "helloYT",
            commandKey = "helloYT")
    @GetMapping("/helloYT")
    public String helloYT(){
        if(RandomUtils.nextBoolean()){
            throw new RuntimeException("YT Failed");
        }
        return "Hello You Tube";
    }

    public String fallBackHello(){
        return "Fall Back Hello Initiated";
    }
}
