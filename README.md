##Storm Demo
- 本地模式运行：以Java Appliaction方式运行StormMain
- 集群方式运行：
> 1,将pom.xml中注释掉的\<scope>provided\</scope>打开

> 2,并修改Main方法，设置debug = false;

> 3,打包成jar包，提交给Storm集群运行

- 执行(storm jar命令):
> storm jar storm-demo-0.0.1-SNAPSHOT.jar com.ds.storm.StormMain src/main/resources/words.txt  
- 停止(storm kill命令，加上提交的名称):
> storm kill realCount