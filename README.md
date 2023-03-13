# AWS MediaConvert Java2.x Tempplate Demo
AWS MediaConvert Java SDK 2.x 通过模板创建任务，可以自定义输入。

# 文件说明
### template.json 模板文件
在AWS Consonle中创建一个**作业模板**，导入json，需要注意：
1. 替换`Queue` 为自己的
2. 替换`Role` 为自己的角色，角色需要有S3输入和输出的权限
3. 替换`Destination` 为自己的目标输出路径

### App.java
提交作业程序。需要注意：
1. 替换`accessKey` 为自己的AK
2. 替换`secretKey` 为自己的SK
3. 替换`region` 为自己的region
4. 替换`jobRole`为自己的角色，角色需要有S3输入和输出的权限