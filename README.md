<H1>🐧 savePenguin 서비스 </H1>

<p align="center">   
<img width="286" alt="savePenguin" src="https://user-images.githubusercontent.com/56347876/152633349-98cf961e-08d0-4ecb-aa4c-44e5c38b47df.png">
</p>

  <H2> 🐧 프로젝트 개요 </H2>

### [동기]

   식당, 카페 등에서 일회용 컵 또는 용기의 사용으로 `많은 양의 플라스틱이 버려져 환경 오염 및 기후변화를 초래`하고 있다. 전 세계는 이 문제에 대하여 중대한 관심사항으로 생각하고 있어, 국가와 기업 등은 이에 대한 정책 및 대책을 마련하고 있다. 또한, 무엇보다 일상 생활에서의 사람들의 참여가 무엇보다 중요한데, 그 중 하나의 방법으로, 카페에서 일회용컵 사용 대신에 리유저블 컵과 텀블러 사용의 권장이다. 하지만, 적극적인 참여가 이루어지지 않고 있는것이 현실이다.
   
### [개요]

   따라서, `savePenguin` 에서는 고객이 식당, 카페 등에서 개인이 소지한 리유저블컵, 텀블러를 앱에 등록을해 QR코드를 발급 받고, 가지고와서 음료를 구매한다면, 매장에서는 savePenguin QRcodeScanner을 이용해 QR 코드를 인증을 하고 포인트를 적립해준다. 적립된 포인트를 통해 다양한 혜택을 받을 수 있도록 한다. 현재 계획 및 개발 내용은 게임의 형태이다. 적립된 포인트를 통해 상점에서 펭귄을 꾸밀 수 있는 아이템을 구매할 수 있다. 이는 목표를 달성할 수 있는 최소한의 구현 계획이 되겠다.
   
### [확장 가능성 & 비지니스 모델]

 실제로는 다양한 확장 가능성을 갖을 수 있는데, 대표적으로 `탄소 포인트제도`에 연결지어 확장할 수 있다. 탄소 포인트 제도는 기후 위기 대응을 위하여 온실가스를 줄일 수 있도록 가정, 상업, 아파트 단지 등에서 전기, 상수도, 도시가스의 사용량을 절감하고 감축률에 따라 탄소포인트를 부여하는 전국민 온실가스 감축 실천제도이다. 
 
 이처럼, 사용자가 savePenguin을 통해 적립 받은 포인트를 지역 화폐로 교환을 해주는 것을 생각해 볼 수 있다.  이를 통해, 사용자는 환경을 지킨다는 자부심을 얻는 동시에 금전적 보상을 얻게 됨을 통해 개인 컵 사용을 지속시킬 수 있을 것으로 기대한다. 나아가, 지역 화폐는 고객층을 확보할 수 있고, 지역 경제를 활성화 시킬 수 있다라는 장점을 이용하여, 참여 매장을 확보 할 수 있을 것으로 기대한다. 따라서,  우리 앱의 서비스의 고객층이 확보가 되면 서버 이용료 및 앱 광고를 통해 수익 창출을 기대할 수 있다. 
 
 또한, 최근에 사람들(주로 청년들) 사이에서 `앱테크`가 유행하고 있다. savePenguin 또한, 포인트를 일정 포인트 이상 모으면 현금화 할 수 있게 한다는 점에서 같은 선상에서 볼 수 있다. 따라서 이를 이용하여 홍보를 한다면, 일회용 컵을 가장 많이 발생시키는 카페의 주 고객층인 청년들의 적극적인 참여를 유도 할 수 있을 것으로 기대하고 앱을 유지시키며 `개인컵 사용을 정착`할 수 있게 한다.

  
  <H2> 🐧 세부 기능 </H2>


