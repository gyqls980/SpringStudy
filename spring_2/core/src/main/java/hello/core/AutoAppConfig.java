package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( // @Component 클래스의 빈을 자동으로 등록해준다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,
                                                classes = Configuration.class)
) // @Configuration이 붙은 AppConfig, TestConfig 등의 앞서 만들어두었던 설정 정보 제외
  // 일단 기존 코드를 남기기 위해서

public class AutoAppConfig {

}
