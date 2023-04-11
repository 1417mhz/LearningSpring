package study.learningspring.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);

        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
        ac.close();

        // 싱글톤과 다르게 여러 번 호출하면 서로 다른 인스턴스를 반환한다
        // 스프링 컨테이너에서 의존관계 주입, 초기화 까지만 관여하기 때문에 종료 메소드는 호출되지 않는것을 볼 수 있다
        // 프로토타입 빈은 프로토타입 빈을 조회한 클라이언트가 관리해야 한다
    }

    @Scope("prototype")
//    @Component // ApplicationContext 에 등록하면 컴포넌트 스캔처럼 동작하기 때문에 생략해도 된다
    static class PrototypeBean {

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }

    }

}
