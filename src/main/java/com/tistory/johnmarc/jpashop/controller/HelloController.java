package com.tistory.johnmarc.jpashop.controller;

import com.tistory.johnmarc.jpashop.domain.Address;
import com.tistory.johnmarc.jpashop.domain.Member;
import com.tistory.johnmarc.jpashop.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HelloController {

    private final MemberRepository memberRepository;

    public HelloController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("hello")
    public String hello(Model model) {
        log.info("Hello Controller!");
        Member member = Member.builder()
                .address(new Address("Seoul", "Mangwon", "1234"))
                .name(RandomString.make()).build();
        this.memberRepository.save(member);
        System.out.println(member);
        model.addAttribute("data", "Hello!!!");
        return "greeting";
    }
}
