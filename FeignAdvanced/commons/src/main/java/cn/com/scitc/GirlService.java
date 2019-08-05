package cn.com.scitc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface GirlService {
    @GetMapping("/girl")
    String girl(@RequestParam("name") String name);
}
