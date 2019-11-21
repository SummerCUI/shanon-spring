package us.codecraft.ioc.aop;

import org.junit.Test;
import us.codecraft.ioc.HelloWorldService;
import us.codecraft.ioc.context.ApplicationContext;
import us.codecraft.ioc.context.ClassPathXmlApplicationContext;

/**
 * @author cuixianing。
 * @version v1.0.0.1。
 * @Description //TODO
 * @since JDK1.8。
 * <p>创建日期：2019年11月21日 16:17。</p>
 */
public class Cglib2AopProxyTest {

    @Test
    public void testInterceptor() throws Exception {
        // helloWorldService without AOP
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("tinyioc.xml");

        HelloWorldService helloWorldService = (HelloWorldService) applicationContext.getBean("helloWorldService");

        helloWorldService.helloWorld();

    }
}
