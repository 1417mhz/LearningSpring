package study.learningspring.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import study.learningspring.common.MyLogger;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    //    private final ObjectProvider<MyLogger> myLoggerProvider;
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();

        // ObjectProvider 방식은 실제 요청이 오기 전까지 빈 요청을 지연시킨다
//        MyLogger myLogger = myLoggerProvider.getObject();

        // 프록시 방식은 실제 요청이 오기 전까지 가짜 MyLogger 프록시 객체를 만들어서 의존관계 주입을 대신한다
        // 실제 요청이 올 경우 진짜 빈을 요청하는 기능을 갖고있다
        System.out.println("myLogger = " + myLogger.getClass());
        myLogger.setRequestUrl(requestURL);

        myLogger.log("controller-test");

        // myLogger.log
        logDemoService.logic("testId");

        return "OK";
    }

}

