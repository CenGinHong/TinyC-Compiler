/* Generated By:JJTree: Do not edit this line. ASTmulop.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTmulop extends SimpleNode {
  public ASTmulop(int id) {
    super(id);
  }

  public ASTmulop(MyCompiler p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(MyCompilerVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=de4903c4b4cfaf64a0d51e7900bbbcea (do not edit this line) */
