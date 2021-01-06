package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// spring이 뜰 때, spring 컨테이너가 생긴다.
// 그 때 @Controller가 있으면 controller 객체(MemberController)를 생성함.
@Controller
public class MemberController {

    // private 나 final 하는 기준?
    // 다른 컨트롤러에서 MemberService 를 사용할 수 있다. 스프링 컨테이너에서 딱 하나만 생성해도 되서 new 사용하지 않는다.
    // 생성자를 이용한다.
    // private final MemberService memberService = new ~

    private final MemberService memberService;

    // 생성자 호출할 때 memberService를 컨테이너가 연결을 시켜준다? 먼소리야
    // MemberController가 생성될 때 spring Bean에 등록되어 있는 memberService 객체를 넣어준다 = DI
    // 근데 memberService에 오류가 뜬다 = memberService가 그냥 java class로 생성되어 있어서
    // -> memberService class에 @Service를 해줘야 한다.
    // -> MemoryMemberRepository에 @Repository를 해줘야 한다. = 이것들이 정형화된 모습.
    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    // html에서 post로 보내주기 때문에 PostMapping
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

//        System.out.println("member = " + member.getName());
        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
