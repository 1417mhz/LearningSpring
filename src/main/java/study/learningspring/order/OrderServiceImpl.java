package study.learningspring.order;

import study.learningspring.discount.DiscountPolicy;
import study.learningspring.discount.FixDiscountPolicy;
import study.learningspring.discount.RateDiscountPolicy;
import study.learningspring.member.Member;
import study.learningspring.member.MemberRepository;
import study.learningspring.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        // 회원 정보 조회
        Member member = memberRepository.findById(memberId);

        // 회원 정보를 넘겨서 할인값을 받아옴
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
