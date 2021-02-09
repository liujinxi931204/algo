import java.util.LinkedList;
import java.util.Queue;

/**
 * 可以使用邻接表或者邻接矩阵来存储图，这里使用邻接表
 */
public class Graph {

    //顶点的个数
    private int v;
    //邻接表
    private LinkedList<Integer> adj[];

    public Graph(int v){
        this.v=v;
        adj=new LinkedList[v];
        for (int i = 0; i <v ; i++) {
            adj[i]=new LinkedList<>();
        }
    }

    public void addEdge(int s,int t){
        //无向图，所以需要在邻接表中存储两次
        adj[s].add(t);
        adj[t].add(s);
    }

    //广度优先搜索
    public void BFS(int s,int t){

        //建立一个boolean的数组，用来表示该节点是否已经被访问过
        boolean[] isVisited= new boolean[v];
        //队列用来记录当前正在被访问，但是子节点没有被访问的节点
        Queue<Integer> queue=new LinkedList<>();
        //用来记录搜索路径，不过是记录的是从哪一个节点到当前节点，例如prev[w]=t,代表t->w
        //所以打印的时候需要递归打印，才能表示从s到t的路径
        int[] prev=new int[v];

        queue.add(s);
        isVisited[s]=true;
        for (int i = 0; i <v; i++) {
            prev[i]=-1;
        }

        //首先从queue中取出队头元素w，去队头相应的邻接表中获取与该节点相连接的顶点q
        //如果该顶点没有访问过，加入到prev中，prev[q]=w,表示w->q
        //如果q==t，说明已经找到目的顶点，遍历结束
        //如果不等于，说明遍历还没有结束，需要继续
        while (queue.size()!=0){
            Integer w = queue.poll();
            for (int i = 0; i <adj[w].size() ; i++) {
                Integer q = adj[w].get(i);
                if(!isVisited[q]){
                    prev[q]=w;
                    if(q==t){
                        print(prev, s, t);
                        return;
                    }
                    isVisited[q]=true;
                    queue.add(q);
                }
            }

        }
    }

    //标记是否已经找到，如果找到，就可以退出整个递归
    boolean found=false;

    //深度优先遍历
    public void DFS(int s,int t){
        if(s==t)
            return;
        //建立一个boolean的数组，用来表示该节点是否已经被访问过
        boolean[] isVisited=new boolean[v];
        //用来记录搜索路径，不过是记录的是从哪一个节点到当前节点，例如prev[w]=t,代表t->w
        //所以打印的时候需要递归打印，才能表示从s到t的路径
        int[] prev=new int[v];
        recurDFS(s,t,isVisited,prev);
        print(prev,s,t);

    }

    public void recurDFS(int w,int t,boolean[] isVisited,int[] prev){
        if(found)
            return;
        isVisited[w]=true;
        if(w==t){
            found=true;
            return;
        }
        for (int i = 0; i <adj[w].size() ; i++) {
            Integer q = adj[w].get(i);
            if(!isVisited[q]){
                prev[q]=w;
                recurDFS(q,t,isVisited,prev);
            }
        }
    }

    //这里使用递归，s->t ==> s->prev[t]->t
    public void print(int[] prev,int s,int t){
        if(prev[t]!=-1 && s!=t){
            print(prev,s,prev[t]);
        }
        System.out.print(t+"");
    }

    public static void main(String[] args) {
        Graph graph = new Graph(8);
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

        graph.BFS(0,6);
        System.out.println();
        graph.DFS(0,6);
    }

}
