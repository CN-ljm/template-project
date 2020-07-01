package com.ljm.controller.web1;

import com.ljm.base.event.MySpringApplicationEvent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 视图控制器
 */
@Api("hello视图控制器")
@RestController
@RequestMapping("/hello")
@Slf4j
public class Hello {

    @Resource
    private ApplicationContext applicationContext;

    @ApiOperation("测试swagger")
    @GetMapping("/sayHello")
    public String sayHello(){

        MySpringApplicationEvent event = new MySpringApplicationEvent(this, "testEvent");
        applicationContext.publishEvent(event);

//        throw new RuntimeException("出事情啦！");

        return "hello!";
    }


    @RequestMapping(value = "/notify", method = {RequestMethod.POST, RequestMethod.GET})
    public void icbcLoanApplyNotify(HttpServletRequest req, HttpServletResponse resp){
        log.info("接收到工行的融资回调请求，请求的业务参数【{}】", req.getParameterMap());
        String method = req.getMethod();
        PrintWriter out = null;
        if (!"POST".equals(method)) {
            log.error("工行请求方式错误！当前http请求方式是：【{}】，不是POST请求，不做处理", method);
            try {
                SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
                String reqTime=sdf.format(new Date());
                String results="Only support POST method. systime is :"+reqTime;
                resp.setContentType("application/json; charset=utf-8");
                out = resp.getWriter();
                out.write(results);
                out.flush();
                out.close();
            } catch (Exception e) {
                log.info("", e);
                out.write(e.getMessage());
            } finally {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            }
            return;
        }

        // 处理工行回到逻辑
        log.info("开始处理工行回调请求！");
        try {
            log.info("来这里了！！");
            resp.setContentType("application/json; charset=utf-8");
            out = resp.getWriter();
            out.write("哈哈哈，可以了！");
            out.flush();
            out.close();
        } catch (Throwable t){
            log.error("处理工行回调逻辑异常", t);
            out.write(t.getMessage());
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }

    }

}
