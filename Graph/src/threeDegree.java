import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 寻找一个图中的某一个顶点的所有三度以内包括三度的顶点
 * 和某一个顶点相连的顶点是一度的顶点，再与其相连的顶点是二度的顶点
 * 最后找到所有一度、二度和三度的顶点
 */
public class threeDegree {

    //顶点数量
    private int v;
    //邻接表
    private LinkedList<Integer>[] adj;

    public threeDegree(int v){
        this.v=v;
        adj=new LinkedList[v];
        for (int i = 0; i <v ; i++) {
            adj[i]=new LinkedList<>();
        }
    }

    public void addEdge(int s,int t){
        adj[s].add(t);
        adj[t].add(s);
    }

    //思路就是利用图的广度优先遍历
    public int[] solution(int s){
        if(v==0){
            return new int[0];
        }
        //记录当前节点是否被访问过
        boolean[] isVisited=new boolean[v];
        //用来记录当前访问，相邻节点没有被访问的节点
        Queue<Integer> queue=new LinkedList<>();
        ArrayList<Integer> res=new ArrayList<>();

        queue.add(s);
        isVisited[s]=true;
        res.add(s);
        int degree=0;
        while (degree<3){
            int currentSize=queue.size();
            for (int j = 0; j <currentSize ; j++) {
                Integer w = queue.poll();
                for (int i = 0; i <adj[w].size() ; i++) {
                    Integer q = adj[w].get(i);
                    if(!isVisited[q]){
                        isVisited[q]=true;
                        queue.add(q);
                        res.add(q);
                    }
                }
            }
            ++degree;
        }
        int[] result=new int[res.size()];
        for (int i = 0; i <result.length ; i++) {
            result[i]=res.get(i);
        }
        return result;
    }


    public static void main(String[] args) {
        threeDegree graph = new threeDegree(8);
        graph.addEdge(0,1);
        graph.addEdge(0,3);
        graph.addEdge(1,2);
        graph.addEdge(1,4);
        graph.addEdge(3,4);
        graph.addEdge(2,5);
        graph.addEdge(4,5);
        graph.addEdge(4,6);
        graph.addEdge(5,7);
        graph.addEdge(6,7);

        int[] solution = graph.solution(0);
        for (int i : solution) {
            System.out.print(i+" ");
        }
        System.out.println();
        int[] solution1 = graph.solution(4);
        for (int i : solution1) {
            System.out.print(i+" ");
        }


    }
}
