import java.util.HashMap;

/**
 * sunday字符串匹配算法，思路如下
 * 这个算法主要关注的是主串中为参加匹配的最末尾的下一个位置的字符
 * 如果这个字符没有出现在模式串中，则直接跳过该字符，即移动位数为模式串长度+1
 * 如果这个字符出现在模式串中，则移动位数是该字符在模式串最右出现的位置到模式串末尾的位置的距离
 */
public class SundayAlgorithm {


    public int solution(String des,String res){
        //模式串长度超过主串的长度直接返回-1
        if(des.length()<res.length())
            return -1;

        char[] desChars = des.toCharArray();
        char[] resChars = res.toCharArray();
        int n = desChars.length;
        int m=resChars.length;

        //可以使用一个hash表来记录模式串中每个元素出现的最右的位置
        HashMap<Character,Integer> table=new HashMap<>();
        for (int i = 0; i <m; i++) {
            table.put(resChars[i],i);
        }
        int i=0;
        while (i<n){
            int j=0;
            while (desChars[i+j]==resChars[j]){
                j++;
                //说明匹配成功
                if(j == m)
                    return i;
            }

            //发生不匹配时，根据主串中最后一个匹配的元素的下一个字符是否在模式串中出现来决定移动的距离
            if(!table.containsKey(desChars[i+m])){
                i+=m+1;
            }else{
                i+=m-table.get(desChars[i+m]);
            }

        }
        return -1;
    }

    public static void main(String[] args) {
        String s1="abcacaxcbcbacabc";
        String s2="bacabc";
        SundayAlgorithm sundayAlgorithm = new SundayAlgorithm();
        System.out.println(sundayAlgorithm.solution(s1, s2));
    }
}
