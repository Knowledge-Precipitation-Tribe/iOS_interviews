## 一、前言
- `iOS` 开发对于方法的拓展实在不行，不像 `android` 可以直接加载十六进制色值
- 我在国外论坛上，看到大佬的一种写法，感觉很有用，这里分享给大家

## 二、实现
- 下面我将逐步提出几种方案，一步步优化，知道最后可以直接使用：`UIColor(argb: 0xFFFFFFFF)`
- 建议大家一步步看下去，当然也可以直接跳到最后 `CV`

#### 2.1 加载六位十六进制颜色
> 我们知道 `#ffffff` 实际上是用十六进制来表示 `3` 种颜色，也就是：红色 `ff`，绿色 `ff` 和蓝色 `ff`。所以你可以使用 `0x` 前缀的十六进制表示法，来设置 `UIColor`，例如 `0xFF`

- 为了简化转换，让我们先创建一个已整数`（0-255）`为参数的构造方法
```swift
extension UIColor {
   convenience init(red: Int, green: Int, blue: Int) {
       assert(red >= 0 && red <= 255, "Invalid red component")
       assert(green >= 0 && green <= 255, "Invalid green component")
       assert(blue >= 0 && blue <= 255, "Invalid blue component")

       self.init(red: CGFloat(red) / 255.0, green: CGFloat(green) / 255.0, blue: CGFloat(blue) / 255.0, alpha: 1.0)
   }

   convenience init(rgb: Int) {
       self.init(
           red: (rgb >> 16) & 0xFF,
           green: (rgb >> 8) & 0xFF,
           blue: rgb & 0xFF
       )
   }
}
```

- 在代码中的使用如下
```swift
	let color = UIColor(red: 0xFF, green: 0xFF, blue: 0xFF)
	let color2 = UIColor(rgb: 0xFFFFFF)
```

#### 2.2 通过 CGFloat 设置设置透明度
> 如何设置 `UIColor` 透明度？
> 我们一般使用 `UIColor.withAlphaComponent` 来设置颜色的透明度，例如
> `let semitransparentBlack = UIColor(rgb: 0x000000).withAlphaComponent(0.5)`

- 我们可以在 2.1 的方法中，添加 `withAlphaComponent()` 来设置颜色的透明度
- 为了提高兼容性，我们这里将透明度作为一种可选参数传入
```swift
convenience init(red: Int, green: Int, blue: Int, a: CGFloat = 1.0) {
    self.init(
        red: CGFloat(red) / 255.0,
        green: CGFloat(green) / 255.0,
        blue: CGFloat(blue) / 255.0,
        alpha: a
    )
}

convenience init(rgb: Int, a: CGFloat = 1.0) {
    self.init(
        red: (rgb >> 16) & 0xFF,
        green: (rgb >> 8) & 0xFF,
        blue: rgb & 0xFF,
        a: a
    )
}
```
> 这里我们不能将透明度参数名设置为 `alpha`，因为这回与现有构造方法名称发生冲突，所以就用缩写字母 `a` 来表示
- 使用时的示例代码如下：
```swift
let color = UIColor(red: 0xFF, green: 0xFF, blue: 0xFF, a: 0.5)
let color2 = UIColor(rgb: 0xFFFFFF, a: 0.5)
```

#### 2.3 已八位十六进制设置带透明度颜色 - 0xFFFFFFFF
- 如果我们要将 `alpha` 设为 `0-255` 的整数，那么我们可以这样修改：
```swift
convenience init(red: Int, green: Int, blue: Int, a: Int = 0xFF) {
    self.init(
        red: CGFloat(red) / 255.0,
        green: CGFloat(green) / 255.0,
        blue: CGFloat(blue) / 255.0,
        alpha: CGFloat(a) / 255.0
    )
}

// let's suppose alpha is the first component (ARGB)
convenience init(argb: Int) {
    self.init(
        red: (argb >> 16) & 0xFF,
        green: (argb >> 8) & 0xFF,
        blue: argb & 0xFF,
        a: (argb >> 24) & 0xFF
    )
}
```

- 使用时示例代码如下：
```swift
let color = UIColor(red: 0xFF, green: 0xFF, blue: 0xFF, a: 0xFF)
let color2 = UIColor(argb: 0xFFFFFFFF)
```

#### 2.4 原文
- 这里直接看我的译文就行，原文内容：
![](https://img-blog.csdnimg.cn/20201224012041766.png)
![](https://img-blog.csdnimg.cn/20201224012108962.png)
![](https://img-blog.csdnimg.cn/20201224012133575.png)

## 总结
- 综上所述，大家可以根据特定的场景，选择上面方法任一方法
- 优点：使用上述任一方法都不需要传字符串
- 原文链接 [https://stackoverflow.com/questions/24263007/how-to-use-hex-color-values](https://stackoverflow.com/questions/24263007/how-to-use-hex-color-values)
- 感觉各位老铁们的三连关注支持，我们下期再见 🚀
