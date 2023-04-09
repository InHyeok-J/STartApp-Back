# About STartApp

<div align="center">
 <img src="https://user-images.githubusercontent.com/28949213/230562039-63df4031-a054-434e-b86b-aab3c757c1d9.png" width="300px"/>
  <img src="https://user-images.githubusercontent.com/28949213/230562402-f4c22d45-7dec-4e9e-8131-6244394ce8fe.jpeg" width="250px"/>
</div>


서울과학기술대학교 학생들을 위한 어플을 제작했습니다.

개발 기간 : 2022.7 ~ 2022.10   
*현재 다른 분들이 유지보수 및 추가 개발 진행중, 현재 적용된 기술과 실제 서비스 되는 앱의 기술은 다를 수 있습니다.   
<a href="https://github.com/suee97/StartApp-Flutter">클라이언트 레포 링크</a>

## 👨‍👩‍👧‍👦 Team Member

<table align="center">
  <tr>
    <td align="center">
       <a href="https://github.com/suee97">
        <img src="https://github.com/suee97.png" alt="오승언" />
      </a>
      <p><strong>오승언</strong></p>
    </td>
    <td align="center">
       <a href="https://github.com/KangInyeong">
        <img src="https://github.com/KangInyeong.png" alt="강인영" />
      </a>
      <p><strong>강인영</strong></p>
    </td>
    <td align="center">
       <a href="https://github.com/s-ryuri">
        <img src="https://github.com/s-ryuri.png" alt="위성률" />
      </a>
      <p><strong>위성률</strong></p>
    </td>
    <td align="center">
       <a href="https://github.com/InHyeok-J">
        <img src="https://github.com/InHyeok-J.png" alt="조인혁" />
      </a>
      <p><strong>조인혁</strong></p>
    </td>
    <td align="center">
       <a href="https://github.com/Songminseon">
        <img src="https://github.com/Songminseon.png" alt="송민선" />
      </a>
      <p><strong>송민선</strong></p>
    </td>
  </tr>
  <tr>
    <td align="center">PM, Front-End</td>
    <td align="center">Front-End</td>
    <td align="center">Back-End</td>
    <td align="center">Back-End</td>
    <td align="center">Front-End</td>
  </tr>
</table>
*사진을 누르면 github profile 로 이동합니다.   

## 🗂 목차

- 개요
- 기능
- 소프트 웨어 아키텍처
- 시스템 아키텍처
- 사용 기술과 근거
  - 로그인과 JWT
  - RefreshToken과 토큰저장소 Redis
  - Jpa와 QueryDSL
  - 학생증 인증과 Slack API
  - Jenkins와 CICD
- 성과 및 회고

## 🌟 개요

