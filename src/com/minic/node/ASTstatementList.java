package src.com.minic.node;

import src.com.minic.compiler.MyCompiler;
import src.com.minic.compiler.MyCompilerVisitor;

/**
 * @author: 郑卓荣
 * @description:
 * @since: 2020/4/2 12:19
 * @version: 1.0
 */
public class ASTstatementList extends SimpleNode {
    public ASTstatementList(int id) {
        super(id);
    }

    public ASTstatementList(MyCompiler p, int id) {
        super(p, id);
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

