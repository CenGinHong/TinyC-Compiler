package src.com.minic.compiler;

/**
 * @author: 陈健航
 * @description:
 * @since: 2020/6/8 17:41
 * @version: 1.0
 */
public class Token implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 单词类别常量，在MyCompilerConstants中
     */
    public int kind;

    /**
     * 起始行
     */
    public int beginLine;

    /**
     * 起始列
     */
    public int beginColumn;

    /**
     * 结束行
     */
    public int endLine;

    /**
     * 结束列
     */
    public int endColumn;

    /**
     * 令牌的字符串图像。
     */
    public String image;

    /**
     * 类型
     */
    public String type;

    /**
     * 对输入流中下一个常规（非特殊）令牌的引用。
     */
    public Token next;


    /**
     * 无参数构造函数
     */
    public Token() {
    }

    /**
     * 为指定的Image构造一个新令牌。
     */
    public Token(int kind) {
        this(kind, null);
    }

    public Token(String type) {
        this.type = type;
    }

    /**
     * 为指定的Image和Kind构造一个新令牌。
     */
    public Token(int kind, String image) {
        this.kind = kind;
        this.image = image;
    }

    public static Token newToken(int ofKind, String image) {
        switch (ofKind) {
            default:
                return new Token(ofKind, image);
        }
    }

    public static Token newToken(int ofKind) {
        return newToken(ofKind, null);
    }

    /**
     * 令牌的可选属性值。
     */
    public Object getValue() {
        return null;
    }

    @Override
    public String toString() {
        return image;
    }

}

