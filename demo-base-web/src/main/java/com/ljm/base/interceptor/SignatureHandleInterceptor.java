package com.ljm.base.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ljm.base.utils.Sha1withRSAUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.util.Map;

@Component
@Slf4j
public class SignatureHandleInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(request.getRequestURI());
        System.out.println("spring mvc preHandle");
        ServletInputStream inputStream = request.getInputStream();
        //验签
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        int len = -1;
        byte[] bf = new byte[1024];
        StringBuilder sb = new StringBuilder();
        while ((len = bis.read(bf)) != -1){
            sb.append(new String(bf, "UTF-8"));
        }
        bis.close();
        log.info("请求内容是：{}", sb.toString());
        Map<String, Object> map = JSONObject.parseObject(sb.toString(), Map.class);
        if (map != null && map.get("sign") != null) {
            String sign = (String) map.get("sign");
            log.info("签名是：{}", map.get("sign"));
            map.remove("sign");
            byte[] bytes = JSONObject.toJSONBytes(map, SerializerFeature.WRITE_MAP_NULL_FEATURES);
            boolean verify = Sha1withRSAUtil.verify(bytes, sign, Sha1withRSAUtil.getPublicKey(null));
            if (!verify){
                log.info("验签失败！");
                response.reset();
                response.setStatus(333);
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                PrintWriter writer = response.getWriter();
                writer.write("verify fail");
                writer.flush();
                writer.close();
                return false;
            }
            log.info("验签通过！");
            return true;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("spring mvc postHandle");

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("spring mvc afterCompletion");
//        ex.printStackTrace();
    }
}
