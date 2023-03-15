package study.learningspring;

import study.learningspring.discount.DiscountPolicy;
import study.learningspring.discount.FixDiscountPolicy;
import study.learningspring.member.MemberRepository;
import study.learningspring.member.MemberService;
import study.learningspring.member.MemberServiceImpl;
import study.learningspring.member.MemoryMemberRepository;
import study.learningspring.order.OrderService;
import study.learningspring.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }

    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
