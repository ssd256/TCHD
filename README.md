## :cat2: 함께하묘 행복하개(Together Cat Happy Dog) :dog2:

### :bulb: 개발배경
도움이 필요한 동물들과 가족을 필요로 하는 사람들을 연결하여 동물과  유기동물 입양사이트 사람 모두의 삶의 질을 높   
이고, 대중들에게 유기동물의 입양을 홍보하기 위해 웹사이트 "함께하묘 행복하게"를 개발하게 되었습니다.   
<br>

### :dart: 개발환경   
- Operating System : Window OS   
- Server : Apache Tomcat 9.0   
- Database : Oracle 11g   
- Development Tool : Eclipse 4.15.0, sqlDeveloper version 19.2.1.247   
- Development Language : JAVA , HTML5, CSS3, JavaScript, jQuery, SQL   
- Team Coop : Github, ERDCloud, kakaoOven   
- API : Swal, Kakao Map, SMTP   
<br> 

### :clipboard: DB 설계도   

![ERD]()

[TCHD ERD](https://www.erdcloud.com/d/6xKpTLXCroHZ4ukTx)   
<br>

### :date: 일정      
  - 7/24 ~ 7/29   기획회의(+ppt)   
  - 7/30 ~ 8/5   UI 설계회의(+ppt)     
  - 8/6 ~ 8/10   DB설계회의(+ppt)   
  - 8/11 ~ 8/24   UI 화면 구현   
  - 8/24 ~ 9/9   기능 구현 및 화면 수정   
  - 9/10 ~ 9/11  베타 테스트 및 통합 테스트      
<br>      

### :memo: 맡은 파트  
:closed_book: 권기현  : 회원가입, 회원탈퇴 / 마이페이지 / 관리자 페이지 / 공지사항(CRUD)   
:green_book: **김미경** :sparkles:  : 입양게시판(CRUD) / 입양 신청
:blue_book: 김수진 : 문의사항(CRUD) / 게시판 페이징      
:orange_book: 이서영 : 봉사(CRUD) / 댓글(CRUD)   
:ledger: 이수진  : 로그인 / 로그아웃 / 아이디, 비밀번호 찾기 / 24시 동물병원 조회 / 후원하기   
<br>

### :computer: 화면 일부   

![AdoptList]()

* 입양 가능한 동물들은 볼 수 있습니다. 입양이 완료된 동물들은 목록에서 제외됩니다.



![AdoptInsert]()

* 로그인을 한 회원이라면 누구나 입양동물을 등록할 수 있습니다. *표시가 붙은 항목을 모두 입력해야 등록이 가능합니다.



![AdoptUpdate]()

* 등록한 입양동물의 정보 중 *표시가 붙은 항목에 대해서 수정할 수 있습니다.



![AdoptDetail]()

* 입양 가능한 동물의 상세정보를 확인할 수 있습니다. 입양하기를 누르면 입양 신청 페이지로 전환됩니다. 
* 입양 동물을 등록한 사용자의 경우 수정하기와 삭제하기 버튼이 활성화 됩니다.



![AdoptApply]()

![AdoptApply_agree]()

* 모든 항목을 작성하고 입양 동의를 체크하면 입양이 완료됩니다. 각 항목의 글자 수는 300자를 넘길 수 없습니다.