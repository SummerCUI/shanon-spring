package us.codecraft.ioc.aop;

import org.junit.Test;

/**
 * @author cuixianing。
 * @version v1.0.0.1。
 * @Description //TODO
 * @since JDK1.8。
 * <p>创建日期：2019年11月12日 14:06。</p>
 */
public class AspectjExpressionPoitcutTest {

    @Test
    public void testClassFilter() {
        String expression = "execution(* us.codecraft.ioc.*.*(..))";

    }
}
