package us.codecraft.ioc;

/**
 * @author cuixianing。
 * @version v1.0.0.1。
 * @Description //TODO
 * @since JDK1.8。
 * <p>创建日期：2019年11月11日 11:09。</p>
 */
public class OutputServiceImpl implements OutputService{
    @Override
    public void output(String text) {
        System.out.println(text);
    }
}
