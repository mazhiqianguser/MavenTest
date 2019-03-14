package cn.com.test.user.controller;// package cn.com.cintel.cin.auth.controller;
//
// import cn.com.cintel.cin.redis.JedisClient;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.bind.annotation.RestController;
//
// @RestController
// @RequestMapping("/redis")
// public class RedisTestController {
//
//
//     @Autowired
//     private JedisClient jedisClient;
//
//     @GetMapping(value="/test",produces = "application/json;charset=utf-8")
//     public @ResponseBody
//     String select(){
//         System.out.println("jedisClient" + jedisClient);
//         jedisClient.set("ss","aa",0);
//         String ss =jedisClient.get("ss");
//         System.out.println("ss = " + ss);
//         return "测试redis";
//     }
// }
