package study.learningspring.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {
    // Ctrl + B 로 해당 어노테이션을 사용중인 코드를 추적 가능

    // @Qualifier("빈 이름")으로 빈 중복은 막았지만 이 경우 단순히 타입이 아닌 문자로 빈을 찾는 것이기 때문에
    // 컴파일시 타입 체크가 불가능하다. 예를들어 오타가 발생했을 때 어떤 오류인지 알 수다 없다
    // 사용자가 직접 만든 어노테이션 코드 위에 @Qualifier("등록할 이름")을 작성해주고
    // 빈을 불러올 파라미터에 해당 어노테이션을 작성하면 @Qualifier 의 역할도 똑같이 수행하고 혹시 모를 사용자의 실수를 방지한다

}
