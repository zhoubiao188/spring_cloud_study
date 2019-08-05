package cn.com.scitc;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("provider")
public interface FeignGirlService extends GirlService {

}
