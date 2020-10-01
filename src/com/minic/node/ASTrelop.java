package src.com.minic.node;


import src.com.minic.compiler.MyCompilerVisitor;

/**
 * @author: 郑卓荣
 * @description:
 * @since: 2020/4/2 12:10
 * @version: 1.0
 */
public class ASTrelop extends SimpleNode {
    public ASTrelop(int id) {
        super(id);
    }


    @Override
    public String toString() {
        return "";
    }


    @Override
    public Object jjtAccept(MyCompilerVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }
}

