package com.ltzz.config;

import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springblade.core.log.error.RRException;
import org.springblade.core.secure.utils.SecureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 自定义接口拦截器
 *
 * @WenJunChi
 */
public class AuthInterceptor implements HandlerInterceptor {
    long start = System.currentTimeMillis();
    //    String userId = "1";//暂时指定id
    protected static Log log = LogFactory.getLog(AuthInterceptor.class);
    @Autowired
    static RestTemplate restTemplate;


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        start = System.currentTimeMillis();
        String token = httpServletRequest.getHeader("token");
        Claims claims = SecureUtil.parseJWT(token);

        if (claims == null) {
            throw new RRException("无效的token", 4007);
        }
        String userId = claims.get("user_id", String.class);

        if (StringUtils.isBlank(userId)) {
            throw new RRException("账号或者密码错误", 4001);
        }
        Date exp = claims.get("exp", Date.class);
        if (System.currentTimeMillis() > exp.getTime()) {
            throw new RRException("token已失效", 4007);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        log.info("接口请求时间== cost=" + (System.currentTimeMillis() - start));
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }

    //    public static void main(String[] args) {
//        String str = AESUtil.encrypt("{\"mobile\":\"18654534565\",\"count\":1,\"cardNo\":\"123\",\"url\":\"https://api.ezuotang.com/app/report/html/shaxReport.html?reportid=795982\",\"username\":\"刘虎彪\"}", "eyJ0eXBlIjoiSnNvbldlYlRva2VuIiwiYWxnIjoiSFMyNTYifQ.eyJpc3MiOiJpc3N1c2VyIiwiYXVkIjoiYXVkaWVuY2UiLCJ1c2VyX2lkIjoiMTUiLCJ1c2VyX25hbWUiOiJhZG1pbiIsImNsaWVudF9pZCI6InN3b3JkIiwiZXhwIjoxNTYzMTcwNDAwLCJuYmYiOjE1NjA0OTQ5NDJ9.kE2Y_iuyQdHIcL2BADXYtudzO-QjtJGs9WjdeOvTN_I");
//        System.out.println(str);
//    }
//    public static void main(String[] args) {
//     //   String str = AESUtil.encrypt("{\"mobile\":\"18654534565\",\"count\":1,\"cardNo\":\"123\",\"url\":\"https://api.ezuotang.com/app/report/html/shaxReport.html?reportid=795982\",\"username\":\"刘虎彪\"}", "eyJ0eXBlIjoiSnNvbldlYlRva2VuIiwiYWxnIjoiSFMyNTYifQ.eyJpc3MiOiJpc3N1c2VyIiwiYXVkIjoiYXVkaWVuY2UiLCJ1c2VyX2lkIjoiMTUiLCJ1c2VyX25hbWUiOiJhZG1pbiIsImNsaWVudF9pZCI6InN3b3JkIiwiZXhwIjoxNTYzMTcwNDAwLCJuYmYiOjE1NjA0OTQ5NDJ9.kE2Y_iuyQdHIcL2BADXYtudzO-QjtJGs9WjdeOvTN_I");
//
//        String   str = AESUtil.decrypt("3B6C89299B088D131471C2354500463D056896E3EDE83B1A0A34EF5E5BE08A401EBA030FC73517D13B48A8CB5026A075F3DDC96C47C0E505602FDCB157714C3A", "eyJ0eXBlIjoiSnNvbldlYlRva2VuIiwiYWxnIjoiSFMyNTYifQ.eyJpc3MiOiJpc3N1c2VyIiwiYXVkIjoiYXVkaWVuY2UiLCJ1c2VyX2lkIjoiMTUiLCJ1c2VyX25hbWUiOiJlenVvdGFuZyIsImNsaWVudF9pZCI6InN3b3JkIiwiZXhwIjoxNTYzNTAxNj");
//        System.out.println(str);
//    }

}
