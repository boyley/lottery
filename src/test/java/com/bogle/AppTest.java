package com.bogle;

import org.junit.Test;

import java.io.IOException;

/**
 * Created by lenovo on 2016/7/7.
 */
public class AppTest {

    @Test
    public void parseHtml() throws IOException {
        App app = new App();
        app.parseHtml("http://www.fczst.com/down/ssq/hqws.asp");
    }

    @Test
    public void parseHtml1() throws IOException {
        App app = new App();
        // 将http://www.fczst.com/down/ssq/200303.asp 内容保存到本地来加载，因为通过网址加载不会一次加载完成，可能是通过ajax加载的
        //http://www.fczst.com/down/ssq/200303.asp
        app.parseHtml1("E:/test.html");
    }
}
