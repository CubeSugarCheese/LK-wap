# LK-wap
一个简单的wap版网页端轻之国度客户端，基于ktor。

# 部署

```bash
gradlew build
```
`gradle`会自动生成 jar 文件`build/libs/LK-wap-1.0-SNAPSHOT-all.jar`

使用 jvm 直接运行即可
```bash
java -jar LK-wap-1.0-SNAPSHOT-all.jar
```

## 配置
项目第一次运行会在运行目录下自动生成配置文件`config.json`

此后每次运行都会自动读取配置
## host
默认 `"0.0.0.0"`
## port
默认 `10023`
## pageSize
分类界面一页显示多少个文章

默认 `40`
## articlePageSize
文章阅读界面一页的预期字数

默认 `"1000"`
## security_key
用于`仅勇者`文章和隐藏分区的访问

默认 `""`
## updateTimeSeconds
缓存更新间隔（秒），被动更新

优先使用缓存中的内容进行渲染，所以可能需要刷新两次才能出现更新的内容

默认 `600`



