import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 使用链式结构定义一颗二叉树
 */

class Node<T>{
    T data;
    Node left;
    Node right;

    public Node(T data) {
        this.data = data;
    }

    public T getData(){
        return this.data;
    }

    public Node getLeft(){
        return this.left;
    }

    public Node getRight(){
        return this.right;
    }

    @Override
    public String toString() {
        return data+"  ";
    }
}

public class BinaryTree<T> {
    //定义根节点，根据这个根节点可以遍历整个二叉树
    public Node root;

    public void setRoot(T data){
        this.root=new Node<>(data);
    }

    public void setLeftChild(T data,Node parent){
        parent.left= new Node<>(data);
    }

    public void setRightChild(T data,Node parent){
        parent.right=new Node<>(data);
    }

    //前序遍历，递归形式
    private void preOrder(Node node){
        if(node==null){
            return;
        }
        System.out.print(node);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void preOrder(){
//        this.preOrder(root);
        //非递归形式，主要使用栈来控制node指针的访问顺序，没有左孩子节点的时候，回退的上一个节点
        //然后访问右孩子节点
//        if(this.root==null){
//            return;
//        }
//        Stack<Node> stack=new Stack<>();
//        Node node=root;
//        while (!stack.isEmpty()|| node!=null){
//            while (node!=null){
//                System.out.print(node);
//                stack.push(node);
//                node=node.left;
//            }
//            node = stack.pop();
//            node=node.right;
//        }
        //非递归的另一种形式
        if(root==null){
            return;
        }
        Stack<Node> stack=new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            Node node=stack.pop();
            System.out.print(node);
            if(node.right!=null){
                stack.push(node.right);
            }
            if(node.left!=null){
                stack.push(node.left);
            }
        }
    }

    //中序遍历
    private void inOrder(Node node){
        if(node==null){
            return;
        }
        inOrder(node.left);
        System.out.print(node);
        inOrder(node.right);
    }

    public void inorder(){
//        this.inOrder(root);
        if(root==null){
            return;
        }
        Stack<Node> stack=new Stack<>();
        Node node=root;
        while (!stack.isEmpty()||node!=null){
            while (node!=null){
                stack.push(node);
                node=node.left;
            }
            node=stack.pop();
            System.out.print(node);
            node=node.right;
        }
    }

    //后序遍历
    private void postOrder(Node node){
        if(node==null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node);
    }

    public void postOrder(){
//        this.postOrder(root);

        //后序遍历，非递归的形式
        if(root==null){
            return;
        }
        Stack<Node> stack=new Stack<>();
        Stack<Node> nodeStack=new Stack<>();
        Node node=root;
        stack.push(root);
        while (!stack.isEmpty()){
            node=stack.pop();
            nodeStack.push(node);
            if(node.left!=null){
                stack.push(node.left);
            }
            if(node.right!=null){
                stack.push(node.right);
            }
        }
        while (!nodeStack.isEmpty()){
            System.out.print(nodeStack.pop());
        }

    }

    //层序遍历,利用队列，处理一个节点时，如果这个节点的孩子节点不为空，就将其孩子节点入队
    public void levelOrder(){
        if(root==null){
            return;
        }
        Queue<Node> queue=new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int currentSize=queue.size();
            for (int i = 1; i <=currentSize ; ++i) {
                Node node = queue.remove();
                System.out.print(node);
                if(node.left!=null){
                    queue.add(node.left);
                }
                if(node.right!=null){
                    queue.add(node.right);
                }
            }
        }
    }

    //求二叉树的高度
    public int high(Node root){
        //递归思想，树的高度是max(左子树高度，右子树高度)+1
        //终止条件是node=null,return 0;
//        if(root==null){
//            //空树的高度为0
//            return 0;
//        }
//        return Math.max(high(root.left),high(root.right))+1;

        //利用层序遍历，每遍历完一层且队列不为空时，高度+1

        if(root==null){
            return 0;
        }
        Queue<Node> queue=new LinkedList<>();
        Node node=root;
        queue.add(root);
        int high=0;
        while (!queue.isEmpty()){
            int currentSize=queue.size();
            for (int i = 1; i <=currentSize ; i++) {
                node= queue.remove();
                if(node.left!=null){
                    queue.add(node.left);
                }
                if(node.right!=null){
                    queue.add(node.right);
                }
            }
            ++high;

        }
        return high;

    }


    public static void main(String[] args) {
        BinaryTree<String> stringBinaryTree = new BinaryTree<>();
        stringBinaryTree.setRoot("A");
        Node root = stringBinaryTree.root;
        stringBinaryTree.setLeftChild("B",root);
        stringBinaryTree.setRightChild("C",root);
        stringBinaryTree.setLeftChild("D",root.getLeft());
        stringBinaryTree.setRightChild("E",root.getLeft());
        stringBinaryTree.setLeftChild("F",root.getRight());
        stringBinaryTree.setRightChild("G",root.getRight());
        stringBinaryTree.setLeftChild("I",root.getRight().getLeft());

        System.out.println("前序遍历");
        stringBinaryTree.preOrder();
        System.out.println();

        System.out.println("中序遍历");
        stringBinaryTree.inorder();
        System.out.println();

        System.out.println("后序遍历");
        stringBinaryTree.postOrder();
        System.out.println();

        System.out.println("层序遍历");
        stringBinaryTree.levelOrder();
        System.out.println();

        System.out.println(stringBinaryTree.high(stringBinaryTree.root));

    }

}
