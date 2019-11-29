package us.codecraft.ioc.aop;

import org.junit.Test;
import us.codecraft.ioc.HelloWorldService;
import us.codecraft.ioc.HelloWorldServiceImpl;
import us.codecraft.ioc.context.ApplicationContext;
import us.codecraft.ioc.context.ClassPathXmlApplicationContext;

/**
 * @author cuixianing。
 * @version v1.0.0.1。
 * @Description
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

        // 1. 设置被代理对象
        AdvisedSupport advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(helloWorldService, HelloWorldServiceImpl.class, HelloWorldService.class);
        advisedSupport.setTargetSource(targetSource);

        // 2. 设置拦截器（advice）
        TimerInterceptor timerInterceptor = new TimerInterceptor();
        advisedSupport.setMethodInterceptor(timerInterceptor);

        // 3. 创建代理
        Cglib2AopProxy cglib2AopProxy = new Cglib2AopProxy(advisedSupport);
        HelloWorldService helloWorldServiceProxy = (HelloWorldService)cglib2AopProxy.getProxy();

        // 4. 基于AOP 的调用
        helloWorldServiceProxy.helloWorld();


    }
}