- 학생들을 위한 총학생회에서 제공하는 물품 대여 기능과 학사 일정 제공, 축제때 사용할 기능 등을 제공합니다.
- 릴리즈 : 2022-09-19(iOS,Android)
    - 플레이 스토어 : [[LINK]](https://play.google.com/store/apps/details?id=com.start.STart)
    - 앱스토어 : [[LINK]](https://apps.apple.com/kr/app/%EC%84%9C%EC%9A%B8%EA%B3%BC%ED%95%99%EA%B8%B0%EC%88%A0%EB%8C%80%ED%95%99%EA%B5%90-%EC%B4%9D%ED%95%99%EC%83%9D%ED%9A%8C/id1641852619)
    - 축제 기준 천명 이상 사용했습니다.

## 🏋️‍♀️ 기능

- 회원 및 인증 기능
- 총학 물품 대여 기능
- 축제 용 스탬프 인증
- 학사 일정 제공 및 학생회 이벤트 제공 기능
- 관련 어드민 기능 (관리 및 승인)


## 🗂 사용 기술
- Spring Boot, JPA, Query DSL, Spring Security, MySQL, Redis, JWT
- AWS ECS,AWS CodeDeploy, AWS ElasticCache, AWS RDS, AWS ELB
- Jenkins, Docker, Docker-Compose 

## 🖥 소프트 웨어 아키텍처

<details>
<summary>접기/펼치기</summary>

<!-- summary 아래 한칸 공백 두어야함 -->

## 헥사고날 아키텍처

```text
main/app
  |-banner
    |-adapter
      |-in
        |-BannerAdminController
        |-BannerController
	      |-dto
      |-out
        |-BannerPersistenceAdapter
        |-JpaBanner
        |-JpaBannerRepository
    |-application
        |-port
          |-in
            |-command
              |-BannerGetUseCase
              |-DeleteBannerUseCase
              |-RegisterBannerUseCase
                |-...
          |-out
            |-LoadBaneerPort
            |-SaveBannerPort
            |-BannerGetService
            |-DeleteBannserService
            |-RegisterBannerService.
  |-domain
    |-Banner
  |-event
  |-festival
  |-global
  |-member
  |-plan
  |-rent
  |-suggestion
-Application.java
```

- 같이 백엔드 개발을 하는 팀원과 도메인 단위로 효율적인 개발을 진행하기 위해 함께 헥사고날 아키텍처 스터디를 진행 후 적용했습니다.
  <a href="https://github.com/DevelopersPath/Make-Clean-Architecture">스터디 REPO 링크</a>
- 포트 앤 어댑터 패턴이라고 불리는 헥사고날 아키텍처는 도메인 영역과 외부 인프라 영역을 분리시키며, 도메인 영역에 port가 있고
  해당 포트의 구현체인 out 어댑터, 도메인 영역을 사용하는 in Adapter로 도메인과 외부 영역의 의존을 최소화 시킬 수 있습니다.

**추가적으로 적용한 규칙**

- UseCase 나타내기. -> 하나의 서비스에 여러 public interface가 있는 것이 아닌, 로직을 담당하는 유스케이스는 하나의 클래스만 처리하게 함.
  (Application)
- 도메인 객체와 JPAEntity 의 연관성 끊기.
- Controller도 Admin과 Client의 컨트롤러 분리하기.
- 각 레이어마다 사용하는 DTO(or Entity) 분리.

**헥사고날 아키텍처를 적용한 이유**

- 가장 큰 이유는 유지보수 였습니다. 총학 애플리케이션의 수명이 1년(총학생회 변경)이 될 수 있고, 그 이후로 연장될 수 있지만
  만약 연장된다면 비대한 서비스 혹은, 아키텍처의 근거가 없는 혹은 지름길을 선택한 레이어드 아키텍처는 문제가 유지보수 측면에서
  문제가 생길 수 있다고 생각했습니다.

**적용 후 회고**

- 아키텍처를 적용하면서 얻었던 이점은 아키텍처 기반의 구조를 지키다 보면 책임 분리와 의존성에 관해서 변경에 (특히 외부적인) 영향이 덜 오게 됐습니다.
- 실제 예시로 회원가입시 SMS인증을 사용하는데 문자 메시지 전송에서 카카오톡 알림톡 전송 기능으로 바꿔도 비지니스 로직의 코드는 건들지 않고 구현체의 교체만으로
  쉽게 처리할 수 있었습니다.


- 하지만 단점도 많았던 아키텍처였습니다.
- 소규모 프로젝트에는 도입하는것에 좋은 것인가? 에 대한 의문이 있었고, 아직 기획상 복잡하지 않은 도메인 로직에 비해
  과한 아키텍처 구조였던것 같습니다.
- 한가지 아쉬운 점은 복잡한 비지니스 로직이 없어서 도메인 객체에 로직을 활용한, 각 객체들 간의 협력으로 로직을 구현할 부분이 없었던 점과 당시에는
  이런 부분에(객체지향)에 대해 모르고, 아키텍처적인 관점만 고려했었던것도 아쉬웠습니다.

</details>

## ♢ 시스템 아키텍처

<details>
<summary>접기/펼치기</summary>

## 시스템 아키텍처

<img src="https://user-images.githubusercontent.com/28949213/230575995-56753946-1620-4920-9a24-cb2d82025414.png" >

- VPC내 Public Subnet 2개와 과 Private Subnet 2개 존재.
- 각 서브넷은 다른 가용영역에 위치.
- Private Subnet은 로드밸런서와 Bastion 서버를 통해서 접근 가능.
- 2개의 서버(상시 유지)로 부하 분산.
- Private Subnet 에서 외부와의 통신은 Nat Gateway를 통한 통신.
- AWS ECS + Docker를 활용한 배포 관리.
- 데이터베이스는 다른 독립된 Private 서브넷에서만 존재, VPC 내부에서만 통신 가능.
- 개발용 서버는 Jenkins와 함께 cicd로 빠른 개발 및 테스팅을 적용시켰습니다.

**설계 근거**

- 시스템 아키텍처를 구축하면서 고민했던 점은 보안입니다.
- 학생 수준의 제가 직접 아키텍처를 구축하면서 보안까지 신경쓰기엔 힘들다고 판단, 가장 손쉬운 방법이 VPC 내부에서 Public 과 Private 서브넷으로 격리 분산 시켜서
  Private 서브넷으로의 통신을 VPC 내부에서만 제한하도록 하는 것이라고 생각했습니다.
  Private Subnet에 서버와 데이터베이스를 위치시켜놓고, 로드밸런서(정상적인 접근) 혹은 개발자가 컨트롤하기 위한 Bastion 서버를 통해서만 네트워크 접근이
  가능하도록 설계했습니다.

</details>


## 사용 기술과 근거
<details>
<summary>접기/펼치기</summary>

## 로그인 JWT
세션이 아닌 JWT를 사용한 이유는 2가지가 있습니다.

1. 서버에서 세션 정보를 유지해야 한다는 것에 대한 고민입니다. 초기 기획상, 사용자 수가 얼마나 될지 모르기 때문에 서버의 자원을 최대한 아낄 수 있는 JWT를 고려했습니다.
2. 두번째는 확장성입니다. 추 후 서버를 확장하게 된다면 토큰이 유효한지 확인을 하는 과정과 Session에 접근해 직접 확인하는 방식 중 후자가 확장성을 고려했을때 단점이 있을것이라고 생각했습니다.

보안적으로는 Session이 좋다고 생각했지만, 서버 리소스 환경과 모바일 이라는 특성상 보안 이슈가 적다고 판단해서 결론적으로 JWT를 활용한 인증 방식을 사용했습니다.

## RefreshToken과 토큰저장소 Redis
Token은 자체적으로 정보를 가지고 있기 때문에 보안을 위해 만료기간을 짧게 설정해야 하고, 유저 경험을 위해 로그인 정보를 오래 유지하고 싶었습니다.   
그래서 Access Token과 Refresh Token을 사용했으며, 해당 유저의 로그인 상태를 계속 유지할 수 있도록 관리했습니다.   

토큰 저장소를 MySQL이 아닌 Redis를 사용한 이유는 2가지 입니다.

- 적절한 유효기간이 지나면 자동으로 만료되게 하기 위해 MySQL을 업데이트하는것 보다, Redis의 Expired를 활용하는 것이 적절하다고 판단했습니다.
- 보안을 위해 Access Token의 만료기간을 매우 짧게 설정했습니다. 만료가 되면 해당 토큰으로 Refresh를 해야 하는데 잦은 IO는 RDB 보다 메모리 스토리지인 Redis에 두어 DB서버의 부하를 분산시키려고 했습니다.

## Jpa와 QueryDSL
주로 Spring Data JPA에서 제공해주는 기본적인 CRUD 메소드로 대부분의 기능을 구현할 수 있었고, 필요한 경우 JPQL을 추가해서 직접 쿼리를 추가할 수 있었습니다.

문제는 물품 대여 로직과 관련해서 복잡한 쿼리가 필요했습니다.

복잡한 쿼리를 작성할 경우 Data JPA가 제공해주는 메소드로는 한계가 있었고 직접 쿼리를 작성하게 된다면 더욱 복잡성이 올라갔기에 컴파일 단계에서 문법 오류를 체크해주며, 동적 쿼리를 쉽게 작성할 수 있는 Query DSL을 활용하게 됐습니다.

## 학생증 인증과 Slack API

앱의 특성상 학생인지를 확인해야 했고, 초기 기획상 학교 전산원에서 API를 제공해주기로 했습니다.

하지만 전산원측에서 제공해주는 기능이 API 가 아닌 사이트 기반 인증이었고, 특정 환경에서 문제가 발생해, 학생증 인증으로 수동 처리해야 했습니다.

문제는 어드민 페이지에서 수동으로 처리하게 된다면, 축제에 갑자기 회원가입이 몰릴 시 사이트 기반으로 인증 확인을 쉽고 빠르게 할 수 없다고 판단해서, 관리자를 위한 Slack API를 도입해, 회원 가입 시 Slack의 버튼 기반으로 쉽게 처리할 수 있도록 변경했습니다.

## Jenkins와 CICD

CICD 를 위해 어떤 툴을 사용해야 하는가? 에 대한 고민으로 github actions와 Jenkins 2가지 선택지가 있었습니다.   
github을 사용하고, 규모가 크지 않은 프로젝트라 github actions이 맞는 선택지인것 같았으나 결론적으로 Jenkins을 선택했습니다.   
그 이유는 다음과 같습니다.   
- private Repository 기준으로 github actions은 특정 리소스 사용 이후 유료입니다. 저희의 빌드 및 테스팅 환경에서 어느정도의 리소스를 사용할지 예상이 안됐고,
젠킨스는 서버를 유지하는 비용만 사용한다면 무료로 사용할 수 있었습니다.
- 다양한 플러그인과 문서화가 잘되어있습니다. 서버 초기 구축 시간을 제외하고 특정 기능을 추가할때 플러그인만 추가해서 사용하는데 편리함을 느꼈습니다.
- 배치 프로세싱 적용 계획. 시간 및 기획 이슈로 처리하지 못했지만, 학생의 학적 상태를 지속적으로 업데이트를 진행해서 처리해야 하는 상황에 주로 배치 프로세스를 사용한다고 알게 돼서 선택하게 됐습니다.


</details>

## 성과 및 회고
<details>
<summary>접기/펼치기</summary>

백엔드 개발자 조인혁이 적은 회고입니다.    

개발 및 기술적 회고와, 비 개발 기술적 회고로 나뉩니다.
프로젝트 전체적으로 성장은 할 수 있었지만, 실패했던 프로젝트였습니다.

### 개발 및 기술적 회고

Nodejs로 백엔드 개발을 시작한지 약 1년뒤, 스프링 부트로 진행한 두 번째 프로젝트였습니다. 프로젝트 시작 전 스프링 관련 기술을 좀 더 익히자 라는 생각으로 진행했습니다.    

결론적으로 말하자면 기술적으로 성장 할 수 있었지만, Spring이 아닌 데브옵스 적으로 많은 인사이트를 얻게 됐습니다.    

실제 앱을 배포하면서 앱을 배포하고 운영할때 기술 운용이 아닌 운영하기 위한 시스템 아키텍처는 어떻게 구축해야 하고, 어떤 기술들이 추가적으로 필요한지 배우고 적용할 수 있었습니다.

실제로 적용하고 해본 것.
- AWS 아키텍처 구축 및 적용.
- CICD를 적용하고, 무중단 배포 구축
- Jmeter를 활용한 성능 테스트 및 적용. 로드밸런서 적용.
- Cloud watch를 활용한 로그 

하지만 기술적, 시간적 이슈로 인해 못한 부분도 많다고 생각합니다.
- 배포 환경에서의 cicd(AWS의 cicd가 버튼 한번으로 적용돼서 그 당시에는 구축할 필요성을 못느꼈습니다..)
- 보안적으로 여러 테스팅 진행(DDOS, 스크립트 공격)
- ELK같은 로깅 시스템 구축(cloud watch 로만 적용)
- 근거 있는 아키텍처 구축

기획을 포함한 약 3개월의 개발 및 운영 기간 동안 실제 테스팅을 적용하는것은 배포 전 2주정도밖에 진행하지 못했고 이는 부족한 설계를 비판할 수 있는 근거를 만들지 못했습니다.
이렇게 생각하는 이유는 다음과 같습니다.

- 축제 기간을 위해 TPS를 올리려고 했는데, 어느정도가 적절한 TPS인가?
- 실제 병목 현상이 발생하는 부분을 제대로 확인하지 못했습니다. 현재는 로드 밸런싱을 통해 TPS를 증가시켰는데, TPS가 낮은 이유가 DB인지, WAS인지, WAS라면 스프링의 스레드풀을 최적화를 시켜봤는지? DB라면 적절한 쿼리가 나갔는지? (실행 계획 확인 등) 이를 통해 최적화를 시켰을 수 있는가?, EC2 스펙에 맞는 적절한 성능은 어떻게 낼 수 있는지 ?
- 위 처럼 많은 부분에 의문이 생긴 채 그저 서버 갯수로(돈으로) TPS를 증가시켰고, 근거가 없으니 불안한 채 축제 기간을 보냈습니다.

데브옵스 외적인 부분에 대해서는 다음과 같습니다.   
실제로 적용하고 해본 것.
- 헥사고날 아키텍처 적용.
- QueryDSL 등의 기술 사용
- Refresh Token 전략 적용.

아쉬운 부분.
- 아키텍처 적용이 끝이 아니다.   
무분별한 서비스 코드를 막고자 소프트웨어의 설계를 위해 아키텍처를 공부하고 적용했습니다. 하지만 중요한 도메인 영역에서의 부족함이 느껴졌습니다.   
당시에는 잘 몰랐지만, 객체지향 공부를 하면서, 과연 도메인의 Entity가 적절한 객체인가? 각 객체간의 협력을 통해 구축했는가?    
단순 아키텍처 공부는 응집성 및 결합도 측면에서 어느정도의 이점을 얻을 수 있으나, 프로젝트의 적용된 객체들은 데이터 중심적인 설계로 탄생했다고 생각이듭니다.   
이는 프로젝트에 적용된 JPAEntity 와 도메인 Entity의 분리의 이점을 활용할 수가 없는 헛수고였다고 생각이 들며 아쉬운 부분입니다.


- 어떻게 테스트를 적용해야 하는가?    
프로젝트에 적용된 테스트는 도메인 useCase 단위의 테스트와 직접 작성한 도메인 input DTO 객체들입니다. 하지만 그외적의 DB 테스트 같은 것은 시간적인 이슈로 하지 못했는데
해당 부분의 테스트를 적용하지 못한것, 통합 테스트의 부재가 아쉬움이 남습니다.



### 비 기술적 회고

비 기술적으로도 많은 생각이 드는 프로젝트였습니다.    
처음으로 개발자가 기획하는 것이 아닌, 기획자와 개발자가 분리된 프로젝트였습니다.   이는 개발자와 기획자간의 완벽한 소통이 아니면 프로젝트 개발에 큰 차질이 생긴다는것을 느꼈습니다.

- 변하는 요구사항, 늦는 기획   
6월부터 시작한 기획은 8월 중순에 마무리됐습니다. 개발은 7월부터 시작했지만, 중간에 변하는 요구사항과 늦게 마무리되는 기획은 개발자 입장에서 당황스러움을 많이 느꼇습니다.
- 소통의 부재    
SMS 인증을 처리하는데 기술적인 어려움은 없었으나 개인 번호가 아닌 public 휴대폰 번호를 사용해야 했습니다.(사업자 등록 번호)  당시 기획팀에게 해당 부분이 가능한지 정확하게 전달하지 못했고 딜레이 되다가 결국 문자 인증은
철회하려고했습니다.      
그래서 해당 인증을 OAuth로 변경하려고 했고 실제 개발을 진행하다가 사업자 등록이 이미 돼있다라는 답을 받았을 때 당황스러웠던 경험이 있습니다.    
하지만 이것도 늦게 처리가 돼어 결국 실제 배포 후 이틀동안은 PM의 번호로 처리하다가 이후 백엔드에서 카카오톡 번호 인증으로 변경했었습니다.   

위와 같은 경험은 단순 개발 외적으로 많은 부분을 놓치고 있었구나 라는 생각이 들게됐던 프로젝트였습니다.



</details>
