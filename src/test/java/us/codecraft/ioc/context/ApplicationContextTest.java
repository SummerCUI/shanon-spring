package us.codecraft.ioc.context;

import org.junit.Test;
import us.codecraft.ioc.HelloWorldService;

/**
 * @author cuixianing。
 * @version v1.0.0.1。
 * @Description
 * @since JDK1.8。
 * <p>创建日期：2019年12月02日 15:06。</p>
 */
public class ApplicationContextTest {

    @Test
    public void test() throws Exception {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("tinyioc.xml");

        HelloWorldService helloWorldService = (HelloWorldService)classPathXmlApplicationContext.getBean("helloWorldService");

        helloWorldService.helloWorld();
    }

    @Test
    public void testPostBeanProcessor() throws Exception {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("tinyioc-postbeanprocessor.xml");

        HelloWorldService helloWorldService = (HelloWorldService)classPathXmlApplicationContext.getBean("helloWorldService");

        helloWorldService.helloWorld();
    }
}
