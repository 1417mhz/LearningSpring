package study.learningspring.singleton;

// 수동으로 구현하는 싱글톤 패턴은 구현하는 코드 자체가 많이 작성된다
public class SingletonService {

    // 딱 1개의 객체 인스턴스만 존재해야 하므로 생성자를 private으로 막음
    private SingletonService() { }

    // 외부에서 호출하지 못하도록 private으로 선언
    // 클래스 내부적으로 객체를 생성하서 가지고 있음
    private static final SingletonService instance = new SingletonService();

    // 외부에서 객체 인스턴스가 필요할 경우 오직 getInstance()를 통해서만 호출할 수 있음
    public static SingletonService getInstance() {
        return instance;
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
