package us.codecraft.ioc.aop;

import us.codecraft.ioc.beans.factory.BeanFactory;

public interface BeanFactoryAware {

    void setBeanFactory(BeanFactory beanFactory) throws Exception;
}
