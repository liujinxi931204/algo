/**
 * 选择排序，将数组看成未排序和已排序两个部分
 * 每次从未排序的部分中选出最小的一个元素
 * 放在已排序部分的末尾
 **/
public class selectionSort {
    public int[] selectionSort(int[] numList){
        for (int i = 0; i <numList.length-1 ; i++) {
            //无序部分的第一个元素的位置
            int minIndex=i;
            for (int j=i;j <numList.length; j++) {
                if(numList[minIndex]>numList[j])
                    minIndex=j;
            }
            int tmp=numList[i];
            numList[i]=numList[minIndex];
            numList[minIndex]=tmp;
        }
       return  numList;
    }

    public static void main(String[] args) {
        selectionSort selectionSort = new selectionSort();
        int[] nums1={1,2,3,4,5,6};
        int[] ints = selectionSort.selectionSort(nums1);
        for (int anInt : ints) {
            System.out.print(anInt);
        }
        System.out.println();

        int[] nums2={6,5,4,3,2,1};
        int[] ints1 = selectionSort.selectionSort(nums2);
        for (int anInt : ints1) {
            System.out.print(anInt);
        }
        System.out.println();

        int[] nums3={4,5,6,3,2,1};
        int[] ints2 = selectionSort.selectionSort(nums3);
        for (int anInt : ints2) {
            System.out.print(anInt);
        }
        System.out.println();

    }




}
