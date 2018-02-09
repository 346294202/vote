## 总纲
- 不需要用户验证的接口名称后面会有"no token"字样，否则需要token。
- 需要用户验证的接口，必须在HTTP Header "VOTE-ACCESS-TOKEN"中提供token。
- 未提供token，无效token，token过期均返回错误码"1000"，不在每个接口中赘述。
- 如无特殊说明，当返回错误时，data字段可以忽略。


## 基础接口
- 登录,no token
    - POST /login
    - 参数：
        - mobile:手机号，字符串，必须，最大13字符
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
- 注册,no token
    - POST /register
    - 参数：
        - mobile:手机号，字符串，必须，最大13字符
        - password：密码，字符串，必须，最大128字符
        - code:验证码，字符串，必须，最大6字符
    - 返回：
      ```
      {
        code:<1成功 1003:账号已经存在 1004验证码错误>,
        error:"<错误描述>",
        data:{
          token:"<token>",
          expir:<token过期时间戳，秒>
        }
      }
      ```
- 绑定房屋
    - POST /house
    - 参数：
        - owner_name： 业主姓名，字符串，必须，最大64字符
        - owner_mobile： 业主手机，字符串，必须，最大13字符
        - code： 验证码，字符串，必须，最大6字符
        - owner_id_number： 业主身份证，字符串，必须，最大64字符
        - area_id： 楼盘id，整形数，必须
        - building_id： 楼栋id，整形数，必须
        - unit_id： 单元ID，整形数，必须
        - house_type_id： 房屋类型id，整形数，必须
        - owner_type_id： 业主类型id，整形数，必须
    - 返回：
      ```
      {
        code:<1成功 >,
        error:"<错误描述>",
        data:{}
      }
      ```
- 获取房屋信息
    - GET /house
    - 参数
    - 返回
      ```
      {
        code:<1成功 >,
        error:"<错误描述>",
        data:{
          items:[
            {
              "id":<房屋id>,
              "text":"<房屋描述>"
            },...
          ]
        }
      }
      ```    
- 取消绑定
    - DELETE /house/<房屋id>
    - 参数
    - 返回
      ```
      {
        code:<1成功 >,
        error:"<错误描述>",
        data:{}
      }
      ```           


