package us.codecraft.ioc.aop;

public interface ClassFilter {

    boolean matches(Class targetClass);
}
