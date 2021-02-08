import java.util.PriorityQueue;

/**
 * 求一个动态数组的中位数
 * 建立一个小顶堆和一个大顶堆，大顶堆存放排序好前一部分的数据，小顶堆存放后一部分数据
 * 小顶堆的堆顶就是这个动态数组的中位数，然后插入数据的时候比小顶堆堆顶小的数据插入大顶堆中，
 * 然后调整大顶堆的数据量，还满足每个堆各占50%，那么小顶堆的堆顶就是新的中位数
 */
public class MidNumCount {
    //建立一个小顶堆
    PriorityQueue<Integer> firstBigHeap=new PriorityQueue<>();
    //建立一个大顶堆,
    PriorityQueue<Integer> lastBigHeap=new PriorityQueue<>((o1, o2) -> o2-o1);

    //记录元素的个数
    private int count;

    public void solution(int[] nums){
        for (int i = 0; i <nums.length>>1 ; i++) {
            lastBigHeap.add(nums[i]);
        }
        for (int i = nums.length>>1; i <nums.length ; i++) {
            firstBigHeap.add(nums[i]);
        }
        count=nums.length;

    }

    public void insert(int data){
        count++;
        //当两个堆都为空时，先插入到大顶堆中
        if(lastBigHeap.isEmpty() && firstBigHeap.isEmpty()){
            lastBigHeap.add(data);
            return;
        }
        //如果插入的数据比小顶堆的堆顶元素大，则插入到小顶堆中
        //否则插入到大顶堆中
        if(data<=lastBigHeap.peek()){
            lastBigHeap.add(data);
        }else{
            firstBigHeap.add(data);
        }
        int countValue=count>>1;


        //从0开始计数,选择n/2作为中位数
        if(count%2==0){
            if(firstBigHeap.size()>countValue){
                lastBigHeap.add(firstBigHeap.remove());
            }else if(lastBigHeap.size()>countValue){
                firstBigHeap.add(lastBigHeap.remove());
            }
        }else{
            if(firstBigHeap.size()>countValue+1){
                lastBigHeap.add(firstBigHeap.remove());
            }else if(lastBigHeap.size()>countValue+1){
                firstBigHeap.add(lastBigHeap.remove());
            }
        }
    }

    public void getMid(){
        System.out.println(firstBigHeap.peek());
    }


    public static void main(String[] args) {
        MidNumCount midNumCount = new MidNumCount();
        int[] nums={1,2,3,4,5,6,7,8};
        midNumCount.solution(nums);
        midNumCount.getMid();

        midNumCount.insert(9);
        midNumCount.getMid();
        midNumCount.insert(10);
        midNumCount.getMid();
        midNumCount.insert(11);
        midNumCount.getMid();

    }




}
