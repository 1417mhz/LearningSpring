package study.learningspring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.learningspring.discount.DiscountPolicy;
import study.learningspring.discount.FixDiscountPolicy;
import study.learningspring.discount.RateDiscountPolicy;
import study.learningspring.member.MemberRepository;
import study.learningspring.member.MemberService;
import study.learningspring.member.MemberServiceImpl;
import study.learningspring.member.MemoryMemberRepository;
import study.learningspring.order.OrderService;
import study.learningspring.order.OrderServiceImpl;

// 스프링에서 관리하는 설정 정보임을 뜻하는 어노테이션
@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
