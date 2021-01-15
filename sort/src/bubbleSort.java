import java.util.concurrent.ForkJoinPool;

/**
 * 冒泡排序
 * 最好时间复杂度为O(n),最差时间复杂度为O(n^2),平均时间复杂度为O(n^2)
 * 稳定的排序
 * 思路就是比较相邻的两个元素，如果不满足大小要求，则交换位置
 * 当没有元素交换位置，说明排序完成
 */
public class bubbleSort {

    public int[] bubbleSort(int[] numList){
            //其实i用来记录冒泡的次数
            for (int i = 0; i < numList.length; ++i) {
                //判断是否有元素交换位置
                boolean flag=false;
                for (int j = 0; j < numList.length - i - 1; ++j) {
                    if (numList[j] > numList[j + 1]) {
                        int tmp = numList[j];
                        numList[j] = numList[j + 1];
                        numList[j + 1] = tmp;
                        flag=true;
                    }
                }
                //如果标志位为false，说明没有元素交换，排序完成
                if(!flag){
                    break;
                }
            }
            return numList;
    }


    public static void main(String[] args) {
        bubbleSort bubbleSort = new bubbleSort();
        int[] num={1,2,3,4,5,6};
        int[] ints = bubbleSort.bubbleSort(num);
        for (int anInt : ints) {
            System.out.print(anInt);
        }
        System.out.println();

        int[] num2={6,5,4,3,2,1};
        int[] ints1 = bubbleSort.bubbleSort(num2);
        for (int i : ints1) {
            System.out.print(i);
        }
        System.out.println();
    }
}
