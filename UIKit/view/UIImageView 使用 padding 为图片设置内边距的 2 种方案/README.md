# UIImageView 使用 padding 为图片设置内边距的 2 种方案



## 一、前言
- 不像 `Flutter / swiftUI` 那样，`UIimageView` 并没有现成的
- 这里介绍两种为 `UIImageView` 内图片设置边距的方法均为自定义方法

## 二、方案
- 包括两种方案
- 第一种：借鉴 `stackoverflow` 上大佬提出的
- 第二种：我自己优化的

#### 2.1 设置 UIImage 大小
- 先设置 `UIImage` 的大小，再将其放入 `UIImageView` 内
- 设置 `contentMode` 为 `center` 居中便可有 `padding` 效果
```swift
    func imageWithImage(image: UIImage, scaledToSize: CGSize) -> UIImage? {
        //UIGraphicsBeginImageContext(newSize);
        // In next line, pass 0.0 to use the current device's pixel scaling factor (and thus account for Retina resolution).
        // Pass 1.0 to force exact pixel size.
        let radio = image.size.width / image.size.height
        UIGraphicsBeginImageContextWithOptions(scaledToSize, false, 0.0)
        image.draw(in: CGRect(x: 0, y: 0, width: scaledToSize.width * radio, height: scaledToSize.height))
        let newImage = UIGraphicsGetImageFromCurrentImageContext()
        UIGraphicsEndImageContext()
        return newImage
    }
```
> 原文链接：[https://stackoverflow.com/questions/32304349/insets-to-uiimageview](https://stackoverflow.com/questions/32304349/insets-to-uiimageview)
> ![](https://img-blog.csdnimg.cn/20201223180039980.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMzc3NzQ5,size_16,color_FFFFFF,t_70)


#### 2.2 方案一使用
- 在设置 `UIImageView.image` 是调用即可
```swift
xxxUIImageView.image = imageWithImage(image: UIImage(named: "six")!, scaledToSize: CGSize(width: 18, height: 18))
xxxUIImageView.contentMode = .center
```


#### 2.3 对 UIImageView 类新增拓展方法
- 该方法是对 stackoverflow 方案的改良版，不需要提前给定图片大小
- 通过设置内边距的大小，就可以自动适应、调整
- 设置上更为方便，同时能自动兼容各种图像
```swift
extension UIImageView {
    
    /// 使用前请先设置 UIImageView.contentMode = .center
    func padding(_ all: CGFloat) {
        guard let image = self.image else {
            print("this image is nil when padding")
            return
        }
        let originSize = self.frame.size
        var scaledToSize: CGSize = .zero
        if image.size.width > image.size.height {
            let radio = image.size.height / image.size.width
            scaledToSize = CGSize(width: originSize.width - all * 2, height: (originSize.height - all * 2) * radio)
        } else {
            let radio = image.size.width / image.size.height
            scaledToSize = CGSize(width: (originSize.width - all * 2) * radio, height: originSize.height - all * 2)
        }
        UIGraphicsBeginImageContextWithOptions(scaledToSize, false, 0.0)
        image.draw(in: CGRect(x: 0, y: 0, width: scaledToSize.width, height: scaledToSize.height))
        let newImage = UIGraphicsGetImageFromCurrentImageContext()
        UIGraphicsEndImageContext()
        self.image = newImage
    }
    
}
```
> 这个方法我没有经过系统的测试，如果有问题欢迎在评论区给我留言改进 👏

#### 2.4 方案二使用
- 分两步走，可以在 `config` 时直接设置
- 如果你的 `UIImageView` 会不断的变化，那么可以把 `padding` 移到 `layoutSubviews` 中调用，以便同步视图刷新
```swift
config：实例化时
	xxxUIImageView.contentMode = .center
	xxxUIImageView.image = UIImage(named: "enter your picture name")

layoutSubviews：刷新 image
	xxxUIImageView.padding(3)
```

## 三、总结
- 如果大家有更好的方案，欢迎在评论区分享，我会更新到本文中 👏
- 欢迎大家点赞或者关注支持，因为这是我持续输出的最大动力～