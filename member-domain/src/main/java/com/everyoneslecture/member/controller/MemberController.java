package com.everyoneslecture.member.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.everyoneslecture.member.domain.dto.MemberDto;
import com.everyoneslecture.member.domain.dto.RequestMember;
import com.everyoneslecture.member.domain.entity.MemberEntity;
import com.everyoneslecture.member.service.MemberService;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.micrometer.core.annotation.Timed;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.net.URISyntaxException;
import java.util.ArrayList;

@RestController
@RequestMapping("/")    // member-service"
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;

        MemberDto memberDto = new MemberDto();
        memberDto.setEmail("admin@sk.com");
        memberDto.setPwd("1111");
        memberDto.setMemberType("ROLE_ADMIN");
        memberDto.setName("테스트관리자");
        memberService.createMember(memberDto);

    }

    //@PostMapping(value = {"/signup", "/admin/members"})
    @PostMapping("/signup")
    public ResponseEntity createMember(@RequestBody RequestMember requestMember) throws URISyntaxException, JsonProcessingException, InterruptedException, ExecutionException {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        MemberDto memberDto = mapper.map(requestMember, MemberDto.class);
        memberService.createMember(memberDto);
        //ResponseUser responseUser = mapper.map(memberDto, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(memberDto);   // return with 201
    }

    @GetMapping("/members")
    public ResponseEntity<List<MemberDto>> getMembers() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

        Iterable<MemberEntity> userList = memberService.getMemberByAll();

        List<MemberDto> result = new ArrayList<>();

        userList.forEach(u -> {
            result.add(mapper.map(u, MemberDto.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(value = "/members/{memberId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MemberDto> getMember(@PathVariable("memberId") Long memberId) {
        MemberDto memberDto = memberService.getMemberByMemberId(memberId);

        return ResponseEntity.status(HttpStatus.OK).body(new ModelMapper().map(memberDto, MemberDto.class));
    }

    @GetMapping("/welcome")
    @Timed(value = "users.welcome", longTask = true)
    public String welcome() {
//        return env.getProperty("greeting.message");
        return "Welcome 모두의강의!!";
    }


}
