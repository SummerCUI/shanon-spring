package us.codecraft.ioc.aop;

import org.junit.Test;
import us.codecraft.ioc.HelloWorldService;
import us.codecraft.ioc.HelloWorldServiceImpl;
import us.codecraft.ioc.context.ClassPathXmlApplicationContext;

/**
 * @author cuixianing。
 * @version v1.0.0.1。
 * @Description
 * @since JDK1.8。
 * <p>创建日期：2019年12月02日 13:47。</p>
 */
public class JdkDynamicAopProxyTest {

    @Test
    public void testInterceptor() throws Exception {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("tinyioc.xml");
        HelloWorldService helloWorldService = (HelloWorldService)classPathXmlApplicationContext.getBean("helloWorldService");

        helloWorldService.helloWorld();

        // --------- helloWorldService with AOP
        // 1. 设置被代理对象(Joinpoint)
        AdvisedSupport advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(helloWorldService, HelloWorldServiceImpl.class, HelloWorldService.class);
        advisedSupport.setTargetSource(targetSource);

        //2. 设置拦截器(advice)
        TimerInterceptor timerInterceptor = new TimerInterceptor();
        advisedSupport.setMethodInterceptor(timerInterceptor);

        //3. 创建代理(proxy)
        JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(advisedSupport);
        HelloWorldService proxy = (HelloWorldService)jdkDynamicAopProxy.getProxy();

        proxy.helloWorld();


    }
}
