package kr.sanus.news.member;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String joinForm(Model model) {
        model.addAttribute("member",new MemberDto());
        return "member/joinForm";
    }

    @PostMapping("/join")
    public String join(@Valid @ModelAttribute("member") MemberDto memberDto,
        BindingResult bindingResult) {

        if (memberDto.getPassword() == null || memberDto.getPassword2() == null) {
            bindingResult.reject("password is null", "비밀번호를 입력하세요");
        }

        if (bindingResult.hasErrors()) {
            return "member/joinForm";
        }

        try {
            memberService.join(memberDto);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("joinFailed", "이미 등록된 사용자 입니다.");
            return "member/joinForm";
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("MemberDto") MemberDto memberDto) {
        return "member/loginForm";
    }

    @GetMapping("/detail")
    public String detail(Model model) {
        model.addAttribute("member",memberService.findByEmail());
        return "member/detail";
    }

    @GetMapping("/edit")
    public String editForm(Model model) {
        model.addAttribute("member",memberService.findByEmail());
        return "member/editForm";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute("member")MemberDto memberDto,
        BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "member/editForm";
        }

        memberService.update(memberDto);
        return "redirect:/member/detail";
    }

}

