import javax.imageio.plugins.tiff.GeoTIFFTagSet;
import javax.print.DocFlavor;
import javax.swing.*;
import javax.swing.undo.AbstractUndoableEdit;

/**
 *
 **/
public class myQueue {

    /**
     * 基于数组的队列
     */
    static class ArrayQueue{
        //使用数组表示队列
        private int[] queue;
        //队列的长度
        private int n;
        //表示队列的头
        private int head;
        //表示队列的尾
        private int tail;

        /**
         * 创建给定长度的队列
         * @param capacity
         */
        public ArrayQueue(int capacity) {
            this.queue=new int[capacity];
            this.n=capacity;
        }
        /**
         * 入队操作
         * 均摊时间复杂度为O(1),因为只有在插入n次后才会整体数据搬移一次，时间复杂度为O(n)
         * 均摊下来就是执行一次插入，有一次搬移，所以均摊时间复杂度为O(1)
         *
         */

        public boolean enqueue(int data){
            if(tail==n){
                //队列满
                if(head==0){
                    return false;
                }
                //数据搬移
                for (int i = head; i < tail; i++) {
                    queue[i-head]=queue[i];
                }

                //使用系统方法也可
//                if(tail-head>=0){
//                    System.arraycopy(queue, head, queue, head - head, tail - head);
//                }
                tail=tail-head;
                head=0;
                //清空
                for (int i = tail+1; i < n; i++) {
                    queue[i]=0;
                }
            }
            queue[tail++]=data;
            return true;
        }


        /**
         * 出队操作
         */

        public int dequeue(){
            //队列空
            if(head==tail){
                return Integer.parseInt(null);
            }
            return queue[head++];

        }

        /**
         * 入队操作
         */
//        public boolean enqueue(int data){
//            //队列满，入队失败
//            if(tail==n)
//                return false;
//            queue[tail++]=data;
//            return true;
//        }
//
//        /**
//         * 出队操作
//         * @return
//         */
//        public int dequeue(){
//            //head==tail，表示队列为空
//            if(head==tail)
//                return Integer.parseInt(null);
//            int data=queue[head];
//            for (int i = 1; i <tail; i++) {
//                queue[i-1]=queue[i];
//            }
//            --tail;
//            return data;
//        }

        public void list(){
            for (int i = head; i < tail; i++) {
                System.out.print(queue[i]+"  ");
            }
        }
    }


    /**
     * 链式队列
     */

    static class LinkedQueue{

        //队列的头
        private Node head;
        //队列的尾
        private Node tail;

        class Node{
            int data;
            Node next;

            public Node(int data) {
                this.data = data;
                this.next=null;
            }
        }

        public boolean enqueue(int data){
            Node node = new Node(data);
            //队列为空
            if(head==null){
                head=node;
                tail=head;
                return true;
            }
            tail.next=node;
            tail=tail.next;
            return true;
        }

        public int dequeue(){
            //队列为空
            if(head==tail)
                return Integer.parseInt(null);
            int data=head.data;
            head=head.next;
            return data;
        }

        public void list(){
            Node p=head;
            while (p!=null){
                System.out.print(p.data+"   ");
                p=p.next;
            }
        }
    }

    /**
     * 循环队列
     */
    static class CircleQueue{

        //使用数组表示队列
        private String[] queue;
        //队列的大小
        private int n;
        //队列的头
        private int head;
        //队列的尾
        private int tail;

        public CircleQueue(int n) {
            queue=new String[n];
            this.n = n;
        }

        public boolean enqueue(String str){
            //队列满
            //循环队列判断满，需要浪费一个空间
            if((tail+1)%n==head){
                return false;
            }
            queue[tail]=str;
            tail=(tail+1)%n;
            return true;
        }

        public String dequeue(){
            //队列空
            if(head==tail){
                return null;
            }
            String str=queue[head];
            head=(head+1)%n;
            return str;
        }

        public void list(){
            int p=head;
            while (p!= tail){
                System.out.print(queue[p]+"   ");
                p=(p+1)%n;
            }
        }
    }



    public static void main(String[] args) {
//        ArrayQueue arrayQueue = new ArrayQueue(3);
//        arrayQueue.list();
//        System.out.println();
//        arrayQueue.enqueue(1);
//        arrayQueue.enqueue(2);
//        arrayQueue.enqueue(3);
//        arrayQueue.list();
//        System.out.println();
//        int data1 = arrayQueue.dequeue();
//        System.out.println(data1);
//        arrayQueue.list();
//        System.out.println();
//        int data2 = arrayQueue.dequeue();
//        System.out.println(data2);
//        arrayQueue.list();
//        System.out.println();
//        int data3 = arrayQueue.dequeue();
//        System.out.println(data3);
//        arrayQueue.list();
//        System.out.println();
//        arrayQueue.list();


//        LinkedQueue linkedQueue = new LinkedQueue();
//        linkedQueue.list();
//        System.out.println();
//        linkedQueue.enqueue(1);
//        linkedQueue.enqueue(2);
//        linkedQueue.enqueue(3);
//        linkedQueue.list();
//        System.out.println();
//        int data1 = linkedQueue.dequeue();
//        System.out.println(data1);
//        linkedQueue.list();
//        System.out.println();
//        int data2 = linkedQueue.dequeue();
//        System.out.println(data2);
//        linkedQueue.list();
//        System.out.println();
//        int data3 = linkedQueue.dequeue();
//        System.out.println(data3);
//        linkedQueue.list();
//        System.out.println();


        CircleQueue circleQueue = new CircleQueue(4);
        circleQueue.list();
        System.out.println();
        circleQueue.enqueue("1");
        circleQueue.enqueue("2");
        circleQueue.enqueue("3");
        circleQueue.list();
        System.out.println();
        String str1 = circleQueue.dequeue();
        System.out.println(str1);
        circleQueue.list();
        System.out.println();
        String str2 = circleQueue.dequeue();
        System.out.println(str2);
        circleQueue.list();
        System.out.println();
        String str3 = circleQueue.dequeue();
        System.out.println(str3);
        circleQueue.list();
        System.out.println();

    }
}
