package study.learningspring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import study.learningspring.member.Grade;
import study.learningspring.member.Member;
import study.learningspring.member.MemberService;
import study.learningspring.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        // ApplicationContext를 스프링 컨테이너라 하고, 이것은 인터페이스다.
        // AnnotationConfigApplicationContext 는 ApplicationContext의 구현체이다.
        // AppConfig에 있는 설정 정보를 가져다 사용할 수 있도록 해줌
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // .getBean(빈 이름, 타입.class)
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member foundMember = memberService.findMember(1L);
        System.out.println("member = " + member.getName());
        System.out.println("foundMember = " + foundMember.getName());
    }
}
