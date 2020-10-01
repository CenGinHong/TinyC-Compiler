package src.com.minic.compiler;

import src.com.minic.node.SimpleNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author: 陈健航
 * @description: 界面主程序
 * @since: 2020/3/30 12:58
 * @version: 1.0
 */
@SuppressWarnings("ALL")
public class MainWindows {

    /**
     * 打开文件
     */
    private JButton openFileBtn;

    /**
     * 另存为文件
     */
    private JButton saveAsButton;

    /**
     * 词法分析
     */
    private JButton lexButton;

    /**
     * 语法分析
     */
    private JButton parseButton;

    /**
     * 语法树另存为
     */
    private JButton saveSyntaxTreeButton;
    /**
     * 语义分析
     */
    private JButton yuYiFenXiButton;
    /**
     * 中间代码生成
     */
    private JButton zhongJianDaiMaButton;
    /**
     * 中间代码另存为
     */
    private JButton saveSzhongJianDaiMaButton;

    /**
     * 源码区
     */
    private JTextArea sourceTextArea;

    /**
     * 词法区
     */
    private JTextArea lexingTextArea;

    /**
     * 语法树区
     */
    private JTextArea parsingTextArea;

    /**
     * 语法树区
     */
    private JTextArea yuYiFenXiTextArea;

    /**
     * 中间代码区
     */
    private JTextArea zhongJianDaiMaTextArea;

    private JFrame frame;

