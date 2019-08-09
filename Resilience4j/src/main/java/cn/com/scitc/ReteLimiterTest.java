/**
 * @program Resilience4j
 * @description: 限流的基本使用
 * @author: zhoubiao
 * @create: 2019/08/05 22:26
 */

package cn.com.scitc;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.vavr.CheckedRunnable;
import io.vavr.control.Try;
import org.junit.Test;

import java.time.Duration;
import java.util.Date;

public class ReteLimiterTest {
    @Test
    public void fn1() {
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitRefreshPeriod(Duration.ofMillis(1000))
                .limitForPeriod(2)
                .timeoutDuration(Duration.ofMillis(1))
                .build();
        RateLimiterRegistry rateLimiterRegistry = RateLimiterRegistry.of(config);

        //创建限流
        RateLimiter rateLimiter = RateLimiter.of("NASDAQ :-)", config);

        CheckedRunnable restrictedCall = RateLimiter.decorateCheckedRunnable(rateLimiter, () -> {
            Date date = new Date();
            System.out.println(date);
        });

        rateLimiter.getEventPublisher().onSuccess(event -> {
            System.out.println(new Date() + ">>>" + event.getEventType() + ">>>" + event.getCreationTime());
        })

        .onFailure(event ->  {
            System.out.println(new Date() + ">>>" + event.getEventType() + ">>>" + event.getCreationTime());
        });

        //运行限流配置
        Try.run(restrictedCall)
                .andThenTry(restrictedCall )
                .andThenTry(restrictedCall)
                .andThenTry(restrictedCall)
                .onFailure(throwable -> System.out.println(throwable.getMessage()));
        //监听状态
        RateLimiter .Metrics metrics = rateLimiter.getMetrics();
        int numberOfThreadsWaitingForPermission = metrics.getNumberOfWaitingThreads();
        int availablePermissions = metrics.getAvailablePermissions();
        System.out.println(numberOfThreadsWaitingForPermission);
        System.out.println(availablePermissions);
    }
}
