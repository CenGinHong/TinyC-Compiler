package src.com.minic.node;

import src.com.minic.compiler.MyCompilerVisitor;

/**
 * @author: 陈健航
 * @description: 所有AST节点必须实现此接口。它提供了用于构建节点之间父亲节点和孩子关系的基本机制。
 * @since: 2020/4/3 12:25
 * @version: 1.0
 */
public interface Node {

    /**
     * 在将节点设为当前节点之后，将调用此方法。它指示现在可以向其添加子节点。
     */
    void jjtOpen();

    /**
     * 添加所有子节点后，将调用此方法。
     */
    void jjtClose();

    /**
     * 这对方法用于通知节点其父节点。
     */
    void jjtSetParent(Node n);

    Node jjtGetParent();

    /**
     * 此方法告诉节点将其参数添加到节点的子级列表中。
     */
    void jjtAddChild(Node n, int i);

    /**
     * 此方法返回一个子节点。孩子节点从零开始，从左到右编号。
     */
    Node jjtGetChild(int i);

    /**
     * 返回节点具有的孩子节点数量。
     */
    int jjtGetNumChildren();

    /**
     * 接受访客。
     */
    Object jjtAccept(MyCompilerVisitor visitor, Object data);
}
