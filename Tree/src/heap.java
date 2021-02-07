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
            if(((low<<1)+1)<=n && nums[low]<nums[(low<<1)+1]){
                i=(low<<1)+1;
            }
            if(((low<<1)+2)<=n && nums[(low<<1)+1]<nums[(low<<1)+2]){
                i=(low<<1)+2;
            }
            if(i==low) break;
            swap(nums,low,i);
            low=i;
        }

    }

    public void list(){
        for (int num : nums) {
            System.out.print(num+" ");
        }
        System.out.println();
    }

    //将数组堆化
    //只需要考虑非叶子节点即可，叶子节点不需要考虑
    //最后一个非叶子节点的位置是(nums.length-1)/2
    public static void buildHeap(int[] nums){
        for (int i = (nums.length-2)/2; i >=0 ; --i) {
            heapifySort(nums,i,nums.length-1);
        }
    }

    public static void heapifySort(int[] nums,int low,int n){

        //从high位置开始自顶向下堆化，满足堆的定义
        int i=low;
        while (true){
            if(((low<<1)+1)<=n && nums[low]<nums[(low<<1)+1]){
                i=(low<<1)+1;
            }
            if(((low<<1)+2)<=n && nums[(low<<1)+1]<nums[(low<<1)+2]){
                i=(low<<1)+2;
            }
            if(i==low) break;
            swapSort(nums,low,i);
            low=i;
        }
    }

    public static void swapSort(int[] nums,int low,int high){
        int tmp=nums[high];
        nums[high]=nums[low];
        nums[low]=tmp;
    }

    //堆排序，有两步
    //第一步是建堆，建堆只需要从最后一个非叶子节点开始即可
    //第二步时排序
    public static void sort(int[] nums){
        buildHeap(nums);
        for (int num : nums) {
            System.out.print(num+" ");
        }
        System.out.println();
        //思路是将大顶堆的最后堆顶元素和最后一个元素交换，完成以后，再将新的堆顶元素和倒数第二个元素做交换
        //然后再进行堆化，直到堆中只剩下一个元素
        for (int i = nums.length-1; i >=0; --i) {
            swapSort(nums,0,i);
            heapifySort(nums,0,i-1);
        }
    }


    public static void main(String[] args) {
//        heap heap = new heap(10);
//        heap.insert(1);
//        heap.insert(2);
//        heap.insert(3);
//        heap.insert(4);
//        heap.insert(5);
//        heap.list();
//        heap.delete();
//        heap.list();
//        heap.delete();
//        heap.list();

        int[] nums={7,5,19,8,4,1,20,13,16};
        sort(nums);
        for (int num : nums) {
            System.out.print(num+" ");
        }
        System.out.println();
    }

}
