package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Slf4j
@RestController
//스프링은 기본적으로 @Controller라고 하면 return값을 view로 인식 -> return을 view로
//RestController는 return에 문자를 넣으면 그 문자 값이 문자 그대로 반환
public class LogTestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name =" + name);
        log.trace("trace log={}", name);
        log.debug("debug={}", name); //debug 할 때 보는 로그
        log.info("info log ={}", name);
        log.warn("warn={}", name); //경고 로그
        log.error("error={}",name); //에러 로그

        return "ok";

    }

}
