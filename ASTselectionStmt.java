/* Generated By:JJTree: Do not edit this line. ASTselectionStmt.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTselectionStmt extends SimpleNode {
  public ASTselectionStmt(int id) {
    super(id);
  }

  public ASTselectionStmt(MyCompiler p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(MyCompilerVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=809d014c10b3082fbd5f52d0a4c273ba (do not edit this line) */