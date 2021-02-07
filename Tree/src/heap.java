/**
 * 构造一个大顶堆
 */
public class heap {
    //用数组构建的完全二叉树，位置i的左子节点是2*i+1，即(i<<1)+1,右子节点是2*i+2，即(i<<1)+2
    //位置i的父节点是(i-1)/2，即(i-1)>>1

    private int[] nums;//数组，因为堆是一个完全二叉树，适合使用数组存储
    private int n;//堆可以存储的最大的数据个数
    private int count;//堆中已经存放的数据的个数


    public heap(int capacity){
        this.nums=new int[capacity];
        this.n=capacity;
        this.count=0;
    }

    public void swap(int[] nums,int low,int high){
        int tmp=nums[high];
        nums[high]=nums[low];
        nums[low]=tmp;
    }

    public void insert(int data){
        if(count==n) return;//堆已经满了，不能再插入

        nums[count]=data;
        int i=count;
        while (i>0 && nums[i]>nums[(i-1)>>1]){
            swap(nums,(i-1)>>1,i);
            i=(i-1)>>1;
        }
        ++count;
    }
    //删除堆顶元素，就是用最后一个叶子节点的元素代替堆顶元素，然后从上向下堆化，最后满足堆的定义
    public void delete(){
        if(count==0) return;//空堆

        swap(nums,0,count-1);
        nums[count-1]=0;
        heapify(nums,0,count-2);
        --count;


    }
    //将打乱顺序的堆重新堆化，使其满足堆的定义
    public void heapify(int[] nums,int low,int n){
        if(count==0) return;//空堆

        //从high位置开始自顶向下堆化，满足堆的定义
        int i=low;
        while (true){
            if(((i<<1)+1)<=n && nums[i]<nums[(i<<1)+1]){
                i=(i<<1)+1;
            }
            if(((i<<1)+2)<=n && nums[(i<<1)+1]<nums[(i<<1)+2]){
                i=(i<<1)+2;
            }
            if(i==low) break;
            swap(nums,i,low);
            low=i;
        }

    }

    public void list(){
        for (int num : nums) {
            System.out.print(num+" ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        heap heap = new heap(10);
        heap.insert(1);
        heap.insert(2);
        heap.insert(3);
        heap.insert(4);
        heap.insert(5);
        heap.list();
        heap.delete();
        heap.list();
        heap.delete();
        heap.list();
    }

}
