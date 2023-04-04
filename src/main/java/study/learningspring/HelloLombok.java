package study.learningspring;

import lombok.*;

@Getter
@Setter
@ToString
public class HelloLombok {
    // Lombok 라이브러리가 getter setter 등의 반복되는 코드들을 간결하게 만들어준다

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("asdf");

        System.out.println("helloLombok = " + helloLombok);
    }

}
