import java.util.ArrayList;
import java.util.List;

/**
 * 对字符串进行排序，要求小写字母在前，大写字母在后，小写字母内部和大写字母内部不要求有序
 * 准备两个桶，小写字母桶和大写字母桶，遍历字符串，将小写字母、大写字母放入相应的桶中
 * 然后遍历两个桶，赋值给原来的数组
 *
 * 双指针,使用双指针遍历char型数组，其中i表示小写部分最后一个元素的位置，j表示大写部分的第一个位置
 **/
public class SortChar {

    private static void sortChar(char[] charList){
        int i=0;
        int j=0;
        for (; j<charList.length ; ++j) {
            if(charList[j]>='a' && charList[j]<='z'){
                char tmp=charList[j];
                charList[j]= charList[i];
                charList[i]=tmp;
                i+=1;
            }
        }
        //不存在list扩容的问题
//        List<Character> lower=new ArrayList<>(charList.length);
//        List<Character> upper=new ArrayList<>(charList.length);
//        for (char c : charList) {
//            if (c >= 'a' && c <= 'z') {
//                lower.add(c);
//            } else if (c >= 'A' && c <= 'Z') {
//                upper.add(c);
//            }
//        }
//        int i=0;
//        int j=0;
//        for (; i <lower.size() ; i++) {
//            charList[i]= lower.get(i);
//        }
//        for(;j<upper.size();j++){
//            charList[i+j]=upper.get(j);
//        }
    }

    public static void main(String[] args) {
        char[] chars={'D','a','F','B','c','A','z'};
        SortChar.sortChar(chars);
        for (char aChar : chars) {
            System.out.print(aChar);
        }
        System.out.println();
    }
}
