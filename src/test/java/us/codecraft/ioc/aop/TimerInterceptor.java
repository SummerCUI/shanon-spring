package us.codecraft.ioc.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author cuixianing。
 * @version v1.0.0.1。
 * @Description
 * @since JDK1.8。
 * <p>创建日期：2019年11月27日 15:33。</p>
 */
public class TimerInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        long l = System.nanoTime();
        System.out.println("Invoctin of Method " + invocation.getMethod().getName() + " start!");
        Object proceed = invocation.proceed();
        System.out.println("Invocation of Method " + invocation.getMethod().getName() + " end! takes " + (System.nanoTime() - l));
        return proceed;
    }
}
