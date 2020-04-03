package com.springcloud.demo;

/**
 * 线程安全的单例模式----双重检查实现方式
 */
public class SingletonDemo {


        private static volatile SingletonDemo singleton;

        private SingletonDemo(){

        }
        private static SingletonDemo getInstence(){
            if (singleton == null){
                synchronized (SingletonDemo.class){
                    if (singleton == null){
                        singleton = new SingletonDemo();
                        return singleton;
                    }
                }
            }
            return singleton;
        }

}


