package study.learningspring;

import study.learningspring.member.Grade;
import study.learningspring.member.Member;
import study.learningspring.member.MemberService;
import study.learningspring.member.MemberServiceImpl;
import study.learningspring.order.Order;
import study.learningspring.order.OrderService;
import study.learningspring.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);
    }
}
