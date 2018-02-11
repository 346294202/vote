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
        - owner_name： 业主姓名，字符串，必须，最大64汉字
        - owner_mobile： 业主手机，字符串，必须，最大13字符
        - code： 验证码，字符串，必须，最大6字符
        - owner_id_number： 业主身份证，字符串，必须，最大64字符
        - area： 楼盘名称，字符串，必须
        - building： 楼栋号，字符串，必须
        - unit： 单元号，字符串，必须
        - house_type_id： 房屋类型id，整形数，必须
        - owner_type_id： 业主类型id，整形数，必须
    - 返回：
      ```
      {
        code:<1成功 2000 房屋不存在>,
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
              id:<房屋id>,
              text:"<房屋描述>"
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
- 获得公告
    - GET /message
    - 参数
    - 返回
      ```
      {
        code:<1成功 >,
        error:"<错误描述>",
        data:{
          items:[
            {
              title:"<公告标题>",
              cpntent:"<公告内容>",
              time:<发布日期，时间戳，秒>
            },...
          ]
        }
      }      
      ```

## 首页
- 首页配置
    - GET /main
    - 参数
    - 返回
      ```
      {
        code:<1成功 >,
        error:"<错误描述>",
        data:{
          banners:[
            {
              img-url:"<图片地址>",
              url:"<跳转页面>"
            },...
          ],
          groups:[
            {
              type:"<分组类型，家宴预约，家政预约，商品配送，优惠活动>",
              label:"<分组标签>",
              items:[
                {
                  img-url:"<图片地址>",
                  id:"<商品id>"
                },...
              ]
            },...
          ]
        }      
      }
      ```

## 发现
周边查询类型有：1 商家，2 政府

- 周边查询配置
    - GET /around/type/<类型id>
    - 参数
    - 返回：
      ```
      {
        code:<1成功 >,
        error:"<错误描述>",
        data:{
          items:[
            {
              id:<子类型id>
              label:"<子类型标题>"
            },...
          ]
        }
      }      
      ```
    
- 周边查询
    - GET /around
    - 参数
        - type 类型id，整数，必须
        - subtype 子类型id，整数，可选
        - lng 经度，浮点数，必须
        - lat 纬度，浮点数，必须
        - q 模糊查询，字符串，可选
    - 返回
      ```
     {
        code:<1成功 >,
        error:"<错误描述>",
        data:{
          items:[
            {
              type:<类型id>,
              subtype:<子类型id>,
              name:"<名称>",
              desc:"<描述>",
              img-url:"<图片>",
              center:{
                lng:<经度>,
                lat:<纬度>
              },
              distance:<与查询参数的距离，米>
            },...
          ]
        }
      }           
      ```
## 物业
- 首页配置
    - GET /service
    - 参数
    - 返回
      ```
      {
        code:<1成功>
        error:"<错误信息>"
        data:{
          owner:{
            name:"<业主姓名>",
            houses:[
              {
                id:<房屋id>
                text:"<房屋描述>"
              },...
            ]
          },
          payments:[费用项列表
            {
              name:"<费用项名称>",
              value:<元>
            },...
          ],
          advs:[通告列表
            {
              img-url:"<图片地址>",
              url:"<跳转页面>"
            },...
          ]
        }
      }
      ```
- 获得维修记录
    - GET /work
    - 参数
        - status 状态筛选，整数，可选，缺省为全部
        - page 页码，整数，可选，缺省为0
        - psize 每页记录数，整数，可选，缺省为20
    - 返回：
      ```
      {
        code:<1成功 >,
        error:"<错误描述>",
        data:{
          items:[
            {
              id:<记录id>
              desc:"<问题描述>"
              img-url:"<现场拍照>"        
              house:{
                id:<房屋id>,
                text:"<房屋描述>"
              }
              status:<状态，1 等待处理，2 安排人员，3 处理完成，4 取消>
              create-time:<创建时间戳，秒>
              update-time:<修改时间戳，秒>
            },...
          ]
        }
      }      
      ```    
 - 申请维修
     - POST /work
     - 参数
         - desc 问题描述，字符串，必须，最大2000汉字
         - house_id 房屋id，整数，必须
         - img-url 图片地址，字符串，可选
 - 投诉
    - POST /complain
    - 参数
         - content 内容，字符串，必须，最大500汉字
    - 返回
      ```
      {
        code:<1成功 >,
        error:"<错误描述>",
        data:{}
      }
      ```             
 
 ## 我的
 - 获取个人信息
     - GET /info
     - 参数
     - 返回
      ```
      {
        code:<1成功 >,
        error:"<错误描述>",
        data:{
          img-url:"<头像地址>"
          nick_name:"<昵称>"
          mobile:"<手机>"
          tile:"<称呼>"
          birthday:<生日，时间戳，秒>
          motto:"<签名>",
          houses:[
            {
              id:<房屋id>
              text:"<房屋信息>"
            },...
          ]
        }
      }
      ```        
        
- 设置个人信息
    - POST /info
    - 参数
        - img-url 头像，字符串，可选，最大2000字符
        - nick_name 昵称，字符串，可选，最大64汉字
        - motto 签名，字符串，可选，最大512汉字
        - birthday 生日，时间戳，秒，整数，可选
    - 返回
      ```
      {
        code:<1成功 >,
        error:"<错误描述>",
        data:{}
      }
      ```        
- 获得订单
    - GET /order
    - 参数
        - status 状态筛选，整数，可选，缺省为全部
        - page 页码，整数，可选，缺省为0
        - psize 每页记录数，整数，可选，缺省为20
    - 返回
      ```
      {
        code:<1成功 >,
        error:"<错误描述>",
        data:{
          items:[
            {
            待定
            },...
          ]
        }
      }
      ```
- 获得气的地址
    - GET /address
    - 参数
    - 返回
      ```
      {
        code:<1成功 >,
        error:"<错误描述>",
        data:{
          items:[
            {
              id:<地址id>
              house:{
                id:<房屋id>
                text:"<房屋信息>"               
              }
              mobile:"<联系电话>"
              contact:"<联系人>"
            },...
          ]
        }
      }
      ```    
- 新增地址
    - POST /address
    - 参数
        - house_id 房屋id，整数，必须
        - mobile   联系电话，字符串，必须
        - contact  联系人，字符串，必须
    - 返回
      ```
      {
        code:<1成功 >,
        error:"<错误描述>",
        data:{}
      }
      ```        
- 删除地址
    - DELETE /address/地址id
    - 参数
    - 返回
      ```
      {
        code:<1成功 >,
        error:"<错误描述>",
        data:{}
      }
      ```            
    
