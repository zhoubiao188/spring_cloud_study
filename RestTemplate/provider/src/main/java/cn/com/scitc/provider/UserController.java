/**
 * @program RestTemplate
 * @description: 传递JSON
 * @author: zhoubiao
 * @create: 2019/07/29 09:57
 */

package cn.com.scitc.provider;

import cn.com.scitc.domain.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller
@ResponseBody
public class UserController {
    @PostMapping("/user")
    public ResponseEntity<Object> userDTO(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<Object>(userDTO, HttpStatus.OK);
    }

    @RequestMapping("/register")
    public String register(UserDTO userDTO) throws UnsupportedEncodingException {
        return "redirect:/loginPage?username=" + URLEncoder.encode(userDTO.getNickname(),"UTF-8") + "&address=" + URLEncoder.encode(userDTO.getAddress(),"UTF-8");
    }

    @GetMapping("/loginPage")
    @ResponseBody
    public String loginPage(UserDTO userDTO) {
        return "loginPage:" + userDTO.getNickname() + ":" + userDTO.getAddress();
    }


    @PutMapping("/user/name")
    @ResponseBody
    public void updateUserDTOByNickname(UserDTO userDTO) {
        System.out.println(userDTO);
    }

    @PutMapping("/user/address")
    @ResponseBody
    public void  updateUserDTOByAddress(@RequestBody  UserDTO userDTO) {
        System.out.println(userDTO);
    }

    @DeleteMapping("/user/{id}")
    @ResponseBody
    public void deleteUserById(@PathVariable Integer id) {
        System.out.println(id);
    }

    @DeleteMapping("/user/")
    @ResponseBody
    public void deleteUserByNickname(String nickname) {
        System.out.println(nickname);
    }

    @GetMapping("diyheader")
    public String diyHeader(HttpServletRequest request) {
        return request.getHeader("cookie");
    }
}
