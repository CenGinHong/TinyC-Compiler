import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class ErrorList {
    private final ArrayList<String> errorList = new ArrayList<>();

    public FileOutputStream out = null;

    public PrintStream p = null;


    public void addError(String error) {
        this.errorList.add(error);
    }

    public void printError() {
        for (int i = 0; i < errorList.size(); i++) {
            System.out.println("error" + (i + 1) + ": " + errorList.get(i));
        }
    }

    public void clear() {
        errorList.clear();
    }

    public ArrayList<String> getErrorList() {
        return this.errorList;
    }

    public void addVarNotDeclared(Token token) {
        String errorInfo = "行" + token.beginLine + ":" + "列" + token.beginColumn +
                " 变量 " +
                token.image +
                " 未定义";
        this.addError(errorInfo);
    }

    /**
     * 表达式不是int
     *
     * @param token
     */
    public void addExpSubscript(Token token) {
        String errorInfo = "行" + token.beginLine + ":" + "列" + token.beginColumn +
                " 表达式需要为int类型";
        this.addError(errorInfo);
    }

    /**
     * 数组下标不是int
     *
     * @param token
     */
    public void addArraySubscript(Token token) {
        String errorInfo = "行" + token.beginLine + ":" + "列" + token.beginColumn +
                " 数组下标需要为int类型";
        this.addError(errorInfo);
    }

    /**
     * 左值错误
     * @param token
     */
    public void addLeftIsNotInt(Token token) {
        String errorInfo = "行" + token.beginLine + ":" + "列" + token.beginColumn +
                " 左值需要为int类型";
        this.addError(errorInfo);
    }

    public void addLeftAndRightNotInt(Token token) {
        String errorInfo = "行" + token.beginLine + ":" + "列" + token.beginColumn +
                " 左值和右值都需要为int类型";
        this.addError(errorInfo);
    }

    /**
     * 变量已经被定义
     * @param token
     */
    public void addVarHaveDeclared(Token token) {
        String errorInfo = "行" + token.beginLine + ":" + "列" + token.beginColumn +
                " 变量 " +
                token.image +
                " 已经定义";
        this.addError(errorInfo);
    }


    /**
     * 函数已经被定义
     * @param token
     */
    public void addFunHaveDeclared(Token token) {
        String errorInfo = "行" + token.beginLine + ":" + "列" + token.beginColumn +
                " 函数 " +
                token.image +
                " 已经定义";
        this.addError(errorInfo);
    }

    /**
     * 函数未定义
     * @param token
     */
    public void addFunNotDeclared(Token token) {
        String errorInfo = "行" + token.beginLine + ":" + "列" + token.beginColumn +
                " 函数 " +
                token.image +
                " 未定义";
        this.addError(errorInfo);
    }


    /**
     * 函数类型错误
     * @param token
     */
    public void addFunArgTypeError(Token token) {
        String errorInfo = "行" + token.beginLine + ":" + "列" + token.beginColumn +
                " 传入函数 " + token.image + " 的参数类型与函数的定义不一致";
        this.addError(errorInfo);
    }

    /**
     * 函数参数错误
     * @param token
     */
    public void addFunArgSizeError(Token token) {
        String errorInfo = "行" + token.beginLine + ":" + "列" + token.beginColumn +
                " 传入函数 " + token.image + " 的参数数量与函数的定义不一致";
        this.addError(errorInfo);
    }

    /**
     * 最后一个函数需要是main
     * @param token
     */
    public void addLastNotMain(Token token) {
        String errorInfo = "行" + token.beginLine + ":" + "列" + token.beginColumn +
                " 最后一个函数必须为main函数";
        this.addError(errorInfo);
    }
}
