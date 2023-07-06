package hello.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @RequestMapping("/request-param-v2")
    @ResponseBody
    public String requestParamV2(
        @RequestParam("username") String memberName,
        @RequestParam("age") int memberAge) {

        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
        //클래스 레벨에 @RestController 어노테이션이 붙어있지 않아서 "ok"를 리턴하면 ok라는 view를 찾음
        //@RestController를 사용해도 되지 @ResponseBody를 메서드 레벨에 사용하면 같은 역할을 함
    }

    @RequestMapping("/request-param-v3")
    @ResponseBody
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age) {

        log.info("username={}, age={}", username, age);
        return "ok";
        //HTTP 파라미터 이름이 변수 이름과 같으면 @RequestParam(name="") 생략 가능
    }


    @RequestMapping("/request-param-v4")
    @ResponseBody
    public String requestParamV4(String username, int age) {

        log.info("username={}, age={}", username, age);
        return "ok";
        //String, int, Integer 등의 단순 타입이면 @RequestParam도 생략 가능
    }

    @RequestMapping("/request-param-required")
    @ResponseBody
    public String requestParamRequired(
            @RequestParam(required = true) String username, //true면 url에 반드시 있어야함
            @RequestParam(required = false) Integer age) { //false면 반드시 있어야되는 건 아님

        log.info("username={}, age={}", username, age);
        return "ok";
        //@RequestParam의 기본 required 값은 true
        /*age에 required=false를 지정했지만 age를 넣지 않으면 500 에러가 뜸 -> 왜?
        age는 int형인데 int는 null이 될 수 없음
        int를 null 허용하게 하려면 int 대신 Integer를 써야 함
         */
    }

    @RequestMapping("/request-param-default")
    @ResponseBody
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            //username이 필수 값인데 username= 이렇게만 넣으면 null(오류)이 아닌 "" 빈 문자열이 들어가게됨
            //그걸 방지하기 위해 기본값을 넣어줌 -> defaultValue에 설정된 값은 값을 넣어주지 않으면 들어감
            @RequestParam(required = false, defaultValue = "2") int age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

}
