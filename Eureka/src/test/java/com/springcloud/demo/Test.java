package com.springcloud.demo;

public class Test {
        private static final Object lock = new Object();  //表示对象锁
        private volatile int index = 1;//表示要输出的数字
        private volatile boolean aHasPrint = false;      //记录A是否被打印过
      private static   int flags = 0;
        class A implements Runnable {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    synchronized (lock) {
                        while (flags==0) {//如果A已经打印过就进行阻塞
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("A:" + index);//A没有打印过则输出A的值
                        index++; //输出的值增加1

                       // aHasPrint = true;//表示A已经打印过
                        flags=1;
                        lock.notifyAll();//唤醒其它线程执行
                    }
                }

            }
        }

        class B implements Runnable {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    synchronized (lock) {
                        while (flags==1) {               //如果A没有打印过则阻塞
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("B:" + index);//输出B的值
                        index++;//计数加一

                      //  aHasPrint = false;//B打印完了说明新的一轮开始了，将标识为计为A没有打印过
                        flags=0;
                        lock.notifyAll();//唤醒阻塞线程
                    }
                }
            }
        }




        public static void main(String[] args) {
            Test test = new Test();
            Thread threadA = new Thread(test.new A());
            Thread threadB = new Thread(test.new B());
          //  Thread threadC = new Thread(test.new C());
            threadA.start();
            threadB.start();
          //  threadC.start();
        }

}
