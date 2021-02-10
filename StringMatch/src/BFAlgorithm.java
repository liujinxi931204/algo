/**
 * 字符串匹配算法，Brute Fource算法，就是暴力匹配算法，也称朴素匹配算法
 * BF的时间复杂度位O(n*m)，其中n是主串的长度，m是模式串的长度
 */
public class BFAlgorithm {

    public int BF(String des,String sou){
        //如果主串的长度小于了模式串的长度，肯定是匹配不出来的，直接返回-1
        //表示没有
        if(des.length()<sou.length()){
            return -1;
        }
        char[] dest = des.toCharArray();
        char[] source = sou.toCharArray();

        for (int i = 0; i <dest.length ; i++) {
            int k=0;
            for (int j = 0; j <source.length ; j++) {
                if(dest[i+j]==source[j]){
                    ++k;
                    if(k==source.length){
                        return i;
                    }
                }else {
                    break;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        BFAlgorithm bfAlgorithm = new BFAlgorithm();
        String  des="0001001";
        String sou="001";
        int bf = bfAlgorithm.BF(des, sou);
        System.out.println(bf);
    }
}
