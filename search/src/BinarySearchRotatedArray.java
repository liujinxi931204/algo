/**
 * 在循环有序(转折点左侧的数据都比右侧的数据大)数组中使用二分查找，例如{4,5,1,2,3}中查找2的位置
 * 循环数组不要想着直接定位到转折点，只需要知道转折点在中点的哪一侧就行
 * 中点的一侧还是循环有序数组，另一侧是有序数组
 * 如果要找的值在有序数组中，使用二分查找
 * 如果要找的值在循环有序数组中，则重复上述方法
 **/
public class BinarySearchRotatedArray {

    private static int binarySearchRotateArray(int[] nums,int val){
        //找到中点
        int low=0;
        int high=nums.length-1;
        while (low<=high){
            int mid=low+((high-low)>>1);
            //中点直接找到
            if(nums[mid]==val){
                return mid;
            }
            if(nums[low]>nums[mid]){
                //说明转折点在左侧
                if(nums[mid]<val && val<=nums[high]){
                    //说明要找的数值在严格递增的部分
                    low=mid+1;
                }else{
                    //说明要找的数值在循环有序的部分
                    high=mid-1;
                }
            }else{
                //说明转折点在右侧
                if(nums[mid]>val && val>=nums[low]){
                    //说明要找的数值在严格递增的部分
                    high=mid-1;
                }else{
                    //说明要找的数值在循环有序的部分
                    low=mid+1;
                }
            }
        }
        //没有找到
        return -1;
    }

    public static void main(String[] args) {
        int[] num={4,5,6,1,2,3};
        System.out.println(BinarySearchRotatedArray.binarySearchRotateArray(num, 2));
    }

}
