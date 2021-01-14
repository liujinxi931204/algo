import java.util.ArrayList;

/**
 * 台阶问题，每次可以走一步或者两步，n级台阶共有多少走法
 **/
public class Step {

    //使用递归，但是会存在重复计算的问题
    public int solve(int n){
        if(n==1)
            return 1;
        if(n==2)
            return 2;
        return solve(n-1)+solve(n-2);
    }

    public int new_solve(int n){
        ArrayList<Integer> numList=new ArrayList<>();
        //利用数组存储重复计算的结果，下次使用时可以直接获取

        if(numList.contains(n-1)){
            return numList.get(n-1);
        }
        if(n==1){
            numList.add(1);
            return 1;
        }

        if(n==2){
            numList.add(2);
            return 2;
        }
        int res=new_solve(n-1)+new_solve(n-2);
        numList.add(res);
        return  res;

    }

    public static void main(String[] args) {
        Step step = new Step();
        System.out.println(step.solve(1));
        System.out.println(step.new_solve(1));


        System.out.println(step.solve(5));
        System.out.println(step.new_solve(5));

        System.out.println(step.solve(10));
        System.out.println(step.new_solve(10));

    }
}
