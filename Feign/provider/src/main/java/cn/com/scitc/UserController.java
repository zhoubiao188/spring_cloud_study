/**
 * @program Feign
 * @description:
 * @author: zhoubiao
 * @create: 2019/08/01 14:25
 */

package cn.com.scitc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @PostMapping("/user")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) {
        logger.info("provider提供了addUser服务");
        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
    }

    @PutMapping("/user")
    public ResponseEntity<Object> updateUser(@RequestBody UserDTO userDTO) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("name",userDTO.getNickname());
        map.put("id", userDTO.getId());
        return new ResponseEntity<Object>(map,HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<UserDTO> getUserDTOByName(@RequestParam String name) {
        UserDTO userDTO = new UserDTO();
        userDTO.setNickname(name);
        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Integer> deleteUserDTOById(@PathVariable Integer id) {
        return new ResponseEntity<Integer>(id, HttpStatus.OK);
    }
}
