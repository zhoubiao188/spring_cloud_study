package cn.com.scitc.service;

import cn.com.scitc.UserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("rest-provider-1")
public interface FeignService {
    @GetMapping("/getUser")
    ResponseEntity<Object> getUser(@RequestBody UserVo user);
}
