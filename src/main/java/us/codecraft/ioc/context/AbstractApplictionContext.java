package us.codecraft.ioc.context;

import us.codecraft.ioc.beans.BeanPostProcessor;
import us.codecraft.ioc.beans.factory.AbstractBeanFactory;

import java.util.List;

/**
 * @author cuixianing。
 * @version v1.0.0.1。
 * @Description //TODO
 * @since JDK1.8。
 * <p>创建日期：2019年11月21日 16:20。</p>
 */
public abstract class AbstractApplictionContext implements ApplicationContext{

    protected AbstractBeanFactory beanFactory;

    public AbstractApplictionContext(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void refresh() throws Exception {
        loadBeanDefinitions(beanFactory);
        registerBeanPostProcessors(beanFactory);
        onRefresh();
    }

    private void registerBeanPostProcessors(AbstractBeanFactory beanFactory) throws Exception {
        List beanPostProcessors = beanFactory.getBeansForType(BeanPostProcessor.class);
        for(Object beanPostProcessor : beanPostProcessors) {
            beanFactory.addBeanPostProcessor((BeanPostProcessor) beanPostProcessor);
        }

    }

    protected abstract void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception;

    protected void onRefresh() throws Exception {
        beanFactory.preInstantiateSingletons();
    }

    @Override
    public Object getBean(String name) throws Exception {
        return beanFactory.getBean(name);
    }
}
