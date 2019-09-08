/**
 * @program spring-cloud-gateway-eureka
 * @description: ProviderController
 * @author: zhoubiao
 * @create: 2019/09/08 11:21
 */

package cn.com.scitc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @RequestMapping(value = "/getUser",method = RequestMethod.POST)
    public ResponseEntity<Object> getUser(@RequestBody UserVo user) {
        log.info("provider中到getUser服务被调用了");
        user.setAddress(user.getAddress());
        user.setAge(user.getAge());
        user.setJob(user.getJob());
        user.setNickname(user.getNickname());
        return new ResponseEntity<Object>(user, HttpStatus.OK);
    }
}
