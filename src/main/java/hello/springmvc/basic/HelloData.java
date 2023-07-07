package hello.springmvc.basic;

import lombok.Data;

@Data //getter, setter, tostring, equalhashcode, requiredargsconstructor 를 자동 생성해줌
public class HelloData {
    private String username;
    private int age;
}
