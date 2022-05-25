package com.example.godtudy.domain.validator;

import com.example.godtudy.domain.member.MemberRepository;
import com.example.godtudy.domain.member.dto.MemberJoinForm;
import com.example.godtudy.domain.member.entity.SubjectEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.yaml.snakeyaml.util.EnumUtils;

@Component
@RequiredArgsConstructor
public class StudentJoinFormValidator implements Validator {

    private final MemberRepository memberRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(MemberJoinForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MemberJoinForm memberJoinForm = (MemberJoinForm) target;

        if (memberRepository.existsByUsername(memberJoinForm.getUsername())) {
            errors.rejectValue("username", "invalid username", new Object[]{memberJoinForm.getUsername()}, "이미 사용중인 아이디 입니다.");
        }
        if (memberRepository.existsByEmail(memberJoinForm.getEmail())) {
            errors.rejectValue("email", "invalid email", new Object[]{memberJoinForm.getEmail()}, "이미 인증된 이메일 입니다.");
        }
        if (memberRepository.existsByNickname(memberJoinForm.getNickname())) {
            errors.rejectValue("nickname", "invalid nickname", new Object[]{memberJoinForm.getNickname()}, "이미 사용중인 닉네임 입니다.");
        }

    }
}
