/**
 * @program Resilience4J-SpringBoot
 * @description:
 * @author: zhoubiao
 * @create: 2019/08/09 16:51
 */

package cn.com.scitc;

import java.util.function.Predicate;

public class RecordFailurePredicate implements Predicate {
    @Override
    public boolean test(Object o) {
        return true;
    }
}
