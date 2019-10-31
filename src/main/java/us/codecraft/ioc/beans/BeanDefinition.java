package us.codecraft.ioc.beans;

/**
 * @author cuixianing。
 * @version v1.0.0.1。
 * @Description bean的内容及元数据，保存在BeanFactory中，包装bean的实体
 * @since JDK1.8。
 * <p>创建日期：2019年10月31日 17:13。</p>
 */
public class BeanDefinition {

    private Object bean;

    private Class beanClass;

    private String beanClassName;

    private PropertyValues propertyValues = new PropertyValues();
}
