/**
 * 求一个整数的平方根，返回其整数部分
 * 牛顿切线法
 **/
public class Sqrt {

    //当两次结果相差一个非常小的数的时候，就认为找到了这个解
    private static int sqrt(int x){

        double x0=x;
        double err=1e-7;
        while (true){
           double result=(x0+x/x0)/2;
           if(Math.abs(x0-result)<err)
               break;
           x0=result;
        }
        return (int)x0;
//        long low=0;
//        long high=x;
//        long mid=0;
//        while (low<=high){
//            mid=low+((high-low)>>1);
//            if(mid*mid==x){
//                return (int) mid;
//            }else if(mid*mid>x){
//                high=mid-1;
//            }else{
//                if((mid+1)*(mid+1)>x){
//                    return (int) mid;
//                }else if((mid+1)*(mid+1)==x){
//                    return (int) mid+1;
//                }else{
//                    low=mid+1;
//                }
//            }
//        }
//        return (int)mid;
    }

    public static void main(String[] args) {
        System.out.println();
        System.out.println(Sqrt.sqrt(2147395599));
    }
}
