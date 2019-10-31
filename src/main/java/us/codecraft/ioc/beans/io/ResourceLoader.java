package us.codecraft.ioc.beans.io;

import java.net.URL;

/**
 * @author cuixianing。
 * @version v1.0.0.1。
 * @Description 本地资源加载
 * @since JDK1.8。
 * <p>创建日期：2019年10月31日 15:56。</p>
 */
public class ResourceLoader {

    public Resource getResource(String location) {
        URL resource = this.getClass().getClassLoader().getResource(location);
        return new UrlResource(resource);
    }
}
