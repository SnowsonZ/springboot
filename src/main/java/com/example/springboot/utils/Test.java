package com.example.springboot.utils;

import com.google.common.base.Splitter;

import java.util.List;

/**
 * @Author: Snowson
 * @Date: 2018/11/19 22:37
 * @Description:
 */
public class Test {

    public static final String REGEX = "\u001E;";
    public static void main(String[] args) {
        String content = "<2>Dec 11 10:05:27 RIIL\u001E;F309005000004932B720921E\u001E;ipv4\u001E;4\u001E; http_transaction\u001E;172.16.117.157\u001E;58:69:6c:d1:8e:c3\u001E;172.16.117.157\u001E;139.215.187.107\u001E;64856\u001E;80\u001E;liebao\u001E;8.tlu.dl.delivery.mp.microsoft.com\u001E;GET\u001E;http://8.tlu.dl.delivery.mp.microsoft.com/filestreamingservice/files/49ea21e9-d6d9-49ea-8399-06d4d368587f?P1=1544500154&P2=402&P3=2&P4=L4Kjt7LVp7PxD2RdyAT1bCa6%2f2qkwhN81KUQR7qbpVdKF621wlcZXjO4IhXlig7U6rBTNsmUlPyFoGWWumzU1A%3d%3d\u001E;206\u001E;application/octet-stream\u001E;\u001E;2018-12-11 10:04:25.562\u001E;2018-12-11 10:05:25.562\u001E;187\u001E;0\u001E;32\u001E;0\u001E;1\u001E;441\u001E;48\u001E;66756";
        List<String> result = Splitter.on(REGEX).splitToList(content);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(i + ": " + result.get(i));
        }
        System.out.println();
        content = "<2>Nov  9 14:21:38 RIIL\u001E;F309005000004932B720921E\u001E;ipv4\u001E;4\u001E; http_transaction\u001E;172.17.163.184\u001E;00:1a:a9:7d:ed:76\u001E;172.17.163.184\u001E;172.17.161.31\u001E;54747\u001E;12306\u001E;liebao\u001E;172.17.161.31:12306\u001E;POST\u001E;http://172.17.161.31:12306/v1/event/get_summary/12\u001E;400\u001E;\u001E;\u001E;2018-11-09 14:21:39.181\u001E;2018-11-09 14:21:39.182\u001E;1\u001E;0\u001E;8\u001E;0\u001E;1\u001E;548\u001E;1\u001E;28\u001E;Chrome69.0.3497.92\u001E;Windows";
        result = Splitter.on(REGEX).splitToList(content);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(i + ": " + result.get(i));
        }

//        Integer num1 = 0, num2 = 0, num3 = 0;
//        System.out.println(0D / 0D);
//        System.out.println(num1.floatValue() / num2.floatValue());
//        NumberZeroTest numberZeroTest = new NumberZeroTest();
//        numberZeroTest.setNum(num1.floatValue() / num2.floatValue());
//        System.out.println(numberZeroTest);
    }
}

//@Data
//@ToString
//class NumberZeroTest{
//    private float num;
//}
