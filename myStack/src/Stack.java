import java.util.PrimitiveIterator;
import java.util.concurrent.BrokenBarrierException;

/**
 * 使用数组定义一个栈
 **/
public class Stack {

    private int[] item;//定义数组
    private int count;//栈中元素的数量
    private int n;//栈的大小


    //初始化一个栈
    public Stack(int n){
        this.item=new int[n];
        this.n=n;
        this.count=0;
    }

    //入栈
    public boolean push(int num){
        //栈满，入栈失败
        if(count==n)
            return false;
        item[count++]=num;
        return true;
    }

    //出栈
    public int pop(){
        //栈为空，返回空值
        if(count==0)
            return Integer.parseInt(null);
        return item[--count];
    }

    public static void main(String[] args) {
//        Stack stack = new Stack(3);
//        stack.push(1);
//        stack.push(2);
//        int data1 = stack.pop();
//        System.out.println(data1);
//        int data2 = stack.pop();
//        System.out.println(data2);
        LinkedStack linkedStack = new LinkedStack(3);
        linkedStack.push(1);
        linkedStack.push(2);
        linkedStack.push(3);
        int data1 = linkedStack.pop();
        System.out.println(data1);
        int data2 = linkedStack.pop();
        System.out.println(data2);


    }

}

/**
 * 链式栈
 */
class LinkedStack{

    //指定链栈的栈的大小
    private int n;

    //栈中元素的数量
    private int count;

    private Node head;

    public LinkedStack(int n) {
        this.n = n;
    }

    static class Node{
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public boolean push(int data){
        //说明栈已经满
        if(count==n)
            return false;
        Node node = new Node(data);
        if(head==null){
            head=node;
        }else{
            Node p=head;
            while (p.next!=null){
                p=p.next;
            }
            p.next=node;
        }
        ++count;
        return true;
    }


    public int pop(){
        //栈空
        if(count==0){
            return Integer.parseInt(null);
        }
        Node p=head;
        int data;
        //说明只有一个元素
        if(p.next==null){
            data=head.data;
            head=null;
            --count;
            return data;
        }
        while (p.next.next!=null){
            p=p.next;
        }
        data=p.next.data;
        p.next=null;
        --count;
        return data;
    }
}
