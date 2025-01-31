# 🐾 Re:PET
<div align="center">
<h2> Re:PET은 펫 로스 증후군을 치유할 수 있도록 도와주는 웹 서비스 플랫폼이에요!  🐶🐱🐰🐹 </h2>

<p> 펫 로스 증후군(pet loss syndrom)이란 자신이 키우는 반려동물을 떠나보낼 때의 슬픈 감정과 괴로움 등의 감정을 느끼고 있는 사람들의 상태를 일컫는 표현입니다. 깊은 유대감을 갖게 된 반려동물이나 동물을 잃는 것은 굉장히 슬픈 일이며, 이따금 가족의 죽음과도 견줄 수 있을 만큼 괴로워하는 사람들이 있습니다. <br>
하지만 아직 이를 직접적으로 치유할 방법이 부족한 상황이고, 그래서 웹 애완동물추모 공간 및 치유 서비스 리펫을 개발하게 되었습니다! <br> <br>
개인적으로는 [소정]은 지금 강아지를 한마리 기르고 있고, [재우]는 강아지를 무지개 다리로 보내본 경험이 있어서, 더 잘 공감할 수 있는 서비스였습니다. <br />
</p>
    
</div>
<br />

## 🧑‍💻 팀원 소개

  <table>
    <tr>
      <td align="center"><img src="https://github.com/Jaiwooyu.png" width="160"></td>
      <td align="center"><img src="https://github.com/doleebest.png" width="160"></td>
    </tr>
    <tr>
      <td align="center">유재우</td>
      <td align="center">이소정</td>
    </tr>
    <tr>
      <td align="center"><a href="https://github.com/Jaiwooyu" target="_blank">@Jaiwooyu</a></td>
      <td align="center"><a href="https://github.com/doleebest" target="_blank" width="160">@doleebest</a></td>
    </tr>
      <tr>
      <td align="center">FE</a></td>
      <td align="center">BE, CSS</a></td>
    </tr>
  </table>
  <br>


## 🐶 프로젝트 소개


리펫에서는 세 가지 탭을 사용해볼 수 있어요.

 
> 1. **기록하기**<br>
반려동물의 정보를 기록할 수 있어요.
> 2. **추억하기**  
반려 동물과의 추억을 사진으로 올릴 수 있어요.
> 3. **대화하기**  
무지개 다리를 건넌 동물들과 대화를 나눌 수 있어요.

<br>

## 🛠 주요 기능

 <table>
    <tr>
      <td align="center">메인화면</td>
      <td align="center">기록하기</td>
    </tr>
    <tr>
      <td align="center"><img src="https://github.com/user-attachments/assets/ba7da632-293e-4e7c-9d56-7cb29ce4b7a9" width="500" /></td>
      <td align="center"><img src="https://github.com/user-attachments/assets/537cb8b4-d423-42f5-9c2d-c80ca9f441c7" width="500"/></td>
    </tr>
    <tr>
      <td align="center">추억하기</td>
      <td align="center">대화하기</td>
    </tr>
    <tr>
      <td align="center"><img src="https://github.com/user-attachments/assets/4a63e1f7-07b2-48fa-83e4-723fa8eea15e" width="500" /></td>
      <td align="center"><img src="https://github.com/user-attachments/assets/6aea33d2-2c1d-4b01-8b7d-ba03e9d9fab3" width="500" /></td>
    </tr>
 </table>


<br>

### 메인화면 및 구글 소셜 로그인 기능
- 웹을 처음 실행하면 화면이 표시됩니다. (`tailwind`로 구름이 둥둥 떠다녀요~ ☁️)
- 그리고 로그인하기 혹은 구글 계정으로 시작하기를 누르면 구글 소셜 로그인 페이지로 redirect 됩니다.
- - login을 하면 `mysql database`에 회원 데이터가 삽입됩니다.
- 상단 네비게이션 바를 누르면 구글 회원 데이터를 불러옵니다.  
<img width="443" alt="Image" src="https://github.com/user-attachments/assets/7408982b-8ef4-4c7c-8321-8c9f9769b13f" /><br>
- 물론 로그아웃도 가능합니다! (다시 처음 화면으로 돌아갑니다.) <br>

### 기록하기

