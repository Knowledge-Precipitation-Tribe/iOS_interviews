# 升级XCode12.3报错 Building for , but the linked and embedded framework was built for iOS + iOS Simulator



## 一、报错
- 报错内容大致如下 
> /xxxx/xxx.xcodeproj Building for iOS, but the linked and embedded framework 'xxx.framework' was built for iOS + iOS Simulator.
> `诸如此类，可能有些许不同`

## 二、原因
- `WWDC 2019` 推出 `XCFramework` 启，其实 XCode 11 开始就会有这个问题，但在 `XCode 11` 上一直是 warning 也就没太注意
- `XCode12.3` 开始，针对类似于 `iOS + iOS Simulator` 这种多平台的包，强制使用 `XCFramework` 架构，否则 `error`（ 好坑啊 ）

## 三、解决
- 这里提供三套方案，前两套比较简单，修改主项目配置即可；第三套是 `Apple `建议的方法，也就是重新打包 `framework` 为 `XCFramework`

#### 3.1 Validate WorkSpace
- 启用 `Validate WorkSpace` ，让 `XCode` 对 `frameworks` 进行自动化管理
- 打开项目路径 - `Build Setting > Build Options > Validate WorkSpace`
![](https://img-blog.csdnimg.cn/20201223163119741.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMzc3NzQ5,size_16,color_FFFFFF,t_70)

- `Validate WorkSpace` 设置为 `true`
![](https://img-blog.csdnimg.cn/2020122316285016.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMzc3NzQ5,size_16,color_FFFFFF,t_70)
> 不建议使用，只能做暂时方案，万一哪天 `Apple` 不开心又把 `Validate WorkSpace` 逻辑改了呢？

#### 3.2 Build System
- 修改编译系统为以前的系统
- 打开 `Files > WorkSpace Setting > Build System`
![](https://img-blog.csdnimg.cn/20201223163633268.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMzc3NzQ5,size_16,color_FFFFFF,t_70)
- 设置 `Build System` 如下
![在这里插入图片描述](https://img-blog.csdnimg.cn/20201223163411756.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMzc3NzQ5,size_16,color_FFFFFF,t_70)
> 不建议使用，总不能一直使用以前的构建系统吧？以后 `WWDC` 推出新功能了，还是得用新系统，旁边苹果的 `Deprecated` 注释也说明了这点


#### 3.3 Apple 建议方案

- Apple 官方对这个错误做了回复：
![](https://img-blog.csdnimg.cn/2020122316404450.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMzc3NzQ5,size_16,color_FFFFFF,t_70)
![](https://img-blog.csdnimg.cn/20201223164128412.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMzc3NzQ5,size_16,color_FFFFFF,t_70)
- 总结成一句话： 使用 `XCFramework`
- [这里是回答中生成 XCFramework 的教程](https://developer.apple.com/videos/play/wwdc2019/416/)
- [这里是回答中生成 XCFramework 的帮助文档](https://help.apple.com/xcode/mac/current/#/dev544efab96)


## 四、总结
- `Apple` 之强硬令人叹为观止
- 如果方法有效请为我点赞，因为这是我持续输出的最大动力
- 感谢大家的三连 + 关注支持，我们下期再见～