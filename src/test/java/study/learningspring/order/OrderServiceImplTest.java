package study.learningspring.order;

import org.junit.jupiter.api.Test;
import study.learningspring.discount.FixDiscountPolicy;
import study.learningspring.member.Grade;
import study.learningspring.member.Member;
import study.learningspring.member.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "memberA", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);

        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}