/* Generated By:JJTree: Do not edit this line. ASTaddop.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTaddop extends SimpleNode {
  public ASTaddop(int id) {
    super(id);
  }

  public ASTaddop(MyCompiler p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(MyCompilerVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=cc9a21dd5e9ecf3511bb3e5736838147 (do not edit this line) */