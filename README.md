```
   一个Android 日志打印库，突破android系统日志长度限制，使用简单。
```
#### 最新版本
 [ ![Download](https://api.bintray.com/packages/lidiwo/lidiwo/android-log/images/download.svg) ](https://bintray.com/lidiwo/lidiwo/android-log/_latestVersion)

#### 一、功能介绍

1. **支持设置全局Tag**
2. **支持无限长度字符串打印**
3. **支持日志打印所在文件的所在方法和行数**
4. **支持JSON格式化析打印**
5. **支持无Tag快捷打印**
6. **支持在Android Studio中点击打印的文件行数，跳转到日志位置**
7. **打包release版本自动屏蔽日志打印**

#### 二、用法

1. 添加依赖和配置
``` gradle
dependencies {

    implementation 'com.lidiwo:android-log:x.x.x'

    ...
}
// 旧版本gradle插件(< 2.2)，implementation 换成 compile
```

2. 添加注解
``` java
// 在BaseApplication 的 onCreate 方法初始化日志
@Override
public void onCreate() {
    super.onCreate();
        ...

    AndroidLog.init(BuildConfig.DEBUG,"lidiwo");
}
```

3. 使用
``` java
AndroidLog.e();
AndroidLog.i();
AndroidLog.w();
AndroidLog.v();
AndroidLog.d();
AndroidLog.json();
```




#### 三、问题反馈

 如果发现有使用问题，可以给我发邮件kolan9527@126.com

