package com.open.hutool;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.file.FileAppender;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.io.watch.WatchMonitor;
import cn.hutool.core.io.watch.Watcher;
import cn.hutool.core.lang.Console;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.WatchEvent;

/**
 * @author liuxiaowei
 * @date 2022年09月29日 13:31
 * @Description
 */
public class IoUtilDemo {

    public static void main(String[] args) throws IOException {
        copy();
        type();
        watch();
        read();
        write();
        append();
    }

    /**
     * 文件追加
     * 用于类似日志这种（此类只有在写入文件的时候打开文件，写入结束之后，此类不需要关闭）
     */
    private static void append() {
        File file = new File("./HuTool测试.txt");
        FileAppender appender = new FileAppender(file, 16, true);
        appender.append("lolly1023");
        appender.append("追加");
        appender.append("成功");
        appender.flush();
        appender.toString();
    }

    /**
     * 文件写入
     */
    private static void write() {
        FileWriter writer = new FileWriter("./HuTool测试.txt");
        writer.write("添加文本",true);
    }

    /**
     * 文件读取
     */
    private static void read() {
        // 默认UTF-8编码，可以在构造中传入第二个参数做为编码
        FileReader fileReader = new FileReader("./HuTool测试.txt");
        String result = fileReader.readString();
        System.out.println(result);
    }

    /**
     * 文件监听
     */
    private static void watch() {
        File file2 = FileUtil.file("example.properties");
        // 这里只监听文件或目录的修改事件
        WatchMonitor watchMonitor = WatchMonitor.create(file2, WatchMonitor.ENTRY_MODIFY);
        watchMonitor.setWatcher(new Watcher(){
            @Override
            public void onCreate(WatchEvent<?> event, Path currentPath) {
                Object obj = event.context();
                Console.log("创建：{}-> {}", currentPath, obj);
            }

            @Override
            public void onModify(WatchEvent<?> event, Path currentPath) {
                Object obj = event.context();
                Console.log("修改：{}-> {}", currentPath, obj);
            }

            @Override
            public void onDelete(WatchEvent<?> event, Path currentPath) {
                Object obj = event.context();
                Console.log("删除：{}-> {}", currentPath, obj);
            }

            @Override
            public void onOverflow(WatchEvent<?> event, Path currentPath) {
                Object obj = event.context();
                Console.log("Overflow：{}-> {}", currentPath, obj);
            }
        });

        //设置监听目录的最大深入，目录层级大于制定层级的变更将不被监听，默认只监听当前层级目录
        watchMonitor.setMaxDepth(3);
        //启动监听
        watchMonitor.start();
    }

    /**
     * 文件类型判断
     * @return 文件的类型
     */
    private static String type() {
        File file = FileUtil.file("./HuTool学习.md");
        String type = FileTypeUtil.getType(file);
        //输出的是文件的格式
        Console.log(type);
        return type;
    }

    /**
     * 文件拷贝
     * @return 文件大小
     */
    private static long copy() throws IOException {
        // 文件的拷贝
        BufferedInputStream in = FileUtil.getInputStream("./HuTool学习.md");
        BufferedOutputStream out = FileUtil.getOutputStream("./HuTool学习复制.md");
        long copySize = IoUtil.copy(in, out, IoUtil.DEFAULT_BUFFER_SIZE);
        // 拷贝文件的大小
        System.out.println(copySize);
        System.out.println("拷贝成功");
        in.close();
        out.close();
        return copySize;
    }
}
