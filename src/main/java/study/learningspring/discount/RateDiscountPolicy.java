package study.learningspring.discount;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import study.learningspring.annotation.MainDiscountPolicy;
import study.learningspring.member.Grade;
import study.learningspring.member.Member;

@Component
//@Primary // 동일한 타입의 빈이 2개 이상 등록되었을 경우 @Primary 를 통해 우선권을 부여한다
// @Qualifier("mainDiscountPolicy")
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
