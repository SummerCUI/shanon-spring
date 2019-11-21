package us.codecraft.ioc.beans.factory;

import us.codecraft.ioc.BeanReference;
import us.codecraft.ioc.aop.BeanFactoryAware;
import us.codecraft.ioc.beans.BeanDefinition;
import us.codecraft.ioc.beans.PropertyValue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author cuixianing。
 * @version v1.0.0.1。
 * @Description 可自动装配内容的BeanFactory
 * @since JDK1.8。
 * <p>创建日期：2019年11月21日 17:36。</p>
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

    protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {
        if(bean instanceof BeanFactoryAware) {
            BeanFactoryAware beanFactoryAware = (BeanFactoryAware) bean;
            beanFactoryAware.setBeanFactory(this);
        }

        for (PropertyValue propertyValue:
             beanDefinition.getPropertyValues().getPropertyValues()) {
            Object value = propertyValue.getValue();
            if(value instanceof BeanReference) {
                BeanReference beanReference = (BeanReference) value;
                value = getBean(beanReference.getName());
            }

            try {
                Method declaredMethod = bean.getClass().getDeclaredMethod("set" + propertyValue.getName().substring(0, 1).toUpperCase()
                        + propertyValue.getName().substring(1), value.getClass());

                declaredMethod.setAccessible(true);
                declaredMethod.invoke(bean, value);
            } catch (NoSuchMethodException e) {
                Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
                declaredField.setAccessible(true);
                declaredField.set(bean, value);
            }
        }
    }
}
