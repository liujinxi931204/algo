
/**
 * 快速排序就是将数组分为3个部分，小于pivot、等于pivot以及大于pivot
 * 然后采用分治的思想，在分的时候将数组分为这3部分，其实也就是在分的过程中
 * 排好了顺序
 *
 **/
public class QuickSort {

    private static void quickSort(int[] num){
        quick_sort(num, 0,num.length-1);
    }

    private static void quick_sort(int[] num,int start,int end){
        if(start>=end) return;

        int partition = partition(num, start, end);
        quick_sort(num,start,partition-1);
        quick_sort(num,partition+1,end);


    }

    //分区函数，其实是在这个函数内完成排序
    //以数组最后一个数组作为pivot，小于pivot的都在数组的左边，大于pivot的都在数组的右边
    //中间则是pivot
    private static int partition(int[] num,int start,int end){
        //设置最后一位是pivot
        int pivot=num[end];
        //这里其实用i代表的是小于pivot的部分的最后一个位置
        //思路类似于选择排序
        int i=start;
        for (int j = start; j <end ; j++) {
            if(num[j]<pivot){
                int tmp=num[j];
                num[j]=num[i];
                num[i]=tmp;
                i++;
            }
        }
        num[end]=num[i];
        num[i]=pivot;
        return i;
    }

    public static void main(String[] args) {
        int[] num={5,4,3,6,1,2};
        quickSort(num);
        for (int i = 0; i < num.length; i++) {
            System.out.print(num[i]);
        }
        System.out.println();


    }
}
