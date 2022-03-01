package com.jeejee.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //JPA Auditing어노테이션을 모두 활성화
@SpringBootApplication //스프링부트의 자동 설정, 스프링 Bean읽기와 생성을 모두 자동으로 생성해준다.
                       //이 어노테이션이 있는 위치부터 설정을 읽기 때문에 프로젝트 최상단에 위치해야한다.
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        //SpringApplication.run으로 인해 내장 WAS(톰캣)을 실행한다.
    }
}
