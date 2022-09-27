package com.open.java.lang;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author liuxiaowei
 * @date 2022年09月26日 18:07
 * @Description 自定义类加载器
 */
public class ClassLoaderDemo extends ClassLoader{

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        try (FileInputStream stream = new FileInputStream("./target/classes/" + name.replace(".", "/") + ".class")) {
            byte[] data = new byte[stream.available()];
            stream.read(data);
            if (data.length == 0) {
                return super.loadClass(name);
            }
            return defineClass(name, data, 0, data.length);
        } catch (IOException e) {
            return super.loadClass(name);
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        Class<?> testClass1 = ClassLoaderDemo.class
                .getClassLoader().loadClass("com.open.java.lang.ClassLoaderCase");

        Class<?> testClass2 = new ClassLoaderDemo()
                .loadClass("com.open.java.lang.ClassLoaderCase");

        // 看看两个类的类加载器是不是同一个
        System.out.println(testClass1.getClassLoader());
        System.out.println(testClass2.getClassLoader());

        // 看看两个类是不是长得一模一样
        System.out.println(testClass1);
        System.out.println(testClass2);

        // 两个类是同一个吗？
        System.out.println(testClass1 == testClass2);

        // 能成功实现类型转换吗？
        ClassLoaderCase classLoaderCase = (ClassLoaderCase) testClass2.newInstance();
    }
}
