## 一、前言
- 我们在写列表的时候，经常出现每一个 Cell 高度不一样的情况，但是 iOS 这边是在是太不智能了
- 比起隔壁 `android` 的 `RecyclerView` ，人家可以自动更具每一项高度，来进行伸缩变化，`iOS` 的列表控件 `UITableView` 竟然都不能直接自适应列表高度

## 二、效果
- 其实具体的实现并不难，只是没学过的人肯定搞不出来，开始前这里可以先看下效果
- 大致就是 `UITableView` 会自动计算每一个 `cell` 的高度，伸缩变换后显示出来，网上有很多类似的帖子，但是大都纸上谈兵，没图没代码地讲不清楚，而且还都是 `n` 年前的文章
- 那么开始前。效果图这里效果图如下：
![](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/db24cd6e4a294071a63f9b21ab046a09~tplv-k3u1fbpfcp-zoom-1.image)
## 三、使用与实现
- 就以上图为例，我带大家边讲解边实现上图中的例子，这样一来成功运行的时候，大家就也都会了

#### 3.1 实现数据提供者 - ContentProvider
- 实现一个数据提供者 - ContentProvider ，用于模拟从网络上拉去数据的情况
```swift
class ContentProvider {
    
    static let datas = ["对我个人而言，美丽的沙滩不仅仅是一个重大的事件，还可能会改变我的人生。",
                 "美丽的沙滩因何而发生？ 我认为， 那么， 查尔斯·史考伯在不经意间这样说过，一个人几乎可以在任何他怀有无限热忱的事情上成功。",
                 "对我个人而言，美丽的沙滩不仅仅是一个重大的事件，还可能会改变我的人生。 带着这些问题，我们来审视一下美丽的沙滩。 美丽的沙滩，发生了会如何，不发生又会如何。 带着这些问题，我们来审视一下美丽的沙滩。 既然如何， 我认为， 而这些并不是完全重要，更加重要的问题是， 这样看来， 带着这些问题，我们来审视一下美丽的沙滩。",
                 "我们都知道，只要有意义，那么就必须慎重考虑。",
                 "对我个人而言，美丽的沙滩不仅仅是一个重大的事件，还可能会改变我的人生。",
                 "美丽的沙滩因何而发生？ 我认为， 那么， 查尔斯·史考伯在不经意间这样说过，一个人几乎可以在任何他怀有无限热忱的事情上成功。",
                 "莎士比亚说过一句富有哲理的话，人的一生是短的，但如果卑劣地过这一生，就太长了。这似乎解答了我的疑惑。 带着这些问题，我们来审视一下美丽的沙滩。 ",
                 "一般来说， 既然如此， 这样看来， 我们都知道，只要有意义，那么就必须慎重考虑。 每个人都不得不面对这些问题。 在面对这种问题时， 了解清楚美丽的沙滩到底是一种怎么样的存在，是解决一切问题的关键。",
                 "我们都知道，只要有意义，那么就必须慎重考虑。"]
    
    static let imgs = ["paperplane.fill","square.and.arrow.down","paperplane.fill","bell","square.and.arrow.down","bell","paperplane.fill","bell","square.and.arrow.down"]
    
}
```
> 这里节约时间，就不做异步拉取的处理了，后续文章我会挤时间，专门搞一篇 `UITableView` 异步请求加观察者模式的文章来给大家分享

