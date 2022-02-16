
### 接口访问测试

```wi
http://localhost:8080  首页访问
http://localhost:8080/apiFei  GET


  // 免登录接口测试
  getBookDetail: 'user/detail',


  //  *********** 商品管理 ***********
  getGoodsList: 'goods/list',
  getGoodsDetail: 'goods/detail',  
  goodsAdd: 'goods/add',
  goodsDel: 'goods/del',

  //*********** 账单管理 ***********
  getBillList: 'billCount/list',
  billBudget: 'billCount/budget',
  billAdd: 'billCount/add',
  billDel: 'billCount/del',
  
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
    
  login: 'user/login', // 登录
  
```

