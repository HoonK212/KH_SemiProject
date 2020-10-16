## 프랜차이즈 메뉴 소개 및 후기 플랫폼 웹 프로젝트 🌱
    
### 1.브랜드평점 프로젝트 
* 프로젝트 기간 : 2020.05.20 ~ 2020.07.02
* 5인 팀프로젝트
* KH 정보교육원 세미프로젝트

---

### 2. 프로젝트 개요
음식의 메뉴를 고민하는 사람들에게 프랜차이즈별 각 메뉴의 정보(출시일, 가격, 음식사진, 지점 위치)를 소개하고  
음식의 메뉴에 따른 평점순, 리뷰순, 출시일순, 가격순으로 정렬하여  
타사용자의 이용후기 들을 쉽고 빠르게 공유할수 있도록 서비스를 제공한다.

---

### 3.웹 어플리케이션 주요기능

* **사용자 페이지**

    |구 분|기 능|
    |-------|------|
    |메인|이미지 슬라이더|
    |로그인|로그인, 로그아웃, 마이페이지(개인정보수정, 프로필수정, 한줄평 수정 & 삭제)|
    |평점보기|사용자가 원하는 메뉴종류의 평점순, 리뷰순, 가격순, 출시일순으로 필터링|
    |메뉴상세보기|메뉴사진을 클릭시, 해당메뉴의 정보를 보여주는 모달|
    |별점 & 한줄평 등록|별점을 등록하는 기능 마우스 드래그시 0.5점씩 누적|
    |지역 검색|해당 메뉴의 원하는 매장 위치 정보 제공|
    |한줄평 신고|해당 한줄평 신고 접수|
    |게시판 & Q&A|자유 게시판 과 Q&A|

* **프랜차이즈 사용자 페이지**

    |구 분|기 능|
    |-------|------|
    |게시물 관리|해당 브랜드의 등록여부 및 blind상태 조회|
    |신메뉴 등록|해당 브랜드의 신메뉴 등록신청|
    |blind 메뉴 신청|해당 브랜드의 특정 메뉴 blind 신청|


* **관리자 페이지**

    |구 분|기 능|
    |-------|------|
    |회원 관리|전체 회원 조회 및 특정 회원 필터 조회|
    |회원 가입 현황|10일간 회원가입 현황 그래프 & 특정일 현황 조회|
    |신메뉴 등록 대기|프랜차이즈 사용자가 등록한 신메뉴 등록 승인 & 취소 관리 - 모달창|
    |신규 블라인드 등록대기|노출 중인 메뉴 Blind 처리|
    |신고 관리|신고 된 사용자 조치(계정정지) 및 신고자 조치 답변|
    |공지|공지 사항 등록|
    |Q&A|사용자 등록 Q&A 관리|
    |프랜차이즈 조회|등록 된 프랜차이즈 관리|

---

### 4.릴리즈 정보

1. **semi.zip 다운로드**

    * 사이트 사용 설명서 - **브랜드평점_사용설명.pdf**

    * 프로젝트 발표 자료 - 브랜드평점_발표자료.pdf

2. **톰캣 사진 업로드** 

    * 음식 메뉴 사진 -> upload.zip

    * 개인 프로필 사진 -> upload_picture.zip

3. **메일인증 코드 및 Oracle SQL 백업 파일**

    * 메일 인증 기능(**계정 및 내용 기입 필수**) - src/web/controller/mail/MailController.java

    * DB backup sql 파일 -> semi_project.sql

---

### 5.개발 환경

|구 분|항 목|
|:-------:|:------:|
|O/S|**Windows 10**|
|Server / DBMS|**Tomcat 9.0 / Oracle 11g**|
|Framework / Platform|**Booststrap, jQuery**|
|Development Languages|**JAVA, JSP, JavaScript, SQL, HTML**|
|Pattern|**MVC MODEL2 Pattern**|
|Development Tools|**Ecilpse, SQL Developer, Star UML**|
|Share Tools|**GitHub, Source Tree**|

---

### 6.세미 프로젝트 관리자 Repository
https://github.com/HoonK212/KH_SemiProject_AdminPage
