ssh密钥

    生成SSH密钥
        ssh-keygen -t rsa -C "1282906135@qq.com"
    
    查看密钥
        cat ~/.ssh/id_rsa.pub
        
    密钥测试
        ssh -T git@gitee.com
        
    Permanently added the RSA host key for IP address '52.74.223.119' to the list of known hosts.
    
    清除旧的公钥信息
            ssh-keygen -R 192.168.1.10 
            
            
            
    当使用 idea/gitbash 操作远程仓库时，会在 ~/.ssh/known 生成一条远程记录  