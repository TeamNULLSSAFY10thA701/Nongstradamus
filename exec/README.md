# :books: 포팅 매뉴얼

<br>

## 버전 및 정보

- Java: azul/zulu-openjdk:17
- Spring boot: 3.2.3
- Python: 3.9.13
- Flask: 3.0.2
- Nginx: 1.24.0
- Docker: 26.0.0
- Jenkins: 2.449
- Vue.js: 3.9.2
- Node.js: 20.11.1
- MariaDB: 10.11.7
- IDE: IntelliJ IDEA 2023.3.2, VSCode 1.82.7

<br>

## 빌드 시 사용되는 환경변수

- Frontend 빌드 시 사용되는 .env, Backend 빌드 시 사용되는 application.yml, Flask 빌드 시 사용되는 config.py 파일은 Jenkins Credentials에 위치함.
- Jenkinsfile에 해당 파일이 복사되는 과정을 확인할 수 있음.

<br>

## 배포시 특이사항

![구조](/images/구조.png)

<br>

- GitLab의 dev-be/dev-fe/dev-data 브랜치에 push시, Jenkins를 통해 자동으로 빌드되고 빌드에 성공하면 자동으로 배포됨.

<br>

## ERD

![ERD](/images/ERD.png)

<br>

## 프로젝트에서 사용하는 외부 서비스 정보

없음

<br>

## 시연 시나리오

<br>

<img src="/images/main2-1.gif" alt="메인페이지1"  width="300px" height="200px">
<img src="/images/main2-2.gif" alt="메인페이지2"  width="300px" height="200px">
<img src="/images/main2-3.gif" alt="메인페이지3"  width="300px" height="200px">

### 알뜰 상품 추천
- 어제와 비교해서 오늘 하락폭이 가장 큰 농산물 1개를 추천합니다.
- 오늘과 비교해서 내일 하락폭이 가장 클 농산물 1개를 추천합니다.
- 어제와 비교해서 오늘 하락폭이 가장 큰 농산물 4개를 추가로 추천합니다.
- 데이터 갱신 일자를 확인하고 세로고침 버튼을 눌러 페이지를 갱신할 수 있습니다.

<br>

<img src="/images/recipe2-1.gif" alt="레시피1"  width="300px" height="600px">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

### 레시피 추천
- 알뜰 상품이 포함된 레시피를 최대 20가지 추천합니다.
- 레시피 재료와 조리방법을 확인할 수 있습니다.
- 영양소 순으로 레시피를 정렬할 수 있습니다.

<br>

<img src="/images/predict2-1.gif" alt="예측페이지"  width="300px" height="600px">

### 상세 가격 예측
- 농산물별로 과거 가격과 예측 가격을 차트를 통해 확인할 수 있습니다.
- 품질별 가격을 확인할 수 있습니다.
- 농산물 분류를 통해 빠르게 원하는 농산물을 선택할 수 있습니다.
