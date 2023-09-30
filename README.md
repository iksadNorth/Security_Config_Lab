# Security_Config_Lab# Security Security Configuration 실험

# 실험 1

### 만약 `authorizeHttpRequests` 를 연거푸 호출하면 모든 설정이 제대로 반영될까?

정확한 실험 이유는 다음과 같다.

<aside>
💡 만약 `authorizeHttpRequests` 를 연거푸 호출하면 모든 설정이 제대로 반영될까?
혹시 마지막에 사용된 `authorizeHttpRequests` 만 반영되는 구조인 것인가?

</aside>

```java
@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // 만약 덮어씌우기라면 사라져야 할 설정 1.
        http.authorizeHttpRequests(b ->
                b.requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/api/case1")).permitAll()
        );

        // 만약 덮어씌우기라면 사라져야 할 설정 2.
        http.authorizeHttpRequests(b ->
                b.requestMatchers(AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/api/case2")).permitAll()
        );

        // 어쨋든 살아남게 될 설정 3.
        http.authorizeHttpRequests(b ->
                b.anyRequest().authenticated()
        );

        return http.build();
    }
```

우선 이론적으로는 `덮어씌우기`가 아닌 `모두 반영`이 맞다는 결론에 이르렀다.

이유는 다음과 같은 코드 때문이다.

![Untitled](Security%20Security%20Configuration%20%E1%84%89%E1%85%B5%E1%86%AF%E1%84%92%E1%85%A5%E1%86%B7%20a0435f417048405eaa8715f2fb6f1ca1/Untitled.png)

![Untitled](Security%20Security%20Configuration%20%E1%84%89%E1%85%B5%E1%86%AF%E1%84%92%E1%85%A5%E1%86%B7%20a0435f417048405eaa8715f2fb6f1ca1/Untitled%201.png)

getOrDefault라는 메서드를 살펴보면 만약 기존에 정의된 설정이 존재한다면
존재하는 Configurer를 출력하고 아니라면 새로운 Configurer를 생성하는 코드이다.

이 코드에 의하면 연거푸 `authorizeHttpRequests` 를 호출해도 모두 반영이 될 것이다.

### 그렇다면 실제로 코드가 위와 같이 진행될까?

확인을 위해 실험을 했다.

[https://github.com/iksadNorth/Security_Config_Lab](https://github.com/iksadNorth/Security_Config_Lab)

![Untitled](Security%20Security%20Configuration%20%E1%84%89%E1%85%B5%E1%86%AF%E1%84%92%E1%85%A5%E1%86%B7%20a0435f417048405eaa8715f2fb6f1ca1/Untitled%202.png)

![Untitled](Security%20Security%20Configuration%20%E1%84%89%E1%85%B5%E1%86%AF%E1%84%92%E1%85%A5%E1%86%B7%20a0435f417048405eaa8715f2fb6f1ca1/Untitled%203.png)

![Untitled](Security%20Security%20Configuration%20%E1%84%89%E1%85%B5%E1%86%AF%E1%84%92%E1%85%A5%E1%86%B7%20a0435f417048405eaa8715f2fb6f1ca1/Untitled%204.png)

![Untitled](Security%20Security%20Configuration%20%E1%84%89%E1%85%B5%E1%86%AF%E1%84%92%E1%85%A5%E1%86%B7%20a0435f417048405eaa8715f2fb6f1ca1/Untitled%205.png)

### 실험 결과

### `덮어씌우기`가 아닌 `모두 반영`이 된다.

실제로 모든 설정이 씹히지 않고 그대로 반영됨을 알 수 있다.
