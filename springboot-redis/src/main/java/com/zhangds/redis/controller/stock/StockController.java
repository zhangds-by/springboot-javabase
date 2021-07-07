package com.zhangds.redis.controller.stock;

import com.zhangds.redis.service.stock.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping("stock")
    public Object stock() {
        // 商品ID
        long commodityId = 1;
        // 库存ID
        String redisKey = "redis_key:stock:" + commodityId;
        long stock = stockService.stock(redisKey, 60 * 60, 2, () -> initStock(commodityId));
        return stock >= 0;
    }

    private int initStock(long commodityId) {
        // TODO 这里做一些初始化库存的操作
        return 1000;
    }

    @PostMapping("getStock")
    public Object getStock() {
        // 商品ID
        long commodityId = 1;
        // 库存ID
        String redisKey = "redis_key:stock:" + commodityId;
        return stockService.getStock(redisKey);
    }

    @PostMapping("addStock")
    public Object addStock() {
        // 商品ID
        long commodityId = 2;
        // 库存ID
        String redisKey = "redis_key:stock:" + commodityId;
        return stockService.addStock(redisKey, 2);
    }

}
