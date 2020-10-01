package src.com.minic.node;

import src.com.minic.compiler.MyCompilerVisitor;

/**
 * @author: 郑卓荣
 * @description:
 * @since: 2020/4/2 12:58
 * @version: 1.0
 */
public class ASTadditiveExpression extends SimpleNode {
    private String name;

    public ASTadditiveExpression(int id) {
        super(id);
    }


    @Override
    public String toString() {
        return name == null ? "" : name;
    }


    @Override
    public Object jjtAccept(MyCompilerVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    public void setName(String name) {
        this.name = name;
    }
}

