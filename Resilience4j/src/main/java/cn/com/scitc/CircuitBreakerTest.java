/**
 * @program Resilience4j
 * @description:
 * @author: zhoubiao
 * @create: 2019/08/05 18:01
 */

package cn.com.scitc;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.vavr.CheckedFunction0;
import io.vavr.CheckedFunction1;
import io.vavr.control.Try;
import org.junit.Test;

import java.time.Duration;


public class CircuitBreakerTest {
    /**
     * @description: CircuitBreaker 对象创建方法
     * @param:
     * @return:
     * @author: zhoubiao
     * @date: 2019-08-05
    */
    @Test
    public void fn1() {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(50) //设置故障率 50%
                .waitDurationInOpenState(Duration.ofMillis(1000))//断路器打开保持时间2秒
                .ringBufferSizeInHalfOpenState(2)//环形缓冲区大小为2
                .ringBufferSizeInClosedState(2)//关闭状态时候，环形缓冲区大小为2
                .build();
        CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.of(circuitBreakerConfig);
        CircuitBreaker circuitBreaker2 = circuitBreakerRegistry.circuitBreaker("otherName");//创建断路器1
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("uniqueName",circuitBreakerConfig);//创建断路器2
    }

    /**
     * @description: 断路器的使用
     * @param:
     * @return:
     * @author: zhoubiao
     * @date: 2019-08-05
    */
    @Test
    public void fn2() {
        CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("testName");
        CheckedFunction0<String> decoratedSupplier = CircuitBreaker.decorateCheckedSupplier(
                circuitBreaker,() -> "hello this is circuitBreaker"
        );
        Try<String> result = Try.of(decoratedSupplier)
                .map(value ->  value + ": world");
        System.out.println(result.isSuccess());
        System.out.println(result.get());
    }

    /**
     * @description: 将不同的断路器连接起来
     * @param:
     * @return:
     * @author: zhoubiao
     * @date: 2019-08-05
    */
    @Test
    public void fn3() {
        CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("testName"); //创建断路器1
        CircuitBreaker anotherBreaker = CircuitBreaker.ofDefaults("anotherTestName");//创建断路器2
        CheckedFunction0<String> decoratedSupplier = CircuitBreaker.decorateCheckedSupplier( //使用的是装饰者模式
                circuitBreaker,() -> "love:"
        );

        CheckedFunction1<String,String> decoratedFunction = CircuitBreaker.decorateCheckedFunction(
                anotherBreaker,(input) -> input + "小蕾"
        );
        Try<String> result = Try.of(decoratedSupplier)
                .mapTry(decoratedFunction::apply);
        System.out.println(result.isSuccess());
        System.out.println(result.get());

    }

    /**
     * @description: 断路器开启时,这里会发生报错
     * @param:
     * @return:
     * @author: zhoubiao
     * @date: 2019-08-05
    */
    @Test
    public void fn4() {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .ringBufferSizeInClosedState(2)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .build();
        CircuitBreaker circuitBreaker = CircuitBreaker.of("testName",circuitBreakerConfig);
        circuitBreaker.onError(0, new RuntimeException());
        System.out.println(circuitBreaker.getState());

        circuitBreaker.onError(0, new RuntimeException());
        System.out.println(circuitBreaker.getState());
        Try<String> result = Try.of(CircuitBreaker.decorateCheckedSupplier(
                circuitBreaker,() -> "love:"
        )).map(value -> value + "小蕾");
        System.out.println(result.isSuccess());
        System.out.println(result.get()); }

    /**
     * @description: 服务器请求降级
     * @param:
     * @return:
     * @author: zhoubiao
     * @date: 2019-08-05
    */
    @Test
    public void fn5() {
        CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("testName");
        CheckedFunction0<String> decoratedSupplier = CircuitBreaker.decorateCheckedSupplier(
                circuitBreaker,()-> {
                    throw  new RuntimeException("BAM");
                }
        );

        Try<String> result = Try.of(decoratedSupplier)
                .recover(throwable -> "Hello Recovery");
        System.out.println(result.isSuccess());
        System.out.println(result.get());
    }


    /**
     * @description: 状态监听
     * @param:
     * @return:
     * @author: zhoubiao
     * @date: 2019-08-05
    */
    @Test
    public void fn6() {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .ringBufferSizeInClosedState(2)
                .ringBufferSizeInHalfOpenState(2)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .recordFailure(e -> (e instanceof ArithmeticException))
                .build();
        CircuitBreaker circuitBreaker = CircuitBreaker.of("testName", circuitBreakerConfig);
        //创建状态监听
        CircuitBreaker.Metrics metrics = circuitBreaker.getMetrics();
        float failureRate = metrics.getFailureRate();
        int buffredCalls = metrics.getNumberOfBufferedCalls();
        int failedCalls = metrics.getNumberOfFailedCalls();

        System.out.println(failureRate);
        System.out.println(buffredCalls);
        System.out.println(failedCalls);
    }

}
