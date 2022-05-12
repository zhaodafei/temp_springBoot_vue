## dev 环境

```html
http://localhost:8071/api   GET
http://localhost:8071/api/test/detail 测试接口
```



## prod 环境

```html
http://localhost:8072/api   GET
http://localhost:8072/api/test/detail  测试接口
```

## Nginx配置

```nginx
server {
    listen        80;
    #server_name  localhost demo.fei.com;
    server_name  demo.fei.com;
    # 配置 Vue 打包首页地址
    root   "E:/self_web/git_dev/vue/zFei_vue/dist";
    location / {
        try_files $uri $uri/ /index.html;
        index  index.html index.htm;
    }
    # 代理到 Java 的8072接口
    # 访问 http://localhost:80/api/test/detail
    # 调用 Java 接口 http://localhost:8072/api/test/detail
    location /api/ {
        proxy_pass http://127.0.0.1:8072/api/;
    }
}
```

### 启动`jar`包

```bash
java -jar fei-0.0.1-SNAPSHOT.jar
java -jar fei-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
java -jar fei-0.0.1-SNAPSHOT.jar --server.port=8072 --spring.profiles.active=prod
```




## 接口文档

### 免登录接口

```html
// 免登录接口测试
getBookDetail: 'user/detail',

login: 'user/login', // 登录
```

### 商品管理

```html
//*********** 商品管理 ***********
getGoodsList: 'goods/list',
getGoodsDetail: 'goods/detail',  
goodsAdd: 'goods/add',
goodsDel: 'goods/del',
```

### 账单管理

```html
//*********** 账单管理 ***********
getBillList: 'billCount/list',
billBudget: 'billCount/budget',
billAdd: 'billCount/add',
billDel: 'billCount/del',
```

### 字典管理

```html
//*********** 字典管理  ***********
getDictTypeList: 'dictType/list',
getDictTypeDetail: 'dictType/detail',  
dictTypeAdd: 'dictType/add',
dictTypeDel: 'dictType/update',
dictTypeDel: 'dictType/del',

getDictDataList: 'dictData/query', //不分页,具体页面下拉框使用
getDictDataList: 'dictData/list',
getDictDataDetail: 'dictData/detail',
dictDataAdd: 'dictData/add',
dictDataDel: 'dictData/del',
```





### xxx

dddd
