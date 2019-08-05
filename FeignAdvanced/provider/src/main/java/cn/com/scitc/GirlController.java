/**
 * @program FeignAdvanced
 * @description:
 * @author: zhoubiao
 * @create: 2019/08/05 09:30
 */

package cn.com.scitc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GirlController  implements GirlService {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public String girl(String name) {

        log.info("provider提供girl的服务");
        return "love" + name + "!";
    }
}
