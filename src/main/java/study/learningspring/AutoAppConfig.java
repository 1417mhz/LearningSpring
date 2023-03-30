package study.learningspring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import study.learningspring.member.MemberRepository;
import study.learningspring.member.MemoryMemberRepository;

@Configuration
@ComponentScan(
        // AppConfig 등 스프링 설정 코드들을 제외하고 스캔하기 위함
        excludeFilters= @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    // 수동 빈 등록과 자동 빈 등록에서 빈 이름이 중복 될 경우 수동 빈 등록이 우선권을 가짐
    // 다만 최신 버전의 스프링부트는 빈 이름이 중복 될 경우 아예 실행이 종료된다 (옵션을 부여하여 오버라이딩이 가능하게 할 수도 있다)
//    @Bean(name = "memoryMemberRepository")
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }

}