#### 3.2 编写列表 item - UITableViewCell
- 要让 `cell` 随自身内容大小而变化高度，只需要注意三点即可
- 首先是，`addSubView` 必须是添加到 `contentView` 上，而非简单的 `self`
- 其次是，内部组件必须设置 `translatesAutoresizingMaskIntoConstraints` 属性为 `true`
- 最后是，这个 `cell` 不能通过简单的 `frame` 设置大小，而是需要通过 `NSLayoutConstraint` 来动态给定
- 首先这里我先提供下最终实现的代码再逐个给大家分析：
```swift
import Foundation
import UIKit

class MemberCell: UITableViewCell {
    
    lazy var contentLabel: UILabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        self.contentView.addSubview(label)
        label.numberOfLines = 0
        return label
    }()
    
    lazy var userImageView: UIImageView = {
        let imageView = UIImageView()
        imageView.translatesAutoresizingMaskIntoConstraints = false
        self.contentView.addSubview(imageView)
        imageView.image = UIImage(systemName: "Camera")
        return imageView
    }()
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        setupConstraint()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    func setupConstraint() {
        userImageView.accessibilityIdentifier = "userImageView"
        contentLabel.accessibilityIdentifier = "profileImageView"
        contentView.accessibilityIdentifier = "profileContentView"

        NSLayoutConstraint.activate([
            contentLabel.topAnchor.constraint(equalTo: contentView.topAnchor, constant: 10),
            contentLabel.leftAnchor.constraint(equalTo: contentView.leftAnchor, constant: 10),
            contentLabel.rightAnchor.constraint(equalTo: contentView.rightAnchor, constant: -10),
            
            userImageView.topAnchor.constraint(equalTo: contentLabel.bottomAnchor, constant: 10),
            userImageView.widthAnchor.constraint(equalToConstant: 25),
            userImageView.heightAnchor.constraint(equalToConstant: 25),
            userImageView.leftAnchor.constraint(equalTo: contentView.leftAnchor, constant: 10),
            userImageView.bottomAnchor.constraint(equalTo: contentView.bottomAnchor, constant: -10),
            
            ])
        
    }
    
}
```

##### 3.2.1 子控件实现
- 为了提高代码可读性，这里建议大家使用懒加载的形式
- 我们注意到，子控件的 `view` 是需要添加到 `cell` 的 `contentView` 上的，而非直接添加到 self 上
- 另一点就是之前说的，需要把 `view` 的 `translatesAutoresizingMaskIntoConstraints` 属性设置为 `false`
- 拿代码 + 注释举个例子：
```swift
    lazy var contentLabel: UILabel = {
        let label = UILabel()
        // translatesAutoresizingMaskIntoConstraints 设为 false
        label.translatesAutoresizingMaskIntoConstraints = false
        // 添加到 contentView
        self.contentView.addSubview(label)
        label.numberOfLines = 0
        return label
    }()
```

##### 3.2.2 计算子控件以及 cell 大小
- 这里我们就不能再采用上古时代设定 `frame` 的方法而是通过 `NSLayoutConstraint.activate([...])` 中设定子控件各边与 cell 各边的关系来指定
- 另外一点就是，对于每个子 `view` 以及我们 `cell` 的 `conteentView` 我们都需要设定它们的 `accessibilityIdentifier` ，其内容直接写该 `view` 的名字就行，只要不重名就行，没有太多的要求
- 给大家举个栗子 🌰 ：
```swift
    func setupConstraint() {
        // 
        userImageView.accessibilityIdentifier = "userImageView"
        contentLabel.accessibilityIdentifier = "profileImageView"
        contentView.accessibilityIdentifier = "profileContentView"
        // 设定子布局各边与 cell 的关系
        NSLayoutConstraint.activate([
            contentLabel.topAnchor.constraint(equalTo: contentView.topAnchor, constant: 10),
            contentLabel.leftAnchor.constraint(equalTo: contentView.leftAnchor, constant: 10),
            contentLabel.rightAnchor.constraint(equalTo: contentView.rightAnchor, constant: -10),
            
            userImageView.topAnchor.constraint(equalTo: contentLabel.bottomAnchor, constant: 10),
            userImageView.widthAnchor.constraint(equalToConstant: 25),
            userImageView.heightAnchor.constraint(equalToConstant: 25),
            userImageView.leftAnchor.constraint(equalTo: contentView.leftAnchor, constant: 10),
            userImageView.bottomAnchor.constraint(equalTo: contentView.bottomAnchor, constant: -10),
            
            ])
        
    }
```
> 其中子 `view` 的大小，我们同样可以在 `activate([...])` 中，通过 `widthAnchor & heightAnchor` 强制来设定

