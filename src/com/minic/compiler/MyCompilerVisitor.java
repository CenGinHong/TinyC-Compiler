package src.com.minic.compiler;

import src.com.minic.node.*;

public interface MyCompilerVisitor {
    Object visit(SimpleNode node, Object data);

    Object visit(ASTprogram node, Object data);

    Object visit(ASTdeclarationList node, Object data);

    Object visit(ASTdeclaration node, Object data);

    Object visit(ASTvarDeclaration node, Object data);

    Object visit(ASTfunDeclaration node, Object data);

    Object visit(ASTparams node, Object data);

    Object visit(ASTparamList node, Object data);

    Object visit(ASTparam node, Object data);

    Object visit(ASTcompoundStmt node, Object data);

    Object visit(ASTlocalDeclarations node, Object data);

    Object visit(ASTstatementList node, Object data);

    Object visit(ASTstatement node, Object data);

    Object visit(ASTexpressionStmt node, Object data);

    Object visit(ASTselectionStmt node, Object data);

    Object visit(ASTiterationStmt node, Object data);

    Object visit(ASTreturnStmt node, Object data);

    Object visit(ASTexpression node, Object data);

    Object visit(ASTvar node, Object data);

    Object visit(ASTsimpleExperession node, Object data);

    Object visit(ASTrelop node, Object data);

    Object visit(ASTadditiveExpression node, Object data);

    Object visit(ASTterm node, Object data);

    Object visit(ASTaddop node, Object data);

    Object visit(ASTmulop node, Object data);

    Object visit(ASTfactor node, Object data);

    Object visit(ASTcall node, Object data);

    Object visit(ASTargs node, Object data);

    Object visit(ASTargList node, Object data);
}
