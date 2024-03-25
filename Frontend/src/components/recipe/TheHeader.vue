<template>
  <!-- startheader -->
  <div class="grid grid-cols-3 gap-4 mt-12 w-1/2 mx-auto">
    <div>
      <img src="@/assets/full_logo1.png" @click="goToMainPage" />
    </div>
    <div class="col-span-2 flex items-center justify-center text-3xl">
      농스트라다무스의 밥도둑
    </div>
  </div>
  <!-- end header -->
</template>

<script setup>
// 이미지 클릭 시 메인 페이지로 이동
const goToMainPage = () => {
  // 메인 페이지로 이동하는 코드 작성
  // 예시로 window.location.href를 사용하였습니다.
  window.location.href = "/";
};
</script>

<style scoped></style>
