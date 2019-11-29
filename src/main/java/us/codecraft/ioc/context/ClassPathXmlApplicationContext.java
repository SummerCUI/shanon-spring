package us.codecraft.ioc.context;

import us.codecraft.ioc.beans.BeanDefinition;
import us.codecraft.ioc.beans.factory.AbstractBeanFactory;
import us.codecraft.ioc.beans.factory.AutowireCapableBeanFactory;
import us.codecraft.ioc.beans.io.ResourceLoader;
import us.codecraft.ioc.beans.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * @author cuixianing。
 * @version v1.0.0.1。
 * @Description
 * @since JDK1.8。
 * <p>创建日期：2019年11月21日 16:19。</p>
 */
public class ClassPathXmlApplicationContext extends AbstractApplictionContext {

    private String configLocation;

    public ClassPathXmlApplicationContext(String configLocation) throws Exception {
        this(configLocation, new AutowireCapableBeanFactory());
    }

    public ClassPathXmlApplicationContext(String configLocation, AbstractBeanFactory beanFactory) throws Exception {
        super(beanFactory);
        this.configLocation = configLocation;
        refresh();
    }

    @Override
    protected void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions(configLocation);
        Map<String, BeanDefinition> registry = xmlBeanDefinitionReader.getRegistry();
        for( Map.Entry<String, BeanDefinition> beanDefinitionEntry :registry.entrySet()) {
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }
    }
}
