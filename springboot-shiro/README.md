> JWT

    JWT的格式：Header.Payload.Signature
     
    Header 
        由以下这个格式的 Json 通过 Base64 编码生成的字符串，Header 中存放的内容是说明编码对象是一个 JWT 以及使用 “SHA-256” 的算法进行加密（加密用于生成 Signature）
    编码不是加密，是可以通过反编码的方式获取到原来的加密算法，所以 JWT 中存放的一般是不敏感的信息
       
        { 
            "typ":"JWT", 
            "alg":"HS256" 
        } 
    
    Claim 
        存放的内容是 JWT 自身的标准属性，所有的标准属性都是可选的，可以自行添加
        将 Claim 通过 Base64 转码之后生成的字符串Payload。
        
        { 
            "iss":"Issuer —— JWT签发者", 
            "sub":"Subject —— 所面向的用户", 
            "aud":"Audience —— 接收该JWT的一方", 
            "exp":"Expiration Time —— 数字类型，JWT过期的时间，Unix时间戳", 
            "nbf":"Not Before —— 数字类型，在该时间之前JWT不能被接受与处理", 
            "iat":"Issued At —— 数字类型，JWT何时被签发", 
            "jti":"JWT ID —— 说明标明JWT的唯一ID", 
            "user-definde1":"自定义属性举例", 
            "user-definde2":"自定义属性举例" 
        }
        
    Signature
        由 Header 和 Payload 组合而成，将 Header 和 Claim 这两个 Json 分别使用 Base64 方式进行编码，生成字符串 Header 和 Payload，
        以 Header.Payload 的格式组合成字符串，然后使用上面定义好的加密算法和一个密匙（这个密匙存放在服务器上，用于进行验证）对这个字符串进行加密，形成Signature。
        
    实现原理
    
        将 Header 进行反编码获取到加密的算法，在通过存放在服务器上的密匙对 Header.Payload 这个字符串进行加密，比对 JWT 中的 Signature 和实际加密出来的结果是否一致
        