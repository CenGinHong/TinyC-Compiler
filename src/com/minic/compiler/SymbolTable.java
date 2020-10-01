package src.com.minic.compiler;


import src.com.minic.symbol.VarInfo;

import java.util.*;

/**
 * @author: 陈健航
 * @description:
 * @since: 2020/6/9 17:41
 * @version: 1.0
 */
public class SymbolTable {


    private List<HashMap<String, VarInfo>> varList;

    private List<FunInfo> funList;

    /**
     * 局部变量偏移位置计数
     */
    int localSize;

    /**
     * 全局变量数量
     */
    int globalSize;


    /**
     * 临时的函数体变量符号储存点,用于进入函数定义是加入变量表
     */
    private final List<VarInfo> tempFunPar;

    public List<HashMap<String, VarInfo>> getVarList() {
        return varList;
    }

    public List<FunInfo> getFunList() {
        return funList;
    }

    public SymbolTable() {
        this.varList = new ArrayList<>();
        this.funList = new ArrayList<>();
        this.tempFunPar = new ArrayList<>();
        localSize = 0;
        globalSize = 0;
    }


    /**
     * 当前块级是否有是否有重复的变量符号
     *
     * @param info
     * @return true已存在该符号， false,不存在
     */
    public boolean checkVarRepeat(VarInfo info) {
        Set<Map.Entry<String, VarInfo>> entries = varList.get(varList.size() - 1).entrySet();
        return entries.stream().anyMatch(e -> e.getValue().getName().equals(info.getName()));
    }

    /**
     * 是否已经定义,查找包括当前块级和以上块级
     *
     * @param name
     * @return 已经定义的变量
     */
    public VarInfo getVarByName(String name) {
        VarInfo res = null;
        boolean flag = true;
        for (int i = varList.size() - 1; i >= 0 && flag; i--) {
            HashMap<String, VarInfo> map = varList.get(i);
            Set<Map.Entry<String, VarInfo>> entries = map.entrySet();
            for (Map.Entry<String, VarInfo> e : entries) {
                if (e.getKey().equals(name)) {
                    res = e.getValue();
                    flag = false;
                    break;
                }
            }
        }
        return res;
    }

    /**
     * 加入当前列表中
     *
     * @param varInfo
     * @return
     */
    public boolean addVar(VarInfo varInfo) {
        //是否已经定义
        if (checkVarRepeat(varInfo)) {
            return false;
        } else {
            //全局变量
            if (varList.size() == 1) {
                varInfo.setGlobal(true);
                varInfo.setLoc(globalSize);
                globalSize += varInfo.getSize();
                varList.get(0).put(varInfo.getName(), varInfo);
            } else {
                varInfo.setGlobal(false);
                varInfo.setLoc(localSize);
                localSize += varInfo.getSize();
                varList.get(varList.size() - 1).put(varInfo.getName(), varInfo);
            }
            return true;
        }
    }

    public boolean isGlobalVar(String varName) {
        for (int i = varList.size() - 1; i >= 0; i--) {
            HashMap<String, VarInfo> map = varList.get(i);
            Set<Map.Entry<String, VarInfo>> entries = map.entrySet();
            for (Map.Entry<String, VarInfo> e : entries) {
                if (e.getKey().equals(varName)) {
                    return i == 0;
                }
            }
        }
        return false;
    }

    public int reduceLocalSize() {
        int size = 0;
        HashMap<String, VarInfo> map = varList.get(varList.size() - 1);
        for (Map.Entry<String, VarInfo> e : map.entrySet()) {
            if ("int".equals(e.getValue().getType())) {
                size += 1;
            } else {
                size += e.getValue().getSize();
            }
        }
        localSize -= size;
        return size;
    }

    /**
     * 加入函数列表中
     *
     * @param funInfo
     * @return
     */
    public boolean addFun(FunInfo funInfo) {
        boolean anyMatch = funList.stream().anyMatch(e -> e.getName().equals(funInfo.getName()));
        //符号重复
        if (anyMatch) {
            return false;
        } else {
            funList.add(funInfo);
            return true;
        }
    }

    /**
     * 返回最后一次定义的函数，检查函数返回类型用
     *
     * @return
     */
    public FunInfo getLatestDeclarationFun() {
        return funList.get(funList.size() - 1);
    }

    /**
     * 通过函数名称查找
     *
     * @param name
     * @return
     */
    public FunInfo getFunInfoByName(String name) {
        for (FunInfo e : funList) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;
    }

    /**
     * 通过变量获取偏移地址
     *
     * @param name
     * @return
     */
    public int getVarLocByVarName(String name) {
        VarInfo var = getVarByName(name);
        if (var == null) {
            return 0;
        } else {
            return var.getLoc();
        }
    }

    public List<VarInfo> getTempFunPar() {
        return tempFunPar;
    }

}
