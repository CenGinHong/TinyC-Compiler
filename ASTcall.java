/* Generated By:JJTree: Do not edit this line. ASTcall.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTcall extends SimpleNode {
  public ASTcall(int id) {
    super(id);
  }

  public ASTcall(MyCompiler p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(MyCompilerVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=c76272960ddddb0f5a6a828b2123cb55 (do not edit this line) */
