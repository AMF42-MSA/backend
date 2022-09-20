package com.everyoneslecture.member.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.everyoneslecture.member.domain.member.dto.MemberDto;
import com.everyoneslecture.member.domain.member.dto.RequestMember;
import com.everyoneslecture.member.domain.member.entity.MemberEntity;
import com.everyoneslecture.member.service.MemberService;
import com.fasterxml.jackson.core.JsonProcessingException;

import io.micrometer.core.annotation.Timed;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.ErrorMessage;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.net.URISyntaxException;
import java.util.ArrayList;

@RestController
@RequestMapping("/")    // member-service"
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) throws Exception {
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
    public ResponseEntity createMember(@RequestBody RequestMember requestMember)
        throws URISyntaxException, JsonProcessingException, InterruptedException, ExecutionException {

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        MemberDto memberDto = mapper.map(requestMember, MemberDto.class);
        try {
            memberService.createMember(memberDto);
        } catch (Exception e) {
            return new ResponseEntity<>("이미 가입된 Email 입니다.",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(memberDto);   // return with 201
    }



    @GetMapping("/members")
    public ResponseEntity<List<MemberDto>> getMembers()
        throws URISyntaxException, JsonProcessingException, InterruptedException, ExecutionException{

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);

        Iterable<MemberEntity> userList = memberService.getMemberByAll();

        List<MemberDto> result = new ArrayList<>();

        userList.forEach(u -> {
            result.add(mapper.map(u, MemberDto.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(value = "/members/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Optional<MemberEntity>> getMember(@PathVariable("id") Long id)
        throws URISyntaxException, JsonProcessingException, InterruptedException, ExecutionException{

        Optional<MemberEntity> memberEntity = memberService.getMemberById(id);
        return ResponseEntity.status(HttpStatus.OK).body(memberEntity);
        //return ResponseEntity.status(HttpStatus.OK).body(new ModelMapper().map(memberDto, MemberDto.class));
    }

    @GetMapping(value = "/members/totalcount")
    public Long getMemberCnt()
        throws URISyntaxException, JsonProcessingException, InterruptedException, ExecutionException{

        Long result = memberService.getMemberCount();

        return result;
        //return ResponseEntity.status(HttpStatus.OK).body(new ModelMapper().map(memberDto, MemberDto.class));
    }

    @GetMapping("/welcome")
    @Timed(value = "users.welcome", longTask = true)
    public String welcome() {
//        return env.getProperty("greeting.message");
        return "Welcome 모두의강의!!";
    }


}
