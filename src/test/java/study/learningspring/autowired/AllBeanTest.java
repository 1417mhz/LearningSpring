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
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        int fixDiscountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(fixDiscountPrice).isEqualTo(1000);

        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
        assertThat(rateDiscountPrice).isEqualTo(2000);
    }

    static class DiscountService {
        // DiscountPolicy 타입으로 등록되어있는 빈들이 Map, List 타입으로 담긴다
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policyList;

//        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policyList) {
            this.policyMap = policyMap;
            this.policyList = policyList;
            // Map 은 키:값 형태로 빈 이름:빈 인스턴스 형태로 출력됨
            System.out.println("policyMap = " + policyMap);
            // List 는 스프링 빈 인스턴스가 출력됨
            System.out.println("policyList = " + policyList);
        }

        // 다형성을 이용한 유연함 발휘
        public int discount(Member member, int price, String discountCode) {
            // 인자로 넘어온 discount 정책 이름에 따라 Map 에서 해당 빈을 가져와 로직을 실행시킨다
            DiscountPolicy discountPolicy = policyMap.get(discountCode);

            System.out.println("discountCode = " + discountCode);
            System.out.println("discountPolicy = " + discountPolicy);

            return discountPolicy.discount(member, price);
        }
    }

}
