##Storm Demo
－　本地模式运行：以Java Appliaction方式运行StormMain
－　集群方式运行：
> 1,将pom.xml中注释掉的<scope>provided</scope>打开

> 2,并修改Main方法，使用StormSubmitter.submitTopology()进行提交拓扑

> 3,打包成jar包，提交给Storm集群运行
