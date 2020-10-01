package src.com.minic.node;

import src.com.minic.compiler.MyCompiler;
import src.com.minic.compiler.MyCompilerVisitor;
import src.com.minic.constants.MyCompilerTreeConstants;

/**
 * @author: 陈健航
 * @description: 语法树节点
 * @since: 2020/4/5 12:11
 * @version: 1.0
 */
public class SimpleNode implements Node {

    protected Node parent;
    protected Node[] children;
    protected int id;
    protected Object value;
    protected MyCompiler parser;

    public SimpleNode(int i) {
        id = i;
    }

    public SimpleNode(MyCompiler p, int i) {
        this(i);
        parser = p;
    }

    @Override
    public void jjtOpen() {
    }

    @Override
    public void jjtClose() {
    }

    @Override
    public void jjtSetParent(Node n) {
        parent = n;
    }

    @Override
    public Node jjtGetParent() {
        return parent;
    }

    @Override
    public void jjtAddChild(Node n, int i) {
        if (children == null) {
            children = new Node[i + 1];
        } else if (i >= children.length) {
            Node c[] = new Node[i + 1];
            System.arraycopy(children, 0, c, 0, children.length);
            children = c;
        }
        children[i] = n;
    }

    @Override
    public Node jjtGetChild(int i) {
        return children[i];
    }

    @Override
    public int jjtGetNumChildren() {
        return (children == null) ? 0 : children.length;
    }

    public void jjtSetValue(Object value) {
        this.value = value;
    }

    public Object jjtGetValue() {
        return value;
    }

    /**
     * 接受访客
     **/
    @Override
    public Object jjtAccept(MyCompilerVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    /**
     * 接受访客。
     **/
    public Object childrenAccept(MyCompilerVisitor visitor, Object data) {
        if (children != null) {
            for (int i = 0; i < children.length; ++i) {
                children[i].jjtAccept(visitor, data);
            }
        }
        return data;
    }


    @Override
    public String toString() {
        return MyCompilerTreeConstants.jjtNodeName[id];
    }

    public String toString(String prefix) {
        return prefix + toString();
    }


    public void dump(String prefix, StringBuilder stringBuilder) {
        if (!"".equals(toString())) {
            stringBuilder.append(toString(prefix) + "\n");
        }
        if (children != null) {
            for (int i = 0; i < children.length; ++i) {
                SimpleNode n = (SimpleNode) children[i];
                if (n != null) {
                    if ("".equals(toString())) {
                        n.dump(prefix, stringBuilder);
                    } else {
                        n.dump(prefix + "  ", stringBuilder);
                    }
                }
            }
        }
    }
}