package us.codecraft.ioc;

import us.codecraft.ioc.beans.BeanPostProcessor;

/**
 * @author cuixianing。
 * @version v1.0.0.1。
 * @Description
 * @since JDK1.8。
 * <p>创建日期：2019年12月02日 15:40。</p>
 */
public class BeanInitializeLogger implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        System.out.println("Initialize bean " + beanName + " start!");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        System.out.println("Initialize bean " + beanName + " end!");
        return bean;
    }
}
