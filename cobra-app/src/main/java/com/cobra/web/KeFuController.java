package com.cobra.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cobra.constants.QiyuConstant;
import com.cobra.constants.TokenConstant;
import com.cobra.dto.QiyuMessageDto;
import com.cobra.param.BaseResponse;
import com.cobra.util.*;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api("客服系统")
@RestController
@RequestMapping("/api/kefu")
public class KeFuController
{
    @RequestMapping("/distribute")
    public BaseResponse distribute(){


        String  content = "{\n" +
                "    \"uid\": \"user1\",\n" +
                "    \"fromPage\": \"www.163.com\",\n" +
                "    \"fromTitle\": \"Netease\",\n" +
                "    \"fromIp\": \"220.220.2.1\",\n" +
                "    \"deviceType\": \"Android#xiaomi#5.0\",\n" +
                "    \"productId\": \"Android package name\",\n" +
                "    \"staffType\": 1,\n" +
                "    \"staffId\": 0,\n" +
                "    \"groupId\": 0,\n" +
                "    \"robotShuntSwitch\":0,\n" +
                "    \"level\":0,\n" +
                "    \"robotId\":22\n" +
                "}";

        /**
         *
         * POST https://qiyukf.com/openapi/event/applyStaff?appKey=1064deea1c362&time=1463217187&checksum=2f13932c4b764
         Content-Type:application/json;charset=utf-8

         *
         */
        long time = DateUtils.getUTCTime();
        String url = QiyuConstant.URL_APPLY_STAFF
                .concat("appKey="+QiyuConstant.QIYU_APP_KEY)
                .concat("&time="+ time).concat("&checksum="+ QiyuPushCheckSum.encode(QiyuConstant.QIYU_APP_SECRET, MD5.md5(content),time));
        String rs = OkHttpUtil.post(url,content);


        return ResponseUtil.success(rs);
    }


    @RequestMapping("/sendMessage")
    public BaseResponse sendMessage(@RequestBody QiyuMessageDto qiyuMessageDto){

      String content = JSON.toJSONString(qiyuMessageDto);
      log.info("-------->"+content);
      long time = DateUtils.getUTCTime();

        String url = QiyuConstant.URL_SEND_MESSAGE
                .concat("appKey="+ QiyuConstant.QIYU_APP_KEY)
                .concat("&time="+ time)
                .concat("&checksum="+ QiyuPushCheckSum.encode(QiyuConstant.QIYU_APP_SECRET, MD5.md5(content),time));
        String rs = OkHttpUtil.post(url,content);
        JSONObject jsonObject = JSON.parseObject(rs);
        if(!"200".equals(jsonObject.getString("code"))){
            return ResponseUtil.error();
        }

        return ResponseUtil.success(content);

    }
}
