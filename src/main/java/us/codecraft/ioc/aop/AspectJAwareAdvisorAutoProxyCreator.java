package us.codecraft.ioc.aop;

import org.aopalliance.intercept.MethodInterceptor;
import us.codecraft.ioc.beans.BeanPostProcessor;
import us.codecraft.ioc.beans.factory.AbstractBeanFactory;
import us.codecraft.ioc.beans.factory.BeanFactory;

import java.util.List;

/**
 * @author cuixianing。
 * @version v1.0.0.1。
 * @Description
 * @since JDK1.8。
 * <p>创建日期：2019年11月29日 14:13。</p>
 */
public class AspectJAwareAdvisorAutoProxyCreator implements BeanPostProcessor, BeanFactoryAware {

    private AbstractBeanFactory abstractBeanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws Exception {
        this.abstractBeanFactory = (AbstractBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {

        if (bean instanceof AspectJExpressionPointcutAdvisor) {
            return bean;
        }

        if(bean instanceof MethodInterceptor) {
            return bean;
        }

        List<AspectJExpressionPointcutAdvisor> advisors = abstractBeanFactory.
                getBeansForType(AspectJExpressionPointcutAdvisor.class);

        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            if(advisor.getPointcut().getClassFilter().matches(bean.getClass())) {
                ProxyFactory advisorSupport = new ProxyFactory();
                advisorSupport.setMethodInterceptor( (MethodInterceptor) advisor.getAdvice());
                advisorSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());

                TargetSource targetSource = new TargetSource(bean, bean.getClass(), bean.getClass().getInterfaces());
                advisorSupport.setTargetSource(targetSource);

                return advisorSupport.getProxy();
            }
        }

        return bean;
    }
}
