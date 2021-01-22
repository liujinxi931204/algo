/**
 * 荷兰国旗问题，对有大写、小写和数字的数字进行分类，使得分类后的数组按照小写字母、数字、大写字母的顺序排列
 * 各分组内部不要求有序
 * 主要思路就是借助于快排中的partition函数，定义三个指针，p0指向小写字母的最后的位置
 * p2指向大写字母开始的位置，i指向数字部分最后的位置
 * 这样的时间复杂度为O(n),空间复杂度为O(1)，属于原地排序
 *
 */
public class HollandFlag {

    private static void solve(char[] charList){
        int p0=0;
        int i=0;
        int p2=charList.length-1;

        //认为开始时数组的小写字母部分、数字部分和大写字母部分都是空

        while (i<=p2){
            if(charList[i] >='a' && charList[i]<='z'){
                //交换位置i和p0的元素
                swap(charList,i,p0);
                i++;
                p0++;
            }else if(charList[i]>='A' && charList[i]<='Z'){
                //交换位置i和p2,这里不需要i++，因为不知道交换回来的元素
                //属于哪一个部分，所以需要再比较一次i位置的元素
                swap(charList,i,p2);
                p2--;
            }else{
                i++;
            }
        }
    }

    //定义一个交换函数
    private static void swap(char[] chars,int i,int j){
        char tmp=chars[i];
        chars[i]= chars[j];
        chars[j]=tmp;
    }

    public static void main(String[] args) {
        char[] chars={'D','a','3','F','3','B','c','5','A','z','1'};
        HollandFlag.solve(chars);
        for (char aChar : chars) {
            System.out.print(aChar);
        }
        System.out.println();
    }
}
