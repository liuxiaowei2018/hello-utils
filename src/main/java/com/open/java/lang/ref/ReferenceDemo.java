package com.open.java.lang.ref;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author liuxiaowei
 * @date 2022年03月25日 16:38
 * @Description 强引用 软引用 弱引用 虚引用
 */
public class ReferenceDemo {

    // 1G
    private byte[] objects = new byte[1024 * 1024 * 1024];

    public static void main1(String[] args) {
        // 强引用 reference1
        ReferenceDemo reference1 = new ReferenceDemo();
        // 强引用 reference2
        ReferenceDemo reference2 = new ReferenceDemo();
        // 堆内存超过 -Xmx2000M ---> OutOfMemoryError
        ReferenceDemo reference3 = new ReferenceDemo();
        System.out.println("测试完成");

    }

    public static void main(String[] args) throws InterruptedException {

        // 强引用
        ReferenceDemo reference1 = new ReferenceDemo();

        // 软引用 referenceSoftReference ----> com.open.learning.basis.java.Reference@123772c4
        SoftReference<ReferenceDemo> referenceSoftReference = new SoftReference<>(new ReferenceDemo());

        // 弱引用
        WeakReference<ReferenceDemo> weakReference = new WeakReference<>(new ReferenceDemo());

        // 软引用指向的对象,在堆内存不够用时才会回收
        System.out.println(referenceSoftReference.get());

        // 弱引用指向的对象,一旦进行了垃圾回收一定会回收
        System.out.println(weakReference.get());

        ReferenceDemo reference2 = new ReferenceDemo();

        System.gc();
        // 保证gc执行完成
        Thread.sleep(1000);

        // 堆内存不够用了, referenceSoftReference已经被回收 ---> null
        System.out.println(referenceSoftReference.get());

        System.out.println(weakReference.get());

        // 虚引用 和 ReferenceQueue 配合使用 通过ReferenceQueue知道某个对象是否被垃圾回收
        ReferenceQueue queue = new ReferenceQueue();
        PhantomReference<ReferenceDemo> phantomReference = new PhantomReference<>(new ReferenceDemo(), queue);
        // null
        System.out.println(phantomReference.get());
        // null
        System.out.println(queue.poll());
        System.gc();
        Thread.sleep(1000);
        // 出现垃圾回收,该对象被回收,同时对象的虚引用被放到ReferenceQueue--->java.lang.ref.PhantomReference@73a3d5c3
        System.out.println(queue.poll());
    }
}
