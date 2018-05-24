package com.ds.storm;

import org.junit.Test;

import java.io.File;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * com.ds.storm
 *
 * @author tongdongsheng
 * @date 2018/05/24
 */
public class StormMainTest {

    @Test
    public void testProperties() {
       Properties properties = System.getProperties();
       Set<Object> keys=properties.keySet();
       for (Object key : keys) {
           System.out.println(String.format("%s===%s",
                   key,
                   properties.getProperty((String) key)));
       }
    }

    @Test
    public void testEnv() {
       for(Map.Entry<String,String> entry : System.getenv().entrySet()) {
           System.out.println(String.format("%s===%s",
                   entry.getKey(),
                   entry.getValue()));
       }
    }

    @Test
    public void testFilePath() {
        File file = new File("src/main/resources/words.txt");
        System.out.println(file.getAbsolutePath());
    }
}