package src.com.minic.node;

import src.com.minic.compiler.MyCompiler;
import src.com.minic.compiler.MyCompilerVisitor;

/**
 * @author: 郑卓荣
 * @description:
 * @since: 2020/4/2 12:12
 * @version: 1.0
 */
public class ASTparams extends SimpleNode {
    public ASTparams(int id) {
        super(id);
    }

    public ASTparams(MyCompiler p, int id) {
        super(p, id);
    }

    @Override
    public String toString() {
        return children == null ? "" : super.toString();
    }

    @Override
    public Object jjtAccept(MyCompilerVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }
}

