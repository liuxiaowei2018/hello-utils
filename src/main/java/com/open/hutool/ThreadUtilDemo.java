package com.open.hutool;

import cn.hutool.core.thread.ThreadUtil;
import com.open.custom.http.HttpConnectionPoolUtil;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author liuxiaowei
 * @date 2022年11月08日 20:09
 * @Description
 */
public class ThreadUtilDemo {

    public static void post(String url, String params) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(params, "UTF-8"));
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        //try (CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        //     CloseableHttpResponse response = httpClient.execute(httpPost))
        // 修改为池化httpClient 解决问题
        try (CloseableHttpResponse response = HttpConnectionPoolUtil.getHttpClient().execute(httpPost))
        {
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity, StandardCharsets.UTF_8));
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 模拟每秒1000次并发
        for (int i = 0; i < 60; i++) {
            ThreadUtil.concurrencyTest(1000, () -> {
                String url = "http://127.0.0.1:18072/boot/selectOne";
                post(url,"1111");
            });
            Thread.sleep(1000);
        }
        // result:
        // 1.netstat -ant|find  /I "127.0.0.1:8080"
        // 2.tcp连接占用端口满了 65535 服务器无法访问报错
        // 对于Client而言，每个连接都需要占用一个端口，而系统允许的可用端口数不足65000个
        //（这也是在TCP参数优化后才能达到）。因此，如果Client发起过多的连接并主动关闭
        //（假设没有重用端口或者连接多个Server），就会有大量的连接在关闭后处于TIME_WAIT状态，
        // 等待2MSL的时间后才能释放网络资源（包括端口），于是Client会由于缺少可用端口而无法新建连接。
        // 解决方案: 池化处理
    }

}
