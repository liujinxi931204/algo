import javax.print.attribute.standard.NumberOfDocuments;

/**
 * 单链表反转
 * 表中环的检测
 * 两个有序的链表合并
 * 删除链表倒数第n个结点
 * 求链表的中间结点
 **/
public class LinkedListAlgo {
    //定义头节点
    private Node head;
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
    public void list(){
        Node p=head;
        while (p!=null){
            System.out.print(p.data);
            p=p.next;
        }
    }

    /**
     * 在尾部添加节点
     */
    public void add(int data){
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
    public Node reverse(){
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

    /**
     * 求链表的中间结点
     * 快慢指针法，需要注意遍历结束的条件
     */
    public Node findMiddle(){
        if(head==null){
            return null;
        }
        Node fast=head;
        Node slow=head;
        while(fast!=null && fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        return slow;
    }

    /**
     * 合并两个有序链表
     *
     */
    public Node mergeLinkedList(LinkedListAlgo list1,LinkedListAlgo list2){
        //两种特殊情况，两个链表都是空链表或者有一个链表是空链表
        if(list1.head==null && list2.head==null){
            return null;
        }
        if(list1.head==null){
            head=list2.head;
            return head;
        }
        if(list2.head==null){
            head=list1.head;
            return head;
        }
        //一般情况，两个链表都不为空
        Node p=list1.head;
        Node q=list2.head;

        if(p.data<=q.data){
            head=p;
            p=p.next;
        }else{
            head=q;
            q=q.next;
        }
        Node r=head;

        while (p!=null && q!=null){
            if(p.data<=q.data){
                r.next=p;
                p=p.next;
            }else{
                r.next=q;
                q=q.next;
            }
            r=r.next;
        }
        if(p==null){
            r.next=q;
        }
        if(q==null){
            r.next=p;
        }
        return head;
    }

    /**
     * 删除单链表到数第k个节点
     * 双指针法，定义两个指针fast、slow
     * 让fast指针先向后走k个位置，然后两个指针同时向后，最后当fast到达链表的结尾的时候，slow.next就是要删除的节点
     * 需要注意删除头节点的情况
     */
    public Node deleteLastKth(int k){
        if(head==null)
            return null;
        Node fast=head;
        Node slow=head;
        for (int i = 0; i < k; i++) {
            fast=fast.next;
        }
        //删除头节点
        if(fast==null){
            Node p=head;
            head=head.next;
            return p;
        }
        //其他节点
        while (fast.next!=null){
            fast=fast.next;
            slow=slow.next;
        }
        Node p=slow.next;
        slow.next=slow.next.next;
        return p;

    }

    /**
     *
     * 检测单链表中是否有环的存在
     * 快慢两个指针，快指针每一次向后走两个位置，慢指针每次向后走一个位置，如果快、慢指针相遇，则说明有环
     */
    public boolean hasLoop(){
        //如果是空链表，则没有环
        if(head==null){
            return false;
        }
        Node fast=head;
        Node slow=head;

        while (fast!=null && fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if (fast==slow){
                //如果相遇，说明有环
                return true;
            }
        }
        return  false;

    }



    public static void main(String[] args) {
        LinkedListAlgo listAlgo = new LinkedListAlgo();
        listAlgo.add(1);
//        listAlgo.add(2);
//        listAlgo.add(3);
        Node node = listAlgo.deleteLastKth(1);
        System.out.println(node.data);
        listAlgo.list();

    }
}
