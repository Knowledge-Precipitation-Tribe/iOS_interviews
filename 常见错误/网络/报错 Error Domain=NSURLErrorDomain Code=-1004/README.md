## 一、原因
- 两种可能


#### 1.1 网络原因
- 请检查你的网络是否连接
- 建议使用浏览器搜索网页，因为 App 可能自带缓存


#### 1.2 使用 LocalHost
- 本地测试时使用 LocalHost 上线时可能报错
- 解决方案转：[StackOverFlow](https://stackoverflow.com/questions/38501012/is-it-safe-to-add-localhost-to-app-transport-security-ats-nsexceptiondomains/40047949#40047949)

#### 1.3 IOS App 传输安全
- 项目目录下找到 `Info.plist`
- 位置：项目目录文件夹下查找，用文本编辑器打开
- 找到 `NSAppTransportSecurity` -> `NSExceptionDomains` 位置：对于添加权限
```xml
<key>文本替换为你的域名或IP</key>
<dict>
    <key>NSIncludesSubdomains</key>
    <true/>
         <key>NSExceptionAllowsInsecureHTTPLoads</key>
    <true/>
</dict>
```

## 二、总结
- 本文主要是对 StackOverflow 的转载，便于小伙伴们快速查询
- 博主这么帅点个收藏还是关注？
- 你的支持是我持续分享的最大动力！
