package src.com.minic.compiler;

/**
 * @author: 陈健航
 * @description: 遇到解析错误时，将引发此异常。
 * @since: 2020/4/3 12:25
 * @version: 1.0
 */
@SuppressWarnings("ALL")
public class ParseException extends Exception {

    private static final long serialVersionUID = 1L;
    /**
     * 这是已成功使用的最后一个单词。如果由于解析错误而创建了该对象，则此单词之后的单词，将是第一个错误单词。
     */
    public Token currentToken;
    /**
     * 此数组中的每个条目都是一个整数数组。每个整数数组*表示在解析的这一点上预期的标记序列（按其顺序值）。
     */
    public int[][] expectedTokenSequences;
    /**
     * 这是对生成的分析器的“ tokenImage”数组的引用，该分析器中发生了分析错误。该数组在生成的Constants接口中定义。
     */
    public String[] tokenImage;
    /**
     * 该机器的行字符串的结尾。
     */
    protected String eol = System.getProperty("line.separator", "\n");


    /**
     * 异常构造方法
     *
     * @param currentTokenVal
     * @param expectedTokenSequencesVal
     * @param tokenImageVal
     */
    public ParseException(Token currentTokenVal,
                          int[][] expectedTokenSequencesVal,
                          String[] tokenImageVal
    ) {
        super(initialise(currentTokenVal, expectedTokenSequencesVal, tokenImageVal));
        currentToken = currentTokenVal;
        expectedTokenSequences = expectedTokenSequencesVal;
        tokenImage = tokenImageVal;
    }

    public ParseException() {
        super();
    }

    /**
     * 带有异常信息的构造器
     */
    public ParseException(String message) {
        super(message);
    }

    /**
     * 使用“ currentToken”和“ expectedTokenSequences”生成一个parse错误消息并返回它。
     * 如果由于解析错误而创建了该对象，则会显示正确的错误消息。
     */
    private static String initialise(Token currentToken,
                                     int[][] expectedTokenSequences,
                                     String[] tokenImage) {
        String eol = System.getProperty("line.separator", "\n");
        StringBuffer expected = new StringBuffer();
        int maxSize = 0;
        for (int i = 0; i < expectedTokenSequences.length; i++) {
            if (maxSize < expectedTokenSequences[i].length) {
                maxSize = expectedTokenSequences[i].length;
            }
            for (int j = 0; j < expectedTokenSequences[i].length; j++) {
                expected.append(tokenImage[expectedTokenSequences[i][j]]).append(' ');
            }
            if (expectedTokenSequences[i][expectedTokenSequences[i].length - 1] != 0) {
                expected.append("...");
            }
            expected.append(eol).append("    ");
        }
        String retval = "行" + currentToken.next.beginLine + " 列" + currentToken.next.beginColumn + ": ";
        retval += "错误的符号 \"";
        Token tok = currentToken.next;
        for (int i = 0; i < maxSize; i++) {
            if (i != 0) {
                retval += " ";
            }
            if (tok.kind == 0) {
                retval += tokenImage[0];
                break;
            }
        }
        retval += " " + tokenImage[tok.kind];
        retval += " \"";
        retval += add_escapes(tok.image);
        retval += " \"";
        tok = tok.next;
        return retval;
    }


    /**
     * 原始版本不能用作ASCII字符串文字的一部分时，用于将原始字符转换为转义版本。
     */
    static String add_escapes(String str) {
        StringBuffer retval = new StringBuffer();
        char ch;
        for (int i = 0; i < str.length(); i++) {
            switch (str.charAt(i)) {
                case 0:
                    continue;
                case '\b':
                    retval.append("\\b");
                    continue;
                case '\t':
                    retval.append("\\t");
                    continue;
                case '\n':
                    retval.append("\\n");
                    continue;
                case '\f':
                    retval.append("\\f");
                    continue;
                case '\r':
                    retval.append("\\r");
                    continue;
                case '\"':
                    retval.append("\\\"");
                    continue;
                case '\'':
                    retval.append("\\\'");
                    continue;
                case '\\':
                    retval.append("\\\\");
                    continue;
                default:
                    if ((ch = str.charAt(i)) < 0x20 || ch > 0x7e) {
                        String s = "0000" + Integer.toString(ch, 16);
                        retval.append("\\u" + s.substring(s.length() - 4, s.length()));
                    } else {
                        retval.append(ch);
                    }
                    continue;
            }
        }
        return retval.toString();
    }

}

