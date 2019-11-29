package us.codecraft.ioc.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * @author cuixianing。
 * @version v1.0.0.1。
 * @Description 代理相关元数据
 * @since JDK1.8。
 * <p>创建日期：2019年11月21日 18:58。</p>
 */
public class AdvisedSupport {

    private TargetSource targetSource;

    private MethodInterceptor methodInterceptor;

    private MethodMatcher methodMatcher;

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}
