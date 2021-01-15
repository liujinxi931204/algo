/**
 * 插入排序，将一个序列分为有序和无序的两个部分
 * 初始的时候将第一个元素视为有序的，剩下的视为无序的
 * 将无序部分的第一个元素依次和有序部分进行比较，找到合适的位置插入
 **/
public class insertSort {

    /**
     * 对序列进行插入排序
     * @param numList
     * @return
     */
    public int[] insertSort(int[] numList){
//        for (int i = 1; i <numList.length ; ++i) {
//            for (int j = 0; j <i ; ++j) {
//                //无序部分的第一个和有序部分的没有一个去比较
//                //直到找到第一个不满足排序要求的位置
//                if(numList[j]<numList[i])
//                    continue;
//                int data=numList[i];
//                while (i>j){
//                    numList[i]=numList[i-1];
//                    --i;
//                }
//                numList[j]=data;
//            }
//        }
//        return numList;

        //将第一个元素略过，因为将它看作是有序的部分
        for (int i = 1; i <numList.length ; i++) {
            int data=numList[i];
            int j=i-1;
            for (;j >=0 ; --j) {
                //寻找插入的位置
                //从后往前匹配，如果大于，则向后移动
                if(numList[j]>data){
                    numList[j+1]=numList[j];
                }else{
                    break;
                }
            }
            numList[j+1]=data;//插入数据
        }
        return numList;
    }


    public static void main(String[] args) {
        insertSort insertSort = new insertSort();
        int[] nums={1,2,3,4,5,6};
        int[] ints = insertSort.insertSort(nums);
        for (int anInt : ints) {
            System.out.print(anInt);
        }
        System.out.println();


        int[] nums1={6,5,4,3,2,1};
        int[] ints1 = insertSort.insertSort(nums1);
        for (int anInt : ints1) {
            System.out.print(anInt);
        }
        System.out.println();

        int[] nums2={4,5,6,3,2,1};
        int[] ints2 = insertSort.insertSort(nums2);
        for (int anInt : ints2) {
            System.out.print(anInt);
        }
        System.out.println();

    }
}
