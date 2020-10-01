package src.com.minic.compiler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 陈健航
 * @description:
 * @since: 2020/6/11 17:07
 * @version: 1.0
 */
public class CodeGen {

    public int tmpOffset = 0;

    /**
     * 指令计数器
     */
    public final int REG_PC = 7;

    /**
     * 临时变量栈指针
     */
    public final int REG_MP = 6;
    /**
     * 全局变量指针
     */
    public final int REG_GP = 5;
    /**
     * 本地变量栈指针
     */
    public final int REG_LP = 4;
    /**
     * 发生函数调用时，函数结束回跳的程序地址指针
     */
    public final int REG_FP = 2;
    /**
     * 变量栈顶指针
     * 每定义一个变量往上自增
     * 发生函数调用时，以根据栈顶位置分配新的活动记录
     */
    public final int REG_SP = 3;
    /**
     * 结果寄存器
     */
    public final int REG_AC = 0;
    /**
     * 结果寄存器
     */
    public final int REG_AC1 = 1;

    public final String OP_HALT = "HALT";
    public final String OP_IN = "IN";
    public final String OP_OUT = "OUT";
    public final String OP_ADD = "ADD";
    public final String OP_SUB = "SUB";
    public final String OP_MUL = "MUL";
    public final String OP_DIV = "DIV";
    public final String OP_LD = "LD";
    public final String OP_LDA = "LDA";
    public final String OP_LDC = "LDC";
    public final String OP_ST = "ST";
    public final String OP_JLT = "JLT";
    public final String OP_JLE = "JLE";
    public final String OP_JGE = "JGE";
    public final String OP_JGT = "JGT";
    public final String OP_JEQ = "JEQ";
    public final String OP_JNE = "JNE";
    /**
     * 自定义操作，reg[r]= dmem[reg[a]+red[d]];
     */
    public final String OP_STS = "STS";

    /**
     * 自定义操作，dmem[reg[a]+red[d]] = reg[r];
     */
    public final String OP_LDS = "LDS";

    public int emitLoc = 0;

    public int highEmitLoc = 0;

    int mainLoc;

    private List<String> qt;

    public List<String> getQt() {
        return qt;
    }

    private boolean traceCode;


    public CodeGen(boolean traceCode) {
        this.qt = new ArrayList<>();
        this.traceCode = traceCode;
    }

    void emitComment(String comment) {
        if (traceCode) {
            qt.add("* " + comment);
        }
    }


    /**
     * Procedure emitRO emits a register-only
     * TM instruction
     * op = the opcode
     * r = target register
     * s = 1st source register
     * t = 2nd source register
     * c = a comment to be printed if TraceCode is TRUE
     */
    void emitRO(String op, int r, int s, int t, String c) {
        String code = String.format("%3d:  %5s  %d,%d,%d ", emitLoc++, op, r, s, t);
        if (traceCode) {
            code += String.format("\t%s", c);
        }
        qt.add(code);
        if (highEmitLoc < emitLoc) {
            highEmitLoc = emitLoc;
        }
    }

    /**
     * Procedure emitRM emits a register-to-memory
     * TM instruction
     * op = the opcode
     * r = target register
     * d = the offset
     * s = the base register
     * c = a comment to be printed if TraceCode is TRUE
     */
    void emitRM(String op, int r, int d, int s, String c) {
        String code = String.format("%3d:  %5s  %d,%d(%d) ", emitLoc++, op, r, d, s);
        if (traceCode) {
            code += String.format("\t%s", c);
        }
        qt.add(code);
        if (highEmitLoc < emitLoc) {
            highEmitLoc = emitLoc;
        }
    }

    /**
     * Function emitSkip skips "howMany" code
     * locations for later backpatch. It also
     * returns the current code position
     */
    int emitSkip(int howMany) {
        int i = emitLoc;
        emitLoc += howMany;
        if (highEmitLoc < emitLoc) {
            highEmitLoc = emitLoc;
        }
        return i;
    }

    /**
     * Procedure emitBackup backs up to
     * loc = a previously skipped location
     */
    void emitBackup(int loc) {
        if (loc > highEmitLoc) {
            emitComment("BUG in emitBackup");
        }
        emitLoc = loc;
    }

    /**
     * Procedure emitRestore restores the current
     * code position to the highest previously
     * unemitted position
     */
    void emitRestore() {
        emitLoc = highEmitLoc;
    }

    /**
     * Procedure emitRM_Abs converts an absolute reference
     * to a pc-relative reference when emitting a
     * register-to-memory TM instruction
     * op = the opcode
     * r = target register
     * a = the absolute location in memory
     * c = a comment to be printed if TraceCode is TRUE
     */
    void emitRM_Abs(String op, int r, int a, String c) {
        String code = String.format("%3d:  %5s  %d,%d(%d) ", emitLoc, op, r, a - (emitLoc + 1), REG_PC);
        ++emitLoc;
        if (traceCode) {
            code += String.format("\t%s", c);
        }
        qt.add(code);
        if (highEmitLoc < emitLoc) {
            highEmitLoc = emitLoc;
        }

    }


}
