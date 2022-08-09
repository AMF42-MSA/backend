package com.everylecture.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import com.everylecture.domain.entity.MemberEntity;

import com.everylecture.domain.dto.MemberDto;
import com.everylecture.service.MemberService;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/")    // member-service"
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/members")
    public ResponseEntity createMember(@RequestBody MemberDto member) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        MemberDto memberDto = mapper.map(member, MemberDto.class);
        memberService.createMember(memberDto);
        //ResponseUser responseUser = mapper.map(memberDto, ResponseUser.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(memberDto);   // return with 201
    }

    @GetMapping("/members")
    public ResponseEntity<List<MemberDto>> getMembers() {
        Iterable<MemberEntity> userList = memberService.getMemberByAll();

        List<MemberDto> result = new ArrayList<>();

        userList.forEach(u -> {
            result.add(new ModelMapper().map(u, MemberDto.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping(value = "/members/{memberId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MemberDto> getMember(@PathVariable("memberId") Long memberId) {
        MemberDto memberDto = memberService.getMemberByMemberId(memberId);

        return ResponseEntity.status(HttpStatus.OK).body(new ModelMapper().map(memberDto, MemberDto.class));
    }

}
