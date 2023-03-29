package study.learningspring.scan;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import study.learningspring.AutoAppConfig;
import study.learningspring.member.MemberService;
import study.learningspring.order.OrderService;

import static org.assertj.core.api.Assertions.*;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);
        OrderService orderService = ac.getBean(OrderService.class);

        assertThat(memberService).isInstanceOf(MemberService.class);
        assertThat(orderService).isInstanceOf(OrderService.class);

    }

}
