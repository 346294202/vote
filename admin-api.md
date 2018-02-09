## 总纲
- 不需要用户验证的接口名称后面会有"no token"字样，否则需要token。
- 需要用户验证的接口，必须在HTTP Header "VOTE-ACCESS-TOKEN"中提供token。
- 未提供token，无效token，token过期均返回错误码"1000"，不在每个接口中赘述。
- 如无特殊说明，当返回错误时，data字段可以忽略。

## 基础接口
- 登录,no token
    - POST /login
    - 参数：
        - username:用户名，字符串，必须，最大128字符
        - password：密码，字符串，必须，最大128字符
    - 返回：
        ```
        {
          code:<1成功，1001:账号不存在，1002手机或密码不匹配>,
          error:"<错误描述>",
          data:{
            token:"<token>",
            expir:<token过期时间戳，秒>
          }
        }
        ```
- 首页配置
    - GET /front
    - 参数
    - 返回
        ```
        {
          code:<1成功，1001:账号不存在，1002手机或密码不匹配>,
          error:"<错误描述>",
          data:{
            menus:[
              {
                "label":"<菜单名称>",
                ""
              },...
            ]
          }
        }        
        ```

## 系统管理

- 查询用户
    - GET /user
    - 参数
        - q 模糊查询，字符串，可选
        - page 页数，整数，可选，缺省0
        - psize 每页个数，整数，可选，缺省20
        - 
