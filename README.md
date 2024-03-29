# Blog 프로젝트 (개인)

<div align="center">
  <img width="60%" src="https://github.com/aozp73/aozp73/assets/122352251/6b8fc143-76d7-4816-99d7-c85ba75eb525"/>
</div>
&nbsp; ① 해당 프로젝트는 2주간 진행하는 Blog 강의 첫날(금요일) 전체 Topic에 대해 듣고, </br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 학원에서 해당 주제의 강의 듣기전 혼자 완성해보고 싶다는 욕심에 3일간(주말) 진행한 프로젝트 </br>
&nbsp; ② Server, Front 혼자 병행 </br></br>
&nbsp;&nbsp;📝 Blog 후기 정리글 :&nbsp;&nbsp; https://blog.naver.com/aozp73/223007266499
</br></br>

## 만들면서 느낀 점 / 어려웠던 부분
&nbsp;&nbsp;※ 프로필 사진 기능 외 나머지 기능을 혼자 독학으로 구현하고자 목표를 설정하면서 해당 기능은 제외하고 구현


#### MVC 각 layer의 책임

    - 개념적으론 Controller는 인증, 권한, 유효성 검사 / Service는 트랜잭션, 비즈니스, 권한 처리 / Repository는 JPA나 
      Mybatis를 활용하여 DB와 통신한다고 배웠다.
    
    - 실제로 뭔가를 만들고, 유지 보수와 디버깅 과정에 대해서 생각해 볼 기회를 가질 수 있었고, 각 layer의 책임을 분리하는 
      것이 얼마나 중요할지 체감할 수 있었다.
    
    - 이번 프로젝트는 다른 여러 포트폴리오의 결과물들에 비해 간단한 편이기 때문에 에러 메시지에서 지체한 부분은 적었다. 
    
    - Controller와 Service, View의 책임을 구분하지 않은 상태에서 코드가 복잡했다면 유지 보수와 디버깅에 많은 시간을 
      할애했을 것이다.
    
    - 어느 부분에서 문제가 생겼는지, 정확하고 올바르게 이해하려면 최초 코드 작성 때부터 생각해야 될 부분이다.
 
#### backend - frontend 작업 
    - 값들을 서버에 넘기고, view에 뿌리는 과정을 해보면서, 이걸 한 사람이 아닌 각각 다른 사람이 협업할 때 어떤 과정이
      필요할지, 고민할 수 있는 기회를 가질 수 있었다.
    
    - 어떤 메서드, URL로 요청해야 하고, 응답 형식이 무엇인지에 대한 것은 기본적으로 숙지하고 있어야 하고, 만약 어떤 문제로 
      인해 관련 이슈가 생겼을 시 협업 관련 문제임을 인지할 수 있어야 한다는 생각이 들었다.
    
    - 수업 시간에 강사님이 만드신 서버를 사용해 볼 기회가 있었는데, 그때 만들어서 주신 API를 본 기억이 났다.
    
    - 내가 만든 것에 대해서 정확히 숙지하고, 이를 필요로 하는 협업자에게 API 문서를 줄 수 있어야 한다고 느꼈다.

#### 인증, 권한 
    - 수업 시간에 강조되어 배운 것 중 하나는 postman 방식을 통한 보안 이슈인데, 이번 프로젝트를 하면서 인증과 권한에 
      대해 꼼꼼하게 생각해 볼 필요가 있다는 생각을 가졌다.
    
    - 프로젝트 도중 느낀 기분은 결과물이 나온다면 해커 전문 기업에 맡겨서 이슈들을 정리하고 릴리즈 해야 하는 것 아닌가 였는데, 
      의뢰받은 결과물에 대해 책임지려면 꼼꼼한 코드 진행이 필요하다고 생각한다.
    
    - 나중에 filter나 다른 스프링 부트 기능으로 이런 것들을 지금보다 간편하게 처리할 수 있다고 한다.
    
    - 관련 학습을 할 때 중요한 부분임을 인지하면서 공부해야겠다는 생각이 들었다.

#### 테스트 코드 / 버그

    - 이번 프로젝트는 혼자서든, 팀으로든 처음 해보는 프로젝트라서 완성에만 초점을 맞추어 제일 중요한 것을 놓친 것 같다.
    
    - 수업 시간에 매번 강조한 것이 테스트 코드를 진행하면서 코드를 작성하는 것인데, 이번 프로젝트에는 그 부분이 빠졌다.
    
    - 강사님과 수업 시간에 진행하고 있는 블로그 프로젝트는 스프링 통합 테스트, MVC 테스트 등 많은 것을 배우면서 진행하고 있는데, 
      다음번에 다시 블로그를 만들어 보거나, 다른 프로젝트를 해볼 때 테스트 코드를 꼼꼼하게 작성해야겠다는 생각이 들었다
</br>

## 기술스택
- JDK 11
- Springboot 2.7.8
- Mybatis
- h2 DB
- MySQL
- JSP
</br>

## 기능정리
### 1단계 (완료)
- 회원가입, 로그인, 글쓰기, 글목록보기, 글상세보기, 글삭제, 글수정
### 2단계 (완료)
- 댓글쓰기, 댓글목록보기, 댓글삭제
### 3단계 (완료)
- 좋아요하기, 좋아요취소, 좋아요보기
### 4단계 (완료)
- 아이디중복체크, 회원수정하기, 페이징
### 5단계 (완료)
- 검색
</br>

## 기능상세
#### 글쓰기 (summernote) 
<div align="center">
  <img width="60%" src="https://github.com/aozp73/aozp73/assets/122352251/185b43ea-186f-46db-a9c8-34ebd96d494f"/>
</div>
</br></br>

#### 정보수정 
<div align="center">
  <img width="60%" src="https://github.com/aozp73/aozp73/assets/122352251/80d988ef-d031-4479-803b-d8d6eb8bffd4"/>
</div>
</br></br>

#### 좋아요, 댓글 
<div align="center">
    <img width="60%" src="https://github.com/aozp73/aozp73/assets/122352251/00048b29-d593-4e78-b55a-fed95c845944"/>
</div>
</br></br>

#### 검색, 페이징 (Ajax-부분 리로딩, CSR 방식 / 해당 프로젝트에선 next, prev만 구현되었음) 
<div align="center">
    <img width="60%" src="https://github.com/aozp73/aozp73/assets/122352251/d29c8d57-0ae4-4043-9fd8-3eee980276ba"/></br></br>
</div>
</br></br>

## 모델링
<div align="center">
    <img width="80%" src="https://github.com/aozp73/aozp73/assets/122352251/75e376c2-8b16-4507-971b-bed66915cf39"/>
</div>

</br></br>
