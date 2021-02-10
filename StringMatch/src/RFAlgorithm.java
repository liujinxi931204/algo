/**
 * RF算法，全称位Rabin-Karp算法，主要思想还是暴力匹配，不过该算法会将每一个主串中
 * 长度和模式串相等的子串计算一个hash值，再计算该hash值的时候和模式串的hash值进行比较
 * 如果hash值相等，再对子串进行BF匹配
 * 如果hash值不相等，可以直接跳过
 */
public class RFAlgorithm {

    public int RF(String  des,String sou){

        if(des.length()<sou.length())
            return -1;
        char[] dest = des.toCharArray();
        char[] source = sou.toCharArray();

        int n=dest.length;
        int m=source.length;
        //用来记录每个子串的hash值，如果匹配到了，不用再向后计算了
        int[] hash=new int[n-m+1];
        //假设字符串都在a-z之间，可以用26进制数来表示，a=0,b=1...z=25
        //那么，"cba"=3*26*26+2*26+0*1=1353
        //并且前一个子串和后一个子串还有一定的关系

        int[] table=new int[m];
        int s=1;
        for (int i = 0; i <m ; i++) {
            table[i]=s;
            s*=26;
        }
        //计算模式串的hash值
        s=0;
        for (int i = 0; i <source.length ; i++) {
            s+=(source[i]-'a')*table[m-1-i];
        }
        //计算主串中每个子串的hash值
        for (int i = 0; i <n-m+1 ; i++) {
            int r=0;
            for (int j = 0; j <m; j++) {
                r+=(dest[i+j]-'a')*table[m-j-1];
            }
            hash[i]=r;
        }
        //这里没有hash碰撞的问题，如果hash碰撞就需要BF算法去匹配了
        for (int i = 0; i <hash.length ; i++) {
            if(hash[i]==s)
                return i;
        }
        //没有找到
        return -1;
    }

    public static void main(String[] args) {
        RFAlgorithm rfAlgorithm = new RFAlgorithm();
        String des="abdefr";
        String sou="efr";
        int rf = rfAlgorithm.RF(des, sou);
        System.out.println(rf);
    }
}
