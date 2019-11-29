package us.codecraft.ioc.aop;

import org.aopalliance.aop.Advice;

/**
 * @author cuixianing。
 * @version v1.0.0.1。
 * @Description
 * @since JDK1.8。
 * <p>创建日期：2019年11月29日 15:07。</p>
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor{

   private AspectjExpressionPointcut pointcut = new AspectjExpressionPointcut();

   private Advice advice;

    public void setExpression(String expression) {
        this.pointcut.setExpression(expression);
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }
}