- 반려동물의 정보를 기록할 수 있는 탭입니다. 최대한 정보를 자세하게 입력하는 것이 좋습니다. 내 반려 동물의 성격, 장점, 단점, 특징, 행복했던 추억, 사고쳤던 기억 등을 기록을 하면서 내가 기르던 반려동물에 대해 상기해보는 효과도 있습니다. 🌿
- 수정하고 싶은 마음이 들어 ‘이전으로’를 눌러 돌아가면 해당 데이터들이 남아 있고, 그리고 입력하지 않은 채로 ‘다음으로’를 누르면 경고문이 뜹니다.

### 추억하기

- 반려 동물과의 추억을 사진으로 올릴 수 있는 페이지입니다. 사진과 함께 제목, 내용을 입력하면 업로드가 됩니다.  
<img width="500" alt="Image" src="https://github.com/user-attachments/assets/d217b8af-2794-4fa7-8967-2bb1d6b2d12c" /><br>
- 그리고 갤러리 기능을 추가하여`masonry view`로 반려동물과의 추억을 모아볼 수 있게 uxui를 구성했습니다. 스크롤도 가능합니다.

### 대화하기
- 내가 추가했던 동물들의 목록이 나오고 그 중에서 동물을 선택하여 대화를 나눌 수 있습니다. <br>
<img width="763" alt="Image" src="https://github.com/user-attachments/assets/765b70d0-7b6d-4e29-bca0-fc7bd0555179" width="500"/><br>
- 이때, `ChatGPT API`를 따왔는데, 이전에 사용자가 “기록하기”에서 입력했던 정보들과 대화 히스토리들이 모두 ChatGPT에 프롬프트로 들어가게 됩니다. 사용자는 세상을 떠난 반려동물이 된 ChatGPT와 대화를 나누며 반려동물이 언제나 곁에 있는 기분을 느낄 수 있습니다 ✨ `(프롬프트 엔지니어링)`
- 더불어, 추억하기 탭에서 사진과 함께 올렸던 content도 ChatGPT에 적용이 됩니다.
- 그리고 이 목록에서 반려동물 정보를 삭제하거나, 수정할 수 있습니다. 목록에서 반려동물을 누르면 “기록하기”탭으로 돌아가서 정보를 수정할 수 있습니다. 예를들어 새로운 추억이 더 생각났다면 기록할 수 있겠죠?
- 대화 히스토리는 저장되어 언제든 다시 들어가서 대화를 나누고 싶을 때마다 다시 대화를 시작할 수 있습니다.


<br />

## 📖 개발 환경  
- **Front-end** : React, tailwind, js, css

- **IDE** : vs code

- **Back-end** : spring, spring boot, java, mysql, postman

- **IDE** : intellij

<br>

## 🕰️ 개발 기간  
2025.01.02 ~ 2025.01.08R


<br />

## 💬 개발 소감
### 이소정

스프링을 포함하여 백엔드 개발 경험은 많이 있지만, 스프링을 처음부터 끝까지 모든 기능을 맡아서 해본 것, API 적용하기 그리고 로그인 구현은 처음이라 조금 긴장이 됐었습니다. 실제로 매우 힘들기도 했지만.. 감사하게도 최고의 플메 *똑똑하고 착한 재우가 있어서🫡😊*  즐겁게 잘 개발을 마칠 수 있었고 스프링 개발에 대한 자신감이 생긴 것 같아서 뿌듯합니다! 더불어 프론트엔드도 조금 경험해볼 수 있는 좋은 기회였습니다 🙇🏻‍♀️

### 유재우

지난 1주차에 거의 인생 첫 번째 개발을 해보고, 2주차를 진행하면서 프론트엔드와 백엔드 개발을 본격적으로 진행하려니 어려움이 많았습니다. 그렇지만 개발 경험이 풍부했던 소정 누나가 잘 알려주고 이끌어줘서 일주일동안 프론트엔드 개발부터, 서버, db에 이르기까지 정말 많은 것을 배울 수 있었던 것 같습니다. 개발 주제부터, 완성된 결과물까지 너무나 맘에 들고 뿌듯합니다..!!😎 일주일동안 많이 배울 수 있게 해준 소정 누나에게 감사합니다~🙇‍♂️
