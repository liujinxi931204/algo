/**
 * 查询一个无序数组中第K大的数，
 * 思路就是使用快排中partition函数一样，以数组的最后一个数做pivot
 * 比pivot大的元素都在右边，比pivot小的元素都在左边，数组按照从小到大的顺序排列
 * 如果在右边,那么依然寻找第K大的数
 * 如果在左边,那么需要寻找第p+k-end-1大的数
 * 如果end+1-k==p,说明pivot就是需要寻找的数
 * 
 * 还可以将数组按照从大到小的顺序排列
 **/
public class FindNth {

    public static int findK(int[] nums,int k){
        return findK(nums,0,nums.length-1,k);
    }

    public static int findK(int[] nums,int start,int end,int k){
        int p=partition(nums,start,end);
        //如果end+1-k==p,说明pivot就是要找的元素，直接返回
        if(end+1-k==p){
            return nums[p];
        }else if(end+1-k<p){
            //说明第K大的元素在pivot的左边，那么寻找的是新数组里第p+k-end-1大的元素
            return findK(nums,start,p-1,p+k-end-1);
        }else{
            //说明第K大的元素在pivot的右边，那么依然是寻找新数组中的第K大的元素
            return findK(nums,p+1,end,k);
        }
    }

    public static int partition(int[] nums,int start,int end){
        int pivot=nums[end];
        int i=start;
        for(int j=start;j<=end;j++){
            if(nums[j]<pivot){
                int tmp=nums[j];
                nums[j]=nums[i];
                nums[i]=tmp;
                i++;
            }
        }
        nums[end]=nums[i];
        nums[i]=pivot;
        return i;
    }

    public static void main(String[] args) {
        int[] nums={4,2,5,12,3};
        System.out.println(findK(nums,3));
    }
}
