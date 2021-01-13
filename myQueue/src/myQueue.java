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
         */
        public boolean enqueue(int data){
            //队列满，入队失败
            if(tail==n)
                return false;
            queue[tail++]=data;
            return true;
        }

        /**
         * 出队操作
         * @return
         */
        public int dequeue(){
            //head==tail，表示队列为空
            if(head==tail)
                return Integer.parseInt(null);
            int data=queue[head];
            for (int i = 1; i <tail; i++) {
                queue[i-1]=queue[i];
            }
            --tail;
            return data;
        }

        public void list(){
            for (int i = 0; i < tail; i++) {
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


        LinkedQueue linkedQueue = new LinkedQueue();
        linkedQueue.list();
        System.out.println();
        linkedQueue.enqueue(1);
        linkedQueue.enqueue(2);
        linkedQueue.enqueue(3);
        linkedQueue.list();
        System.out.println();
        int data1 = linkedQueue.dequeue();
        System.out.println(data1);
        linkedQueue.list();
        System.out.println();
        int data2 = linkedQueue.dequeue();
        System.out.println(data2);
        linkedQueue.list();
        System.out.println();
        int data3 = linkedQueue.dequeue();
        System.out.println(data3);
        linkedQueue.list();
        System.out.println();

    }
}
