package us.codecraft.ioc.aop;

public interface PointcutAdvisor extends Advisor{

    Pointcut getPointcut();
}
