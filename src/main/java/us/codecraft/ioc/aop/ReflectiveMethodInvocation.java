package us.codecraft.ioc.aop;
import java.lang.reflect.AccessibleObject;
import	java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;

/**
 * @author cuixianing。
 * @version v1.0.0.1。
 * @Description
 * @since JDK1.8。
 * <p>创建日期：2019年11月27日 15:57。</p>
 */
public class ReflectiveMethodInvocation implements MethodInvocation {

    protected Object target;

    protected Method method;

    protected Object [] arguments;

    public ReflectiveMethodInvocation(Object target, Method method, Object[] arguments) {
        this.target = target;
        this.method = method;
        this.arguments = arguments;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getArguments() {
        return arguments;
    }

    @Override
    public Object proceed() throws Throwable {
        return method.invoke(target, arguments);
    }

    @Override
    public Object getThis() {
        return target;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return method;
    }
}
