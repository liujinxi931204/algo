/**
 * 查询一个无序数组中第K大的数，
 * 思路就是使用快排中partition函数一样，以数组的最后一个数做pivot
 * 比pivot大的数都在左边，比pivot小的数都在右边，pivot的位置为p
 * 如果p+1=k,说明p位置上的数就是需要找的，
 * 如果k<p+1,说明要找的数在[0,p-1]内，重复上述
 * 如果k>p+1，说明要找的数在[p+1,end]内，重复上述
 **/
public class FindNth {

    //寻找数组中第k大的数
    private static int solve(int[] num,int k){
        return solve_findnth(num,0,num.length-1,k);
    }

    private static int solve_findnth(int[] num,int start,int end,int k){
        int p = partition(num, start, end);
        //说明已经找到所需要的数，直接返回
        if(p+1==k){
            return num[p];
        }else if(k<p+1){
            return solve_findnth(num,start,p-1,k);
        }else{
            return solve_findnth(num,p+1,end,k);
        }
    }

    private static int partition(int[] num,int start,int end){
        int i=start;
        int pivot=num[end];
        for (int j = start; j <end ; j++) {
            //把数组分为两个部分，左边是大于pivot的，i指向的是大于pivot的最后的位置
            //右边是小于pivot的，j指向的是第一个小于pivot的位置
            if(num[j]>pivot){
                int tmp=num[i];
                num[i]=num[j];
                num[j]=tmp;
                i+=1;
            }
        }
        num[end]=num[i];
        num[i]=pivot;
        return i;
    }

    public static void main(String[] args) {
        int[] nums={6,1,3,5,7,2,4,9,11,8};
        int solve = FindNth.solve(nums, 10);
        System.out.println(solve);
    }
}
