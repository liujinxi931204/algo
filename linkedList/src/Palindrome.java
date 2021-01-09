/**
 *
 *
 * 如果字符串是通过单链表连接起来的，判断是否是回文
 **/
public class Palindrome {


    //定义节点类
    static class Node{
        private char data;
        private Node next;

        public Node() {
        }

        Node(char data, Node next) {
            this.data = data;
            this.next = next;
        }

        public char getData() {
            return data;
        }
    }

    //定义头节点
    private Node head;
    //定义快、慢两个指针，快指针一次走两个，慢指针一次走一个
    private Node fast;
    private Node slow;
    //用于指向slow指针的位置，防止链表丢掉
    private Node p;
    //用于指向slow指针的下一个位置，防止链表丢掉
    private Node q;


    //从链表的头开始添加节点
    private void addNode(char value){
        Node newNode=new Node(value,null);
        if(head==null){
            head=newNode;
        }else{
            p=head;
            while(p.next!=null){
                p=p.next;
            }
            p.next=newNode;
        }
    }

    //遍历链表
    private void list(){
        p=head;
        while (p!=null){
            System.out.print(p.data);
            p=p.next;
        }
    }

    //判断是否是回文
    private boolean isPalindrome(){
        //如果是空链表或者只有一个节点的链表，则返回true
        if(head==null || head.next==null){
            return true;
        }else{
            //否则继续判断
            //快、慢两个指针都指向头节点，快指针每次走两个位置，慢指针每次走一个位置
            //当快指针到链表的尾节点时，慢指针正好指向链表的中间节点
            //在慢指针前进的过程中，将链表反序
            fast=head;
            slow=head;
            while(fast!=null&&fast.next!=null){
                fast=fast.next.next;
                q=slow.next;
                slow.next=p;
                p=slow;
                slow=q;
            }

            //如果fast！=null，说明链表的长度为奇数，q需要向后走一位
            //如果fast==null，说明链表的长度为偶数，q不需要移动
            //此时q指向的是后半部分链表的头，p指向的是前半部分链表的头
            if(fast!=null){
                q=q.next;
            }
            while (p!=null){
                if(p.data!=q.data){
                    return false;
                }
                p=p.next;
                q=q.next;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        Palindrome palindrome = new Palindrome();
//        System.out.println(palindrome.isPalindrome());
        palindrome.addNode('b');
//        palindrome.list();
//        System.out.println();
//        System.out.println(palindrome.isPalindrome());
        palindrome.addNode('c');
//        palindrome.list();
//        System.out.println();
//        System.out.println(palindrome.isPalindrome());
        palindrome.addNode('c');
//        palindrome.list();
//        System.out.println();
//        System.out.println(palindrome.isPalindrome());
//        palindrome.addNode('b');
//        palindrome.list();
//        System.out.println();
        System.out.println(palindrome.isPalindrome());

    }



}