    public static void main(String[] args) {
        MainWindows mainWindows = new MainWindows();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                mainWindows.createAndShowGUI();
            }
        });
    }

    /**
     * 创建窗口
     */
    private void createAndShowGUI() {
        // 创建 JFrame 实例
        frame = new JFrame("MyCompiler");
        // Setting the width and height of frame
        frame.setSize(1000, 600);
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //面板
        JPanel panel = new JPanel(new BorderLayout());
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(null);
        panel.add(centerPanel, BorderLayout.CENTER);
        frame.setContentPane(panel);
        //选择项卡面板
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBounds(400, 20, 500, 500);
        centerPanel.add(tabbedPane);
        //工具栏
        JToolBar toolBar = getJToolbar();
        toolBar.setMargin(new Insets(0, 10, 0, 20));
        panel.add(toolBar, BorderLayout.PAGE_START);

        //源码编辑区
        sourceTextArea = new JTextArea();
        sourceTextArea.setEditable(true);
        sourceTextArea.setFont(new Font(Font.SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 20));
        JScrollPane sourceTextAreaScrollPane = new JScrollPane(sourceTextArea);
        sourceTextAreaScrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sourceTextAreaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        sourceTextAreaScrollPane.setBounds(20, 20, 350, 500);
        centerPanel.add(sourceTextAreaScrollPane);

        //词法分析区
        lexingTextArea = new JTextArea();
        lexingTextArea.setEditable(false);
        lexingTextArea.setFont(new Font(Font.SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 20));
        JScrollPane lexingTextAreaScrollPane = new JScrollPane(lexingTextArea);
        lexingTextAreaScrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        lexingTextAreaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        tabbedPane.add("词法分析", lexingTextAreaScrollPane);

        //语法树区
        parsingTextArea = new JTextArea();
        parsingTextArea.setEditable(false);
        parsingTextArea.setFont(new Font(Font.SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 20));
        JScrollPane parsingTextAreaScrollPane = new JScrollPane(parsingTextArea);
        parsingTextAreaScrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        parsingTextAreaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        tabbedPane.add("语法分析", parsingTextAreaScrollPane);
        frame.setVisible(true);

        //语义分析区
        yuYiFenXiTextArea = new JTextArea();
        yuYiFenXiTextArea.setEditable(false);
        yuYiFenXiTextArea.setFont(new Font(Font.SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 20));
        JScrollPane yuYiFenXiAreaScrollPane = new JScrollPane(yuYiFenXiTextArea);
        parsingTextAreaScrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        parsingTextAreaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        tabbedPane.add("语义分析", yuYiFenXiAreaScrollPane);
        frame.setVisible(true);

        zhongJianDaiMaTextArea = new JTextArea();
        zhongJianDaiMaTextArea.setEditable(false);
        zhongJianDaiMaTextArea.setFont(new Font(Font.SERIF, Font.LAYOUT_LEFT_TO_RIGHT, 20));
        JScrollPane zhongJianDaiMaAreaScrollPane = new JScrollPane(zhongJianDaiMaTextArea);
        parsingTextAreaScrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        parsingTextAreaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        tabbedPane.add("中间代码", zhongJianDaiMaAreaScrollPane);
        frame.setVisible(true);
    }

    /**
     * 工具栏
     *
     * @return
     */
    private JToolBar getJToolbar() {
        JToolBar toolBar = new JToolBar();
        //打开文件
        openFileBtn = new JButton("打开文件");
        saveAsButton = new JButton("保存文件");
        lexButton = new JButton("词法分析");
        parseButton = new JButton("语法分析");
        saveSyntaxTreeButton = new JButton("语法树另存为");
        yuYiFenXiButton = new JButton("语义分析");
        zhongJianDaiMaButton = new JButton("生成中间代码");
        saveSzhongJianDaiMaButton = new JButton("中间代码另存为");


        initAction();
        toolBar.add(openFileBtn);
        toolBar.add(saveAsButton);
        toolBar.add(lexButton);
        toolBar.add(parseButton);
        toolBar.add(saveSyntaxTreeButton);
        toolBar.add(yuYiFenXiButton);
        toolBar.add(zhongJianDaiMaButton);
        toolBar.add(saveSzhongJianDaiMaButton);
        toolBar.setFloatable(false);
        return toolBar;
    }

    private void initAction() {
        //打开文件并显示到文件上
        openFileBtn.addActionListener(e -> {
            //得到文件
            File file = showFileOpenDialog(frame);
            //文件为空，直接取消文件选择框
            if (file == null) {
                return;
            }
            //读取文件
            try {
                String content = getContent(file);
                //显示在源码区
                sourceTextArea.setText(content);
            } catch (FileNotFoundException fileNotFoundException) {
                //文件未找到异常，抛出警告框
                JOptionPane.showMessageDialog(frame, "文件未找到.", "警告", JOptionPane.ERROR_MESSAGE);
            }
        });

        //词法分析
        lexButton.addActionListener(e -> {
            String code = sourceTextArea.getText();
            if (code.length() == 0) {
                JOptionPane.showMessageDialog(frame, "源码为空！", "警告", JOptionPane.ERROR_MESSAGE);
                return;
            }
            MyCompiler myCompiler = new MyCompiler(new ByteArrayInputStream(code.getBytes()));
            try {
                myCompiler.start();
                lexingTextArea.setText(myCompiler.myLex.toString());
            } catch (ParseException parseException) {
                JOptionPane.showMessageDialog(frame, "未通过词法分析！", "警告", JOptionPane.ERROR_MESSAGE);
                parsingTextArea.setText(parseException.getMessage());
                lexingTextArea.setText(parseException.getMessage());
            }
        });

        //语法分析
        parseButton.addActionListener(e -> {
            String code = sourceTextArea.getText();
            if (code.length() == 0) {
                JOptionPane.showMessageDialog(frame, "源码为空！", "警告", JOptionPane.ERROR_MESSAGE);
                return;
            }
            MyCompiler myCompiler = new MyCompiler(new ByteArrayInputStream(code.getBytes()));
            try {
                myCompiler.start();
                JOptionPane.showMessageDialog(frame, "已通过语法分析！", "提示", JOptionPane.PLAIN_MESSAGE);
                lexingTextArea.setText(myCompiler.myLex.toString());
                SimpleNode root = (SimpleNode) myCompiler.jjtree.rootNode();
                StringBuilder stringBuilder = new StringBuilder();
                root.dump("", stringBuilder);
                parsingTextArea.setText(stringBuilder.toString());
            } catch (ParseException parseException) {
                JOptionPane.showMessageDialog(frame, "未通过语法分析！", "警告", JOptionPane.ERROR_MESSAGE);
                parsingTextArea.setText(parseException.getMessage());
                lexingTextArea.setText(parseException.getMessage());
            }
        });

        //语法树另存为
        saveSyntaxTreeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = showFileSaveDialog(frame);
                if (parsingTextArea.getText().length() == 0) {
                    JOptionPane.showMessageDialog(frame, "未生成语法树！", "警告", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (file == null) {
                    JOptionPane.showMessageDialog(frame, "保存文件异常！", "警告", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    FileOutputStream outputStream = new FileOutputStream(file);
                    outputStream.write(parsingTextArea.getText().getBytes());
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(frame, "保存文件异常！", "警告", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //源码另存为
        saveAsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = showFileSaveDialog(frame);
                if (sourceTextArea.getText().length() == 0) {
                    JOptionPane.showMessageDialog(frame, "未生成语法树！", "警告", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (file == null) {
                    JOptionPane.showMessageDialog(frame, "保存文件异常！", "警告", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    FileOutputStream outputStream = new FileOutputStream(file);
                    outputStream.write(sourceTextArea.getText().getBytes());
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(frame, "保存文件异常！", "警告", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //语义分析
        yuYiFenXiButton.addActionListener(e -> {
            String code = sourceTextArea.getText();
            if (code.length() == 0) {
                JOptionPane.showMessageDialog(frame, "源码为空！", "警告", JOptionPane.ERROR_MESSAGE);
                return;
            }
            MyCompiler myCompiler = new MyCompiler(new ByteArrayInputStream(code.getBytes()));
            try {
                myCompiler.start();
                //语义分析错误列表
                StringBuilder error = new StringBuilder();
                for (String s : myCompiler.errorList.getErrorList()) {
                    error.append(s + "\n");
                }
                if (error.toString().isEmpty()) {
                    error.append("无发现语义错误");
                }
                System.out.println(error);
                yuYiFenXiTextArea.setText(error.toString());
            } catch (ParseException parseException) {
                JOptionPane.showMessageDialog(frame, "未通过词法或语法分析！", "警告", JOptionPane.ERROR_MESSAGE);
                parsingTextArea.setText(parseException.getMessage());
                lexingTextArea.setText(parseException.getMessage());
            }
        });

        //中间代码
        zhongJianDaiMaButton.addActionListener(e -> {
            String code = sourceTextArea.getText();
            if (code.length() == 0) {
                JOptionPane.showMessageDialog(frame, "源码为空！", "警告", JOptionPane.ERROR_MESSAGE);
                return;
            }
            MyCompiler myCompiler = new MyCompiler(new ByteArrayInputStream(code.getBytes()));
            try {
                myCompiler.start();
                //语义分析错误列表
                List<String> qt = myCompiler.codeGen.getQt();
                if (!qt.isEmpty()) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (String s : qt) {
                        stringBuilder.append(s + "\n");
                    }
                    zhongJianDaiMaTextArea.setText(stringBuilder.toString());
                } else {
                    JOptionPane.showMessageDialog(frame, "未通过语义分析！", "警告", JOptionPane.ERROR_MESSAGE);
                }
            } catch (ParseException parseException) {
                JOptionPane.showMessageDialog(frame, "未通过词法或语法分析！", "警告", JOptionPane.ERROR_MESSAGE);
                parsingTextArea.setText(parseException.getMessage());
                lexingTextArea.setText(parseException.getMessage());
            }
        });

        //源码另存为
        saveSzhongJianDaiMaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = showFileSaveDialog(frame);
                if (zhongJianDaiMaTextArea.getText().length() == 0) {
                    JOptionPane.showMessageDialog(frame, "未生成中间代码！", "警告", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (file == null) {
                    JOptionPane.showMessageDialog(frame, "保存文件异常！", "警告", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    FileOutputStream outputStream = new FileOutputStream(file);
                    outputStream.write(zhongJianDaiMaTextArea.getText().getBytes());
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(frame, "保存文件异常！", "警告", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    /**
     * 读取文件内容
     *
     * @return
     */
    private String getContent(File file) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
        for (; ; ) {
            int rsz = 0;
            try {
                rsz = in.read(buffer, 0, buffer.length);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "读取错误.", "警告", JOptionPane.ERROR_MESSAGE);
            }
            if (rsz < 0) {
                break;
            }
            out.append(buffer, 0, rsz);
        }
        return out.toString();
    }


    /**
     * 打开文件
     *
     * @param parent frame
     * @return file
     */
    private File showFileOpenDialog(Component parent) {
        // 创建一个默认的文件选取器
        JFileChooser fileChooser = new JFileChooser();

        // 设置默认显示的文件夹为当前文件夹
        fileChooser.setCurrentDirectory(new File("."));

        // 设置文件选择的模式（只选文件、只选文件夹、文件和文件均可选）
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        // 设置是否允许多选
        fileChooser.setMultiSelectionEnabled(false);

        // 打开文件选择框（线程将被阻塞, 直到选择框被关闭）
        int result = fileChooser.showOpenDialog(parent);

        if (result == JFileChooser.APPROVE_OPTION) {
            // 如果点击了"确定", 则获取选择的文件路径
            return fileChooser.getSelectedFile();
        }
        return null;
    }

    private File showFileSaveDialog(Component parent) {
        // 创建一个默认的文件选取器
        JFileChooser fileChooser = new JFileChooser();


        // 打开文件选择框（线程将被阻塞, 直到选择框被关闭）
        int result = fileChooser.showSaveDialog(parent);

        if (result == JFileChooser.APPROVE_OPTION) {
            // 如果点击了"保存", 则获取选择的保存路径
            File file = fileChooser.getSelectedFile();
            return file;
        }
        return null;
    }
}




