## 项目功能

一个基于Javacc ，使用Java编写的Mini C编译器前端。

主要实现词法分析，语法分析，语义分析，中间代码生成功能

### 运行

- 项目需要Java环境，查看本机是否存在Java环境

```
java -version
```

* 若选择运行exe前请确保同目录下有jre运行环境或本机有java环境

- 如若不能运行exe，请在有java环境下运行

```
java -jar MyCompiler.jar
```

## 项目运行效果

### 主界面

- 左方是源码区，可以直接在上面编辑源码，也点击打开文件可以选择打开源码，编辑后可以另存为

<img src="https://gitee.com/chen_jian_hang/pic/raw/master/image/1588253984945.png" alt="1588254844162" style="zoom:67%;" />

### 词法分析

- 点击词法分析，切换至选项卡，在右侧会显示对应词汇表

<img src="https://gitee.com/chen_jian_hang/pic/raw/master/image/1588254829457.png" alt="1588254829457" style="zoom:67%;" />

### 语法分析

- 点击语法分析，切换至选项卡，在右侧会显示对应语法树

  <img src="https://gitee.com/chen_jian_hang/pic/raw/master/image/1588254925069.png" alt="1588254925069" style="zoom:67%;" />

- 当代码出现编写错误时，未通过词法分析时，会出现友好的错误提示

<img src="https://gitee.com/chen_jian_hang/pic/raw/master/image/1588254905927.png" alt="1588254905927" style="zoom:67%;" />

- 点击另存为语法可以把语法另存为文件

<img src="https://gitee.com/chen_jian_hang/pic/raw/master/image/1588255392962.png" alt="1588255392962" style="zoom:67%;" />

### 语义分析

- 语义检查，当不存在语义错误时，提示不存在错误

<img src="https://gitee.com/chen_jian_hang/pic/raw/master/image/image-20200619184117773.png" alt="image-20200619184117773" style="zoom:67%;" />

- 存在语义错误时，例如使用未定义的变量，函数参数不一致时，会提示具体错误，也无法生成中间代码

<img src="https://gitee.com/chen_jian_hang/pic/raw/master/image/image-20200619184256037.png" alt="image-20200619184256037" style="zoom:67%;" />

### 中间代码生成

- 通过词法分析、语法分析、语义分析后，点击中间代码生成，切换至选项卡

<img src="https://gitee.com/chen_jian_hang/pic/raw/master/image/image-20200619184321696.png" alt="image-20200619184321696" style="zoom:67%;" />

- 中间代码能另存为文件保存

<img src="https://gitee.com/chen_jian_hang/pic/raw/master/image/image-20200619184935269.png" alt="image-20200619184935269" style="zoom:67%;" />

- 生成的中间代码能被修改后的TM虚拟机执行（这里演示的是回文数）

<img src="https://gitee.com/chen_jian_hang/pic/raw/master/image/image-20200619185030672.png" alt="image-20200619185030672" style="zoom:67%;" />

## 软件缺陷

1. 函数如果缺少return语句，不能正确回调到主调函数，个人认为这是文法的设计缺陷，暂时未找到除了修改文法之外的修改方法。
2. 当传递数组作为参数时，如果只存在一次调用能正确访问，若被调函数再把数组传参其他函数，发生两层调用关系时不能正确访问。
3. 当多次递归调用自身时会出现未能返回的情况（有时候能，有时候不能，例如利用递归求最大公约数那个，输入10，5能返回正确结果，输入36，24则不能正确返回）
4. 没有处理好编码问题，注释不能有中文

## 参考资料

https://www.cnblogs.com/suhaha/p/11733716.html

https://www.cnblogs.com/suhaha/p/11733709.html

https://www.cnblogs.com/suhaha/p/11733699.html

https://blog.csdn.net/lusic01/article/details/80735850

https://blog.csdn.net/weixin_34203426/article/details/93612202?utm_medium=distribute.pc_relevant.none-task-blog-OPENSEARCH-4&depth_1-utm_source=distribute.pc_relevant.none-task-blog-OPENSEARCH-4 

