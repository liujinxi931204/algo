import java.util.HashMap;

/**
 * 基于hashMap的LRU算法
 */
public class LRU<K,V> {

    private int currentSize;

    //总容量
    private int capacity;
    private HashMap<K,Node> caches;
    //设置两个哨兵，这样链表中间的节点就可以统一的处理
    private final Node head=new Node();
    private final Node tail=new Node();

    //定义node节点
    class Node {
        Object key;
        Object value;
        Node pre;
        Node next;

        Node(){

        }

        Node(Object key,Object value){
            this.key=key;
            this.value=value;
        }
    }

    public LRU(int capacity){
        this.currentSize=0;
        this.capacity=capacity;
        this.caches= new HashMap<>(capacity);
        head.next=tail;
    }

    //将一个node节点移动到链表的头部
    public void moveToHead(Node node){
        //这里应该分为是新创建的节点还是已有的节点
        if(node.pre!=null){
            //已存在的节点
            node.pre.next=node.next;
            node.next.pre=node.pre;
        }
        node.next=head.next;
        head.next.pre=node;
        head.next=node;
        node.pre=head;

    }
    //删除最后一个node节点
    public void removeLast(){
        Node tmp=tail.pre;
        tail.pre.pre.next=tail;
        tail.pre.next=null;
        tail.pre=tail.pre.pre;
        tmp.pre=null;
    }

    //清除所有节点
    public void clear(){
        head.next=null;
        tail.pre=null;
        caches.clear();
    }

    //添加元素，需要先判断链表中是否有该元素，如果有需要更新该元素，然后移动节点到链表的头部
    //如果是链表中没有该元素，则需要判断链表是不是已经满了，如果没有满，将节点新添加到链表的头部
    //如果是链表中没有该元素，并且链表已经满了，则抛弃最后一个节点，然后将新节点添加到链表的头部
    public void put(K key,V value){
        Node node = caches.get(key);
        if(node!=null){
            //需要更新该数据，并且将节点添加到链表的头部
            node.value=value;
        }else{
            if(caches.size()>=capacity){
                //说明链表已经满了,删除最后一个节点
                caches.remove(tail.pre.key);
                removeLast();
            }
            node=new Node(key,value);
        }
        //不论是新节点还是就节点都需要移动到链表的头部
        moveToHead(node);
        caches.put(key,node);
    }

    //通过key获取元素
    public Object get(K key){
        Node node = caches.get(key);
        if(node==null){
            return null;
        }
        moveToHead(node);
        return node.value;
    }

    public Object remove(K key){
        Node node = caches.get(key);
        if(node!=null){
            node.pre.next=node.next;
            node.next.pre=node.pre;
        }
        return caches.remove(key);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Node node=head.next;
        while (node!=tail){
            stringBuilder.append(String.format("%s:%s ",node.key,node.value));
            node=node.next;
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        LRU<Integer, String> lru = new LRU<Integer, String>(5);
        lru.put(1, "a");
        lru.put(2, "b");
        lru.put(3, "c");
        lru.put(4,"d");
        lru.put(5,"e");
        System.out.println("原始链表为:"+lru.toString());

        lru.get(4);
        System.out.println("获取key为4的元素之后的链表:"+lru.toString());

        lru.put(6,"f");
        System.out.println("新添加一个key为6之后的链表:"+lru.toString());

        lru.remove(3);
        System.out.println("移除key=3的之后的链表:"+lru.toString());
    }
}
