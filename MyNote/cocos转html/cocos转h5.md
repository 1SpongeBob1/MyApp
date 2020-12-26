#### cocos转h5

##### 使用工具

1. fkworld库（项目地址：<https://github.com/fkworld/cocos-to-playable-ad>）
2. cocos creator2.1.3
3. node.js 12.9.0

##### npm配置

+ 下载并安装node.js。

+ 配置变量：

  ​	只需配置用户变量：配置路径到下载地址的根目录即可。

##### 使用方法

1. cocos导出web项目，然后将整个web项目文件复制到fkworld库的src目录，并新建一个dist文件夹用来存放生成后的html文件。
2. 屏蔽项目main.js中的如下代码：
3. 在fkworld根目录执行npm install
4. 在fkworld根目录执行npm run build

