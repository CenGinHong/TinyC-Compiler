package src.com.minic.constants;


public interface MyCompilerConstants {


    int EOF = 0;

    int IF = 7;

    int ELSE = 8;

    int WHILE = 9;

    int INT = 10;

    int RETURN = 11;

    int VOID = 12;

    int OUTPUT = 13;

    int INPUT = 14;

    int letter = 15;

    int ID = 16;

    int digit = 17;

    int NUM = 18;

    int SEMICOLON = 19;

    int EQUAL = 20;

    int NOTEQUAL = 21;

    int ASSIGN = 22;

    int PLUS = 23;

    int MINUS = 24;

    int TIMES = 25;

    int DIVIDE = 26;

    int COMMA = 27;

    int LEFTPARENTHESES = 28;

    int RIGHTPARENTHESES = 29;

    int LEFTBRACKET = 30;

    int RIGHTBRACKET = 31;

    int ARRAYELEMENT = 32;

    int LEFTBRACES = 33;

    int RIGHTBRACES = 34;

    int LT = 35;

    int GT = 36;

    int LTE = 37;

    int GTE = 38;


    int DEFAULT = 0;

    /**
     * Literal token values.
     */
    String[] tokenImage = {
            "<EOF>",
            "\" \"",
            "\"\\n\"",
            "\"\\r\"",
            "\"\\t\"",
            "<token of kind 5>",
            "<token of kind 6>",
            "\"if\"",
            "\"else\"",
            "\"while\"",
            "\"int\"",
            "\"return\"",
            "\"void\"",
            "\"output\"",
            "\"input\"",
            "<letter>",
            "<ID>",
            "<digit>",
            "<NUM>",
            "\";\"",
            "\"==\"",
            "\"!=\"",
            "\"=\"",
            "\"+\"",
            "\"-\"",
            "\"*\"",
            "\"/\"",
            "\",\"",
            "\"(\"",
            "\")\"",
            "\"[\"",
            "\"]\"",
            "<ARRAYELEMENT>",
            "\"{\"",
            "\"}\"",
            "\"<\"",
            "\">\"",
            "\"<=\"",
            "\">=\"",
    };

}
