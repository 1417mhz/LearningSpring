package study.learningspring.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import study.learningspring.AppConfig;
import study.learningspring.member.MemberRepository;
import study.learningspring.member.MemberServiceImpl;
import study.learningspring.order.OrderServiceImpl;

import static org.assertj.core.api.Assertions.*;

public class ConfigurationSingletonTest {

//    @Test
//    void configurationTest() {
//        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
//
//        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
//        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
//        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);
//
//        MemberRepository memberRepository1 = memberService.getMemberRepository();
//        MemberRepository memberRepository2 = orderService.getMemberRepository();
//
//        // 모두 같은 memberRepository 인스턴스를 참조하고 있다
//        System.out.println("memberRepository = " + memberRepository);
//        System.out.println("MemberService -> memberRepository = " + memberRepository1);
//        System.out.println("OrderService -> memberRepository = " + memberRepository2);
//
//        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
//        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
//    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());

    }

}
