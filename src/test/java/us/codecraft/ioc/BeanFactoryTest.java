package us.codecraft.ioc;

import org.junit.Test;
import us.codecraft.ioc.beans.BeanDefinition;
import us.codecraft.ioc.beans.BeanPostProcessor;
import us.codecraft.ioc.beans.factory.AbstractBeanFactory;
import us.codecraft.ioc.beans.factory.AutowireCapableBeanFactory;
import us.codecraft.ioc.beans.io.ResourceLoader;
import us.codecraft.ioc.beans.xml.XmlBeanDefinitionReader;

import java.util.List;
import java.util.Map;

/**
 * @author cuixianing。
 * @version v1.0.0.1。
 * @Description
 * @since JDK1.8。
 * <p>创建日期：2019年12月02日 15:43。</p>
 */
public class BeanFactoryTest {

    @Test
    public void testLazy() throws Exception {
        //1.读取配置
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions("tinyioc.xml");

        //2. 初始化beanFactory并注册bean
        AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }

        // 3.获取bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.helloWorld();
    }

    @Test
    public void testPreInstantiate() throws Exception {
        // 1.读取配置
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions("tinyioc-postbeanprocessor.xml");

        // 2.初始化BeanFactory并注册bean
        AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }
        List beanPostProcessors = beanFactory.getBeansForType(BeanPostProcessor.class);
        for(Object beanPostProcessor : beanPostProcessors) {
            beanFactory.addBeanPostProcessor((BeanPostProcessor) beanPostProcessor);
        }
        // 3.初始化bean
        beanFactory.preInstantiateSingletons();

        // 4.获取bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.helloWorld();
    }


}
