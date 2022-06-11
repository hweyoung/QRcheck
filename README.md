openCV를 이용하여 QR코드 출근명부 만들기 
과제 youtube link : 
https://youtu.be/3W3_IsPN4TA

- 학생이 로그인 하면 학생증이랑 QR코드 생성 
  => spring_boot + jsp 로 간단한 로그인 화면 구현
- 라즈베리파이 + openCV로 QR코드 인식해서 출석 시간 체크 
  => RDS(mariaDB)를 활용하여 데이터베이스 연동, 라즈베리파이 환경에서 쉽게 확인할 수 있도록 CSV파일로 저장
  => tip) 라즈베리파이 4 에서는 mysql 지원이 안되고, mariaDB를 권장하고 있다. 
