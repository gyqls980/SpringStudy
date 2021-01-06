package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        //MemberService memberService = new MemberServiceImpl();

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
        // memberService에는 MemberServiceImpl이 들어가게 됨.

        // spring 객체들을 모두 관리해주는 컨테이너.
        // 모든게 ApplicationContext가 관리해주고 이걸로 시작된다.
        // AnnotationConfigApplicationContext : AppConfig가 이거 기반으로 생성되어 있음.
        // AppConfig 환경 설정 정보를 기반으로 객체를 관리해준다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // 이름(AppConfig의 메소드 명으로 주로 생성. @Bean(name="mm") 이라고 했으면 mm이라고 한다.), 반환 타입 명
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName());

    }
}
