/**
 * KMP算法
 */
public class KMP {

    /**
     * KMP算法中求next数组，利用next[i-1]求得next[i]
     * 数组的下标是模式串子串的长度
     * 数组的内容是子串中最长匹配前后缀中前缀的结尾位置
     * 思想如下：
     * 如果next[i-1]=k-1,说明子串b[0..k-1]中b[0..k-1]和b[i-k..i-1]是最长匹配的前后缀
     * 如果b[k]==b[i]，那么next[i]=k
     * b[k]!=b[i]，需要在b[0..k-1]和b[i-k..i-1]中找出次长的匹配的前后缀b[0,k-i+r]和b[r..i-1]
     * 然后判断b[k-i+r+1]是否等于b[i]
     * 此时k=next[k]
     * @param b
     * @return
     */
    public int[] getNext(char[] b){
        int[] next=new int[b.length];
        //-1表示没有最长匹配的前后缀
        next[0]=-1;
        int k=-1;
        for(int i=1;i<b.length;++i){
            while (k!=-1 && b[k+1]!=b[i])
                k=next[k];
            if(b[k+1]==b[i])
                ++k;
            next[i]=k;
        }
        return next;
    }

    public int solution(String des,String res){
        if(des.length()<res.length())
            return -1;
        char[] desChars = des.toCharArray();
        char[] resChars = res.toCharArray();
        int n=des.length();
        int m=res.length();
        int[] next = getNext(resChars);
        int j=0;
        for (int i = 0; i <n ; ++i) {
            //如果没有匹配，就需要将模式串向后滑动
            //滑动的距离由next数组决定
            while (j>0 && desChars[i]!=resChars[j])
                j=next[j-1]+1;
            if(desChars[i]==resChars[j])
                ++j;
            //匹配完成
            if(j==m)
                return i-m+1;
        }
        //没有找到
        return -1;
    }

    public static void main(String[] args) {
        String s1="abcacabcbcbacabc";
        String s2="bacabc";
        KMP kmp = new KMP();
        System.out.println(kmp.solution(s1, s2));
    }
}
