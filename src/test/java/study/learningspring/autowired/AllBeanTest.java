package study.learningspring.autowired;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import study.learningspring.AutoAppConfig;
import study.learningspring.discount.DiscountPolicy;
import study.learningspring.member.Grade;
import study.learningspring.member.Member;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBeanTest {

    @Test
    void findAllBean() {
        // AnnotationConfigApplicationContext 를 통해 스프링 컨테이너를 생성하고
        // AutoAppConfig 와 DiscountService 는 컴포넌트 스캔을 통해 빈을 등록한다
        // DiscountPolicy 타입 빈을 가져오기 위해 AutoAppConfig 도 등록한다
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        DiscountService discountService = ac.getBean(DiscountService.class);

        Member member = new Member(1L, "userA", Grade.VIP);

        // 어떤 할인 정책을 이용할지를 인자로 메소드에 전달한다
        int fixDiscountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(fixDiscountPrice).isEqualTo(1000);

        // 어떤 할인 정책을 이용할지를 인자로 메소드에 전달한다
        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
        assertThat(rateDiscountPrice).isEqualTo(2000);
    }

    // 기존 코드를 건드리면 복잡해지므로 별도로 생성한 서비스 코드
    static class DiscountService {

        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policyList;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policyList) {
            // DiscountPolicy 타입으로 등록되어있는 빈들을 가져와 Map, List 타입으로 담는다
            this.policyMap = policyMap;
            this.policyList = policyList;

            /* 의존관계 주입 여부 확인 **/
            // Map 은 키:값 형태로 빈 이름:빈 인스턴스 형태로 출력됨
            System.out.println("policyMap = " + policyMap);
            // List 는 스프링 빈 인스턴스가 출력됨
            System.out.println("policyList = " + policyList);
        }

        // 다형성을 이용한 유연함 발휘
        public int discount(Member member, int price, String discountCode) {
            /* 인자로 넘어온 할인 정책 이름에 따라 Map 에서 해당 빈을 가져와 로직을 실행시킨다
               할인 정책 이름(코드)을 빈 이름과 일치시킨다 **/
            DiscountPolicy discountPolicy = policyMap.get(discountCode);

            // 어떤 할인 정책으로 동작하는지 확인하기 위한 코드이다. 실제 사용자는 인터페이스만 알 뿐 어떤 구현체인지는 알 수 없다.
            // Code = 할인 정책의 이름, Policy = 실제 할인 정책 빈 인스턴스
            System.out.println("discountCode = " + discountCode);
            System.out.println("discountPolicy = " + discountPolicy);

            // return 값은 int
            return discountPolicy.discount(member, price);
        }
    }

}
