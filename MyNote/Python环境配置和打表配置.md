#### Python环境配置和打表配置

1. 下载python（公司版本2.7.17，版本不对时打表会导致[**runtimeerror bad magic number in .pyc file错误**](https://www.cnblogs.com/kid-kid/p/13463586.html)）。
2. 配置环境变量
   - 配置path到下载路径根目录。
   -  如果后续出现"pip不是内部或外部命令，也不是可运行的程序 或批处理文件"问题，则再配置path到根目录下的Script文件夹。
3. 下载xlrd模块
   +  python要读取excel表必须安装此模块，不然会报错**"ModuleNotFoundError: No module named 'xlrd'"**。[下载方式][https://blog.csdn.net/qq_22521211/article/details/80020980]：利用pip命令，安装xlrd包-> pip install xlrd。
   + 如果下载完毕后打表出现**"xlrd.biffh.XLRDError: Excel xlsx file; not supported"**错误，是因为xlrd版本不对导致。[解决方式][https://www.cnblogs.com/xiaoqiangink/p/14144517.html]：执行**pip list**查看下载的python插件列表版本信息。执行**pip uninstall xlrd**卸载原版本，然后执行**pip install xlrd==1.2.0**安装指定版本（公司所用版本1.2.0）。

