# openCV를 이용하여 QR코드 출근명부 만들기 
과제 youtube link :   <br>
https://youtu.be/3W3_IsPN4TA

프로젝트 노션 정리 : <br>
https://lavender-cartwheel-279.notion.site/2-17d2427d162b4f7895e9f0599f007234

- 학생이 로그인 하면 학생증이랑 QR코드 생성 <br>
  => spring_boot + jsp 로 간단한 로그인 화면 구현<br>
- 라즈베리파이 + openCV로 QR코드 인식해서 출석 시간 체크 <br>
  => RDS(mariaDB)를 활용하여 데이터베이스 연동, 라즈베리파이 환경에서 쉽게 확인할 수 있도록 CSV파일로 저장<br>
  => tip) 라즈베리파이 4 에서는 mysql 지원이 안되고, mariaDB를 권장하고 있다. 
