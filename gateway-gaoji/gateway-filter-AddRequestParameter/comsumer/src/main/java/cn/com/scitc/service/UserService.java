package cn.com.scitc.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient("provider")
public interface UserService {
    @RequestMapping(value = "/rest/SaveUser",method = RequestMethod.POST)
    ResponseEntity<Object> SaveUser(@RequestBody Map<String,Object> user);
}