#### 3.3 列表界面 - UITableViewController
- 相比于 cell 中的注意点，对于 `UItableView` 本身需要注意的地方并不多
- 相比于普通 `UItableView` 的使用，这里要添加  `translatesAutoresizingMaskIntoConstraints` 的设置
- 同时通过 `NSLayoutConstraint.activate(...)` 设置设置 `tableView` 之于 `ViewController` 大小
```swift
import Foundation
import UIKit

class LandscapeListViewController: UITableViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        view.backgroundColor = .white
        config()
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        
        NSLayoutConstraint.activate(tableView.edgeConstraints(top: 80, left: 0, bottom: 0, right: 0))
    }
    
    func config() {
        tableView.delegate = self
        tableView.dataSource = self
        tableView.translatesAutoresizingMaskIntoConstraints = false
        tableView.estimatedRowHeight = 80
        tableView.rowHeight = UITableView.automaticDimension

        tableView.register(MemberCell.self, forCellReuseIdentifier: "MemberCell")
    }
    
}

extension LandscapeListViewController {
    
    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return max(ContentProvider.datas.count, ContentProvider.imgs.count)
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "MemberCell", for: indexPath) as! MemberCell
        cell.contentLabel.text = ContentProvider.datas[indexPath.row]
        cell.userImageView.image = UIImage(systemName: ContentProvider.imgs[indexPath.row])
        return cell
    }

    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        
    }
    
}
```

##### 3.3.1 自动标注尺寸
- 首先我们需要设置 `UITableView` 的高度计算方案为自动标注尺寸，也就是 `automaticDimension`
- 其次我们同样需要标注 `tableview` 的 `translatesAutoresizingMaskIntoConstraints` 为 `false`
```swift
    func config() {
        tableView.delegate = self
        tableView.dataSource = self
        tableView.translatesAutoresizingMaskIntoConstraints = false
        tableView.estimatedRowHeight = 80
        tableView.rowHeight = UITableView.automaticDimension

        tableView.register(MemberCell.self, forCellReuseIdentifier: "MemberCell")
    }
```

##### 3.3.2 设定大小
- 更 `TableViewCell` 一样，对于 `viewController` 中的 `tableView` ，我们也需要设定它们的大小关系
- 为了方便起见，我才用了网上的一套设定方案：
```swift
extension UIView {
    /// 设定 view 与其父 view 各边之间的关系
    public func edgeConstraints(top: CGFloat, left: CGFloat, bottom: CGFloat, right: CGFloat) -> [NSLayoutConstraint] {
        return [
            self.leftAnchor.constraint(equalTo: self.superview!.leftAnchor, constant: left),
            self.rightAnchor.constraint(equalTo: self.superview!.rightAnchor, constant: -right),
            self.topAnchor.constraint(equalTo: self.superview!.topAnchor, constant: top),
            self.bottomAnchor.constraint(equalTo: self.superview!.bottomAnchor, constant: -bottom)
        ]
    }
}

```
- 这样一来，带代码里我们只要通过 `view.edgeConstraints(...)` 就可以快速设定子 `view` 与其 `superview` 之间的关系：
```swift
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        // 调用封装的方法：edgeConstraints(）
        NSLayoutConstraint.activate(tableView.edgeConstraints(top: 80, left: 0, bottom: 0, right: 0))
    }
```

## 总结
- 我在 `GitHub` 新建了一个仓库，正在为大家整理、分享我的 `iOS` 学习笔记，欢迎大家 `star` 支持：[https://github.com/Knowledge-Precipitation-Tribe/ios_notes](https://github.com/Knowledge-Precipitation-Tribe/ios_notes)
- 如果大家有更好的方案，欢迎在评论区分享代码，我会更新到本文中 👏
- 同时欢迎大家点赞或者关注支持，因为这是我持续输出的最大动力～
