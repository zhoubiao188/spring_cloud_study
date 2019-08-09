/**
 * @program Resilience4j
 * @description:
 * @author: zhoubiao
 * @create: 2019/08/09 12:40
 */

package cn.com.scitc;

import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.BulkheadConfig;
import io.github.resilience4j.bulkhead.BulkheadRegistry;
import io.vavr.CheckedFunction0;
import io.vavr.control.Try;
import org.junit.Test;

public class BulkheadTest {

    @Test
    public void fn1() {
        BulkheadConfig config = BulkheadConfig.custom()
                .maxConcurrentCalls(150)
                .maxWaitTime(100)
                .build();
        BulkheadRegistry registry = BulkheadRegistry.of(config);
        Bulkhead bulkhead1 = registry.bulkhead("foo");
        BulkheadConfig custom = BulkheadConfig.custom()
                .maxWaitTime(0).build();


        Bulkhead bulkhead2 = registry.bulkhead("bar", custom);
        System.out.println(bulkhead1 + ">>>>>" + bulkhead2);
    }

    @Test
    public void fn2() {
        Bulkhead bulkhead1 = Bulkhead.ofDefaults("foo");
        Bulkhead bulkhead2 = Bulkhead.of("bar",
                BulkheadConfig.custom().maxConcurrentCalls(50).build());

        System.out.println(bulkhead1 + ">>>>>" + bulkhead2);
    }

    @Test
    public void fn3() {
        BulkheadConfig config = BulkheadConfig.custom()
                .maxConcurrentCalls(1)
                .maxWaitTime(100)
                .build();
        Bulkhead bulkhead = Bulkhead.of("testName", config);
        CheckedFunction0<String> decoratedSupplier = Bulkhead.decorateCheckedSupplier(
                bulkhead,()-> "this is bulkhead: love "
        );

        Try<String> result = Try.of(decoratedSupplier).map(
                value -> value + "小蕾"
        );

        System.out.println(result.isSuccess());
        System.out.println(result.get());
    }

}
