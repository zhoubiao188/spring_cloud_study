/**
 * @program Resilience4j
 * @description:
 * @author: zhoubiao
 * @create: 2019/08/09 13:11
 */

package cn.com.scitc;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.vavr.CheckedFunction0;
import io.vavr.control.Try;
import org.junit.Test;

import java.time.Duration;
import java.util.Date;

public class RetryTest {
    @Test
    public void fn1A() {
        RetryConfig config = RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofMillis(500))
                .build();
        Retry retry = Retry.of("id", config);

        CheckedFunction0<String> retryAllSupplier = Retry.decorateCheckedSupplier(
                retry, () -> {
                    //这个里面就可以执行重试的业务逻辑
                    System.out.println("date:" + new Date() + ":" + Math.random());
                    return "love 小蕾";
                });

        Try<String> result = Try.of(retryAllSupplier).recover((throwable -> "Hello world from recovery function"));
        System.out.println(result.isSuccess());
        System.out.println(result.get());
    }
}
