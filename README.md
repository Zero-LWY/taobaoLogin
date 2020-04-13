# 基于request的代码模拟淘宝登陆过程
1. 输入账号后校验是否需要滑块验证,数据返回为json格式的数据{"needcode":false}
2. 验证账户名密码,成功会返回token.返回数据为页面形式,里面包含多个script标签,可以更具token获取需要获script 标签下的链接
3. 利用token去交换st码,数据返回为js的 function 函数体,里面包含了st码。根据里面的内容分离出data下st的值
4. 浏览器获取st码之后，拿着st码获取cookies，登录成功

