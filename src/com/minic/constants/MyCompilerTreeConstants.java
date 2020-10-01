package src.com.minic.constants;

public interface MyCompilerTreeConstants {
    int JJTPROGRAM = 0;
    int JJTDECLARATIONLIST = 1;
    int JJTDECLARATION = 2;
    int JJTVARDECLARATION = 3;
    int JJTFUNDECLARATION = 4;
    int JJTPARAMS = 5;
    int JJTPARAMLIST = 6;
    int JJTPARAM = 7;
    int JJTCOMPOUNDSTMT = 8;
    int JJTLOCALDECLARATIONS = 9;
    int JJTSTATEMENTLIST = 10;
    int JJTSTATEMENT = 11;
    int JJTEXPRESSIONSTMT = 12;
    int JJTSELECTIONSTMT = 13;
    int JJTITERATIONSTMT = 14;
    int JJTRETURNSTMT = 15;
    int JJTEXPRESSION = 16;
    int JJTVAR = 17;
    int JJTSIMPLEEXPERESSION = 18;
    int JJTRELOP = 19;
    int JJTADDITIVEEXPRESSION = 20;
    int JJTTERM = 21;
    int JJTADDOP = 22;
    int JJTMULOP = 23;
    int JJTFACTOR = 24;
    int JJTCALL = 25;
    int JJTARGS = 26;
    int JJTARGLIST = 27;


    String[] jjtNodeName = {
            "program",
            "declarationList",
            "declaration",
            "varDeclaration",
            "funDeclaration",
            "params",
            "paramList",
            "param",
            "compoundStmt",
            "localDeclarations",
            "statementList",
            "statement",
            "expressionStmt",
            "selectionStmt",
            "iterationStmt",
            "returnStmt",
            "expression",
            "var",
            "simpleExperession",
            "relop",
            "additiveExpression",
            "term",
            "addop",
            "mulop",
            "factor",
            "call",
            "args",
            "argList",
    };
}
