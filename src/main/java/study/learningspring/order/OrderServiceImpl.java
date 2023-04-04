package study.learningspring.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import study.learningspring.discount.DiscountPolicy;
import study.learningspring.discount.FixDiscountPolicy;
import study.learningspring.discount.RateDiscountPolicy;
import study.learningspring.member.Member;
import study.learningspring.member.MemberRepository;
import study.learningspring.member.MemoryMemberRepository;

@Component
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // 필드 주입
    // 코드가 간결하다는 장점이 있으나 DI 프레임워크(스프링)이 없으면 아무것도 할 수 없다는 단점이 있다.
    // 위의 단점때문에 자바 코드만으로의 테스트가 불가능하다
//    @Autowired private MemberRepository memberRepository;
//    @Autowired private DiscountPolicy discountPolicy;

    // 수정자 주입(setter 주입)
    // 선택, 변경 가능성이 있는 의존관계에 사용된다.
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }

    @Autowired
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

    // Test
//    public MemberRepository getMemberRepository() {
//        return memberRepository;
//    }

}
