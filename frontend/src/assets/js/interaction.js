export const main = () => {
  //스크롤 값을 다루기 위해 변수 저장,
  // pageYOffset을 그대로 사용하거나, 인련의 계산 과정을 거친 후 사용할 예정
  let yOffset = 0;
  let prevScrollHeight = 0; // 현재 스크롤 위치(yOffset) 보다 이전에 위치한 스크롤 section들의 높이 값의 합
  let currentScene = 0; //  현재 활성화된(눈 앞에 보이는) 씬(scroll-section)
  let enterNewScene = false; // 새로운 scene이 시작된 순간 ture, 애니메이션 오작동 방지

  let endScrollHeight = 0; // section-2 애니메이션 종료 후 자연스럽게 스크롤을 따라 올라가도록 계산하기 위한 기본 값

  let imgStopPoint = 0; // img가 absolute로 변경되는 시점
  let img = document.querySelector('#main-wine-img');

  //section interaction과 관련된 변수들
  const sceneInfo = [
    {
      type: 'normal',
      scrollHeight: 0, //최초 1회, 윈도우 리사이즈시 동적 할당
      objs: {
        container: document.querySelector('#main-section-0'),
      },
    },
    {
      type: 'sticky',
      heightNum: 5, //브라우저 높이의 배수
      scrollHeight: 0, //최초 1회, 윈도우 리사이즈시 동적 할당
      objs: {
        container: document.querySelector('#main-section-1'),
        wineTop: document.querySelector('#main-section-1 .wine-top'),
        wineName: document.querySelector('#main-section-1 .wine-name'),
        wineNameSub: document.querySelector('#main-section-1 .wine-name-sub'),
        wineNameSubHr: document.querySelector('#main-section-1 .wine-name-sub hr'),
        wineNameSubKname: document.querySelector('#main-section-1 .kname'),
        wineDesc: document.querySelector('#main-section-1 .wine-desc'),
        wineDescPin: document.querySelector('#main-section-1 .wine-desc .pin'),
        //
        wineInfo: document.querySelector('#main-section-1 .wine-info'),
        wineInfoHr: document.querySelectorAll('#main-section-1 .wine-info hr'),
        wineInfoItem: document.querySelectorAll('#main-section-1 .wine-info-item'),
      },
      values: {
        wineTop_opacity_in: [0, 1, { start: 0, end: 0.12 }],
        wineTop_translateY_in: [20, 0, { start: 0, end: 0.12 }],
        wineTop_opacity_out: [1, 0, { start: 0.8, end: 0.8 }],
        wineTop_translateY_out: [0, -20, { start: 0.8, end: 0.8 }],
        //
        wineName_opacity_in: [0, 1, { start: 0.05, end: 0.17 }],
        wineName_translateY_in: [20, 0, { start: 0.05, end: 0.17 }],
        wineName_opacity_out: [1, 0, { start: 0.8, end: 0.9 }],
        wineName_translateY_out: [0, -20, { start: 0.8, end: 0.9 }],
        //
        wineNameSub_opacity_in: [0, 1, { start: 0.05, end: 0.06 }],
        wineNameSubHr_width_in: [0, 40, { start: 0.05, end: 0.24 }],
        wineNameSubKname_opacity_in: [0, 1, { start: 0.17, end: 0.26 }],
        wineNameSubKname_translateX_in: [7, 0, { start: 0.17, end: 0.26 }],
        wineNameSubHr_opacity_out: [1, 0, { start: 0.85, end: 0.94 }],
        wineNameSubHr_translateY_out: [0, -20, { start: 0.85, end: 0.94 }],
        wineNameSubKname_opacity_out: [1, 0, { start: 0.85, end: 0.94 }],
        wineNameSubKname_translateY_out: [0, -20, { start: 0.85, end: 0.94 }],
        //
        wineDesc_opacity_in: [0, 1, { start: 0.25, end: 0.45 }],
        wineDesc_translateY_in: [20, 0, { start: 0.25, end: 0.45 }],
        wineDescPin_width_in: [0, 25, { start: 0.25, end: 0.45 }],
        wineDesc_opacity_out: [1, 0, { start: 0.55, end: 0.65 }],
        wineDesc_translateY_out: [0, -20, { start: 0.55, end: 0.65 }],
        //
        wineInfo_opacity_in: [0, 1, { start: 0.5, end: 0.56 }],
        wineInfoHr_width_in: [0, 40, { start: 0.45, end: 0.65 }],
        wineInfoItem_width_in: [0, 40, { start: 0.55, end: 0.65 }],
        wineInfoHr_width_out: [40, 100, { start: 0.8, end: 0.95 }],
        wineInfoItem_width_out: [40, 100, { start: 0.82, end: 0.95 }],
        wineInfo_opacity_out: [1, 0, { start: 0.75, end: 0.95 }],
      },
    },
    {
      type: 'sticky',
      heightNum: 4, //브라우저 높이의 배수
      scrollHeight: 0, //최초 1회, 윈도우 리사이즈시 동적 할당
      objs: {
        container: document.querySelector('#main-section-2'),
        wineImg: document.querySelector('#main-wine-img'),
        wineReview: document.querySelector('#main-section-2 .wine-review'),
        wineAvg: document.querySelector('#main-section-2 .wine-review> .frame> p'),
        wineAvgValue: document.querySelector('#main-section-2 .wine-review> .frame> p').innerText,
        wineRate: document.querySelector('#main-section-2 .star-rate'),
      },
      values: {
        wineImg_left_in: [50, 30, { start: 0.02, end: 0.25 }],
        wineImg_height_in: [60, 80, { start: 0.05, end: 0.3 }],
        wineReview_opacity_in: [0, 1, { start: 0.15, end: 0.42 }],
        wineAvg_text_in: [0, document.querySelector('#main-section-2 .wine-review> .frame> p').innerText, { start: 0.15, end: 0.4 }],
        wineRate_width_in: [0, document.querySelector('#main-section-2 .wine-review> .frame> p').innerText * 10, { start: 0.15, end: 0.4 }],
      },
    },
    {
      type: 'sticky',
      heightNum: 1, //브라우저 높이의 배수
      scrollHeight: 0, //최초 1회, 윈도우 리사이즈시 동적 할당
      objs: {
        container: document.querySelector('#main-section-3'),
      },
    },
  ];

  // 애니메이션
  function playAnimation() {
    const objs = sceneInfo[currentScene].objs;
    const values = sceneInfo[currentScene].values;
    const currentYOffset = yOffset - prevScrollHeight;
    const scrollHeight = sceneInfo[currentScene].scrollHeight;
    const scrollRatio = currentYOffset / scrollHeight;

    switch (currentScene) {
      case 1:
        if (scrollRatio <= 0.55) {
          objs.wineTop.style.opacity = calcValues(values.wineTop_opacity_in, currentYOffset);
          objs.wineTop.style.transform = `translate3d(0, ${calcValues(values.wineTop_translateY_in, currentYOffset)}%, 0)`;
          //
          objs.wineName.style.opacity = calcValues(values.wineName_opacity_in, currentYOffset);
          objs.wineName.style.transform = `translate3d(0, ${calcValues(values.wineName_translateY_in, currentYOffset)}%, 0)`;
          objs.wineNameSub.style.opacity = calcValues(values.wineNameSub_opacity_in, currentYOffset);
          objs.wineNameSubHr.style.width = `${calcValues(values.wineNameSubHr_width_in, currentYOffset)}%`;
          objs.wineNameSubKname.style.opacity = calcValues(values.wineNameSubKname_opacity_in, currentYOffset);
          objs.wineNameSubKname.style.transform = `translate3d(${calcValues(values.wineNameSubKname_translateX_in, currentYOffset)}%,0,0)`;
          //
          objs.wineDesc.style.opacity = calcValues(values.wineDesc_opacity_in, currentYOffset);
          objs.wineDesc.style.transform = `translate3d(0, ${calcValues(values.wineDesc_translateY_in, currentYOffset)}%, 0)`;
          objs.wineDescPin.style.width = `${calcValues(values.wineDescPin_width_in, currentYOffset)}vw`;
        } else {
          objs.wineTop.style.opacity = calcValues(values.wineTop_opacity_out, currentYOffset);
          objs.wineTop.style.transform = `translate3d(0, ${calcValues(values.wineTop_translateY_out, currentYOffset)}%, 0)`;
          //
          objs.wineName.style.opacity = calcValues(values.wineName_opacity_out, currentYOffset);
          objs.wineName.style.transform = `translate3d(0, ${calcValues(values.wineName_translateY_out, currentYOffset)}%, 0)`;
          objs.wineNameSubHr.style.opacity = calcValues(values.wineNameSubHr_opacity_out, currentYOffset);
          objs.wineNameSubHr.style.transform = `translate3d(0,${calcValues(values.wineNameSubHr_translateY_out, currentYOffset)}px,0)`;
          objs.wineNameSubKname.style.opacity = calcValues(values.wineNameSubKname_opacity_out, currentYOffset);
          objs.wineNameSubKname.style.transform = `translate3d(0,${calcValues(values.wineNameSubKname_translateY_out, currentYOffset)}px,0)`;
          //
          objs.wineDesc.style.opacity = calcValues(values.wineDesc_opacity_out, currentYOffset);
          objs.wineDesc.style.transform = `translate3d(0, ${calcValues(values.wineDesc_translateY_out, currentYOffset)}%, 0)`;
        }

        if (scrollRatio <= 0.7) {
          objs.wineInfo.style.opacity = calcValues(values.wineInfo_opacity_in, currentYOffset);
          for (let i = 0; i < objs.wineInfoHr.length; i++) {
            const element = objs.wineInfoHr[i];
            element.style.width = `${calcValues(values.wineInfoHr_width_in, currentYOffset - i * 50)}%`;
          }
          for (let i = 0; i < objs.wineInfoItem.length; i++) {
            const element = objs.wineInfoItem[i];
            element.style.width = `${calcValues(values.wineInfoItem_width_in, currentYOffset - i * 30)}%`;
          }
        } else {
          objs.wineInfo.style.opacity = calcValues(values.wineInfo_opacity_out, currentYOffset);
          for (let i = 0; i < objs.wineInfoHr.length; i++) {
            const element = objs.wineInfoHr[i];
            element.style.width = `${calcValues(values.wineInfoHr_width_out, currentYOffset + i * 35)}%`;
          }
          for (let i = 0; i < objs.wineInfoItem.length; i++) {
            const element = objs.wineInfoItem[i];
            element.style.width = `${calcValues(values.wineInfoItem_width_out, currentYOffset + i * 25)}%`;
          }
        }
        sceneInfo[2].objs.wineImg.style.left = `50%`;
        sceneInfo[2].objs.wineImg.style.height = `60vh`;
        break;
      case 2:
        if (scrollRatio <= 0.5) {
          objs.wineImg.style.left = `${calcValues(values.wineImg_left_in, currentYOffset)}%`;
          objs.wineImg.style.height = `${calcValues(values.wineImg_height_in, currentYOffset)}vh`;
          objs.wineReview.style.opacity = `${calcValues(values.wineReview_opacity_in, currentYOffset)}`;
          objs.wineAvg.innerText = (Math.round(calcValues(values.wineAvg_text_in, currentYOffset) * 10) / 10).toFixed(1);
          objs.wineRate.style.width = `${(Math.round(calcValues(values.wineRate_width_in, currentYOffset) * 10) / 10).toFixed(1)}vw`;
        } else if (scrollRatio > 0.5 && scrollRatio <= 0.7) {
          objs.wineAvg.innerText = objs.wineAvgValue;
          objs.wineRate.style.width = objs.wineAvgValue * 10;
          // objs.wineImg.style.marginTop = `0px`;
          objs.wineReview.style.marginTop = `0px`;
        } else {
          // objs.wineImg.style.marginTop = `${endScrollHeight - currentYOffset}px`;
          objs.wineReview.style.marginTop = `${endScrollHeight - currentYOffset}px`;
        }
        break;
      case 3:
        if (scrollRatio <= 0.5) {
        }
        break;
    }
  }

  function calcValues(values, currentYOffset) {
    let rv;
    //현재 씬(scroll-section)에서 스크롤된 범위를 비율로 구하기
    const scrollHeight = sceneInfo[currentScene].scrollHeight;
    const scrollRatio = currentYOffset / scrollHeight;

    if (values.length === 3) {
      // start ~ end 사이에 애니메이션 실행
      // values에 들어있는 비율에 따라 해당 위치의 스크롤 값 계산
      const partScrollStart = values[2].start * scrollHeight;
      const partScrollEnd = values[2].end * scrollHeight;
      const partScrollHeigth = partScrollEnd - partScrollStart;

      //viewport 내에 있을 때만 적용해주기 위한 제어문
      if (currentYOffset >= partScrollStart && currentYOffset <= partScrollEnd) {
        rv = ((currentYOffset - partScrollStart) / partScrollHeigth) * (values[1] - values[0]) + values[0];
      } else if (currentYOffset < partScrollStart) {
        rv = values[0];
      } else if (currentYOffset > partScrollEnd) {
        rv = values[1];
      }
    } else {
      rv = scrollRatio * (values[1] - values[0]) + values[0];
    }
    return rv;
  }

  function setLayout() {
    //각 스크롤 섹션의 높이scrof 세팅
    for (let i = 0; i < sceneInfo.length; i++) {
      if (sceneInfo[i].type === 'sticky') {
        sceneInfo[i].scrollHeight = sceneInfo[i].heightNum * window.innerHeight;
      } else if (sceneInfo[i].type === 'normal') {
        sceneInfo[i].scrollHeight = sceneInfo[i].objs.container.offsetHeight;
      }
      sceneInfo[i].objs.container.style.height = `${sceneInfo[i].scrollHeight}px`;
    }
    // 페이지 새로고침 대응
    yOffset = window.pageYOffset;
    let totalScrollHeight = 0;
    for (let i = 0; i < sceneInfo.length; i++) {
      totalScrollHeight += sceneInfo[i].scrollHeight;
      if (totalScrollHeight >= yOffset) {
        currentScene = i;
        break;
      }
    }
    document.body.setAttribute('id', `show-scene-${currentScene}`);

    // 애니메이션의 플리커 현상 해결을 위한 sol
    // opacity가 null로 되어 있어서 발생하는 문제로 초깃값을 0으로 세팅
    let stickys = document.querySelectorAll('.sticky-elem');
    for (let index = 0; index < stickys.length; index++) {
      stickys[index].style.opacity = 0;
    }
    endScrollHeight = sceneInfo[2].scrollHeight * 0.7;

    // 이미지 position 전환
    let documentHeight = document.body.offsetHeight;
    let vheight = window.innerHeight;
    imgStopPoint = documentHeight - vheight * 2 - vheight / 4;
    imgPotisionChanger();
  }

  function imgPotisionChanger() {
    if (yOffset <= imgStopPoint) {
      img.style.position = 'fixed';
      img.style.top = '50%';
      img.style.transform = 'translate(-50%, -50%)';
    } else {
      img.style.position = 'absolute';
      img.style.transform = 'translate(-50%, 13%)';
      img.style.top = `${imgStopPoint}px`;
    }
  }

  function scrollLoop() {
    let documentHeight = document.body.offsetHeight;
    let vheight = document.documentElement.clientHeight;
    imgStopPoint = documentHeight - vheight * 2 - vheight / 4;
    imgPotisionChanger();

    enterNewScene = false;

    prevScrollHeight = 0;
    for (let i = 0; i < currentScene; i++) {
      prevScrollHeight += sceneInfo[i].scrollHeight;
    }

    // currentScene을 증가시킴, 다음 section의 top이 윈도우 최 상단에 올 경우
    if (yOffset >= prevScrollHeight + sceneInfo[currentScene].scrollHeight) {
      enterNewScene = true;
      currentScene++;
      //body에 display:block;을 붙여 none되어있던 요소를 보여줌
      document.body.setAttribute('id', `show-scene-${currentScene}`);
    }
    // currentScene을 감소시킴, 이전 section의 bottom이 화면 상단에 1px이라도 보일 경우
    if (yOffset < prevScrollHeight) {
      enterNewScene = true;
      if (currentScene === 0) return; // 브라우저 바운스 효과로 인해 음수 값 되는 것 방지
      currentScene--;
      document.body.setAttribute('id', `show-scene-${currentScene}`);
    }

    //scene이 변경되는 순간에 발생할 수 있는 음수 값 처리
    //애니메이션 오작동을 예방하기 위해
    if (enterNewScene) return;

    playAnimation();
  }

  // 윈도우 로드시 한 번 실행하여 값 초기화
  // window.addEventListener('DOMContentLoaded', setLayout); 86 line과 비슷하나, 이미지 등 리소스 요소는 포함 안됨.
  window.addEventListener('load', setLayout);
  // 윈도우 리사이징 시 section height 재설정
  window.addEventListener('resize', setLayout);

  //스크롤 할 때 인터렉션, 익명함 수 안에 복잡한 기능 구현
  window.addEventListener('scroll', () => {
    yOffset = window.pageYOffset;
    scrollLoop();
  });

  setLayout();
};
