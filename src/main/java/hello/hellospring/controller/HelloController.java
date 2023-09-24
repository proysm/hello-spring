package hello.hellospring.controller;

import com.fasterxml.jackson.annotation.JsonSetter;
import jdk.jfr.DataAmount;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model){ // 스프링부트에서 model을 넣어준다.
        model.addAttribute("data", "hello!?"); // key: data, 값: hello!?
        return "hello"; // return 의 이름이 hello 이므로, templates 폴더에 있는 hello.html 로 간다.
    }

    @GetMapping("/hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("/hello-string")
    @ResponseBody // 직접 Json 형태로 반환하겠다.
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


    static class Hello{
        private String name;

        public String getName() {
            return name;
        }
        public void setName(String name){
            this.name = name;
        }
    }

    
}
