package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        Object memberService = ac.getBean("memberService",
                MemberService.class);
        // memberService가 MemberServiceImpl의 객체인지 확인
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
    @Test
    @DisplayName("이름 없이 타입만으로 조회")
    // 같은 타입의 빈이 여러개이면 곤란할 수 있다
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
    @Test
    @DisplayName("구체 타입으로 조회")
    // 위에는 인터페이스 명으로 조회했지만 바로 스프링 컨테이너에 있는 객체(인스턴스 타입)로도 조회가 된다.
    // 이건 역할이 아닌 구현에 의존한 것이기에 좋은 경우는 아니다.
    // 구체 타입으로 조회하면 유연성이 떨어짐.
    void findBeanByName2() {
        MemberServiceImpl memberService = ac.getBean("memberService",
                MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNameX() {
        //ac.getBean("xxxxx", MemberService.class);
        // junit의 Assertions가 제공
        // () -> ac.getBean("xxxxx", MemberService.class)를 실행했을때 왼쪽 예외가 발생해야 성공한 것.
        assertThrows(NoSuchBeanDefinitionException.class, () ->
                ac.getBean("xxxxx", MemberService.class));
    }

}
