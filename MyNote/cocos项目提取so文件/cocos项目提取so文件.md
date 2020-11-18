# **cocos项目提取so文件**



#### 目的

​		cocos引擎代码主要是c++构成，由于c++文件编译较慢，且编译过一次以后基本不会再有任何改动，为了避免每一次打包安卓项目都重复编译，所以在第一次编译成功后就提取出so文件与aar文件。

#### 方式

  1. 首先构建cocos项目，加密密钥自定（**每个密钥与so文件是绑定的，如果以后移植so文件到其他项目，则该项目构建的加密密钥也要用对应的**）。
     ![](.\1.jpg)

  2. 编译完成后在android studio打开编译出来的android项目，打包release包。将打出来的apk复制到一个目录，更改后缀名为zip然后解压。找到lib文件夹，里面根据构建时所选的架构会有几个子文件夹，子文件夹里面就是so文件了。
     ![](.\2.jpg)

		3. 在android studio里面，右边gradle工具栏里打开proj.android-studio（cocos构建生成的android项目）-libcocos2dx（cocos库）- Tasks - build ,运行第一个指令“:libcocos2dx:assemble”生成aar文件。将项目切换成project找到生成的aar文件（下图目录），为了方便一直，将aar文件放到之前存放so文件的lib文件夹里，即和架构子文件夹同级。

     ![](.\6.jpg)
     ![](.\7.jpg)

		4. 将上述lib文件夹里！面的所有文件复制到android工程的libs文件夹（没有就新建），然后在项目build.gradle(app)中屏蔽相关代码。具体如下：
     ![](.\8.jpg)

     ![](.\3.jpg)

     ![](.\4.jpg)

     ![](.\5.jpg)

     并在setting.gradle文件里屏蔽库的引用：

     ![](.\9.jpg)