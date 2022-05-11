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
