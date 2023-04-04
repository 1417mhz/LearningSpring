package study.learningspring.member;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    // Lombok 라이브러리가 final 키워드가 붙은 필드들을 모아서 생성자를 자동으로 만들어준다
    private final MemberRepository memberRepository;

//    @Autowired
//    public MemberServiceImpl(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // Test
//    public MemberRepository getMemberRepository() {
//        return memberRepository;
//    }

}
