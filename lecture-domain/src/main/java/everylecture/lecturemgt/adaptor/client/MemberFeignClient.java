package everylecture.lecturemgt.adaptor.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import everylecture.lecturemgt.config.feign.FeignConfiguration;
import everylecture.lecturemgt.controller.dto.MemberInfoDTO;

@FeignClient(name= "member", url="http://member:8080", configuration = {FeignConfiguration.class})
public interface MemberFeignClient {
    @GetMapping("/member/members/{memberId}")
    ResponseEntity<MemberInfoDTO> findById(@PathVariable("memberId") int id);
}
