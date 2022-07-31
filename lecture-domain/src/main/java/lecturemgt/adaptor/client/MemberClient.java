package lecturemgt.adaptor.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lecturemgt.config.feign.FeignConfiguration;
import lecturemgt.controller.dto.MemberInfoDTO;

@FeignClient(name= "member", configuration = {FeignConfiguration.class})
public interface MemberClient {
    @GetMapping("/member/members/{memberId}")
    ResponseEntity<MemberInfoDTO> findById(@PathVariable("memberId") int id);
}
