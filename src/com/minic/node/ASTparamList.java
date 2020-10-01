package src.com.minic.node;

import src.com.minic.compiler.MyCompiler;
import src.com.minic.compiler.MyCompilerVisitor;

/**
 * @author: 郑卓荣
 * @description:
 * @since: 2020/4/2 12:12
 * @version: 1.0
 */
public class ASTparamList extends SimpleNode {

    public ASTparamList(int id) {
        super(id);
    }

    public ASTparamList(MyCompiler p, int id) {
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