![1](https://user-images.githubusercontent.com/56347876/152633314-a30e6824-3072-4aeb-bdac-16539b8a668e.png)

![2](https://user-images.githubusercontent.com/56347876/152633325-97f1176f-0a25-4845-8361-4d42f94daa19.png)

![3](https://user-images.githubusercontent.com/56347876/152633332-28a8e4d8-a908-4512-9f40-55a94849b17a.png)

![4](https://user-images.githubusercontent.com/56347876/152633333-97f2bb8f-1797-409e-a359-94b68caca18f.png)

![5](https://user-images.githubusercontent.com/56347876/152633336-8497d226-f295-4eed-abb9-0ea97bd47289.png)

![6](https://user-images.githubusercontent.com/56347876/152633343-dff51631-2b7c-460f-8ec8-1e561865e318.png)

![7](https://user-images.githubusercontent.com/56347876/152633345-2e59919f-dc67-4d26-9761-b4ce3b2e5e62.png)


<H2> 🐧 사용한 google 기술 </H2>

zxing( 구글에서 제공하는 오픈소스로 Zebra Crossing의 약자)을 사용하여 등록한 텀블러 또는 리유저블 컵의 qr코드 생성 및 seller의 qr리더기 개발.

참고링크: https://github.com/zxing/zxing

<H2> 🐧 기술스택 </H2>

||사용 언어 및 라이브러리|Tools|
|:---:|:---:|:---:|
|Android|java, Retrofit2, gson, junit, volley |AndroidStudio|
|Server|SpringBoot, Spring Data JPA|Eclipse STS4|

<H2> 🐧 System Architecture </H2>
<img width="629" alt="아키텍처" src="https://user-images.githubusercontent.com/56347876/152634181-6674c4e7-7204-4751-9e5e-7c28a45f15af.png">


<H2> 🐧 savePenguin 제안서 </H2>
https://github.com/gdsckoreahackathon2022/22_savePenguin/blob/main/GDSCHack_22team_savePenguin.pdf


<H2> 🐧 Demo 영상 </H2>
https://www.youtube.com/embed/Jg0C5mlqN8w

<H2> 🐧 어플 다운로드 링크 </H2>
savePenguin 어플 (유저용)
https://drive.google.com/file/d/1Lz0s0DlTzIAYjMSA5o0binErW1a8hM7X/view?usp=sharing

savePenguin 어플 (매장용) - QR 스캐너 기능
https://drive.google.com/file/d/1iaNkM-93HzAWRpk1ugoKWjfBN0r9e0CH/view?usp=sharing

<H2> 🐧 역할 </H2>

|![휘경](https://user-images.githubusercontent.com/56347876/152635383-5f9a26a5-cb92-45b0-8645-ad031d2e6805.png)|![희선언니](https://user-images.githubusercontent.com/56347876/152635382-eb3ae567-0b09-449f-878f-756970972c37.png)|![범수오빠](https://user-images.githubusercontent.com/56347876/152635384-40f16d90-2506-48eb-bee7-7cdc26113954.png)|
|[:rabbit:김휘경](https://github.com/orgs/savePenguin/people/hwikyungkim-1215)|[:tiger:채희선](https://github.com/chaehuiseon)|[:penguin:조범수](https://github.com/orgs/savePenguin/people/jobum97)|
|:---:|:---:|:---:|
|담당|담당|담당|
|서버 개발|서버 개발|안드로이드 개발|
|담당|담당|담당|
|서버 개발|서버 개발|안드로이드 개발|



|![휘경](https://user-images.githubusercontent.com/56347876/152635383-5f9a26a5-cb92-45b0-8645-ad031d2e6805.png)|![희선언니](https://user-images.githubusercontent.com/56347876/152635382-eb3ae567-0b09-449f-878f-756970972c37.png)|![범수오빠](https://user-images.githubusercontent.com/56347876/152635384-40f16d90-2506-48eb-bee7-7cdc26113954.png)|
| ------ | ---------------------------------------------- | ------------------------------------------------------ |
|[:rabbit:김휘경](https://github.com/orgs/savePenguin/people/hwikyungkim-1215)|[:tiger:채희선](https://github.com/chaehuiseon)|[:penguin:조범수](https://github.com/orgs/savePenguin/people/jobum97)|
|:---:|:---:|:---:|
|담당|담당|담당|
|서버 개발|서버 개발|안드로이드 개발|

