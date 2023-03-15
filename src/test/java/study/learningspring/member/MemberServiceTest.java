package study.learningspring.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import study.learningspring.AppConfig;

import static org.assertj.core.api.Assertions.*;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        // given
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when
        memberService.join(member);

        Member foundMember = memberService.findMember(1L);

        // then
        assertThat(member).isEqualTo(foundMember);

    }
}
