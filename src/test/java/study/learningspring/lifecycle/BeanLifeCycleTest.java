package study.learningspring.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        // ApplicationContext > ConfigurableApplicationContext > AnnotationConfigApplicationContext
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close(); // 스프링 컨테이너를 종료. ConfigurableApplicationContext 필요
    }

    @Configuration
    static class LifeCycleConfig {

        @Bean
        public NetworkClient networkClient() {
            // NetworkClient 생성자 호출 -> .setUrl -> NetworkClient 빈 인스턴스 리턴 및 빈 등록 -> init 호출 (초기화)
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev"); // setUrl 은 빈 등록의 과정 속에서 동작한다
            return networkClient;
        }

    }

}
