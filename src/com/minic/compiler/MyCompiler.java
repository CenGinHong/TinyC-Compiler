package src.com.minic.compiler;


import src.com.minic.constants.MyCompilerConstants;
import src.com.minic.constants.MyCompilerTreeConstants;
import src.com.minic.node.*;
import src.com.minic.symbol.VarInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MyCompiler implements MyCompilerTreeConstants, MyCompilerConstants {

    static private int[] jj_la1_0;
    static private int[] jj_la1_1;

    static {
        jj_la1_init_0();
        jj_la1_init_1();
    }

    /**
     * 错误列表
     */
    public final ErrorList errorList = new ErrorList();
    /**
     * 中间代码生成工具
     */
    public final CodeGen codeGen = new CodeGen(false);
    /**
     * 词法生成
     */
    public final StringBuilder myLex = new StringBuilder();
    /**
     * 符号表
     */
    private final SymbolTable symbolTable = new SymbolTable();
    final private int[] jj_la1 = new int[26];
    final private JJCalls[] jj_2_rtns = new JJCalls[7];
    final private LookaheadSuccess jj_ls = new LookaheadSuccess();
    private final java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
    private final int[] jj_lasttokens = new int[100];
    /**
     * 生成的令牌管理器。
     */
    public MyCompilerTokenManager token_source;
    /**
     * 当前令牌。
     */
    public Token token;
    /**
     * 下一个令牌。
     */
    public Token jj_nt;
    public JJTMyCompilerState jjtree = new JJTMyCompilerState();
    SimpleCharStream jj_input_stream;
    private int jj_ntk;
    private Token jj_scanpos, jj_lastpos;
    private int jj_la;
    private int jj_gen;
    private boolean jj_rescan = false;
    private int jj_gc = 0;
    private int[] jj_expentry;
    private int jj_kind = -1;
    private int jj_endpos;

    /**
     * 使用InputStream的构造方法。
     */
    public MyCompiler(java.io.InputStream stream) {
        this(stream, null);
    }

    /**
     * 具有InputStream和提供的编码的构造函数
     */
    public MyCompiler(java.io.InputStream stream, String encoding) {
        try {
            jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1);
        } catch (java.io.UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        token_source = new MyCompilerTokenManager(jj_input_stream);
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 26; i++) {
            jj_la1[i] = -1;
        }
        for (int i = 0; i < jj_2_rtns.length; i++) {
            jj_2_rtns[i] = new JJCalls();
        }
    }

    /**
     * 构造函数。
     */
    public MyCompiler(java.io.Reader stream) {
        jj_input_stream = new SimpleCharStream(stream, 1, 1);
        token_source = new MyCompilerTokenManager(jj_input_stream);
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 26; i++) {
            jj_la1[i] = -1;
        }
        for (int i = 0; i < jj_2_rtns.length; i++) {
            jj_2_rtns[i] = new JJCalls();
        }
    }

    /**
     * 具有生成的令牌管理器的构造函数。
     */
    public MyCompiler(MyCompilerTokenManager tm) {
        token_source = tm;
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 26; i++) {
            jj_la1[i] = -1;
        }
        for (int i = 0; i < jj_2_rtns.length; i++) {
            jj_2_rtns[i] = new JJCalls();
        }
    }

    private static void jj_la1_init_0() {
        jj_la1_0 = new int[]{0x1400, 0x1400, 0x400, 0x1400, 0x1400, 0x8000000, 0x400, 0x400, 0x100d6a80, 0x100d6a80, 0x100d6000, 0x80, 0x800, 0x10056000, 0x10000, 0x300000, 0x300000, 0x1800000, 0x6000000, 0x1800000, 0x6000000, 0x10000000, 0x50000, 0x16000, 0x10056000, 0x8000000,};
    }

    private static void jj_la1_init_1() {
        jj_la1_1 = new int[]{0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x3, 0x3, 0x1, 0x0, 0x0, 0x1, 0x1, 0x78, 0x78, 0x0, 0x0, 0x0, 0x0, 0x0, 0x1, 0x0, 0x1, 0x0,};
    }

    final public void start() throws ParseException {
        myLex.delete(0, myLex.length());
        program();
    }

    /**
     * 文法：program -> declaration-list
     *
     * @throws ParseException
     */
    final public void program() throws ParseException {
        ASTprogram jjtn000 = new ASTprogram(JJTPROGRAM);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        Token id;
        try {
            //创建符号表
            HashMap<String, VarInfo> map = new HashMap<>();
            symbolTable.getVarList().add(map);
            //初始化寄存器
            codeGen.emitRM(codeGen.OP_LD, codeGen.REG_MP, 0, codeGen.REG_AC, "load maxaddress from location 0");
            codeGen.emitRM(codeGen.OP_ST, codeGen.REG_AC, 0, codeGen.REG_AC, "clear location 0");
            codeGen.emitRM(codeGen.OP_LDA, codeGen.REG_LP, 0, codeGen.REG_GP, "init lp");
            codeGen.emitRM(codeGen.OP_LDA, codeGen.REG_SP, 0, codeGen.REG_GP, "init sp");
            declarationList();
            id = jj_consume_token(0);
            jjtree.closeNodeScope(jjtn000, true);
            jjtc000 = false;
            //最后一个非main函数报错
            if (!"main".equals(symbolTable.getLatestDeclarationFun().getName())) {
                errorList.addLastNotMain(id);
            }
            //删除符号表
            symbolTable.getVarList().remove(symbolTable.getVarList().size() - 1);
            codeGen.emitRO(codeGen.OP_HALT, 0, 0, 0, "");
            //语义分析错就删除所有中间代码
            if (!errorList.getErrorList().isEmpty()) {
                codeGen.getQt().clear();
            }
        } catch (Throwable jjte000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
            if (jjte000 instanceof RuntimeException) {
                {
                    throw (RuntimeException) jjte000;
                }
            }
            if (jjte000 instanceof ParseException) {
                {
                    throw (ParseException) jjte000;
                }
            }
            {
                throw (Error) jjte000;
            }
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }

    private void printToken(Token token) {
        myLex.append(token.image + ":  " + tokenImage[token.kind] + "\n");
    }

    /**
     * 文法：declaration-list -> declaration{ declaration}
     *
     * @throws ParseException
     */
    final public void declarationList() throws ParseException {
        ASTdeclarationList jjtn000 = new ASTdeclarationList(JJTDECLARATIONLIST);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        try {
            declaration();
            label_1:
            while (true) {
                switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                    case INT:
                    case VOID:
                        break;
                    default:
                        jj_la1[0] = jj_gen;
                        break label_1;
                }
                declaration();
            }
        } catch (Throwable jjte000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
            if (jjte000 instanceof RuntimeException) {
                {
                    throw (RuntimeException) jjte000;
                }
            }
            if (jjte000 instanceof ParseException) {
                {
                    throw (ParseException) jjte000;
                }
            }
            {
                throw (Error) jjte000;
            }
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }

    /**
     * 文法：declaration -> var-declaration | fun-declaration
     *
     * @throws ParseException
     */
    final public void declaration() throws ParseException {
        ASTdeclaration jjtn000 = new ASTdeclaration(JJTDECLARATION);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        try {
            //向前展望
            if (jj_2_1(2147483647)) {
                varDeclaration();
            } else {
                switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                    case INT:
                    case VOID:
                        funDeclaration();
                        break;
                    default:
                        jj_la1[1] = jj_gen;
                        jj_consume_token(-1);
                        throw new ParseException();
                }
            }
        } catch (Throwable jjte000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
            if (jjte000 instanceof RuntimeException) {
                {
                    throw (RuntimeException) jjte000;
                }
            }
            if (jjte000 instanceof ParseException) {
                {
                    throw (ParseException) jjte000;
                }
            }
            {
                throw (Error) jjte000;
            }
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }

    /**
     * var-declaration -> type-specifier ID; | type-specifier ID[NUM];
     * 因为只能定义int变量和数组，修改文法
     * var-declaration -> int ID; | int ID[NUM];
     */
    final public void varDeclaration() throws ParseException {
        ASTvarDeclaration jjtn000 = new ASTvarDeclaration(JJTVARDECLARATION);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        Token t;
        Token id;
        VarInfo var;
        Token size;
        try {
            //向前展望
            if (jj_2_2(2147483647)) {
                jj_consume_token(INT);
                id = jj_consume_token(ID);
                jj_consume_token(SEMICOLON);
                jjtree.closeNodeScope(jjtn000, true);
                jjtc000 = false;
                jjtn000.setName("int: " + id.image);
                var = new VarInfo("int", id);
                var.setSize(1);
                if (!symbolTable.addVar(var)) {
                    errorList.addVarHaveDeclared(id);
                } else {
                    codeGen.emitRM(codeGen.OP_LDA, codeGen.REG_SP, 1, codeGen.REG_SP, "var local ins sp");
                    if (symbolTable.isGlobalVar(id.image)) {
                        codeGen.emitRM(codeGen.OP_LDA, codeGen.REG_LP, 1, codeGen.REG_LP, "var global ins sp");
                    }
                }
            } else {
                if (((jj_ntk == -1) ? jj_ntk() : jj_ntk) == INT) {
                    jj_consume_token(INT);
                    id = jj_consume_token(ARRAYELEMENT);
                    size = jj_consume_token(NUM);
                    jj_consume_token(RIGHTBRACKET);
                    jj_consume_token(SEMICOLON);
                    jjtree.closeNodeScope(jjtn000, true);
                    jjtc000 = false;
                    jjtn000.setName("int: " + id.image + size.image + "]");
                    id.image = id.image.replace("[", "");
                    var = new VarInfo("int[]", id);
                    var.setSize(Integer.parseInt(size.image));
                    //加入符号表，重复则报错
                    if (!symbolTable.addVar(var)) {
                        errorList.addVarHaveDeclared(id);
                    } else {
                        //抬高栈顶sp
                        codeGen.emitRM(codeGen.OP_LDA, codeGen.REG_SP, Integer.parseInt(size.image), codeGen.REG_SP, "var[] local ins sp");
                        //如果时全局变量就抬高局部变量指针
                        if (symbolTable.isGlobalVar(id.image)) {
                            codeGen.emitRM(codeGen.OP_LDA, codeGen.REG_LP, Integer.parseInt(size.image), codeGen.REG_LP, "var global ins sp");
                        }
                    }
                } else {
                    jj_la1[2] = jj_gen;
                    jj_consume_token(-1);
                    throw new ParseException();
                }
            }
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }

    /**
     * 文法：params -> param-list | void
     *
     * @throws ParseException
     */
    final public void funDeclaration() throws ParseException {
        ASTfunDeclaration jjtn000 = new ASTfunDeclaration(JJTFUNDECLARATION);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        Token id;
        Token returnType;
        FunInfo fun;
        List<VarInfo> par;
        int callFunLoc = 0;
        int jumpLoc = 0;
        int currentLoc = 0;
        try {
            switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                case INT:
                    returnType = jj_consume_token(INT);
                    id = jj_consume_token(ID);
                    jj_consume_token(LEFTPARENTHESES);
                    par = params();
                    jj_consume_token(RIGHTPARENTHESES);
                    jjtn000.setName(id.image + ":int");
                    if (!"main".equals(id.image)) {
                        jumpLoc = codeGen.emitSkip(1);
                    }
                    //把函数加入符号表
                    fun = new FunInfo(returnType.image, id, par);
                    //函数入口保存
                    callFunLoc = codeGen.emitSkip(0);
                    fun.setLoc(callFunLoc);
                    if (!symbolTable.addFun(fun)) {
                        errorList.addFunHaveDeclared(id);
                    } else {
                        List<VarInfo> tempFunPar = symbolTable.getTempFunPar();
                        tempFunPar.clear();
                        //把形参临时保存，便于进入函数能使用形参
                        tempFunPar.addAll(par);
                    }
                    compoundStmt();
                    jjtree.closeNodeScope(jjtn000, true);
                    jjtc000 = false;
                    //非main函数要执行代码时跳过，保存临时位置，函数结束反填
                    if (!"main".equals(id.image)) {
                        currentLoc = codeGen.emitSkip(0);
                        codeGen.emitBackup(jumpLoc);
                        codeGen.emitRM_Abs(codeGen.OP_LDA, codeGen.REG_PC, currentLoc, "jmp to fun end");
                        codeGen.emitRestore();
                    }
                    break;
                case VOID:
                    returnType = jj_consume_token(VOID);
                    id = jj_consume_token(ID);
                    jj_consume_token(LEFTPARENTHESES);
                    par = params();
                    jj_consume_token(RIGHTPARENTHESES);
                    jjtn000.setName(id.image + ":void");
                    if (!"main".equals(id.image)) {
                        jumpLoc = codeGen.emitSkip(1);
                    }
                    //把函数加入符号表
                    fun = new FunInfo(returnType.image, id, par);
                    callFunLoc = codeGen.emitSkip(0);
                    fun.setLoc(callFunLoc);
                    if (!symbolTable.addFun(fun)) {
                        errorList.addFunHaveDeclared(id);
                    } else {
                        List<VarInfo> tempFunPar = symbolTable.getTempFunPar();
                        tempFunPar.clear();
                        //函数入口保存
                        tempFunPar.addAll(par);
                    }
                    compoundStmt();
                    jjtree.closeNodeScope(jjtn000, true);
                    jjtc000 = false;
                    //非main函数要执行代码时跳过，保存临时位置，函数结束反填
                    if (!"main".equals(id.image)) {
                        currentLoc = codeGen.emitSkip(0);
                        codeGen.emitBackup(jumpLoc);
                        codeGen.emitRM_Abs(codeGen.OP_LDA, codeGen.REG_PC, currentLoc, "jmp to fun end");
                        codeGen.emitRestore();
                    }
                    break;
                default:
                    jj_la1[3] = jj_gen;
                    jj_consume_token(-1);
                    throw new ParseException();
            }
        } catch (Throwable jjte000) {
            if (jjtc000) {
                jjtree.clearNodeScope(jjtn000);
                jjtc000 = false;
            } else {
                jjtree.popNode();
            }
            if (jjte000 instanceof RuntimeException) {
                {
                    throw (RuntimeException) jjte000;
                }
            }
            if (jjte000 instanceof ParseException) {
                {
                    throw (ParseException) jjte000;
                }
            }
            {
                throw (Error) jjte000;
            }
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }

    /**
     * 文法：params -> param-list | void
     *
     * @throws ParseException
     */
    final public List<VarInfo> params() throws ParseException {
        ASTparams jjtn000 = new ASTparams(JJTPARAMS);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        List<VarInfo> par;
        try {
            switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                case INT:
                    par = paramList();
                    jjtree.closeNodeScope(jjtn000, true);
                    jjtc000 = false;
                {
                    return par;
                }
                case VOID:
                    jj_consume_token(VOID);
                    jjtree.closeNodeScope(jjtn000, true);
                    jjtc000 = false;
                {
                    return Collections.emptyList();
                }
                default:
                    jj_la1[4] = jj_gen;
                    jj_consume_token(-1);
                    throw new ParseException();
            }
        } catch (Throwable jjte000) {
            if (jjtc000) {
                jjtree.clearNodeScope(jjtn000);
                jjtc000 = false;
            } else {
                jjtree.popNode();
            }
            if (jjte000 instanceof RuntimeException) {
                {
                    throw (RuntimeException) jjte000;
                }
            }
            if (jjte000 instanceof ParseException) {
                {
                    throw (ParseException) jjte000;
                }
            }
            {
                throw (Error) jjte000;
            }
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }

    /**
     * 文法：param-list -> param{ , param}
     *
     * @throws ParseException
     */
    final public List<VarInfo> paramList() throws ParseException {
        ASTparamList jjtn000 = new ASTparamList(JJTPARAMLIST);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        //参数列表
        List<VarInfo> par;
        //参数元素
        VarInfo para;
        try {
            para = param();
            par = new ArrayList<>();
            par.add(para);
            while (true) {
                if (((jj_ntk == -1) ? jj_ntk() : jj_ntk) == COMMA) {
                } else {
                    jj_la1[5] = jj_gen;
                    break;
                }
                jj_consume_token(COMMA);
                para = param();
                par.add(para);
            }
            jjtree.closeNodeScope(jjtn000, true);
            jjtc000 = false;
            {
                return par;
            }
        } catch (Throwable jjte000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
            if (jjte000 instanceof RuntimeException) {
                {
                    throw (RuntimeException) jjte000;
                }
            }
            if (jjte000 instanceof ParseException) {
                {
                    throw (ParseException) jjte000;
                }
            }
            {
                throw (Error) jjte000;
            }
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }

    /**
     * param -> type-specifier ID | type-specifier ID[]
     * 修改文法
     * param -> int ID | int ID[]
     */
    final public VarInfo param() throws ParseException {
        ASTparam jjtn000 = new ASTparam(JJTPARAM);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        Token id;
        try {
            //向前展望
            if (jj_2_3(2147483647)) {
                jj_consume_token(INT);
                id = jj_consume_token(ID);
                jjtree.closeNodeScope(jjtn000, true);
                jjtc000 = false;
                jjtn000.setName("int:" + id.image);
                {
                    return new VarInfo("int", id.image);
                }
            } else {
                if (((jj_ntk == -1) ? jj_ntk() : jj_ntk) == INT) {
                    jj_consume_token(INT);
                    id = jj_consume_token(ARRAYELEMENT);
                    jj_consume_token(RIGHTBRACKET);
                    jjtree.closeNodeScope(jjtn000, true);
                    jjtc000 = false;
                    jjtn000.setName("int[]:" + id.image);
                    id.image = id.image.replace("[", "");
                    //返回参数
                    return new VarInfo("int[]", id.image);
                } else {
                    jj_la1[6] = jj_gen;
                    jj_consume_token(-1);
                    throw new ParseException();
                }
            }
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }

    /**
     * 文法：compound-stmt -> {local-declarations statement-list}
     *
     * @throws ParseException
     */
    final public void compoundStmt() throws ParseException {
        ASTcompoundStmt jjtn000 = new ASTcompoundStmt(JJTCOMPOUNDSTMT);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        int paraSize = 0;
        int sp = 0;
        int tableSize = 0;
        try {
            jj_consume_token(LEFTBRACES);
            //进入块级，新建参数表
            HashMap<String, VarInfo> map = new HashMap<>();
            symbolTable.getVarList().add(map);
            //判断是否进入的是参数内部，是的话把参数加入参数表
            for (VarInfo e : symbolTable.getTempFunPar()) {
                if ("int".equals(e.getType())) {
                    e.setSize(1);
                } else {
                    e.setSize(50);
                }
                symbolTable.addVar(e);
                paraSize = e.getSize();
            }
            tableSize = symbolTable.getVarList().size();
            //如果是进入函数的参数，把栈抬高
            codeGen.emitRM(codeGen.OP_LDA, codeGen.REG_SP, paraSize, codeGen.REG_SP, "ins sp");
            symbolTable.getTempFunPar().clear();
            localDeclarations();
            statementList();
            //离开块级，删除符号表
            if (tableSize == symbolTable.getVarList().size()) {
                sp = symbolTable.reduceLocalSize();
                //降低栈位
                codeGen.emitRM(codeGen.OP_LDA, codeGen.REG_SP, -sp, codeGen.REG_SP, "des sp");
                symbolTable.getVarList().remove(symbolTable.getVarList().size() - 1);
            }
            jj_consume_token(RIGHTBRACES);
        } catch (Throwable jjte000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
            if (jjte000 instanceof RuntimeException) {
                {
                    throw (RuntimeException) jjte000;
                }
            }
            if (jjte000 instanceof ParseException) {
                {
                    throw (ParseException) jjte000;
                }
            }
            {
                throw (Error) jjte000;
            }
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }

    /**
     * 文法：local-declarations -> empty{ var-declaration}
     *
     * @throws ParseException
     */
    final public void localDeclarations() throws ParseException {
        ASTlocalDeclarations jjtn000 = new ASTlocalDeclarations(JJTLOCALDECLARATIONS);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        try {
            label_3:
            while (true) {
                if (((jj_ntk == -1) ? jj_ntk() : jj_ntk) == INT) {
                } else {
                    jj_la1[7] = jj_gen;
                    break label_3;
                }
                varDeclaration();
            }
        } catch (Throwable jjte000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
            if (jjte000 instanceof RuntimeException) {
                {
                    throw (RuntimeException) jjte000;
                }
            }
            if (jjte000 instanceof ParseException) {
                {
                    throw (ParseException) jjte000;
                }
            }
            {
                throw (Error) jjte000;
            }
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }

    /**
     * 文法：local-declarations -> empty{ var-declaration}
     *
     * @throws ParseException
     */
    final public void statementList() throws ParseException {
        ASTstatementList jjtn000 = new ASTstatementList(JJTSTATEMENTLIST);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        try {
            label_4:
            while (true) {
                switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                    case IF:
                    case WHILE:
                    case RETURN:
                    case OUTPUT:
                    case INPUT:
                    case ID:
                    case NUM:
                    case SEMICOLON:
                    case LEFTPARENTHESES:
                    case ARRAYELEMENT:
                    case LEFTBRACES:
                        break;
                    default:
                        jj_la1[8] = jj_gen;
                        break label_4;
                }
                statement();
            }
        } catch (Throwable jjte000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
            if (jjte000 instanceof RuntimeException) {
                {
                    throw (RuntimeException) jjte000;
                }
            }
            if (jjte000 instanceof ParseException) {
                {
                    throw (ParseException) jjte000;
                }
            }
            {
                throw (Error) jjte000;
            }
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }

    /**
     * statement -> expression-stmt | compound-stmt | selection-stmt | iteration-stmt | return stmt
     *
     * @throws ParseException
     */
    final public void statement() throws ParseException {
        ASTstatement jjtn000 = new ASTstatement(JJTSTATEMENT);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        try {
            switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                case OUTPUT:
                case INPUT:
                case ID:
                case NUM:
                case SEMICOLON:
                case LEFTPARENTHESES:
                case ARRAYELEMENT:
                    expressionStmt();
                    break;
                case LEFTBRACES:
                    compoundStmt();
                    break;
                case IF:
                    selectionStmt();
                    break;
                case WHILE:
                    iterationStmt();
                    break;
                case RETURN:
                    returnStmt();
                    break;
                default:
                    jj_la1[9] = jj_gen;
                    jj_consume_token(-1);
                    throw new ParseException();
            }
        } catch (Throwable jjte000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
            if (jjte000 instanceof RuntimeException) {
                {
                    throw (RuntimeException) jjte000;
                }
            }
            if (jjte000 instanceof ParseException) {
                {
                    throw (ParseException) jjte000;
                }
            }
            {
                throw (Error) jjte000;
            }
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }

    /**
     * 文法：expression-stmt -> expression; | ;
     *
     * @throws ParseException
     */
    final public void expressionStmt() throws ParseException {
        ASTexpressionStmt jjtn000 = new ASTexpressionStmt(JJTEXPRESSIONSTMT);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        try {
            switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                case OUTPUT:
                case INPUT:
                case ID:
                case NUM:
                case LEFTPARENTHESES:
                case ARRAYELEMENT:
                    expression();
                    jj_consume_token(SEMICOLON);
                    break;
                case SEMICOLON:
                    jj_consume_token(SEMICOLON);
                    break;
                default:
                    jj_la1[10] = jj_gen;
                    jj_consume_token(-1);
                    throw new ParseException();
            }
        } catch (Throwable jjte000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
            if (jjte000 instanceof RuntimeException) {
                {
                    throw (RuntimeException) jjte000;
                }
            }
            if (jjte000 instanceof ParseException) {
                {
                    throw (ParseException) jjte000;
                }
            }
            {
                throw (Error) jjte000;
            }
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }

    /**
     * 文法：selection-stmt -> matched-stmt | unmatched-stmt
     *
     * @throws ParseException
     */
    final public void selectionStmt() throws ParseException {
        ASTselectionStmt jjtn000 = new ASTselectionStmt(JJTSELECTIONSTMT);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        int savedLoc1 = 0;
        int savedLoc2 = 0;
        int currentLoc = 0;
        Token exp;
        try {
            if (jj_2_4(2147483647)) {
                jj_consume_token(IF);
                jj_consume_token(LEFTPARENTHESES);
                exp = expression();
                jjtn000.setName("if");
                if (!"int".equals(exp.type)) {
                    errorList.addExpSubscript(exp);
                } else {
                    savedLoc1 = codeGen.emitSkip(1);
                }
                jj_consume_token(RIGHTPARENTHESES);
                statement();
                //if反填
                savedLoc2 = codeGen.emitSkip(1);
                currentLoc = codeGen.emitSkip(0);
                codeGen.emitBackup(savedLoc1);
                codeGen.emitRM_Abs(codeGen.OP_JEQ, codeGen.REG_AC, currentLoc, "if: jmp to else");
                codeGen.emitRestore();
                jj_consume_token(ELSE);
                statement();
                jjtree.closeNodeScope(jjtn000, true);
                jjtc000 = false;
                //结束反填
                currentLoc = codeGen.emitSkip(0);
                codeGen.emitBackup(savedLoc2);
                codeGen.emitRM_Abs(codeGen.OP_LDA, codeGen.REG_PC, currentLoc, "if jmp to end");
                codeGen.emitRestore();
            } else {
                if (((jj_ntk == -1) ? jj_ntk() : jj_ntk) == IF) {
                    jj_consume_token(IF);
                    jj_consume_token(LEFTPARENTHESES);
                    exp = expression();
                    jjtn000.setName("if");
                    if (!"int".equals(exp.type)) {
                        errorList.addExpSubscript(exp);
                    } else {
                        savedLoc1 = codeGen.emitSkip(1);
                    }
                    jj_consume_token(RIGHTPARENTHESES);
                    statement();
                    jjtree.closeNodeScope(jjtn000, true);
                    jjtc000 = false;
                    //结束反填
                    currentLoc = codeGen.emitSkip(0);
                    codeGen.emitBackup(savedLoc1);
                    codeGen.emitRM_Abs(codeGen.OP_JEQ, codeGen.REG_AC, currentLoc, "if: jmp to end");
                    codeGen.emitRestore();
                } else {
                    jj_la1[11] = jj_gen;
                    jj_consume_token(-1);
                    throw new ParseException();
                }
            }
        } catch (Throwable jjte000) {
            if (jjtc000) {
                jjtree.clearNodeScope(jjtn000);
                jjtc000 = false;
            } else {
                jjtree.popNode();
            }
            if (jjte000 instanceof RuntimeException) {
                {
                    throw (RuntimeException) jjte000;
                }
            }
            if (jjte000 instanceof ParseException) {
                {
                    throw (ParseException) jjte000;
                }
            }
            {
                throw (Error) jjte000;
            }
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }

    /**
     * 文法：iteration-stmt -> while (expression) statement
     *
     * @throws ParseException
     */
    final public void iterationStmt() throws ParseException {
        /*@bgen(jjtree) iterationStmt */
        ASTiterationStmt jjtn000 = new ASTiterationStmt(JJTITERATIONSTMT);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        int savedLoc1 = 0;
        int savedLoc2 = 0;
        int currentLoc = 0;
        Token exp;
        try {
            jj_consume_token(WHILE);
            jj_consume_token(LEFTPARENTHESES);
            savedLoc1 = codeGen.emitSkip(0);
            exp = expression();
            if (!"int".equals(exp.type)) {
                errorList.addLeftAndRightNotInt(exp);
            } else {
                savedLoc2 = codeGen.emitSkip(1);
            }
            jj_consume_token(RIGHTPARENTHESES);
            statement();
            jjtree.closeNodeScope(jjtn000, true);
            jjtc000 = false;
            codeGen.emitRM_Abs(codeGen.OP_LDA, codeGen.REG_PC, savedLoc1, "while unconditional jmp");

            currentLoc = codeGen.emitSkip(0);
            codeGen.emitBackup(savedLoc2);
            codeGen.emitRM_Abs(codeGen.OP_JEQ, codeGen.REG_AC, currentLoc, "while out of body");
            codeGen.emitRestore();
        } catch (Throwable jjte000) {
            if (jjtc000) {
                jjtree.clearNodeScope(jjtn000);
                jjtc000 = false;
            } else {
                jjtree.popNode();
            }
            if (jjte000 instanceof RuntimeException) {
                {
                    throw (RuntimeException) jjte000;
                }
            }
            if (jjte000 instanceof ParseException) {
                {
                    throw (ParseException) jjte000;
                }
            }
            {
                throw (Error) jjte000;
            }
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }

    /**
     * 文法：return-stmt -> return; | return expression;
     *
     * @throws ParseException
     */
    final public void returnStmt() throws ParseException {
        ASTreturnStmt jjtn000 = new ASTreturnStmt(JJTRETURNSTMT);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        Token retExp;
        try {
            if (jj_2_5(2147483647)) {
                retExp = jj_consume_token(RETURN);
                jj_consume_token(SEMICOLON);
                jjtree.closeNodeScope(jjtn000, true);
                jjtc000 = false;
                //是否与最近的函数返回值匹配
                if (!"void".equals(symbolTable.getLatestDeclarationFun().getReturnType())) {
                    errorList.addFunReturnType(retExp);
                } else {
                    //return结束反填，回跳调用
                    if (!"main".equals(symbolTable.getLatestDeclarationFun().getName())) {
                        int sp = symbolTable.reduceLocalSize();
                        codeGen.emitRM(codeGen.OP_LDA, codeGen.REG_SP, -sp, codeGen.REG_SP, "des sp");
                        codeGen.emitRM(codeGen.OP_LDA, codeGen.REG_PC, 0, codeGen.REG_FP, "jump  back to caller");
                    }
                }
            } else {
                if (((jj_ntk == -1) ? jj_ntk() : jj_ntk) == RETURN) {
                    jj_consume_token(RETURN);
                    retExp = expression();
                    jj_consume_token(SEMICOLON);
                    jjtree.closeNodeScope(jjtn000, true);
                    jjtc000 = false;
                    //是否与最近的函数返回值匹配
                    if (!retExp.type.equals(symbolTable.getLatestDeclarationFun().getReturnType())) {
                        errorList.addFunReturnType(retExp);
                    } else {
                        //return结束反填，回跳调用
                        if (!"main".equals(symbolTable.getLatestDeclarationFun().getName())) {
                            int sp = symbolTable.reduceLocalSize();
                            codeGen.emitRM(codeGen.OP_LDA, codeGen.REG_SP, -sp, codeGen.REG_SP, "des sp");
                            codeGen.emitRM(codeGen.OP_LDA, codeGen.REG_PC, 0, codeGen.REG_FP, "jump  back to caller");
                        }
                    }
                } else {
                    jj_la1[12] = jj_gen;
                    jj_consume_token(-1);
                    throw new ParseException();
                }
            }
        } catch (Throwable jjte000) {
            if (jjtc000) {
                jjtree.clearNodeScope(jjtn000);
                jjtc000 = false;
            } else {
                jjtree.popNode();
            }
            if (jjte000 instanceof RuntimeException) {
                {
                    throw (RuntimeException) jjte000;
                }
            }
            if (jjte000 instanceof ParseException) {
                {
                    throw (ParseException) jjte000;
                }
            }
            {
                throw (Error) jjte000;
            }
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }

    /**
     * 文法：expression -> var=expression | simple-expression
     *
     * @throws ParseException
     */
    final public Token expression() throws ParseException {
        ASTexpression jjtn000 = new ASTexpression(JJTEXPRESSION);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        Token left;
        Token right;
        String varName;
        try {
            if (jj_2_6(2147483647)) {
                left = var();
                varName = left.image;
                codeGen.emitRM(codeGen.OP_ST, codeGen.REG_AC1, codeGen.tmpOffset--, codeGen.REG_MP, "op: push index");
                jj_consume_token(ASSIGN);
                right = expression();
                jjtree.closeNodeScope(jjtn000, true);
                jjtc000 = false;
                jjtn000.setName("assign");
                //检查左值
                if (!"int".equals(right.type)) {
                    errorList.addLeftIsNotInt(right);
                } else {
                    if (varName.contains("[")) {
                        varName = varName.substring(0, varName.indexOf("["));
                    }
                    codeGen.emitRM(codeGen.OP_LD, codeGen.REG_AC1, ++codeGen.tmpOffset, codeGen.REG_MP, "op: load index");
                    if (symbolTable.isGlobalVar(varName)) {
                        codeGen.emitRM(codeGen.OP_STS, codeGen.REG_AC, codeGen.REG_AC1, codeGen.REG_GP, "assign: global store value");
                    } else {
                        codeGen.emitRM(codeGen.OP_STS, codeGen.REG_AC, codeGen.REG_AC1, codeGen.REG_LP, "assign: local store value");
                    }
                }
                //返回值设为void
                left.type = "void";
                {
                    return left;
                }
            } else {
                switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                    case OUTPUT:
                    case INPUT:
                    case ID:
                    case NUM:
                    case LEFTPARENTHESES:
                    case ARRAYELEMENT:
                        left = simpleExperession();
                        jjtree.closeNodeScope(jjtn000, true);
                        jjtc000 = false;
                    {
                        return left;
                    }
                    default:
                        jj_la1[13] = jj_gen;
                        jj_consume_token(-1);
                        throw new ParseException();
                }
            }
        } catch (Throwable jjte000) {
            if (jjtc000) {
                jjtree.clearNodeScope(jjtn000);
                jjtc000 = false;
            } else {
                jjtree.popNode();
            }
            if (jjte000 instanceof RuntimeException) {
                {
                    throw (RuntimeException) jjte000;
                }
            }
            if (jjte000 instanceof ParseException) {
                {
                    throw (ParseException) jjte000;
                }
            }
            {
                throw (Error) jjte000;
            }
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }

    /**
     * 文法：var -> ID| ID[expression]
     *
     * @throws ParseException
     */
    final public Token var() throws ParseException {
        ASTvar jjtn000 = new ASTvar(JJTVAR);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        Token id;
        Token exp;
        VarInfo var;
        int loc;
        try {
            switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                case ID:
                    id = jj_consume_token(ID);
                    jjtree.closeNodeScope(jjtn000, true);
                    jjtc000 = false;
                    jjtn000.setName(id.image);
                    var = symbolTable.getVarByName(id.image);
                    if (var == null) {
                        errorList.addVarNotDeclared(id);
                    } else {
                        loc = symbolTable.getVarLocByVarName(id.image);
                        codeGen.emitRM(codeGen.OP_LDC, codeGen.REG_AC1, loc, 0, "load const");
                        if (var.getGlobal()) {
                            codeGen.emitRM(codeGen.OP_LDS, codeGen.REG_AC, codeGen.REG_AC1, codeGen.REG_GP, "load global id value");
                        } else {
                            codeGen.emitRM(codeGen.OP_LDS, codeGen.REG_AC, codeGen.REG_AC1, codeGen.REG_LP, "load local id value");
                        }
                        //当作为函数参数传递时，数组a不会带括号，也可能为数组
                        id.type = var.getType();
                    }
                {
                    return id;
                }
                case ARRAYELEMENT:
                    id = jj_consume_token(ARRAYELEMENT);
                    exp = expression();
                    jj_consume_token(RIGHTBRACKET);
                    jjtree.closeNodeScope(jjtn000, true);
                    jjtc000 = false;
                    jjtn000.setName(id.image);
                    id.image = id.image.replace("[", "");
                    var = symbolTable.getVarByName(id.image);
                    if (var == null) {
                        errorList.addVarNotDeclared(id);
                    } else if (!"int[]".equals(var.getType())) {
                        errorList.addVarNotDeclared(id);
                    } else {
                        //检查下标类型
                        if (!"int".equals(exp.type)) {
                            errorList.addArraySubscript(exp);
                        } else {
                            //取得在内存的起始位置
                            loc = symbolTable.getVarLocByVarName(id.image);
                            codeGen.emitRM(codeGen.OP_LDC, codeGen.REG_AC1, loc, 0, "load const");
                            //得到下边的的值
                            codeGen.emitRO(codeGen.OP_ADD, codeGen.REG_AC1, codeGen.REG_AC1, codeGen.REG_AC, "op +");
                            if (var.getGlobal()) {
                                codeGen.emitRM(codeGen.OP_LDS, codeGen.REG_AC, codeGen.REG_AC1, codeGen.REG_GP, "load global id value");
                            } else {
                                codeGen.emitRM(codeGen.OP_LDS, codeGen.REG_AC, codeGen.REG_AC1, codeGen.REG_LP, "load local id value");
                            }
                        }
                    }
                    id.image = id.image + "[" + exp.image + "]";
                    id.type = "int";
                {
                    return id;
                }
                default:
                    jj_la1[14] = jj_gen;
                    jj_consume_token(-1);
                    throw new ParseException();
            }
        } catch (Throwable jjte000) {
            if (jjtc000) {
                jjtree.clearNodeScope(jjtn000);
                jjtc000 = false;
            } else {
                jjtree.popNode();
            }
            if (jjte000 instanceof RuntimeException) {
                {
                    throw (RuntimeException) jjte000;
                }
            }
            if (jjte000 instanceof ParseException) {
                {
                    throw (ParseException) jjte000;
                }
            }
            {
                throw (Error) jjte000;
            }
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }

    /**
     * 文法：simple-experession -> additive-expression (relop additive-expression)?
     *
     * @throws ParseException
     */
    final public Token simpleExperession() throws ParseException {
        ASTsimpleExperession jjtn000 = new ASTsimpleExperession(JJTSIMPLEEXPERESSION);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        Token ret;
        Token temp;
        Token re;
        try {
            ret = additiveExpression();
            switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                case EQUAL:
                case NOTEQUAL:
                case LT:
                case GT:
                case LTE:
                case GTE:
                    re = relop();
                    jjtn000.setName("relop: " + re);
                    //左值压栈
                    codeGen.emitRO(codeGen.OP_ST, codeGen.REG_AC, codeGen.tmpOffset--, codeGen.REG_MP, "op: push left");

                    temp = additiveExpression();
                    //只有int能比较，当两个有不同时为int
                    if (!"int".equals(ret.type) || !"int".equals(temp.type)) {
                        errorList.addLeftAndRightNotInt(ret);
                    } else {
                        codeGen.emitRO(codeGen.OP_LD, codeGen.REG_AC1, ++codeGen.tmpOffset, codeGen.REG_MP, "op: load left");
                        codeGen.emitRO(codeGen.OP_SUB, codeGen.REG_AC, codeGen.REG_AC1, codeGen.REG_AC, "sub before relop");
                        switch (re.image) {
                            case "<=": {
                                codeGen.emitRM(codeGen.OP_JLE, codeGen.REG_AC, 2, codeGen.REG_PC, "op <=");
                                break;
                            }
                            case "<": {
                                codeGen.emitRM(codeGen.OP_JLT, codeGen.REG_AC, 2, codeGen.REG_PC, "op <");
                                break;
                            }
                            case ">": {
                                codeGen.emitRM(codeGen.OP_JGT, codeGen.REG_AC, 2, codeGen.REG_PC, "op >");
                                break;
                            }
                            case ">=": {
                                codeGen.emitRM(codeGen.OP_JGE, codeGen.REG_AC, 2, codeGen.REG_PC, "op >=");
                                break;
                            }
                            case "==": {
                                codeGen.emitRM(codeGen.OP_JEQ, codeGen.REG_AC, 2, codeGen.REG_PC, "op ==");
                                break;
                            }
                            case "!=": {
                                codeGen.emitRM(codeGen.OP_JNE, codeGen.REG_AC, 2, codeGen.REG_PC, "op !=");
                                break;
                            }
                            default:
                        }
                        codeGen.emitRM(codeGen.OP_LDC, codeGen.REG_AC, 0, codeGen.REG_AC, "false case");
                        codeGen.emitRM(codeGen.OP_LDA, codeGen.REG_PC, 1, codeGen.REG_PC, "unconditional jmp");
                        codeGen.emitRM(codeGen.OP_LDC, codeGen.REG_AC, 1, codeGen.REG_AC, "true case");
                    }
                    ret.type = "int";
                    break;
                default:
                    jj_la1[15] = jj_gen;
            }
            jjtree.closeNodeScope(jjtn000, true);
            jjtc000 = false;
            {
                return ret;
            }
        } catch (Throwable jjte000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
            if (jjte000 instanceof RuntimeException) {
                {
                    throw (RuntimeException) jjte000;
                }
            }
            if (jjte000 instanceof ParseException) {
                {
                    throw (ParseException) jjte000;
                }
            }
            {
                throw (Error) jjte000;
            }
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }

    /**
     * 文法：relop -> <=|<|>|>=|==|!=
     *
     * @return
     * @throws ParseException
     */
    final public Token relop() throws ParseException {
        ASTrelop jjtn000 = new ASTrelop(JJTRELOP);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        Token id;
        try {
            switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                case LTE:
                    id = jj_consume_token(LTE);
                    jjtree.closeNodeScope(jjtn000, true);
                    jjtc000 = false;
                {
                    return id;
                }
                case LT:
                    id = jj_consume_token(LT);
                    jjtree.closeNodeScope(jjtn000, true);
                    jjtc000 = false;
                {
                    return id;
                }
                case GT:
                    id = jj_consume_token(GT);
                    jjtree.closeNodeScope(jjtn000, true);
                    jjtc000 = false;
                {
                    return id;
                }
                case GTE:
                    id = jj_consume_token(GTE);
                    jjtree.closeNodeScope(jjtn000, true);
                    jjtc000 = false;
                {
                    return id;
                }
                case EQUAL:
                    id = jj_consume_token(EQUAL);
                    jjtree.closeNodeScope(jjtn000, true);
                    jjtc000 = false;
                {
                    return id;
                }
                case NOTEQUAL:
                    id = jj_consume_token(NOTEQUAL);
                    jjtree.closeNodeScope(jjtn000, true);
                    jjtc000 = false;
                {
                    return id;
                }
                default:
                    jj_la1[16] = jj_gen;
                    jj_consume_token(-1);
                    throw new ParseException();
            }
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }

    /**
     * 文法：additive-expression -> term{ addop term}
     *
     * @throws ParseException
     */
    final public Token additiveExpression() throws ParseException {
        ASTadditiveExpression jjtn000 = new ASTadditiveExpression(JJTADDITIVEEXPRESSION);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        Token op;
        Token ret;
        Token temp;
        try {
            ret = term();
            label_5:
            while (true) {
                switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                    case PLUS:
                    case MINUS:
                        break;
                    default:
                        jj_la1[17] = jj_gen;
                        break label_5;
                }
                op = addop();
                jjtn000.setName("addop:" + op);
                //中间代码：左值压栈
                codeGen.emitRO(codeGen.OP_ST, codeGen.REG_AC, codeGen.tmpOffset--, codeGen.REG_MP, "op: push left");


                temp = term();
                //当两个都不为int
                if (!"int".equals(ret.type) || !"int".equals(temp.type)) {
                    errorList.addLeftAndRightNotInt(ret);
                } else {
                    codeGen.emitRO(codeGen.OP_LD, codeGen.REG_AC1, ++codeGen.tmpOffset, codeGen.REG_MP, "op: load left");
                    if ("+".equals(op.image)) {
                        codeGen.emitRO(codeGen.OP_ADD, codeGen.REG_AC, codeGen.REG_AC1, codeGen.REG_AC, "op +");
                    } else {
                        codeGen.emitRO(codeGen.OP_SUB, codeGen.REG_AC, codeGen.REG_AC1, codeGen.REG_AC, "op -");
                    }
                }
                ret.type = "int";
            }
            jjtree.closeNodeScope(jjtn000, true);
            jjtc000 = false;
            {
                return ret;
            }
        } catch (Throwable jjte000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
            if (jjte000 instanceof RuntimeException) {
                {
                    throw (RuntimeException) jjte000;
                }
            }
            if (jjte000 instanceof ParseException) {
                {
                    throw (ParseException) jjte000;
                }
            }
            {
                throw (Error) jjte000;
            }
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }

    /**
     * 文法：additive-expression -> term{ addop term}
     *
     * @throws ParseException
     */
    final public Token term() throws ParseException {
        ASTterm jjtn000 = new ASTterm(JJTTERM);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        Token op;
        Token ret;
        Token temp;
        try {
            ret = factor();
            label_6:
            while (true) {
                switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                    case TIMES:
                    case DIVIDE:
                        break;
                    default:
                        jj_la1[18] = jj_gen;
                        break label_6;
                }
                op = mulop();
                jjtn000.setName("mulop: " + op);
                //左值压栈
                codeGen.emitRO(codeGen.OP_ST, codeGen.REG_AC, codeGen.tmpOffset--, codeGen.REG_MP, "op: push left");
                temp = factor();
                //当两个都不为int,报错
                if (!"int".equals(ret.type) || !"int".equals(temp.type)) {
                    errorList.addLeftAndRightNotInt(ret);
                } else {
                    codeGen.emitRO(codeGen.OP_LD, codeGen.REG_AC1, ++codeGen.tmpOffset, codeGen.REG_MP, "op: load left");
                    if ("*".equals(op.image)) {
                        codeGen.emitRO(codeGen.OP_MUL, codeGen.REG_AC, codeGen.REG_AC1, codeGen.REG_AC, "op *");
                    } else {
                        codeGen.emitRO(codeGen.OP_DIV, codeGen.REG_AC, codeGen.REG_AC1, codeGen.REG_AC, "op /");
                    }
                }
                ret.type = "int";
            }
            jjtree.closeNodeScope(jjtn000, true);
            jjtc000 = false;
            {
                return ret;
            }
        } catch (Throwable jjte000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
            if (jjte000 instanceof RuntimeException) {
                {
                    throw (RuntimeException) jjte000;
                }
            }
            if (jjte000 instanceof ParseException) {
                {
                    throw (ParseException) jjte000;
                }
            }
            {
                throw (Error) jjte000;
            }
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }

    /**
     * 文法：addop -> +|-
     *
     * @return
     * @throws ParseException
     */
    final public Token addop() throws ParseException {
        ASTaddop jjtn000 = new ASTaddop(JJTADDOP);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        Token id;
        try {
            switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                case PLUS:
                    id = jj_consume_token(PLUS);
                    jjtree.closeNodeScope(jjtn000, true);
                    jjtc000 = false;
                {
                    return id;
                }
                case MINUS:
                    id = jj_consume_token(MINUS);
                    jjtree.closeNodeScope(jjtn000, true);
                    jjtc000 = false;
                {
                    return id;
                }
                default:
                    jj_la1[19] = jj_gen;
                    jj_consume_token(-1);
                    throw new ParseException();
            }
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }

    /**
     * 文法：mulop -> *|/
     *
     * @return
     * @throws ParseException
     */
    final public Token mulop() throws ParseException {
        ASTmulop jjtn000 = new ASTmulop(JJTMULOP);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        Token id;
        try {
            switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                case TIMES:
                    id = jj_consume_token(TIMES);
                    jjtree.closeNodeScope(jjtn000, true);
                    jjtc000 = false;
                {
                    return id;
                }
                case DIVIDE:
                    id = jj_consume_token(DIVIDE);
                    jjtree.closeNodeScope(jjtn000, true);
                    jjtc000 = false;
                {
                    return id;
                }
                default:
                    jj_la1[20] = jj_gen;
                    jj_consume_token(-1);
                    throw new ParseException();
            }
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }

    /**
     * 文法：factor -> (expression) | var | call | NUM
     *
     * @throws ParseException
     */
    final public Token factor() throws ParseException {
        ASTfactor jjtn000 = new ASTfactor(JJTFACTOR);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        Token t;
        try {
            if (((jj_ntk == -1) ? jj_ntk() : jj_ntk) == LEFTPARENTHESES) {
                jj_consume_token(LEFTPARENTHESES);
                t = expression();
                jj_consume_token(RIGHTPARENTHESES);
                jjtree.closeNodeScope(jjtn000, true);
                jjtc000 = false;
                return t;
            } else {
                jj_la1[21] = jj_gen;
                if (jj_2_7(2147483647)) {
                    t = call();
                    jjtree.closeNodeScope(jjtn000, true);
                    jjtc000 = false;
                    {
                        return t;
                    }
                } else {
                    switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                        case ID:
                        case ARRAYELEMENT:
                            t = var();
                            jjtree.closeNodeScope(jjtn000, true);
                            jjtc000 = false;
                        {
                            return t;
                        }
                        case NUM:
                            t = jj_consume_token(NUM);
                            jjtree.closeNodeScope(jjtn000, true);
                            jjtc000 = false;
                            jjtn000.setName(t.image);
                            codeGen.emitRM(codeGen.OP_LDC, codeGen.REG_AC, Integer.parseInt(t.image), 0, "load const");
                            t.type = "int";
                        {
                            return t;
                        }
                        default:
                            jj_la1[22] = jj_gen;
                            jj_consume_token(-1);
                            throw new ParseException();
                    }
                }
            }
        } catch (Throwable jjte000) {
            if (jjtc000) {
                jjtree.clearNodeScope(jjtn000);
                jjtc000 = false;
            } else {
                jjtree.popNode();
            }
            if (jjte000 instanceof RuntimeException) {
                {
                    throw (RuntimeException) jjte000;
                }
            }
            if (jjte000 instanceof ParseException) {
                {
                    throw (ParseException) jjte000;
                }
            }
            {
                throw (Error) jjte000;
            }
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }

    /**
     * 文法：call -> ID(args)
     *
     * @throws ParseException
     */
    final public Token call() throws ParseException {
        ASTcall jjtn000 = new ASTcall(JJTCALL);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        Token id;
        List<String> arList;
        int jumpLoc;
        try {
            switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                case ID:
                    id = jj_consume_token(ID);
                    jjtn000.setName(id.image);
                    FunInfo fun = symbolTable.getFunInfoByName(id.image);
                    if (fun == null) {
                        errorList.addFunNotDeclared(id);
                    } else {
                        //函数调用前准备，sp,lp,fp压栈
                        codeGen.emitRM(codeGen.OP_ST, codeGen.REG_SP, codeGen.tmpOffset--, codeGen.REG_MP, "push sp");
                        codeGen.emitRM(codeGen.OP_ST, codeGen.REG_LP, codeGen.tmpOffset--, codeGen.REG_MP, "push lp");
                        codeGen.emitRM(codeGen.OP_ST, codeGen.REG_FP, codeGen.tmpOffset--, codeGen.REG_MP, "push fp");
                        codeGen.emitRM(codeGen.OP_ST, codeGen.REG_AC1, codeGen.tmpOffset--, codeGen.REG_MP, "push ac1");
                    }
                    jj_consume_token(LEFTPARENTHESES);
                    arList = args();
                    jj_consume_token(RIGHTPARENTHESES);
                    jjtree.closeNodeScope(jjtn000, true);
                    jjtc000 = false;
                    //查找函数有无定义
                    if (fun == null) {
                        errorList.addFunNotDeclared(id);
                    } else {
                        //查找参数列表
                        List<VarInfo> params = fun.getParams();
                        id.type = fun.getReturnType();
                        //形参实参数量判断
                        if (params.size() != arList.size()) {
                            errorList.addFunArgSizeError(id);
                        } else {
                            boolean flag = true;
                            //形参实参类型判断
                            for (int i = 0; i < params.size(); i++) {
                                if (!params.get(i).getType().equals(arList.get(i))) {
                                    flag = false;
                                    break;
                                }
                            }
                            if (!flag) {
                                errorList.addFunArgTypeError(id);
                            } else {
                                //抬高lp至栈顶sp,新建一个活动记录
                                codeGen.emitRM(codeGen.OP_LDA, codeGen.REG_LP, 0, codeGen.REG_SP, "get new Lp");
                                //设置回调位置
                                codeGen.emitRM(codeGen.OP_LDA, codeGen.REG_FP, 1, codeGen.REG_PC, "get new fp");
                                jumpLoc = fun.getLoc();
                                codeGen.emitRM_Abs(codeGen.OP_LDA, codeGen.REG_PC, jumpLoc, "jump to fun");
                                codeGen.emitRM(codeGen.OP_LD, codeGen.REG_AC1, ++codeGen.tmpOffset, codeGen.REG_MP, "op: load ac1");
                                codeGen.emitRM(codeGen.OP_LD, codeGen.REG_FP, ++codeGen.tmpOffset, codeGen.REG_MP, "op: load fp");
                                codeGen.emitRM(codeGen.OP_LD, codeGen.REG_LP, ++codeGen.tmpOffset, codeGen.REG_MP, "op: load lp");
                                codeGen.emitRM(codeGen.OP_LD, codeGen.REG_SP, ++codeGen.tmpOffset, codeGen.REG_MP, "op: load sp");
                            }
                        }
                        id.type = fun.getReturnType();
                    }
                {
                    return id;
                }
                case OUTPUT:
                    jj_consume_token(OUTPUT);
                    jj_consume_token(LEFTPARENTHESES);
                    id = expression();
                    jj_consume_token(RIGHTPARENTHESES);
                    jjtree.closeNodeScope(jjtn000, true);
                    jjtc000 = false;
                    jjtn000.setName("output");
                    if (!"int".equals(id.type)) {
                        errorList.addFunArgTypeError(id);
                    }
                    codeGen.emitRO(codeGen.OP_OUT, codeGen.REG_AC, 0, 0, "write ac");
                {
                    return new Token("void");
                }
                case INPUT:
                    id = jj_consume_token(INPUT);
                    jj_consume_token(LEFTPARENTHESES);
                    jj_consume_token(RIGHTPARENTHESES);
                    jjtree.closeNodeScope(jjtn000, true);
                    jjtc000 = false;
                    jjtn000.setName("input");
                    codeGen.emitRO(codeGen.OP_IN, codeGen.REG_AC, 0, 0, "read integer value");
                    id.type = "int";
                {
                    return id;
                }
                default:
                    jj_la1[23] = jj_gen;
                    jj_consume_token(-1);
                    throw new ParseException();
            }
        } catch (Throwable jjte000) {
            if (jjtc000) {
                jjtree.clearNodeScope(jjtn000);
                jjtc000 = false;
            } else {
                jjtree.popNode();
            }
            if (jjte000 instanceof RuntimeException) {
                {
                    throw (RuntimeException) jjte000;
                }
            }
            if (jjte000 instanceof ParseException) {
                {
                    throw (ParseException) jjte000;
                }
            }
            {
                throw (Error) jjte000;
            }
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }

    /**
     * 文法：args -> arg-list | empty
     *
     * @throws ParseException
     */
    final public List<String> args() throws ParseException {
        ASTargs jjtn000 = new ASTargs(JJTARGS);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        List<String> args;
        try {
            switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                case OUTPUT:
                case INPUT:
                case ID:
                case NUM:
                case LEFTPARENTHESES:
                case ARRAYELEMENT:
                    args = argList();
                {
                    return args;
                }
                default:
                    jj_la1[24] = jj_gen;
            }
            jjtree.closeNodeScope(jjtn000, true);
            jjtc000 = false;
            {
                return Collections.emptyList();
            }
        } catch (Throwable jjte000) {
            if (jjtc000) {
                jjtree.clearNodeScope(jjtn000);
                jjtc000 = false;
            } else {
                jjtree.popNode();
            }
            if (jjte000 instanceof RuntimeException) {
                {
                    throw (RuntimeException) jjte000;
                }
            }
            if (jjte000 instanceof ParseException) {
                {
                    throw (ParseException) jjte000;
                }
            }
            {
                throw (Error) jjte000;
            }
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }

    /**
     * 文法：arg-list -> expression{ , expression}
     *
     * @throws ParseException
     */
    final public List<String> argList() throws ParseException {
        ASTargList jjtn000 = new ASTargList(JJTARGLIST);
        boolean jjtc000 = true;
        jjtree.openNodeScope(jjtn000);
        Token t;
        List<String> args;
        int i = 0;
        VarInfo var;
        try {
            t = expression();
            args = new ArrayList<>();
            args.add(t.type);
            if ("int".equals(t.type)) {
                codeGen.emitRM(codeGen.OP_ST, codeGen.REG_AC, i++, codeGen.REG_SP, "prepare arg");
            } else {
                var = symbolTable.getVarByName(t.image);
                if (var.getGlobal()) {
                    for (int j = var.getLoc(); j < var.getLoc() + 50; j++) {
                        codeGen.emitRM(codeGen.OP_LD, codeGen.REG_AC, j, codeGen.REG_GP, "prepared load int[]");
                        codeGen.emitRM(codeGen.OP_ST, codeGen.REG_AC, i++, codeGen.REG_SP, "prepare arg");
                    }
                } else {
                    for (int j = var.getLoc(); j < var.getLoc() + 50; j++) {
                        codeGen.emitRM(codeGen.OP_LD, codeGen.REG_AC, j, codeGen.REG_LP, "prepared load int[]");
                        codeGen.emitRM(codeGen.OP_ST, codeGen.REG_AC, i++, codeGen.REG_SP, "prepare arg");
                    }
                }
            }
            label_7:
            while (true) {
                if (((jj_ntk == -1) ? jj_ntk() : jj_ntk) == COMMA) {
                } else {
                    jj_la1[25] = jj_gen;
                    break;
                }
                jj_consume_token(COMMA);
                t = expression();
                args.add(t.type);
                if ("int".equals(t.type)) {
                    codeGen.emitRM(codeGen.OP_ST, codeGen.REG_AC, i++, codeGen.REG_SP, "prepare arg");
                } else {
                    var = symbolTable.getVarByName(t.image);
                    if (var.getGlobal()) {
                        for (int j = var.getLoc(); j < var.getLoc() + 50; j++) {
                            codeGen.emitRM(codeGen.OP_LD, codeGen.REG_AC, j, codeGen.REG_GP, "prepare load int[]");
                            codeGen.emitRM(codeGen.OP_ST, codeGen.REG_AC, i++, codeGen.REG_SP, "prepare arg");
                        }
                    } else {
                        for (int j = var.getLoc(); j < var.getLoc() + 50; j++) {
                            codeGen.emitRM(codeGen.OP_LD, codeGen.REG_AC, j, codeGen.REG_LP, "prepare load int[]");
                            codeGen.emitRM(codeGen.OP_ST, codeGen.REG_AC, i++, codeGen.REG_SP, "prepare arg");
                        }

                    }
                }
            }
            jjtree.closeNodeScope(jjtn000, true);
            jjtc000 = false;
            {
                return args;
            }
        } catch (Throwable jjte000) {
            jjtree.clearNodeScope(jjtn000);
            jjtc000 = false;
            if (jjte000 instanceof RuntimeException) {
                {
                    throw (RuntimeException) jjte000;
                }
            }
            if (jjte000 instanceof ParseException) {
                {
                    throw (ParseException) jjte000;
                }
            }
            {
                throw (Error) jjte000;
            }
        } finally {
            if (jjtc000) {
                jjtree.closeNodeScope(jjtn000, true);
            }
        }
    }

    private boolean jj_2_1(int xla) {
        jj_la = xla;
        jj_lastpos = jj_scanpos = token;
        try {
            return !jj_3_1();
        } catch (LookaheadSuccess ls) {
            return true;
        } finally {
            jj_save(0, xla);
        }
    }

    private boolean jj_2_2(int xla) {
        jj_la = xla;
        jj_lastpos = jj_scanpos = token;
        try {
            return !jj_3_2();
        } catch (LookaheadSuccess ls) {
            return true;
        } finally {
            jj_save(1, xla);
        }
    }

    private boolean jj_2_3(int xla) {
        jj_la = xla;
        jj_lastpos = jj_scanpos = token;
        try {
            return !jj_3_3();
        } catch (LookaheadSuccess ls) {
            return true;
        } finally {
            jj_save(2, xla);
        }
    }

    private boolean jj_2_4(int xla) {
        jj_la = xla;
        jj_lastpos = jj_scanpos = token;
        try {
            return !jj_3_4();
        } catch (LookaheadSuccess ls) {
            return true;
        } finally {
            jj_save(3, xla);
        }
    }

    private boolean jj_2_5(int xla) {
        jj_la = xla;
        jj_lastpos = jj_scanpos = token;
        try {
            return !jj_3_5();
        } catch (LookaheadSuccess ls) {
            return true;
        } finally {
            jj_save(4, xla);
        }
    }

    private boolean jj_2_6(int xla) {
        jj_la = xla;
        jj_lastpos = jj_scanpos = token;
        try {
            return !jj_3_6();
        } catch (LookaheadSuccess ls) {
            return true;
        } finally {
            jj_save(5, xla);
        }
    }

    private boolean jj_2_7(int xla) {
        jj_la = xla;
        jj_lastpos = jj_scanpos = token;
        try {
            return !jj_3_7();
        } catch (LookaheadSuccess ls) {
            return true;
        } finally {
            jj_save(6, xla);
        }
    }

    private boolean jj_3R_19() {
        return jj_3R_30();
    }

    private boolean jj_3R_18() {
        return jj_3R_29();
    }

    private boolean jj_3R_17() {
        return jj_3R_28();
    }

    private boolean jj_3R_16() {
        return jj_3R_27();
    }

    private boolean jj_3R_15() {
        return jj_3R_26();
    }

    private boolean jj_3R_10() {
        Token xsp;
        xsp = jj_scanpos;
        if (jj_3R_15()) {
            jj_scanpos = xsp;
            if (jj_3R_16()) {
                jj_scanpos = xsp;
                if (jj_3R_17()) {
                    jj_scanpos = xsp;
                    if (jj_3R_18()) {
                        jj_scanpos = xsp;
                        return jj_3R_19();
                    }
                }
            }
        }
        return false;
    }

    private boolean jj_3R_44() {
        return jj_3R_10();
    }

    private boolean jj_3R_11() {
        Token xsp;
        xsp = jj_scanpos;
        if (jj_3R_20()) {
            jj_scanpos = xsp;
            return jj_3R_21();
        }
        return false;
    }

    private boolean jj_3R_20() {
        return jj_scan_token(ID);
    }

    private boolean jj_3R_55() {
        if (jj_scan_token(COMMA)) {
            return true;
        }
        return jj_3R_9();
    }

    private boolean jj_3R_36() {
        Token xsp;
        while (true) {
            xsp = jj_scanpos;
            if (jj_3R_44()) {
                jj_scanpos = xsp;
                break;
            }
        }
        return false;
    }

    private boolean jj_3R_43() {
        return jj_3R_8();
    }

    private boolean jj_3R_47() {
        if (jj_3R_59()) {
            return true;
        }
        return jj_3R_46();
    }

    private boolean jj_3R_29() {
        if (jj_scan_token(WHILE)) {
            return true;
        }
        if (jj_scan_token(LEFTPARENTHESES)) {
            return true;
        }
        if (jj_3R_9()) {
            return true;
        }
        if (jj_scan_token(RIGHTPARENTHESES)) {
            return true;
        }
        return jj_3R_10();
    }

    private boolean jj_3R_35() {
        Token xsp;
        while (true) {
            xsp = jj_scanpos;
            if (jj_3R_43()) {
                jj_scanpos = xsp;
                break;
            }
        }
        return false;
    }

    private boolean jj_3R_14() {
        return jj_3R_25();
    }

    private boolean jj_3R_40() {
        if (jj_3R_46()) {
            return true;
        }
        Token xsp;
        while (true) {
            xsp = jj_scanpos;
            if (jj_3R_47()) {
                jj_scanpos = xsp;
                break;
            }
        }
        return false;
    }

    private boolean jj_3R_13() {
        if (jj_scan_token(INT)) {
            return true;
        }
        if (jj_scan_token(ARRAYELEMENT)) {
            return true;
        }
        if (jj_scan_token(NUM)) {
            return true;
        }
        if (jj_scan_token(RIGHTBRACKET)) {
            return true;
        }
        return jj_scan_token(SEMICOLON);
    }

    private boolean jj_3R_33() {
        if (jj_3R_42()) {
            return true;
        }
        return jj_3R_32();
    }

    private boolean jj_3R_45() {
        if (jj_3R_9()) {
            return true;
        }
        Token xsp;
        while (true) {
            xsp = jj_scanpos;
            if (jj_3R_55()) {
                jj_scanpos = xsp;
                break;
            }
        }
        return false;
    }

    private boolean jj_3R_25() {
        if (jj_3R_32()) {
            return true;
        }
        Token xsp;
        xsp = jj_scanpos;
        if (jj_3R_33()) {
            jj_scanpos = xsp;
        }
        return false;
    }

    private boolean jj_3R_12() {
        Token xsp;
        xsp = jj_scanpos;
        if (jj_3R_22()) {
            jj_scanpos = xsp;
            if (jj_3R_23()) {
                jj_scanpos = xsp;
                return jj_3R_24();
            }
        }
        return false;
    }

    private boolean jj_3R_22() {
        if (jj_scan_token(ID)) {
            return true;
        }
        if (jj_scan_token(LEFTPARENTHESES)) {
            return true;
        }
        if (jj_3R_31()) {
            return true;
        }
        return jj_scan_token(RIGHTPARENTHESES);
    }

    private boolean jj_3_2() {
        if (jj_scan_token(INT)) {
            return true;
        }
        if (jj_scan_token(ID)) {
            return true;
        }
        return jj_scan_token(SEMICOLON);
    }

    private boolean jj_3R_8() {
        Token xsp;
        xsp = jj_scanpos;
        if (jj_3_2()) {
            jj_scanpos = xsp;
            return jj_3R_13();
        }
        return false;
    }

    private boolean jj_3R_37() {
        if (jj_scan_token(IF)) {
            return true;
        }
        if (jj_scan_token(LEFTPARENTHESES)) {
            return true;
        }
        if (jj_3R_9()) {
            return true;
        }
        if (jj_scan_token(RIGHTPARENTHESES)) {
            return true;
        }
        return jj_3R_10();
    }

    private boolean jj_3R_39() {
        return jj_3R_45();
    }

    private boolean jj_3R_9() {
        Token xsp;
        xsp = jj_scanpos;
        if (jj_3_6()) {
            jj_scanpos = xsp;
            return jj_3R_14();
        }
        return false;
    }

    private boolean jj_3_6() {
        if (jj_3R_11()) {
            return true;
        }
        if (jj_scan_token(ASSIGN)) {
            return true;
        }
        return jj_3R_9();
    }

    private boolean jj_3R_41() {
        if (jj_3R_48()) {
            return true;
        }
        return jj_3R_40();
    }

    private boolean jj_3R_31() {
        Token xsp;
        xsp = jj_scanpos;
        if (jj_3R_39()) {
            jj_scanpos = xsp;
        }
        return false;
    }

    private boolean jj_3R_58() {
        return jj_scan_token(NUM);
    }

    private boolean jj_3R_27() {
        if (jj_scan_token(LEFTBRACES)) {
            return true;
        }
        if (jj_3R_35()) {
            return true;
        }
        if (jj_3R_36()) {
            return true;
        }
        return jj_scan_token(RIGHTBRACES);
    }

    private boolean jj_3R_57() {
        return jj_3R_11();
    }

    private boolean jj_3R_32() {
        if (jj_3R_40()) {
            return true;
        }
        Token xsp;
        while (true) {
            xsp = jj_scanpos;
            if (jj_3R_41()) {
                jj_scanpos = xsp;
                break;
            }
        }
        return false;
    }

    private boolean jj_3_7() {
        return jj_3R_12();
    }

    private boolean jj_3_1() {
        return jj_3R_8();
    }

    private boolean jj_3R_24() {
        if (jj_scan_token(INPUT)) {
            return true;
        }
        if (jj_scan_token(LEFTPARENTHESES)) {
            return true;
        }
        return jj_scan_token(RIGHTPARENTHESES);
    }

    private boolean jj_3R_56() {
        if (jj_scan_token(LEFTPARENTHESES)) {
            return true;
        }
        if (jj_3R_9()) {
            return true;
        }
        return jj_scan_token(RIGHTPARENTHESES);
    }

    private boolean jj_3_4() {
        if (jj_scan_token(IF)) {
            return true;
        }
        if (jj_scan_token(LEFTPARENTHESES)) {
            return true;
        }
        if (jj_3R_9()) {
            return true;
        }
        if (jj_scan_token(RIGHTPARENTHESES)) {
            return true;
        }
        if (jj_3R_10()) {
            return true;
        }
        if (jj_scan_token(ELSE)) {
            return true;
        }
        return jj_3R_10();
    }

    private boolean jj_3R_54() {
        return jj_scan_token(NOTEQUAL);
    }

    private boolean jj_3R_46() {
        Token xsp;
        xsp = jj_scanpos;
        if (jj_3R_56()) {
            jj_scanpos = xsp;
            if (jj_3_7()) {
                jj_scanpos = xsp;
                if (jj_3R_57()) {
                    jj_scanpos = xsp;
                    return jj_3R_58();
                }
            }
        }
        return false;
    }

    private boolean jj_3R_28() {
        Token xsp;
        xsp = jj_scanpos;
        if (jj_3_4()) {
            jj_scanpos = xsp;
            return jj_3R_37();
        }
        return false;
    }

    private boolean jj_3R_53() {
        return jj_scan_token(EQUAL);
    }

    private boolean jj_3R_38() {
        if (jj_scan_token(RETURN)) {
            return true;
        }
        if (jj_3R_9()) {
            return true;
        }
        return jj_scan_token(SEMICOLON);
    }

    private boolean jj_3R_52() {
        return jj_scan_token(GTE);
    }

    private boolean jj_3R_51() {
        return jj_scan_token(GT);
    }

    private boolean jj_3R_50() {
        return jj_scan_token(LT);
    }

    private boolean jj_3_3() {
        if (jj_scan_token(INT)) {
            return true;
        }
        return jj_scan_token(ID);
    }

    private boolean jj_3R_23() {
        if (jj_scan_token(OUTPUT)) {
            return true;
        }
        if (jj_scan_token(LEFTPARENTHESES)) {
            return true;
        }
        if (jj_3R_9()) {
            return true;
        }
        return jj_scan_token(RIGHTPARENTHESES);
    }

    private boolean jj_3R_63() {
        return jj_scan_token(DIVIDE);
    }

    private boolean jj_3R_49() {
        return jj_scan_token(LTE);
    }

    private boolean jj_3R_42() {
        Token xsp;
        xsp = jj_scanpos;
        if (jj_3R_49()) {
            jj_scanpos = xsp;
            if (jj_3R_50()) {
                jj_scanpos = xsp;
                if (jj_3R_51()) {
                    jj_scanpos = xsp;
                    if (jj_3R_52()) {
                        jj_scanpos = xsp;
                        if (jj_3R_53()) {
                            jj_scanpos = xsp;
                            return jj_3R_54();
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean jj_3R_62() {
        return jj_scan_token(TIMES);
    }

    private boolean jj_3R_59() {
        Token xsp;
        xsp = jj_scanpos;
        if (jj_3R_62()) {
            jj_scanpos = xsp;
            return jj_3R_63();
        }
        return false;
    }

    private boolean jj_3R_21() {
        if (jj_scan_token(ARRAYELEMENT)) {
            return true;
        }
        if (jj_3R_9()) {
            return true;
        }
        return jj_scan_token(RIGHTBRACKET);
    }

    private boolean jj_3R_61() {
        return jj_scan_token(MINUS);
    }

    private boolean jj_3R_26() {
        Token xsp;
        xsp = jj_scanpos;
        if (jj_3R_34()) {
            jj_scanpos = xsp;
            return jj_scan_token(19);
        }
        return false;
    }

    private boolean jj_3R_34() {
        if (jj_3R_9()) {
            return true;
        }
        return jj_scan_token(SEMICOLON);
    }

    private boolean jj_3_5() {
        if (jj_scan_token(RETURN)) {
            return true;
        }
        return jj_scan_token(SEMICOLON);
    }

    private boolean jj_3R_30() {
        Token xsp;
        xsp = jj_scanpos;
        if (jj_3_5()) {
            jj_scanpos = xsp;
            return jj_3R_38();
        }
        return false;
    }

    private boolean jj_3R_60() {
        return jj_scan_token(PLUS);
    }

    private boolean jj_3R_48() {
        Token xsp;
        xsp = jj_scanpos;
        if (jj_3R_60()) {
            jj_scanpos = xsp;
            return jj_3R_61();
        }
        return false;
    }

    /**
     * Reinitialise.
     */
    public void ReInit(java.io.InputStream stream) {
        ReInit(stream, null);
    }

    /**
     * Reinitialise.
     */
    public void ReInit(java.io.InputStream stream, String encoding) {
        try {
            jj_input_stream.ReInit(stream, encoding, 1, 1);
        } catch (java.io.UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        token_source.ReInit(jj_input_stream);
        token = new Token();
        jj_ntk = -1;
        jjtree.reset();
        jj_gen = 0;
        for (int i = 0; i < 26; i++) {
            jj_la1[i] = -1;
        }
        for (int i = 0; i < jj_2_rtns.length; i++) {
            jj_2_rtns[i] = new JJCalls();
        }
    }

    /**
     * Reinitialise.
     */
    public void ReInit(java.io.Reader stream) {
        jj_input_stream.ReInit(stream, 1, 1);
        token_source.ReInit(jj_input_stream);
        token = new Token();
        jj_ntk = -1;
        jjtree.reset();
        jj_gen = 0;
        for (int i = 0; i < 26; i++) {
            jj_la1[i] = -1;
        }
        for (int i = 0; i < jj_2_rtns.length; i++) {
            jj_2_rtns[i] = new JJCalls();
        }
    }

    /**
     * Reinitialise.
     */
    public void ReInit(MyCompilerTokenManager tm) {
        token_source = tm;
        token = new Token();
        jj_ntk = -1;
        jjtree.reset();
        jj_gen = 0;
        for (int i = 0; i < 26; i++) {
            jj_la1[i] = -1;
        }
        for (int i = 0; i < jj_2_rtns.length; i++) {
            jj_2_rtns[i] = new JJCalls();
        }
    }

    private Token jj_consume_token(int kind) throws ParseException {
        Token oldToken;
        if ((oldToken = token).next != null) {
            token = token.next;
        } else {
            token = token.next = token_source.getNextToken();
        }
        jj_ntk = -1;
        if (token.kind == kind) {
            jj_gen++;
            if (++jj_gc > 100) {
                jj_gc = 0;
                for (int i = 0; i < jj_2_rtns.length; i++) {
                    JJCalls c = jj_2_rtns[i];
                    while (c != null) {
                        if (c.gen < jj_gen) {
                            c.first = null;
                        }
                        c = c.next;
                    }
                }
            }
            printToken(token);
            return token;
        }
        token = oldToken;
        jj_kind = kind;
        throw generateParseException();
    }

    private boolean jj_scan_token(int kind) {
        if (jj_scanpos == jj_lastpos) {
            jj_la--;
            if (jj_scanpos.next == null) {
                jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
            } else {
                jj_lastpos = jj_scanpos = jj_scanpos.next;
            }
        } else {
            jj_scanpos = jj_scanpos.next;
        }
        if (jj_rescan) {
            int i = 0;
            Token tok = token;
            while (tok != null && tok != jj_scanpos) {
                i++;
                tok = tok.next;
            }
            if (tok != null) {
                jj_add_error_token(kind, i);
            }
        }
        if (jj_scanpos.kind != kind) {
            return true;
        }
        if (jj_la == 0 && jj_scanpos == jj_lastpos) {
            throw jj_ls;
        }
        return false;
    }

    /**
     * Get the next Token.
     */
    final public Token getNextToken() {
        if (token.next != null) {
            token = token.next;
        } else {
            token = token.next = token_source.getNextToken();
        }
        jj_ntk = -1;
        jj_gen++;
        return token;
    }

    /**
     * Get the specific Token.
     */
    final public Token getToken(int index) {
        Token t = token;
        for (int i = 0; i < index; i++) {
            if (t.next != null) {
                t = t.next;
            } else {
                t = t.next = token_source.getNextToken();
            }
        }
        return t;
    }

    private int jj_ntk() {
        if ((jj_nt = token.next) == null) {
            return (jj_ntk = (token.next = token_source.getNextToken()).kind);
        } else {
            return (jj_ntk = jj_nt.kind);
        }
    }

    private void jj_add_error_token(int kind, int pos) {
        if (pos >= 100) {
            return;
        }
        if (pos == jj_endpos + 1) {
            jj_lasttokens[jj_endpos++] = kind;
        } else if (jj_endpos != 0) {
            jj_expentry = new int[jj_endpos];
            if (jj_endpos >= 0) {
                System.arraycopy(jj_lasttokens, 0, jj_expentry, 0, jj_endpos);
            }
            jj_entries_loop:
            for (int[] oldentry : jj_expentries) {
                if (oldentry.length == jj_expentry.length) {
                    for (int i = 0; i < jj_expentry.length; i++) {
                        if (oldentry[i] != jj_expentry[i]) {
                            continue jj_entries_loop;
                        }
                    }
                    jj_expentries.add(jj_expentry);
                    break;
                }
            }
            if (pos != 0) {
                jj_lasttokens[(jj_endpos = pos) - 1] = kind;
            }
        }
    }

    /**
     * Generate ParseException.
     */
    public ParseException generateParseException() {
        jj_expentries.clear();
        boolean[] la1tokens = new boolean[39];
        if (jj_kind >= 0) {
            la1tokens[jj_kind] = true;
            jj_kind = -1;
        }
        for (int i = 0; i < 26; i++) {
            if (jj_la1[i] == jj_gen) {
                for (int j = 0; j < 32; j++) {
                    if ((jj_la1_0[i] & (1 << j)) != 0) {
                        la1tokens[j] = true;
                    }
                    if ((jj_la1_1[i] & (1 << j)) != 0) {
                        la1tokens[32 + j] = true;
                    }
                }
            }
        }
        for (int i = 0; i < 39; i++) {
            if (la1tokens[i]) {
                jj_expentry = new int[1];
                jj_expentry[0] = i;
                jj_expentries.add(jj_expentry);
            }
        }
        jj_endpos = 0;
        jj_rescan_token();
        jj_add_error_token(0, 0);
        int[][] exptokseq = new int[jj_expentries.size()][];
        for (int i = 0; i < jj_expentries.size(); i++) {
            exptokseq[i] = jj_expentries.get(i);
        }
        return new ParseException(token, exptokseq, tokenImage);
    }

    /**
     * 启用跟踪
     */
    final public void enable_tracing() {
    }

    /**
     * 禁用跟踪
     */
    final public void disable_tracing() {
    }

    private void jj_rescan_token() {
        jj_rescan = true;
        for (int i = 0; i < 7; i++) {
            try {
                JJCalls p = jj_2_rtns[i];
                do {
                    if (p.gen > jj_gen) {
                        jj_la = p.arg;
                        jj_lastpos = jj_scanpos = p.first;
                        switch (i) {
                            case 0:
                                jj_3_1();
                                break;
                            case 1:
                                jj_3_2();
                                break;
                            case 2:
                                jj_3_3();
                                break;
                            case 3:
                                jj_3_4();
                                break;
                            case 4:
                                jj_3_5();
                                break;
                            case 5:
                                jj_3_6();
                                break;
                            case 6:
                                jj_3_7();
                                break;
                            default:
                                break;
                        }
                    }
                    p = p.next;
                } while (p != null);
            } catch (LookaheadSuccess ignored) {
            }
        }
        jj_rescan = false;
    }

    private void jj_save(int index, int xla) {
        JJCalls p = jj_2_rtns[index];
        while (p.gen > jj_gen) {
            if (p.next == null) {
                p = p.next = new JJCalls();
                break;
            }
            p = p.next;
        }
        p.gen = jj_gen + xla - jj_la;
        p.first = token;
        p.arg = xla;
    }

    static private final class LookaheadSuccess extends java.lang.Error {
    }

    static final class JJCalls {
        int gen;
        Token first;
        int arg;
        JJCalls next;
    }

}
