package us.codecraft.ioc.aop;

/**
 * @author cuixianing。
 * @version v1.0.0.1。
 * @Description  代理工厂类
 * @since JDK1.8。
 * <p>创建日期：2019年11月29日 15:39。</p>
 */
public class ProxyFactory extends AdvisedSupport implements AopProxy{


    @Override
    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    protected final AopProxy createAopProxy() {
        return new Cglib2AopProxy(this);
    }

}
