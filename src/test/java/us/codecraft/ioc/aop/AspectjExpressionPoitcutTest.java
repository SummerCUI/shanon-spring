package us.codecraft.ioc.aop;

import org.junit.Assert;
import org.junit.Test;
import us.codecraft.ioc.HelloWorldService;
import us.codecraft.ioc.HelloWorldServiceImpl;

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
        AspectjExpressionPointcut aspectjExpressionPointcut = new AspectjExpressionPointcut();
        aspectjExpressionPointcut.setExpression(expression);
        boolean matches = aspectjExpressionPointcut.getClassFilter().matches(HelloWorldService.class);
        ClassFilter classFilter = aspectjExpressionPointcut.getClassFilter();
        Assert.assertTrue(matches);
    }

    @Test
    public void testMethodInterceptor() throws NoSuchMethodException {
        String expression = "execution(* us.codecraft.ioc.*.*(..))";
        AspectjExpressionPointcut aspectjExpressionPointcut = new AspectjExpressionPointcut();
        aspectjExpressionPointcut.setExpression(expression);
        boolean helloWorld = aspectjExpressionPointcut.getMethodMatcher().matches(HelloWorldService.class.getDeclaredMethod("helloWorld"), HelloWorldServiceImpl.class);
        Assert.assertTrue(helloWorld);
    }
}
