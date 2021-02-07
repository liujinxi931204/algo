/**
 * 求一个数组中的最大的k个元素
 * 首先将数组的前k个元素建立一个小顶堆，然后遍历后面的元素，如果有大于堆顶元素的，就替换为堆顶元素，然后调整堆
 * 遍历结束后就得到最大的k个元素
 *
 * 这k个元素其实不需要有序
 */
public class topK {

    public static int[] solution(int[] nums,int k){
        //用前k个元素建立一个小顶堆
        int[] result=new int[k];
        for (int i = 0; i < k; i++) {
            insert(result,i,nums[i]);
        }
        //遍历后续的数组，如果大于堆顶元素，将替换堆顶元素，然后再进行堆化
        for (int i = k; i <nums.length ; i++) {
            if(nums[i]>result[0]){
                result[0]=nums[i];
                heapify(result,0,k-1);
            }
        }
        return result;
    }

    public static void insert(int[] nums,int i,int data){
        nums[i]=data;
        while (i>0 && nums[i]<nums[(i-1)>>1]){
            swap(nums,(i-1)>>1,i);
            i=(i-1)>>1;
        }
    }

    public static void swap(int[] nums,int low,int high){
        int tmp=nums[high];
        nums[high]=nums[low];
        nums[low]=tmp;
    }

    public static void heapify(int[] nums,int low,int n){
        int i=low;
        while (true){
            if((low<<1)+1<=n && nums[low]>nums[(low<<1)+1])
                i=(low<<1)+1;
            if((low<<1)+2<=n && nums[(low<<1)+1]>nums[(low<<1)+2])
                i=(low<<1)+2;
            if(i==low)
                break;
            swap(nums,low,i);
            low=i;
        }
    }

    public static void main(String[] args) {
        int[] nums={7,5,19,8,4,1,20,13,16};
        int[] solution = solution(nums, 5);
        for (int i : solution) {
            System.out.print(i+" ");
        }
        System.out.println();
    }
}
