package src.com.minic.compiler;

import src.com.minic.node.Node;

/**
 * @author: 陈健航
 * @description:
 * @since: 2020/3/31 12:57
 * @version: 1.0
 */
@SuppressWarnings("ALL")
public class JJTMyCompilerState {
    /**
     * 节点
     */
    private final java.util.List<Node> nodes;
    /**
     * 标记
     */
    private final java.util.List<Integer> marks;

    /**
     * 堆栈上的节点数
     */
    private int sp;
    /**
     * 当前标记
     */
    private int mk;
    /**
     * 构造函数
     */
    private boolean node_created;

    public JJTMyCompilerState() {
        nodes = new java.util.ArrayList<Node>();
        marks = new java.util.ArrayList<Integer>();
        sp = 0;
        mk = 0;
    }

    /**
     * 确定当前节点是否实际上已关闭并被推送。仅应在节点作用域的最终用户操作中调用此方法
     *
     * @return
     */
    public boolean nodeCreated() {
        return node_created;
    }

    /**
     * 调用它以重新初始化节点堆栈。它由解析器的ReInit()方法自动调用。
     */
    public void reset() {
        nodes.clear();
        marks.clear();
        sp = 0;
        mk = 0;
    }

    /**
     * 返回AST的根节点。仅在成功解析后调用this才有意义。
     *
     * @return
     */
    public Node rootNode() {
        return nodes.get(0);
    }

    /**
     * 将节点压入堆栈。
     *
     * @param n
     */
    public void pushNode(Node n) {
        nodes.add(n);
        ++sp;
    }

    /**
     * 返回堆栈顶部的节点，并将其从堆栈中删除。
     *
     * @return
     */
    public Node popNode() {
        if (--sp < mk) {
            mk = marks.remove(marks.size() - 1);
        }
        return nodes.remove(nodes.size() - 1);
    }

    /**
     * 返回当前位于堆栈顶部的节点。
     *
     * @return
     */
    public Node peekNode() {
        return nodes.get(nodes.size() - 1);
    }

    /**
     * 返回当前节点范围中堆栈上的孩子节点数。
     *
     * @return
     */
    public int nodeArity() {
        return sp - mk;
    }

    /**
     * 移除节点
     *
     * @param n
     */
    public void clearNodeScope(Node n) {
        while (sp > mk) {
            popNode();
        }
        mk = marks.remove(marks.size() - 1);
    }


    public void openNodeScope(Node n) {
        marks.add(mk);
        mk = sp;
        n.jjtOpen();
    }


    /**
     * 一个确定的节点由指定数量的孩子构成。从堆栈中弹出该数量的节点，并将其作为确定节点的子代。
     * 然后将确定的节点压入堆栈。
     *
     * @param n
     * @param num
     */
    public void closeNodeScope(Node n, int num) {
        mk = marks.remove(marks.size() - 1);
        while (num-- > 0) {
            Node c = popNode();
            c.jjtSetParent(n);
            n.jjtAddChild(c, num);
        }
        n.jjtClose();
        pushNode(n);
        node_created = true;
    }


    /**
     * 如果条件为真，则构造条件节点。自从节点打开以来，所有已推入的节点都成为条件节点孩子，
     * 然后将其推入堆栈。如果条件为假，则不构建该节点，并将其保留在堆栈中。
     *
     * @param n
     * @param condition
     */
    public void closeNodeScope(Node n, boolean condition) {
        if (condition) {
            int a = nodeArity();
            mk = marks.remove(marks.size() - 1);
            while (a-- > 0) {
                Node c = popNode();
                c.jjtSetParent(n);
                n.jjtAddChild(c, a);
            }
            n.jjtClose();
            pushNode(n);
            node_created = true;
        } else {
            mk = marks.remove(marks.size() - 1);
            node_created = false;
        }
    }
}
/* JavaCC - OriginalChecksum=e8d48a914dab136521465f47942dc4ce (do not edit this line) */
