/**
 * 二分查找,二分查找也有很大局限性
 * 1.数组需要有序，如果无序的话需要先排序
 * 2.只能对数组进行二分查找，如果是链表的话，时间复杂度会非常高
 * 3.数据量也不能特别大，比如在1GB数组中查找，但是内存中没有连续的1GB的空间
 * 即使剩余内存空间再大，也无法申请1GB的数组
 **/
public class BinarySearch {

    //非递归形式的实现
    private static int BinarySearch_1(int[] nums,int val){
        int p1=0;
        int p2=nums.length-1;
        //这里是数组的中间节点，本来mid=(p1+p2)/2，这里p1+((p2-p1)>>1)
        //1.是为了防止p1和p2非常大的时候，超过整数的界限
        //2.右移1位相当于除以2，但是计算机中的移位运算要比触发运算快
        while (p1<=p2){
            //需要注意终止条件是p1>p2
            int mid=p1+((p2-p1)>>1);
            if(nums[mid]==val){
                return mid;
            }else if(nums[mid]>val){
                p2=mid-1;
            }else{
                p1=mid+1;
            }
        }
        //表示没有找到
        return -1;
    }


    //递归的形式
    //递归的递推公式为
    //BinarySearch_2(nums,low,high,val)=BinarySearch_2(nums,low,mid-1,val)+BinarySearch_2(nums,mid+1,high,int val)+find(mid)
    //终止条件是low>high
    private static int BinarySearch_2(int[] nums,int low,int high,int val){
        //没有找到
        if(low>high)
            return -1;
        int mid=low+((high-low)>>1);
        if(nums[mid]==val){
            return mid;
        }else if(nums[mid]>val){
            return BinarySearch_2(nums,low,mid-1,val);
        }else{
            return BinarySearch_2(nums,mid+1,high,val);
        }


    }
    public static void main(String[] args) {
        int[] nums={8,11,19,23,27,33,45,55,67,98};
        int i = BinarySearch.BinarySearch_1(nums, 19);
        System.out.println(i);
        int i1 = BinarySearch_2(nums, 0, nums.length - 1, 19);
        System.out.println(i1);
    }
}
