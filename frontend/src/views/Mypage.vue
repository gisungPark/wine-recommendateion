<template>
  <!-- fram -> container -> wrap -->
  <div class="fram">
    <div class="item" style="background-color: #e84545">
      <div class="left-wrap">
        <div class="nav-title">
          <span>Mypage</span>
        </div>
        <div class="nav-btn">
          <button class="nav-btn-item1">스크랩한 와인</button>
          <button class="nav-btn-item2">내가 남긴 리뷰</button>
          <button class="nav-btn-item3">내 취향 통계</button>
        </div>
      </div>
    </div>
    <div class="item" style="background-color: #2b2e4a">
      <div class="right-wrap">
        <div class="content">
          <div class="imgBox">
            <img
              class="profile"
              src="https://newsimg.hankookilbo.com/cms/articlerelease/2020/10/02/f18cb31b-168f-4c4e-8fac-86a9864350ef.jpg"
              alt="프로필 이미지"
            />
          </div>
          <div class="content-item">
            <div
              style="
                margin: 15px;
                background-color: black;
                height: 100px;
                width: 98%;
              "
            >
              aaaaa
            </div>
            <div
              style="
                margin: 15px;
                background-color: black;
                height: 100px;
                width: 98%;
              "
            >
              aaaaa
            </div>
            <div
              style="
                margin: 15px;
                background-color: black;
                height: 100px;
                width: 98%;
              "
            >
              aaaaa
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Mypage",
};
</script>

<style scoped>
.fram {
  height: 100vh;
  width: 100vw;
  background-color: white;
  display: flex;
  z-index: 0;
}
.item:nth-child(1) {
  flex: 2 1 0;
  display: flex;
  flex-direction: column;
}
.item:nth-child(2) {
  flex-grow: 1;
  flex: 14 1 0;
}

.left-wrap {
  display: flex;
  flex-direction: column;
  flex-basis: auto;
  /* transform: rotate(-90deg); */
}

.nav-title {
  font-size: 40px;
  font-weight: bold;
  margin: 5px;
  position: absolute;
  bottom: 90px;
  left: 30px;
  transform: rotate(-90deg);
}
.nav-btn {
  display: flex;
  flex-flow: row nowrap;
}
.nav-btn > button {
  border: 1px solid black;
  background: #903749;
  border-radius: 20px;
  font-weight: bold;
  transform: rotate(-90deg);
  /* width: 150px; */
}
.nav-btn-item1 {
  position: absolute;
  width: 120px;
  bottom: 70px;
  left: 100px;
}
.nav-btn-item2 {
  position: absolute;
  width: 120px;
  bottom: 200px;
  left: 100px;
}
.nav-btn-item3 {
  position: absolute;
  width: 120px;
  bottom: 330px;
  left: 100px;
}

.right-wrap {
  display: flex;
  justify-content: center;
}
.content {
  position: relative;
  top: 150px;
  width: 90%;
  height: 70vh;
  background-color: white;
  border-radius: 2em;
}
.imgBox {
  position: absolute;
  top: -90px;
  left: 25px;
  width: 180px;
  height: 180px;
  border-radius: 70%;
  overflow: hidden;
}
.profile {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.content-item {
  position: relative;
  top: 100px;
}
</style>