ecs架构:每个实体由多个组件和多个系统组成<br>
组件:每个组件负责表示对象的某一数据,例如血量数值<br>
系统:每个系统负责表示对象的某一行为,例如攻击行为<br>

<h2>类图分析:</h2>
软件包World:<br>
(1) World 世界类, 内部构建映射关系, 实体<->组件<->组件池 资源<->资源池<br>
(2) Commands 命令, 可执行命令管理实体 <br>
(3) Resourcer 资源管理器, 管理资源<br>
(4) Queryer 查询器, 由于查询实体与组件<br>

软件包Component: 组件相关,其下的软件包Components开放给用户自定义组件<br>

软件包System: <br>
系统相关,分为启动系统和更新系统<br>
在软件包StartSystems和UpdateSystems中编写的System会被管理器自动扫描添加<br>
在其他软件包写的System需要手动添加<br>

软件包Entity:仅包含实体id及其id生成器

软件包Resource: 资源相关, 软件包Resources开放给用户自定义资源,资源需要手动挂载到World对象上

软件包Event: 事件系统,内部含有Reader和Writer,可进行线程之间的消息传递

软件包Timer: 定时任务管理器<br>
