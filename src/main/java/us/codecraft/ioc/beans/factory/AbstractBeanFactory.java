package us.codecraft.ioc.beans.factory;

import us.codecraft.ioc.beans.BeanDefinition;
import us.codecraft.ioc.beans.BeanPostProcessor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author cuixianing。
 * @version v1.0.0.1。
 * @Description
 * @since JDK1.8。
 * <p>创建日期：2019年11月21日 16:25。</p>
 */
public abstract class AbstractBeanFactory implements BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    private final List<String> beanDefinitionNames = new ArrayList<>();

    @Override
    public Object getBean(String name) throws Exception {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if(beanDefinition == null) {
            throw new IllegalArgumentException("No bean named " + name + " is defines");
        }
        Object bean = beanDefinition.getBean();
        if(bean == null) {
            bean = doCreateBean(beanDefinition);
            bean = initializeBean(bean, name);
            beanDefinition.setBean(bean);
        }
        return bean;
    }

    private Object initializeBean(Object bean, String name) throws Exception {
        for(BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessBeforeInitialization(bean, name);
        }

        // FIXME :call initialize method
        for(BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessAfterInitialization(bean, name);
        }
        return bean;
    }

    private Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
        Object bean = createBeanInstance(beanDefinition);
        beanDefinition.setBean(bean);
        applyPropertyValues(bean, beanDefinition);
        return bean;
    }

    protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {

    }

    private Object createBeanInstance(BeanDefinition beanDefinition) throws IllegalAccessException, InstantiationException {
        return beanDefinition.getBeanClass().newInstance();
    }

    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
        beanDefinitionNames.add(name);
    }

    public void preInstantiateSingletons() throws Exception {
        for (Iterator it = this.beanDefinitionNames.iterator(); it.hasNext();) {
            String beanName = (String) it.next();
            getBean(beanName);
        }
    }

    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public List getBeansForType(Class type) throws Exception {
        List<Object> beans = new ArrayList<>();
        for(String beanDefinitionName : beanDefinitionNames) {
            if(type.isAssignableFrom(beanDefinitionMap.get(beanDefinitionName).getBeanClass())) {
                beans.add(getBean(beanDefinitionName));
            }
        }
        return beans;
    }
}
