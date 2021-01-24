import javax.security.auth.login.AccountNotFoundException;
import javax.swing.text.StyledEditorKit;

/**
 * 二分查找的变种
 * 1. 寻找第一个等于给定值的位置
 * 2. 寻找第二个等于给定值的位置
 * 3. 寻找第一个大于等于给定值的位置
 * 4. 寻找最后一个小于等于给定值的位置
 **/
public class AnotherBinarySearch {

    //寻找第一个等于给定值的位置
    //思路还是类似于二分查找，就是找到mid以后需要判断，mid的前一个是不是也等于给定的值
    //如果mid==0或者num[mid-1]!=x,说明mid就是要找的位置
    //如果上述不满足，high=mid-1,说明在[low,mid-1]的范围内还存在等于给定的值

    private static int binarySearchFirst(int[] nums,int val){
        int low=0;
        int high=nums.length-1;
        while (low<=high) {
            int mid = low + ((high - low) >> 1);
            if (nums[mid] > val) {
                high = mid - 1;
            } else if (nums[mid] < val) {
                low = mid + 1;
            } else if (mid == 0 || nums[mid - 1] != val)
                return mid;
            else{
                //说明在[low,mid-1]的范围内还存在等于给定的值
                high=mid-1;
            }
        }
        //不存在
        return -1;
    }

    private static int binarySearchLast(int[] nums,int val){
        int low=0;
        int high=nums.length-1;
        while (low<=high){
            int mid=low+((high-low)>>1);
            if(nums[mid]<val){
                low=mid+1;
            }else if(nums[mid]>val){
                high=mid-1;
            }else if(mid==nums.length-1 || nums[mid+1]!=val){
                return mid;
            } else{
                low=mid+1;
            }
        }
        //没有找到
        return -1;
    }

    //第一个大于等于的位置
    private static int binarySearchFirstGE(int[] nums,int val){
        int low=0;
        int high=nums.length-1;
        while (low<=high){
            int mid=low+((high-low)>>1);
            if(nums[mid]<val){
                low=mid+1;
            }else{
                if(mid==0 || nums[mid-1]<val){
                    return mid;
                }else{
                    high=mid-1;
                }
            }
        }
        return -1;
    }

    private static int binarySearchLastLE(int[] nums,int val){
        int low=0;
        int high=nums.length-1;
        while (low<=high){
            int mid=low+((high-low)>>1);
            if(nums[mid]>val){
                high=mid-1;
            }else{
                if(mid==nums.length-1 || nums[mid+1]>val){
                    return mid;
                }else{
                    low=mid+1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums={1,3,4,5,6,8,8,8,11,18};
        System.out.println(AnotherBinarySearch.binarySearchFirst(nums, 8));
        System.out.println(AnotherBinarySearch.binarySearchLast(nums, 8));
        System.out.println(AnotherBinarySearch.binarySearchFirstGE(nums, 100));
        System.out.println(AnotherBinarySearch.binarySearchLastLE(nums, 100));
    }
}
