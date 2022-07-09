package com.atguigu.crowd;

import com.atguigu.crowd.utils.CrowdUtil;
import org.junit.Test;

/**
 * @author linlingde
 * @version 1.0
 * @className StringTest
 * @description 测试类
 * @date 2022/7/9 14:08
 **/
public class StringTest {

    @Test
    public void testMD5() {

        String source = "LLD";
        String target = CrowdUtil.toMd5(source);
        System.out.println(target);

    }
}
