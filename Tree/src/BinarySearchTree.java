import java.util.Stack;

/**
 * 二叉查找树，每一个结点的左子节点的值小于当前节点，右子节点的值大于当前节点
 */


public class BinarySearchTree{

    static class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data=data;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        @Override
        public String toString() {
            return data+" ";
        }
    }

    //定义二叉排序树的根节点
    public Node root;

    public void setRoot(int data){
        this.root= new Node(data);
    }
    public Node getRoot(){
        return this.root;
    }
    
    public void setLeftChild(int data,Node parent){
        parent.left=new Node(data);
    }
    
    public void setRightChild(int data,Node parent){
        parent.right=new Node(data);
    }
    
    
    //中序遍历，二叉排序树的中序遍历是有个有序的数组
    //非递归形式
    public void midOrder(){
        if(root==null){
            return;
        }
        Stack<Node> stack= new Stack<Node>();
        //使用一个指针指向树的根节点
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
    
    //给一个二叉排序树添加一个节点，将该节点添加到叶子节点的位置
    //满足二叉排序树的要求
    public void add(int data){
        if(root==null){
            this.setRoot(data);
            return;
        }
        //这里添加有一个前提就是添加的数据不会和树中的数据重复
        Node node=root;
        while (node!=null){
            if(data< node.data){
                if(node.left==null){
                    node.left=new Node(data);
                    return;
                }
                node=node.left;
            }
            if(data> node.data){
                if(node.right==null){
                    node.right=new Node(data);
                    return;
                }
                node=node.right;
            }
        }
    }

    //删除一个节点
    public void delete(int data){
        //树为空
        if(root==null){
            return;
        }
        //node为要删除的节点，parent为要删除的节点的父节点
        Node node=root;
        Node parent=null;
        while (node!=null&&node.data!=data){
            parent=node;
            if(data<node.data){
                node=node.left;
            }else {
                node=node.right;
            }
        }
        //没有找到要删除的节点
        if(node==null){
            return;
        }
        //要删除的节点有左孩子和有孩子
        if(node.left!=null&&node.right!=null){
            //查找该节点右子树中的最小的节点
            Node minNode=node.right;
            Node minParent=node;
            while (minNode.left!=null){
                minParent=minNode;
                minNode=minParent.left;
            }
            //用这个最小节点的值替换要删除的节点的值
            node.data=minNode.data;
            //把删除原来的节点替换为删除这个最小值的节点,也就是删除minNode节点
            //接下来需要判断minNode有没有右子节点，肯定是没有左子节点的
            parent=minParent;
            node=minNode;
        }

        Node child=null;
        //这里不需要判断node的左孩子是否存在，因为肯定是没有左孩子的
        if(node.right!=null){
            //和上述删除有两个子节点的节点合并为同一种情况，就是删除最小值的节点，并且该节点只有右子树
            child=node.right;
        }

        if(parent==null){
            //删除的是根节点
            root=child;
        }else if(parent.left==node){
            parent.left=child;
        }else{
            parent.right=child;
        }

    }

    public int get(int data){
        if(root==null){
            //表示没有找到
            return -1;
        }
        Node node=root;
        while (node!=null){
            if(data == node.data){
                return node.data;
            }else if(data< node.data){
                node=node.left;
            }else{
                node= node.right;
            }
        }
        //没有找到
        return -1;
    }


    //获取二叉排序树的最小的节点
    public int getMin(){
        if(root==null){
            //树为空
            return -1;
        }
        Node node=root;
        while (node.left!=null){
            node=node.left;
        }
        return node.data;
    }


    //获取排序二叉树的最大的子节点
    public int getMax(){
        if(root==null){
            return -1;
        }
        Node node=root;
        while (node.right!=null){
            node=node.right;
        }
        return node.data;
    }

    //找到一个节点的父节点
    public Node getParent(int data){
        if(root==null||root.data==data){
            return null;
        }
        Node node=root;
        Node parent=null;
        while (node!=null){
            if(node.data==data){
                return parent;
            }else if(data<node.data){
                parent=node;
                node=node.left;
            }else{
                parent=node;
                node=node.right;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.setRoot(33);
        binarySearchTree.setLeftChild(17, binarySearchTree.getRoot());
        binarySearchTree.setRightChild(50,binarySearchTree.getRoot());
        binarySearchTree.setLeftChild(13,binarySearchTree.getRoot().getLeft());
        binarySearchTree.setRightChild(18,binarySearchTree.getRoot().getLeft());
        binarySearchTree.setLeftChild(34,binarySearchTree.getRoot().getRight());
        binarySearchTree.setRightChild(58,binarySearchTree.getRoot().getRight());
        binarySearchTree.setRightChild(16,binarySearchTree.getRoot().getLeft().getLeft());
        binarySearchTree.setRightChild(25,binarySearchTree.getRoot().getLeft().getRight());
        binarySearchTree.setLeftChild(51, binarySearchTree.getRoot().getRight().getRight());
        binarySearchTree.setRightChild(66, binarySearchTree.getRoot().getRight().getRight());
        binarySearchTree.setLeftChild(19,binarySearchTree.getRoot().getLeft().getRight().getRight());
        binarySearchTree.setRightChild(27, binarySearchTree.getRoot().getLeft().getRight().getRight());
        binarySearchTree.setRightChild(55,binarySearchTree.getRoot().getRight().getRight().getLeft());

        binarySearchTree.midOrder();
        System.out.println();
        binarySearchTree.add(56);
        binarySearchTree.midOrder();
        System.out.println();

        System.out.println(binarySearchTree.getMin());
        System.out.println(binarySearchTree.getMax());

        System.out.println(binarySearchTree.getParent(20));


    }
}



