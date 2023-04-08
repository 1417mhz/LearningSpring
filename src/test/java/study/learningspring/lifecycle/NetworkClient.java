package study.learningspring.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {

    private String url;

    // 빈 등록과 동시에 생성자가 호출됨
    public NetworkClient() {
        System.out.println("생성자 호출: url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    // 서비스 종료시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }

    /*
    * InitializingBean, DisposableBean 를 이용한 빈 생명주기 콜백
    * 이 인터페이스들은 스프링 전용 인터페이스이고 초기화, 소멸 메소드의 이름을 변경할 수가 없다
    * 지금은 거의 사용하지 않는다

    // InitializingBean 구현 코드
    // PropertiesSet(의존관계 주입)이 끝나면 호출된다는 뜻
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

    // DisposableBean 구현 코드
    @Override
    public void destroy() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
    **/

    /*
    * 설정 정보에 초기화, 소멸 메소드를 직접 지정할 수 있다.
    * 메소드 이름을 자유롭게 지정할 수 있고 스프링 빈이 스프링 코드에 의존하지 않는다.
    *

    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
    **/

    /* @PostConstruct, @PreDestroy 어노테이션을 이용
    * 어노테이션만 갖다 붙이면 되므로 간편하다
    * 다만 코드를 고치지 못하는 외부 라이브러리엔 적용이 안되므로
    * 그때는 @Bean 의 initMethod, destroyMethod 를 사용하자 **/

    // 생성자가 호출 된 이후 초기화를 수행하도록 함 (생성자의 호출과 로직 동작 타이밍의 사이에서 동작하는 코드)
    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() {
        // 테스트 코드에서 스프링 컨테이너가 종료되는 시점 이전에 실행됨
        System.out.println("NetworkClient.close");
        disconnect();
    }

}
