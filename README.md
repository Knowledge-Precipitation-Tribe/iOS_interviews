# iOS_interviews

![https://img-blog.csdnimg.cn/20200730222209985.png](https://img-blog.csdnimg.cn/20200730222209985.png)

个人 iOS 私房菜，汇总全网有关开发、面试的点点滴滴，当之不愧的开发宝库

</br>
</br>

# # 文章

</br>

## # swift

- [swift原生网络请求简单封装，第三方库Alamofire简单使用](https://www.codenong.com/js16b2c6ea0bc6/)

</br>
</br>

# # 面试

</br>

## # 常见面试题

- iOS单例（复杂版的）
- 1、iOS Runtime问了比较深入，方法查找，方法查找不到怎么办等
- 2、UIView的重载和重画机制。
- 问到有关block，动态库，mvc
- 问写出n种可以让程序crash的方法(在mac上操作)
- interface和abstract有什么区别
- 什么时候应该用weak？追问:
- weak和assign有什么区别答:weak在对象被销毁的时候会自动把指针置为nil；
- 有一些特别基础的类似消息传递区别；
- 你了解转发机制吗？  
- runtime的使用场景？为什么能做到运行时替换方法？如果是在C语言中如何实现？   
- block的实现？注意事项？   
- 为什么能够获取外部变量？   
- runloop是什么？哪些场景会用？有哪些源，通知？   
- autoreleasePool的实现原理？   
- 如何保证嵌套pool的正确管理？   
- 内存管理机制？   
- weak如何实现？   
- 多线程中GCD，OperationQueue使用场景？   
- 多线程中碰到的挑战？如何解决？
- OC相关：KVO实现机制，消息转发；   
- UITableView优化（offscreen/高度缓存/轻量化）；   
- 网络优化，property属性，RunLoop；   
- Weak实现机制/释放时机；   
- association对象实现机制/释放时机；   
- 数据库相关业务相关：xxx。
- 问iOS是怎么实现的；   
- iOS调用c方法怎么调；  
- 你做的一些应用不够花哨，那如何将丰富多彩的图片放进应用显示呢？
- 在MRC模式下，重写setter函数；
- 描述OC的内存管理模式；
- alloc，retain，copy，release，autoreleasepool，dealloc的用处；
- 描述autorelease对象释放时机，描述autoreleasePool的工作机制；
- 在OC中如何判断俩个对象完全相同；
- dispatch_sync(dispatch_get_main_queue(), ^(void){NSLog(@"log");});代码有什么问题没？
- 在一个类中retain一个NSTimer类型的成员变量会有什么问题？
- 问KVO的实现原理是什么?（答：KVO实现原理很复杂 ）   
- XMPP底层的数据交换格式是什么?（答：XMPP的数据交换格式是XML）；   
- 请概括一下Core Audio，Core Data以及Core Location各是什么。它们对iOS应用有何意义？   
- 请描述SpriteKit和SceneKit的作用。   
- Metal是什么？   
- 响应链（Responder Chain）是什么？它是如何发挥作用的？   
- 按钮和其他控制方式对哪些操作做出回应？   
- AppDelegate扮演着什么样的角色？   
- 请解释一下NSUserDefaults。就你而言，你会如何在磁盘中对数组对象进行序列化？
- 简单介绍一下runloop和runtime；   
- 问了ios底层的机制；   
- 你是通过什么方式学习新技术的；   
- UIButton继承了那些类；   
- 问一些关于网络协议方面的问题   
- 问block和delegate的具体区别，什么时候使用什么。
- import/include的区别 
- ARC的理解 
- delegate是assign还是retain；
- 问block的具体实现，原理是什么
- 非arc定义属性，写coredata
- runtime底层；
- 产品逆向开发，越狱开发；
- 问手写代码实现socket ；
- 问运行时机制是什么？用在哪里；
- 问runtime机制，和method swizzing； 
- 问我如果是多线程如何加锁；   
- 给我一个算法题，M= 2^a+3^b+5^c，a,b,c都是正整数，可以随便取值，求第十万个数；   
- 问我用过shell你么；
- strong和weak的区别？ 
- 使用原子性属性的特点？ 
- 类别和代理的用途，自己在项目中如何使用？ 
- 都是用过什么社会化分享平台？ 
- 一个runloop的问题，是NSThread和NSTimer的区别？
- 问block的生命周期；
- 问UIViewController的初始化的一些函数；
- 协议是什么？Kvc是什么，Kvo是什么？有什么特性？
- 问你对oc的理解和应用；
- 问viewcontroller的生存周期；
- 问了些内存溢出的处理问题；
- 基本的内存管理知识；
- obj-c里面强引用与弱引用的区别；
- ios笔试：内存管理 消息推送tableview；
- 问NSNull的不正常使用;
- 问你学习情况;
- 问有关服务器推送;
- 你做过即时通讯么，xmpp云云，做过柱状图没有；
- 问堆栈的区别；
- socket粘包，ip6+的适配，视频；
- 问故事版用的多不多；
- appdelegate,服务器推送；
- 解释一下kvo,kvc
- 问我怎么做iOS适配；
- 问了我APNs知不知道原理；
- 问ARC的内存泄露会表现在哪些地方
- 问对GCD的认识；   
- 静态库和动态库的区别；
- 你知道的数据加密方法；
- 还有关于以前项目的问题 
- 登录信息怎么帮用户保存；
- 问用C怎么构建一个工具库（答:一般OC里没有的 需要用到C、C++写一些工具）
- 问协议的好处（答：定义接口，类不用关心接口的实现，各自实现自己的接口）
- 问大量图片数据展示的问题（答：视图重用）
- 问了restful和soap的区别；
- 问了cell重载，生命周期；
- 你对你的未来规划是什么样子的；
- 问NSOperation的Dependency是怎么实现的；
- 问64位下nsnumber的优化
- 问copy,retain,assign的区别 ；
- 问的都是iOS开发中常见的一些东西：内存、多线程、Block、GCD、Runloop、KVC/KVO、Notif、数据存储、推送等等；
- 问block内部怎么实现的
- 解释一下@dymatic，self和super，iskindofclass，catogary加变量；
- tableView,多线程，sqlite，post请求等;   
- 问ios的控件有多少种实现方式;  
- 问判断链表里面有环? objc的消息转发; 
- 问如何定位跟踪IOS设备;
- 问都是怎么调试BUG啊，内存优化
- 问内存管理，app运行周期，多线程
- 内存管理；
- 深浅拷贝；
- 性能优化；
- 循环引用
- 问tableView的复用；
- 问对oc的认识；
- 用C写一个二分法查找的程序；
- 问对大数据是否有一定的了解；
- 问代理的主要作用；
- 问消息传递，项目经验；
- 问tableView是怎么加载网络图片的；
- 问static关键字的作用；
- 堆和栈的区别；
- objc的内存管理；
- UIViewcontroller的生命周期，Appdelegate的生命周期
- retain、copy等的区别，内存管理
- 问背出tableview的代理方法名 多线程操作copy的使用
- 如何适配iphone4和5
- arc，多线程
- 问是否使用过CoreData，block编程等
- 问了些oc知识；
- 问了ios技能熟练度；
- 问会2d绘图么；
- get和post的区别以及应用场景；
- 推送消息做过么；
- tableVIew的重用机制；
- 问table view的管理机制，线程；
- 问OC的基本概念和特性；
- 问view生命周期；
- 问返回局部变量指针的问题；
- 问内存管理，tableView重用，多线程；
- 问ARC的优缺点，需要使用的什么调试方式？

</br>
</br>

# MIT License

```
Copyright (c) 2021 hornhaung

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
