package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // http get method와 같은 것. /hello 를 받으면 이 메소드 실행
    @GetMapping("hello")
    // spring이 model을 만들어서 넘겨줌.
    public String hello(Model model){
        model.addAttribute("data", "Hello!!");
        // resources - templates - hello(.html)를 찾아서 model을 렌더링
        // viewResolver가 처리하는 것
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        //model에 담아서 View에서 받아 랜더링 한다.
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-spring")
    @ResponseBody // html의 body 태그가 아니고, http 통신의 body부에 return을 넣어준다는 것
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;
        // ctrl+n : getter setter generator 시 사용
        // Java Bean 표준 방식. property 접근 방식.
        // private 객체를 외부에서 접근하기 위해서 사용함.
        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name = name;
        }
    }
}
