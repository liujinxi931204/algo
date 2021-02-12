import java.util.HashMap;

/**
 * BM算法匹配字符串，BM算法从模式串的后面向前匹配
 * 需要注意区分坏字符和好字符后缀两种情况
 * 然后从坏字符和好后缀中选择能够滑动的最长的距离向后滑动
 */
public class BMAlgorithm {

    //坏字符规则，bad char
    private void generateBC(char[] b,HashMap<Character, Integer> table){
        //这里是坏字符表，记录的是模式串中每个字符出现的最后的位置
        for (int i = 0; i <b.length ; i++) {
            table.put(b[i],i);
        }
    }

    //好后缀规则,good string
    private void generateGS(char[] a,int[] suffix,boolean[] prefix){
        for (int i = 0; i <a.length ; ++i) {
            //初始化
            suffix[i]=-1;
        }
        for (int i = 0; i <a.length-1 ; ++i) {
            int j=i;
            int k=0;
            while ( j>=0 && a[j]==a[a.length-1-k]){
                --j;
                ++k;
//                suffix[k]=j+1;
            }
            suffix[k]=j+1;
            if(j==-1) prefix[k]=true;
        }
    }

    //j表示坏字符对应的模式串中的字符下标;m表示模式串长度
    private int moveByGS(int j,int m,int[] suffix,boolean[] prefix){
        int k=m-1-j;
        //如果sufixx[k]!=-1,说明模式串中存在好后缀
        if(suffix[k]!=-1){
            return j-suffix[k]+1;
        }
        //说明模式串中不存在好后缀，需要找到匹配的好后缀子串与模式串前缀子串
        for (int r=j+2;r<=m-1;++r){
            if(prefix[m-r]){
                return r;
            }
        }
        //说明上面两者都不存在，直接移动模式串的长度
        return m;
    }

    //对des、res两个字符串进行匹配，如果匹配，返回出现的位置，如果不匹配，返回-1
    public int solution(String des,String res){
        int n=des.length();
        int m=res.length();
        if(n<m)
            return -1;
        char[] charDes = des.toCharArray();
        char[] charRes = res.toCharArray();
        int[] suffix=new int[res.length()];
        boolean[] prefix=new boolean[res.length()];
        HashMap<Character,Integer> table=new HashMap<>();
        generateBC(charRes,table);
        generateGS(charRes,suffix,prefix);
        int i=0;
        while (i<=n-m){
            int j;
            //从后向前匹配
            for(j=m-1;j>=0;--j){
                if(charDes[i+j]!=charRes[j])//坏字符对应的字符在模式串中的下标是j
                    break;
            }
            //匹配成功
            if(j<0)
                return i;
            //找到主串中不匹配的字符在模式串的位置，如果不存在，则为-1
            Integer index= table.getOrDefault(charDes[i + j], -1);
            int x=j-index;
            int y=0;
            if(y<m-1){
                y=moveByGS(j,m,suffix,prefix);
            }
            i=i+Math.max(x,y);
        }
        return -1;
    }

    public static void main(String[] args) {
        String s1="abcacabcbcbacabc";
        String s2="cacabc";
        BMAlgorithm bmAlgorithm = new BMAlgorithm();
        System.out.println(bmAlgorithm.solution(s1, s2));
    }
}
