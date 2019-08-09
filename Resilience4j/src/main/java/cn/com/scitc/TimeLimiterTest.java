/**
 * @program Resilience4j
 * @description:
 * @author: zhoubiao
 * @create: 2019/08/09 13:29
 */

package cn.com.scitc;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import io.vavr.control.Try;
import org.junit.Test;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;
import org.junit.Test;

public class TimeLimiterTest {

    @Test
    public void fn1() throws Exception {
        TimeLimiterConfig config = TimeLimiterConfig.custom()
                .timeoutDuration(Duration.ofSeconds(60))
                .cancelRunningFuture(true)
                .build();
        TimeLimiter timeLimiter = TimeLimiter.of(config);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Supplier<Future<Integer>> futureSupplier = () -> executorService.submit(userService::doSomething);
        Callable restrictedCall = TimeLimiter
                .decorateFutureSupplier(timeLimiter, futureSupplier);
        Try.of(restrictedCall.call)
                .onFailure(throwable -> System.out.println(throwable.getMessage()));
    }
}

