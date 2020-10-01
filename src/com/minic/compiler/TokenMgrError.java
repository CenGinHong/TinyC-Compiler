package src.com.minic.compiler;

/**
 * @author: 陈健航
 * @description: 单词错误
 * @since: 2020/6/8 17:41
 * @version: 1.0
 */
public class TokenMgrError extends Error {

    private static final long serialVersionUID = 1L;


    static final int LEXICAL_ERROR = 0;

    /**
     * 试图创建静态令牌管理器的第二个实例。
     */
  static final int STATIC_LEXER_ERROR = 1;

    /**
     * 试图更改为无效的词法状态。
     */
  static final int INVALID_LEXICAL_STATE = 2;

    /**
     * 在令牌管理器中检测到无限循环（并使其脱离了循环）。
     */
  static final int LOOP_DETECTED = 3;

    /**
     * 指示引发异常的原因。它具有以上4个值之一。
     */
  int errorCode;

    /**
     * 用给定字符串中的转义（或Unicode换码）等价字符替换无法打印的字符
     */
  protected static final String addEscapes(String str) {
    StringBuffer retval = new StringBuffer();
    char ch;
    for (int i = 0; i < str.length(); i++) {
      switch (str.charAt(i))
      {
        case 0 :
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
      }
    }
    return retval.toString();
  }


  protected static String LexicalError(boolean EOFSeen, int lexState, int errorLine, int errorColumn, String errorAfter, char curChar) {
    return("Lexical error at line " +
          errorLine + ", column " +
          errorColumn + ".  Encountered: " +
          (EOFSeen ? "<EOF> " : ("\"" + addEscapes(String.valueOf(curChar)) + "\"") + " (" + (int)curChar + "), ") +
          "after : \"" + addEscapes(errorAfter) + "\"");
  }

    @Override
  public String getMessage() {
    return super.getMessage();
  }


    public TokenMgrError() {
  }


    public TokenMgrError(String message, int reason) {
    super(message);
    errorCode = reason;
  }


    public TokenMgrError(boolean EOFSeen, int lexState, int errorLine, int errorColumn, String errorAfter, char curChar, int reason) {
    this(LexicalError(EOFSeen, lexState, errorLine, errorColumn, errorAfter, curChar), reason);
  }
}

