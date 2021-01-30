/**
 * 实现简单的hash
 */
public class hashTable {

    //默认的初始值
    private static final int DEFAULT_INITAL_CAPACITY=8;

    //默认装载因子，
    private static final float LOAD_FACTORY=0.75f;

    //默认散列表数组
    private Entry[] table;

    //实际元素数量
    private int size=0;

    //散列表索引数量
    private int use=0;

    public hashTable(){
        table=(Entry[])new Entry[DEFAULT_INITAL_CAPACITY];
    }

    static class Entry<K ,V>{
        K key;
        V value;
        Entry<K,V> next;

        Entry(K key,V value,Entry next){
            this.key=key;
            this.value=value;
            this.next=next;
        }

    }


    /**
     * 插入功能
     */
    public void put(K key,V value) {
        //通过hash函数计算在table中的位置
        int index = hash(key);

        //说明第一次put，没有元素
        if (table[index] == null) {
            //将该元素放入这个位置
            table[index] = new Entry<>(key, value, null);
            size++;
            use++;
            //说明需要扩容
            if(use>table.length*LOAD_FACTORY){
                resize();
            }
            return;
        }
        //如果该位置有元素存在就需要判断是hash碰撞，还是更新
        Entry<K, V> tmp = table[index];

        do {
            //更新操作
            if (tmp.key == key) {
                tmp.value = value;
                return;
            }
            tmp = tmp.next;
        } while (tmp != null);
        //头插法
        Entry<K, V> temp = new Entry<K, V>(key, value, table[index].next);
        table[index].next = temp;
        size++;
    }

    /**
     * 获取操作
     */

    public V get(K key){
        int index=hash(key);
        //说明不存在
        if(table[index]==null){
            return null;
        }
        Entry<K,V> tmp=table[index];
        //在链表中寻找
        while (tmp!=null){
            if(tmp.key==key){
                return tmp.value;
            }else{
                tmp=tmp.next;
            }
        }
        return null;
    }
    /**
     * 删除操作
     */
    public void remove(K key){
        int index=hash(key);
        //元素不存在
        if(table[index]==null){
            return;
        }
        Entry<K,V> pre=null;
        Entry<K,V> tmp=table[index];
        Entry<K,V> head=table[index];
        while (tmp!=null){
            if(head.key==key){
                //删除链表头元素
                table[index]=head.next;
                size--;
            }else{
                //删除链表非头元素
                pre=tmp;
                tmp=tmp.next;
                if(tmp!=null && tmp.key==key){
                    pre.next=tmp.next;
                    size--;
                }
            }
        }
    }

    /**
     * hash函数
     */
    private int hash(Object key) {
        int h;
        return (key == null) ? 0 : ((h = key.hashCode()) ^ (h >>> 16)) % table.length;
    }
    /**
     * 扩容函数
     */
    private void resize(){
        Entry<K,V>[] oldTable=table;
        table=(Entry[])new Entry[table.length*2];
        use=0;
        for (int i = 0; i <oldTable.length ; i++) {
            if(table[i]==null){
                continue;
            }
            Entry<K,V> tmp=oldTable[i];
            while (tmp!=null){
                int index=hash(tmp.key);
                if(table[index]==null){
                    table[index] = new Entry<>(tmp.key, tmp.value,null);
                    use++;
                }else{
                    Entry<K, V> temp = new Entry<K, V>(tmp.key, tmp.value, table[index].next);
                    table[index].next = temp;
                }
                tmp=tmp.next;
            }
        }
    }

    public static void main(String[] args) {

    }

}
