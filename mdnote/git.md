ssh密钥

    生成SSH密钥
        ssh-keygen -t rsa -C "1282906135@qq.com"
    
    查看密钥
        cat ~/.ssh/id_rsa.pub
        
    密钥测试
        ssh -T git@gitee.com
        
    清除旧的公钥信息
        ssh-keygen -R 192.168.1.10    
    