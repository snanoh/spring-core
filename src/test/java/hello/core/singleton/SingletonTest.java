package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();

        //1. 조회: 호출 할 때 마다 객체를 생성
        MemberService memberService = appConfig.memberService();

        //2. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        System.out.println("memberService = " + memberService);
        System.out.println("memberService1 = " + memberService1);

        Assertions.assertThat(memberService).isNotSameAs(memberService1);
    }
    
    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
        SingletonService instance1 = SingletonService.getInstance();
        SingletonService instance2 = SingletonService.getInstance();

        System.out.println("instance1 = " + instance1);
        System.out.println("instance2 = " + instance2);

        Assertions.assertThat(instance1).isSameAs(instance2);
        //same ==
        //equal 자바 이퀄
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = ac.getBean("memberService", MemberService.class);
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);

        System.out.println("memberService = " + memberService);
        System.out.println("memberService1 = " + memberService1);

        Assertions.assertThat(memberService).isSameAs(memberService1);
    }

}
