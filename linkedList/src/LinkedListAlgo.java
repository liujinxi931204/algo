import javax.print.attribute.standard.NumberOfDocuments;
import javax.swing.table.JTableHeader;
import java.awt.event.WindowListener;
import java.net.http.HttpRequest;
import java.util.LinkedList;

/**
 * 单链表反转
 * 表中环的检测
 * 两个有序的链表合并
 * 删除链表倒数第n个结点
 * 求链表的中间结点
 **/
public class LinkedListAlgo {
    //定义头节点
    private static Node head;

    //定义链表节点
    static class Node{
        //定义链表的数据节点
        private int data;
        //定义链表中指向下一个节点的指针
        private Node next;


        private Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        private int getData() {
            return data;
        }
    }

    /**
     * 链表的遍历
     */
    public static void list(){
        Node p=head;
        while (p!=null){
            System.out.print(p.data);
            p=p.next;
        }
    }
    /**
     * 在尾部添加节点
     */
    public static void add(int data){
        Node newNode=new Node(data,null);
        if(head==null){
            head=newNode;
        }else {
            Node p=head;
            while (p.next!=null){
                p=p.next;
            }
            p.next=newNode;
        }

    }

    /**
     * 单链表反转
     */
    public static Node reverse(){
        Node p=head;
        Node prev;
        Node next=null;
        while (p!=null){
            prev=p.next;
            p.next=next;
            next=p;
            p=prev;
        }
        //结束以后需要重新指向head节点，不要忘记
        head=next;
        return head;
    }


    public static void main(String[] args) {
        LinkedListAlgo.list();
        LinkedListAlgo.reverse();
        LinkedListAlgo.list();
        LinkedListAlgo.add(1);
        LinkedListAlgo.add(2);
        LinkedListAlgo.add(3);
        LinkedListAlgo.list();
        System.out.println();
        LinkedListAlgo.reverse();
        LinkedListAlgo.list();
    }
}
