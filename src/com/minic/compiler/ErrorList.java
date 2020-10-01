package src.com.minic.compiler;

import java.util.ArrayList;

/**
 * @author: �½���
 * @description:
 * @since: 2020/6/10 14:07
 * @version: 1.0
 */
public class ErrorList {
    private final ArrayList<String> errorList = new ArrayList<>();


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
        String errorInfo = "��" + token.beginLine + ":" + "��" + token.beginColumn +
                " ���� " +
                token.image +
                " δ����";
        this.addError(errorInfo);
    }

    /**
     * ���ʽ����int
     *
     * @param token
     */
    public void addExpSubscript(Token token) {
        String errorInfo = "��" + token.beginLine + ":" + "��" + token.beginColumn +
                " ���ʽ��ҪΪint����";
        this.addError(errorInfo);
    }

    /**
     * �����±겻��int
     *
     * @param token
     */
    public void addArraySubscript(Token token) {
        String errorInfo = "��" + token.beginLine + ":" + "��" + token.beginColumn +
                " �����±���ҪΪint����";
        this.addError(errorInfo);
    }

    /**
     * ��ֵ����
     * @param token
     */
    public void addLeftIsNotInt(Token token) {
        String errorInfo = "��" + token.beginLine + ":" + "��" + token.beginColumn +
                " ��ֵ��ҪΪint����";
        this.addError(errorInfo);
    }

    public void addLeftAndRightNotInt(Token token) {
        String errorInfo = "��" + token.beginLine + ":" + "��" + token.beginColumn +
                " ��ֵ����ֵ����ҪΪint����";
        this.addError(errorInfo);
    }

    /**
     * �����Ѿ�������
     * @param token
     */
    public void addVarHaveDeclared(Token token) {
        String errorInfo = "��" + token.beginLine + ":" + "��" + token.beginColumn +
                " ���� " +
                token.image +
                " �Ѿ�����";
        this.addError(errorInfo);
    }


    /**
     * �����Ѿ�������
     * @param token
     */
    public void addFunHaveDeclared(Token token) {
        String errorInfo = "��" + token.beginLine + ":" + "��" + token.beginColumn +
                " ���� " +
                token.image +
                " �Ѿ�����";
        this.addError(errorInfo);
    }

    /**
     * ����δ����
     * @param token
     */
    public void addFunNotDeclared(Token token) {
        String errorInfo = "��" + token.beginLine + ":" + "��" + token.beginColumn +
                " ���� " +
                token.image +
                " δ����";
        this.addError(errorInfo);
    }


    /**
     * �������ʹ���
     * @param token
     */
    public void addFunArgTypeError(Token token) {
        String errorInfo = "��" + token.beginLine + ":" + "��" + token.beginColumn +
                " ���뺯�� " + token.image + " �Ĳ��������뺯���Ķ��岻һ��";
        this.addError(errorInfo);
    }

    /**
     * ������������
     * @param token
     */
    public void addFunArgSizeError(Token token) {
        String errorInfo = "��" + token.beginLine + ":" + "��" + token.beginColumn +
                " ���뺯�� " + token.image + " �Ĳ��������뺯���Ķ��岻һ��";
        this.addError(errorInfo);
    }

    /**
     * ���һ��������Ҫ��main
     *
     * @param token
     */
    public void addLastNotMain(Token token) {
        String errorInfo = "��" + token.beginLine + ":" + "��" + token.beginColumn +
                " ���һ����������Ϊmain����";
        this.addError(errorInfo);
    }

    /**
     * ���һ����������ֵ��ƥ��
     *
     * @param token
     */
    public void addFunReturnType(Token token) {
        String errorInfo = "��" + token.beginLine + ":" + "��" + token.beginColumn +
                " ��������ֵ��ƥ��";
        this.addError(errorInfo);
    }
}
