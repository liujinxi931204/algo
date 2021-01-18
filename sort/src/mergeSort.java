import javax.imageio.metadata.IIOMetadataFormatImpl;
import javax.sound.midi.MidiDevice;
import java.util.concurrent.RunnableFuture;

/**
 * 归并排序
 **/
public class mergeSort {
    /**
     * 归并排序就是将数组分成两个部分，然后对这两个部分进行排序
     * 再将这两个排好序的部分合并在一起，这样整个数组都有序了
     * 归并排序利用了分治的思想，“分”是数组分成了两个子数组
     * “治”则是对这两个子数组进行排序，然后合并起来
     */
    public static void  mergeSort(int[] num){
        mergeSort_c(num,0,num.length-1);
    }

    public static void mergeSort_c(int[] num,int start,int end){
        //递归终止条件
        if(start>=end){
            return ;
        }
        int middle=(start+end)/2;
        //前半部分
        mergeSort_c(num, start, middle);
        mergeSort_c(num, middle + 1, end);
        //将排序好的两个部分合并在一起，然后拷贝给原来的数组
        merge(num,start,middle,end);
    }

    public static void merge(int[] num,int start,int middle,int end){
//        int[] tmp =new int[end-start+1];
//        int i=start;
//        int j=middle+1;
//        int k=0;
//        while (i<=middle && j<=end){
//            if(num[i]<=num[j]){
//                tmp[k++]=num[i++];
//            }else{
//                tmp[k++]=num[j++];
//            }
//        }
//        //判断哪个数组中还有剩余的数,将剩余的数全部拷贝到tmp中
//        if(i>middle){
//            while (j<=end){
//                tmp[k++]=num[j++];
//            }
//        }else {
//            while (i<=middle){
//                tmp[k++]=num[i++];
//            }
//        }
//
//        //感觉这里不好想
//        //将tmp中的数拷贝回num中
//        if (end - start + 1 >= 0) System.arraycopy(tmp, 0, num, start, end - start + 1);
        int[] leftTmp=new int[middle+2-start];
        int[] rightTmp=new int[end-middle+1];
        //设置两个哨兵，可以简化向tmp中拷贝数据的代码
        leftTmp[middle+1-start]=Integer.MAX_VALUE;
        rightTmp[end-middle]=Integer.MAX_VALUE;

        for (int i = 0; i <=middle-start; i++) {
            leftTmp[i]=num[start+i];
        }

        for (int i = 0; i < end-middle; i++) {
            rightTmp[i]=num[i+middle+1];
        }

        int k=start;
        int i=0;
        int j=0;
        while (k<=end){
            if(leftTmp[i]<=rightTmp[j])
                num[k++]=leftTmp[i++];
            else
                num[k++]=rightTmp[j++];
        }





    }



    public static void main(String[] args) {
        int[] nums={1,2,3,4,5,6};
        mergeSort.mergeSort(nums);
        for (int num : nums) {
            System.out.print(num);
        }
        System.out.println();

        int[] nums_1={6,5,4,3,2,1};
        mergeSort.mergeSort(nums_1);
        for (int num : nums_1) {
            System.out.print(num);
        }
        System.out.println();


        int[] nums_2={4,5,6,3,2,1};
        mergeSort.mergeSort(nums_2);
        for (int num : nums_2) {
            System.out.print(num);
        }
        System.out.println();


        int[] nums_3={2,3,1,5,6,4};
        mergeSort.mergeSort(nums_3);
        for (int num : nums_3) {
            System.out.print(num);
        }
        System.out.println();
    }
}
