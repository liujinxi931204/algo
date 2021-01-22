/**
 * 计数排序
 **/
public class CountSort {

    private static void sort(int[] num){

        //首先寻找数组内最大的元素
        int max=0;
        for (int i : num) {
            if (max < i)
                max = i;
        }
        //利用max，申请一个max+1的数组
        int[] tmp=new int[max+1];
        //计算每个元素的个数，放入tmp中
        for (int value : num) {
            tmp[value]++;
        }
        //一次累加，意味着i位置的元素可以放在排序好的数组中的最后一个位置
        for (int i = 1; i <tmp.length ; i++) {
            tmp[i]=tmp[i-1]+tmp[i];
        }
        //临时数组，临时存储排序好的结果
        int[] result=new int[num.length];
        for (int i = num.length-1; i>=0; --i) {
            result[tmp[num[i]]-1]=num[i];
            tmp[num[i]]--;
        }
        //将排序好的结果复制到num中
        System.arraycopy(result, 0, num, 0, result.length);

    }

    public static void main(String[] args) {
        int[] nums={2,5,3,0,2,3,0,3};
        CountSort.sort(nums);
        for (int num : nums) {
            System.out.print(num);
        }
        System.out.println();
    }
}
