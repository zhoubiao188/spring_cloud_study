/**
 * @program FeignAdvanced
 * @description:
 * @author: zhoubiao
 * @create: 2019/08/05 09:37
 */

package cn.com.scitc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoveGirlController {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    FeignGirlService girlService;

    @GetMapping("/girl")
    public String girl(String name) {
        log.info("consumer调用了provider提供的girl服务");
        return girlService.girl(name);
    }
}
