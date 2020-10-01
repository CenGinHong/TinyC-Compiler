package src.com.minic.node;

import src.com.minic.compiler.MyCompiler;
import src.com.minic.compiler.MyCompilerVisitor;

/**
 * @author: 郑卓荣
 * @description:
 * @since: 2020/4/2 12:58
 * @version: 1.0
 */
public class ASTcall extends SimpleNode {

    private String name;

    public ASTcall(int id) {
        super(id);
    }

    public ASTcall(MyCompiler p, int id) {
        super(p, id);
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name == null ? "" : "call: " + name;
    }

    /**
     * Accept the visitor.
     **/
    @Override
    public Object jjtAccept(MyCompilerVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }
}

