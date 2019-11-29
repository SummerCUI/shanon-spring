package us.codecraft.ioc.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author cuixianing。
 * @version v1.0.0.1。
 * @Description cglib2aop 代理
 * @since JDK1.8。
 * <p>创建日期：2019年11月27日 15:40。</p>
 */
public class Cglib2AopProxy extends AbstractAopProxy{

    public Cglib2AopProxy(AdvisedSupport advisedSupport) {
        super(advisedSupport);
    }

    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(advisedSupport.getTargetSource().getTargetClass());
        enhancer.setInterfaces(advisedSupport.getTargetSource().getInterfaces());
        enhancer.setCallback(new DynamicAdvisedInterceptor(advisedSupport));
        Object enhanced = enhancer.create();
        return enhanced;
    }

    private static class DynamicAdvisedInterceptor implements MethodInterceptor {
        private AdvisedSupport advisedSupport;

        private org.aopalliance.intercept.MethodInterceptor delegateMethodInterceptor;

        public DynamicAdvisedInterceptor(AdvisedSupport advisedSupport) {
            this.advisedSupport = advisedSupport;
            this.delegateMethodInterceptor = advisedSupport.getMethodInterceptor();
        }


        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            if (advisedSupport.getMethodMatcher() == null
                || advisedSupport.getMethodMatcher().matches(method, advisedSupport.getTargetSource().getTargetClass())) {
                return delegateMethodInterceptor.invoke(new CglibMethodInvocation(advisedSupport.getTargetSource().getTarget(), method, objects, methodProxy));
            }
            return new CglibMethodInvocation(advisedSupport.getTargetSource().getTarget(), method, objects, methodProxy).proceed();
        }
    }

    private static class CglibMethodInvocation extends ReflectiveMethodInvocation {

        private final MethodProxy methodProxy;

        public CglibMethodInvocation(Object target, Method method, Object[] args, MethodProxy methodProxy) {
            super(target, method, args);
            this.methodProxy = methodProxy;
        }

        @Override
        public Object proceed() throws Throwable {
            return this.methodProxy.invoke(this.target, this.arguments);
        }
    }
}
