package com.atguigu.queue;

import javax.lang.model.element.VariableElement;
import java.util.Scanner;

/**
 * @author XiangZhang
 * @date 2020/4/11 - 20:48
 */
public class CircleArrayQueueDemo {

    public static void main(String[] args) {
        CircleArray queue = new CircleArray(4);//设置为4，但是队列的有效数据是3
        char key = ' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列中取出数据");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key){
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop=false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }

}

class CircleArray {
    private int maxSize;
    //front 变量的含义做一个调整：front 就指向队列的第一个元素，也就是说arr[front]就是队列的一个元素
    //front 的初始值 = 0
    private int front;
    //rear 变量的含义做一个调整：rear 指向队列的最后一个元素的后一个位置，因为希望空出一个空间做约定
    //rear 的初始值 = 0
    private int rear;
    private int[] arr;

    public CircleArray (int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[arrMaxSize];
    }

    public boolean isFull(){
        return (rear + 1 ) % maxSize == front;
    }

    public boolean isEmpty(){
        return rear == front;
    }


    public void addQueue(int n){
        if(isFull()){
            System.out.println("队列满，不能加入数据");
            return;
        }
        //直接将数据插入
        arr[rear] = n;
        //将rear后移，因为这里是环形队列，所以需要用取模的方式后移
        rear = (rear + 1) % maxSize;
    }

    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列空，不能取数据");
        }
        //先把front的值取出来
        int value = arr[front];
        //再把front后移，也需要取模
        front = (front + 1) % maxSize;
        return value;
    }

    public void showQueue(){
        if(isEmpty()){
            System.out.println("队列空的，没有数据");
            return;
        }
        //遍历，从front开始
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    //求出当前队列有效数据的个数
    public int size(){
        return (rear + maxSize - front) % maxSize;
    }

    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列空的，没有数据");
        }
        return arr[front];
    }


}