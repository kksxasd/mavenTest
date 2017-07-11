package com.zxh.controller;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by zhengxh on 2017/7/4.
 * 线程知识的运用
 */
public class ThreadController {
    public static void main(String[] args) {
        //blockingQueueTest();
        blockDeque();
    }

    /**
     * 阻塞队列的测试,到达最大值时,要等有空位时,才可以继续
     */
    private static void blockingQueueTest() {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(20);
        try {
            for (int i = 0; i < 50; i++) {
                if (i > 18) {
                    int j=(Integer)blockingQueue.take();
                    System.out.print("阻塞队列里取走元素:" + j + "\n");
                }
                blockingQueue.put(i);
                System.out.print("阻塞队列里添加元素:" + i + "\n");
            }
            System.out.print("结束");
        } catch (Exception e) {
            System.out.print("Exception");
        }
    }

    /**
     * 阻塞栈,后进先出
     * @since1.6
     */
    private static void blockDeque(){
        try {
            BlockingDeque blockingDeque = new LinkedBlockingDeque(20);
            for (int i = 0; i < 50; i++) {
                if (i > 18) {
                    int j=(Integer)blockingDeque.take();
                    System.out.print("阻塞栈里取走元素:" + j + "\n");
                }
                blockingDeque.putFirst(i);
                System.out.print("阻塞栈里添加元素:" + i + "\n");
            }
        }catch (Exception e){
            System.out.print("Exception");
        }

    }
}
