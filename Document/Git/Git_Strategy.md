## 💎 git 폴더 구조   



- frontend - Vue.js
- backend - Spring Boot
- analy - 파이썬 데이터 분석
- document - 문서 작업   


  <br>
    
    
## 💎  git 규칙



- Commit규칙

    -참고 링크 : [https://udacity.github.io/git-styleguide/](https://udacity.github.io/git-styleguide/)

    - **feat:** A new feature

        : 새로운 기능 구현

    - **fix:** A bug fix

        : 버그 픽스, 기타 기능 수정

    - **docs:** Changes to documentation

        : 문서 변경 시 ( readme 같은 것 변경시)

    - **style:** Formatting, missing semi colons, etc; no code change

        : 코드 변경 사항없어 자잘한 포맷 변경 시: 예를 들어 작은따옴표에서 큰 따옴표로 변경

    - **refactor:** Refactoring production code

        : 이미 있던 코드를 리팩토링할때 (깔끔하게 수정)

    - **test:** Adding tests, refactoring test; no production code change

        : 테스트 코드 추가 시

    - **chore:** Updating build tasks, package manager configs, etc; no production code change

        : 잡일 , 생산성 없는 코드 변경 시, 위 모든 것에 해당사항없을 시 여기로


<br>


## 💎 git commit 예시


   ```
ex) [backend] feat : 로그인기능

    feat

    fix

    docs

    refactor

    test
   ```


<br>


## 🥢 branch전략 예시


```
master

    **develop**

    analy

    backend

        B/user

        B/wine

        B/reco

    frontend

        (App.vue)

        F/nav

        F/guideBtn

        (Router-link)

        F/main

        F/recommendation

        F/wines

        F/community

        F/topic

        F/myInfo

        F/wineDetails

        F/tasteSet

        F/makingTaste

        (modal)

        F/login

        F/join
```

<br>


## 💎push 예시


```
배포는 develop push할 경우만 (젠킨스 사용해서)
```
### [backend]

1. backend 하위 기능별 브런치에서 작업
2. local에서 테스트 → 제대로 돌아갈 때

    2-1. git push origin B/reco

    2-1. backend로 B/reco를 merge

    2-3. git push origin backend

### [frontend]

1. frontend 하위 기능별 브런치에서 작업
2. 테스트

    2-1. 클론 백엔드

    2-2. local에서 테스트 → 제대로 돌아갈 때

    2-3. git push origin F/reco

    2-4. frontend로 F/reco를 merge

    2-5. git push origin frontend