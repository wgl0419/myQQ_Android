1. 在LoginActivity.java中使用了SurfaceView进行了视频的播放。

2. 基本完成了登录过程的架子的搭建。

2017.07.20


1. 在LoginActivity.java中使用了SurfaceView进行了视频的播放不能作为背景。上面的输入框和登录按钮等其他的控件都不见了。改为MyVideoVIew.java重新作为背景。

2017.07.25


1. 完成了LoginActivity.java中视频做背景的功能。

2. 完成了MainActivity.java的主界面的布局。

3. 在MainActivity.java中动态添加了三个Fragment（不适用ViewPager）：消息、联系人、动态。

4. 在消息模块（MessageFrag）中使用了继承于EditText的自定义View实现了搜索框。

5. 在fzq.com.myqq.ui.activity.login包中的StartUpdateActivity.java和LoginActivity.java除了联网功能外，其他的流程基本完成。

2017.07.27


1. 主要写了fzq.com.myqq.ui.fragment.message包中的内容。

2. 使用了RecyclerView显示了模拟的数据。

3. 使用了MyItemDecoration为RecycleView添加了分割线，并且该分割线是在xml文件随时可以替换的。

4. 完成了MySlideItem.java的绝大部分内容。但是使用起来和教程还有明显的不同和bug。

2017.07.28



1. 主要写了fzq.com.myqq.ui.activity.setting包和fzq.com.myqq.ui.fragment.contact包的内容。把程序的流程搭建起来了。

2. fzq.com.myqq.ui.activity.leftmenu.SettingsActivity.java类开始使用了ButterKnife框架。并在这个类中对ButterKnife框架进入的简单的验证和学习。

2017.07.31
