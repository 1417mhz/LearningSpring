package study.learningspring.singleton;

public class StatefulService {

//    private int price; // 상태를 유지하는 필드가 있으면 안됨

    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
//        this.price = price; // <- 여기가 문제
        return price;
    }

}
