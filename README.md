
<img src="/images/logo.png"  width="250px" height="250px">

# 목차

1. [**서비스 소개**](#-멍글멍글)
1. [**기획 배경**](#-기획-배경)
2. [**팀원 소개**](#-팀원-소개)
3. [**기술 스택**](#-기술-스택)
4. [**아키텍처**](#%EF%B8%8F-아키텍처)
5. [**erd 다이어그램**](#-erd-다이어그램)
6. [**와이어프레임**](#-와이어프레임)
7. [**주요 기능**](#-주요-기능)

# 농스트라다무스
> null (서울 7반 1조)
>
> 농산물 시세정보 예측과 레시피 추천 서비스
>
> 프로젝트 기간 : 2024.02.19 - 2024.04.04 (7주)

## 🌍 기획 배경

### 배경
식품의 소비자물가지수는 전 세계 물가상승을 주도하고 있으며, 특히 농식품 가격의 상승은 소비자들의 지출을 제약하고 물가안정에 부정적인 영향을 미치고 있습니다. 이러한 상황에서 취약계층은 물가상승에 따른 실질소득 감소에 대응하기 위해 식료품 소비를 줄이고 있으며, 특히 농산물 가격의 지속적 상승으로 인해 극심한 경제적 스트레스를 겪고 있습니다.

농산물의 가격은 기후, 유통, 수입 등 다양한 요인에 의해 크게 변동하며, 특히 장기보관이 어려운 신선식품의 경우 가격 변동성이 더욱 크게 작용합니다.

### 문제 해결
- 기후, 유통, 수입 등의 다양한 요인을 고려하여 미래의 농산물 가격을 예측합니다. 이를 통해 소비자들은 미래의 가격 변동을 예측하여 소비 계획을 세울 수 있습니다.

- 가격이 상대적으로 안정적이며 메리트가 있는 농산물을 활용하여 다양한 레시피를 추천합니다. 이를 통해 사용자들은 경제적이면서도 맛있는 요리를 만들 수 있습니다.

## 🤝 팀원 소개

### 김시은 (백엔드)
### 김평섭 (백엔드)
### 김한중 (백엔드)
### 반주현 (백엔드)
### 우혁 (프론트엔드, 팀장)
### 장태수 (프론트엔드)

## 💻 기술 스택

### FrontEnd & BackEnd

[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.1-green?style=flat&logo=spring)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17-orange?style=flat&logo=java)](https://www.oracle.com/java/)
[![MariaDB](https://img.shields.io/badge/MariaDB-10.11.6-yellow?style=flat&logo=mariadb)](https://mariadb.org/)

### Cooperation

[![Git](https://img.shields.io/badge/Git-gray?style=flat&logo=git)](https://git-scm.com/)
[![GitLab](https://img.shields.io/badge/GitLab-gray?style=flat&logo=gitlab)](https://about.gitlab.com/)
[![Notion](https://img.shields.io/badge/Notion-gray?style=flat&logo=notion)](https://www.notion.so/)
[![Jira](https://img.shields.io/badge/Jira-gray?style=flat&logo=jira)](https://www.atlassian.com/software/jira)
[![Mattermost](https://img.shields.io/badge/Mattermost-gray?style=flat&logo=mattermost)](https://mattermost.com/)

### Tools

[![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ_IDEA-2023.3.2-red?style=flat&logo=intellij-idea)](https://www.jetbrains.com/idea/)
[![Visual Studio Code](https://img.shields.io/badge/Visual%20Studio%20Code-1.85.1-blue.svg?style=flat&logo=visual-studio-code)](https://code.visualstudio.com/)

### Infra

[![AWS EC2](https://img.shields.io/badge/AWS_EC2-gray?style=flat&logo=amazon-aws)](https://aws.amazon.com/ec2/)
[![Docker](https://img.shields.io/badge/Docker-24.0.7-blue?style=flat&logo=docker)](https://www.docker.com/)

## 🏛️ 아키텍처

![구조](/images/구조.png)

## 📊 ERD 다이어그램

![ERD](/images/ERD.png)

## 🎨 와이어프레임
<img src="/images/와이어프레임.png"  width="800px" height="400px">

## 주요 기능

<img src="/images/로그인.png"  width="200px" height="400px">

### 알뜰 상품 추천
- 어제와 비교해서 오늘 하락폭이 가장 큰 농산물 1개를 추천합니다.
- 오늘과 비교해서 내일 하락폭이 가장 클 농산물 1개를 추천합니다.
- 어제와 비교해서 오늘 하락폭이 가장 큰 농산물 4개를 추가로 추천합니다.
- 데이터 갱신 일자와

<img src="./images/detail.gif" alt="디테일"  width="200px" height="400px">

<img src="./images/add.gif" alt="등록"  width="200px" height="400px">

### 레시피 추천
- 레시피

![산책](./images/walk.gif)

### 상세 가격 예측
- 가격예측

![매칭](./images/matching.gif)
