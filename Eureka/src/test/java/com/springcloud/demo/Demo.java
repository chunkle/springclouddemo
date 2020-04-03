package com.springcloud.demo;

/**
 * 两个线程交替打印1-100  之后另一个新线程或主线程输出1-50
 */
public class Demo {

        public static Boolean isPrint = false;//状态标记
        private static int num = 1;//表示要输出的数字

        public static void main(String[] args) {
            final Demo demo = new Demo();//对象锁

            new Thread(new Runnable() {
                public void run() {
                    for (int i = 0; i < 50; i++) {
                        synchronized (demo) {
                            while (isPrint) {
                                try {
                                    demo.wait();
                                } catch (InterruptedException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            }
                            System.out.println("A: "+num);
                            num++;
                            isPrint = true;
                            demo.notifyAll();
                        }
                    }
                }
            }).start();

            new Thread(new Runnable(){
                public void run() {
                    for (int i = 0; i < 50; i++) {
                        synchronized (demo) {
                            while (!isPrint) {
                                try {
                                    demo.wait();
                                } catch (InterruptedException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            }
                            System.out.println("B: "+num);
                            num++;
                            isPrint = false;
                            demo.notifyAll();
                        }
                    }
                }
            }).start();

            new Thread(new Runnable() {
                public void run() {

                        synchronized (demo) {
                            while (num==100) {
                                try {
                                    demo.wait();
                                } catch (InterruptedException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            }
                            for (int i = 1; i <= 50; i++) {
                                System.out.println("C: " + i);
                            }
                            demo.notifyAll();
                        }
                    }

            }).start();
        }

}
