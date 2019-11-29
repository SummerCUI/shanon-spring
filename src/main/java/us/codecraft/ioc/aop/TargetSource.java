package us.codecraft.ioc.aop;

/**
 * @author cuixianing。
 * @version v1.0.0.1。
 * @Description  代理对象
 * @since JDK1.8。
 * <p>创建日期：2019年11月21日 18:59。</p>
 */
public class TargetSource {

    private Class<?> targetClass;

    private Class<?>[] interfaces;

    private Object target;

    public TargetSource( Object target, Class<?> targetClass, Class<?>... interfaces) {
        this.targetClass = targetClass;
        this.interfaces = interfaces;
        this.target = target;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Class<?>[] getInterfaces() {
        return interfaces;
    }

    public Object getTarget() {
        return target;
    }
}
