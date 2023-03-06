# crawler

## 기능 요구사항

- 입력한 사이트의 HTML 코드 데이터 머지
- 머지한 문자열에서 영문 숫자 파싱
- 정렬 및 중복문자 제거 후 조건에 맞춰 교차 출력

---
## 기능 적용 사항

- Async 및 CompletableFuture를 이용하여 동시 처리
  - 동시 처리 중 크롤링 실패 사이트에 대한 응답 처리
- 크롤링 처리에 대한 cache(ehcache) 적용
- 테스트를 위한 swagger 적용

---
## 어플리케이션 환경

- JAVA 17
- Gradle 7.6.1
- Spring Boot 2.7.9
- cache(ehcache)
- 편의사항
  - swagger 페이지(http://localhost:8080/swagger-ui/index.html) 
  - 테스트를 편하게 하기 위해 미리 지정된 사이트를 입력없이 조회하는 endpoint를 추가했습니다(/crawling/default)