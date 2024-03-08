<template>
  <div class="all-font max-w-md mx-auto">
    <!-- startheader -->
    <div class="grid grid-cols-3 gap-4 ml-4 mr-4 mt-8">
      <div>
        <img src="@/assets/full_logo1.png" />
      </div>
      <div class="col-span-2 flex items-center justify-center text-3xl">
        농스트라다무스의 밥도둑
      </div>
    </div>
    <!-- end header -->

    <!-- start body -->
    <div v-for="data in foodDatas" :key="data" class="m-12 photo">
      <div
        class="w-full max-w-sm bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700"
      >
        <div class="flex flex-col items-center py-10">
          <img
            class="w-24 h-24 mb-3 rounded-full shadow-lg"
            :src="`../../src/assets/${data['사진 파일명']}`"
            alt="Bonnie image"
          />
          <h5 class="mb-1 text-xl font-medium text-gray-900 dark:text-white">
            {{ data["제목"] }}
          </h5>
          <span class="text-sm text-gray-500 dark:text-gray-400">{{
            data["작성자"]
          }}</span>
          <div class="flex mt-4 md:mt-6">
            <a
              href="#"
              class="inline-flex items-center px-4 py-2 text-sm font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
              >성분</a
            >
            <a
              href="#"
              class="py-2 px-4 ms-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-100 dark:focus:ring-gray-700 dark:bg-gray-800 dark:text-gray-400 dark:border-gray-600 dark:hover:text-white dark:hover:bg-gray-700"
              >조리방법</a
            >
          </div>
        </div>
      </div>
    </div>
    <!-- end body -->
  </div>
</template>

<script setup>
import { ref } from "vue";

const foodDatas = {
  1: {
    제목: "맛있는 초콜릿 케이크",
    "사진 파일명": "food1.png",
    작성자: "뤼튼",
    성분: {
      밀가루: "200g",
      설탕: "150g",
      "코코아 파우더": "50g",
      "베이킹 파우더": "10g",
      소금: "약간",
      우유: "200ml",
      버터: "100g",
      달걀: "2개",
      "바닐라 추출물": "1작은 숟가락",
    },
    조리법: [
      "1. 밀가루, 설탕, 코코아 파우더, 베이킹 파우더, 소금을 섞어주세요.",
      "2. 다른 그릇에 우유, 버터, 달걀, 바닐라 추출물을 섞고, 거품기로 잘 풀어주세요.",
      "3. 1단계에서 만든 건강한 재료를 2단계에 섞고, 부드러운 반죽을 만드세요.",
      "4. 반죽을 케이크 팬에 부어주세요.",
      "5. 180도로 예열된 오븐에서 25-30분 동안 구워주세요.",
      "6. 완전히 식혀서 케이크를 장식하고, 맛있게 즐기세요!",
    ],
  },
  2: {
    제목: "상큼한 과일 샐러드",
    "사진 파일명": "food2.png",
    작성자: "뤼튼",
    성분: {
      사과: "2개",
      바나나: "2개",
      딸기: "200g",
      포도: "150g",
      "오렌지 주스": "100ml",
      "레몬 주스": "1큰 숟가락",
      꿀: "2큰 숟가락",
    },
    조리법: [
      "1. 사과와 바나나를 껍질을 벗기고 썰어주세요.",
      "2. 딸기와 포도를 깨끗하게 씻은 후, 적절한 크기로 썬 후릇에 담아주세요.",
      "3. 오렌지 주스와 레몬 주스, 꿀을 잘 섞어 소스를 만들어주세요.",
      "4. 과일에 소스를 부어 섞어주세요.",
      "5. 상큼한 과일 샐러드를 그릇에 담아 맛있게 즐기세요!",
    ],
  },
  3: {
    제목: "담백한 샐러드",
    "사진 파일명": "food3.png",
    작성자: "뤼튼",
    성분: {
      양상추: "1개",
      토마토: "2개",
      오이: "1개",
      당근: "1개",
      "올리브 오일": "2큰 숟가락",
      "레몬 주스": "1큰 숟가락",
      소금: "약간",
      후추: "약간",
    },
    조리법: [
      "1. 양상추, 토마토, 오이, 당근을 깨끗하게 씻어 썰어주세요.",
      "2. 그릇에 양상추, 토마토, 오이, 당근을 담고, 올리브 오일, 레몬 주스, 소금, 후추를 넣어주세요.",
      "3. 잘 섞어 담백한 샐러드를 맛있게 즐기세요!",
    ],
  },
  4: {
    제목: "가볍고 건강한 샌드위치",
    "사진 파일명": "food4.png",
    작성자: "뤼튼",
    성분: {
      식빵: "2장",
      "닭 가슴살": "100g",
      양파: "1/4개",
      토마토: "1/2개",
      양상추: "적당량",
      마요네즈: "1큰 숟가락",
      머스타드: "1작은 숟가락",
      소금: "약간",
      후추: "약간",
    },
    조리법: [
      "1. 닭 가슴살을 삶아서 잘게 썰어주세요.",
      "2. 양파와 토마토를 얇게 썰어주세요.",
      "3. 식빵에 마요네즈와 머스타드를 발라주세요.",
      "4. 양상추, 닭 가슴살, 양파, 토마토를 식빵에 올려주세요.",
      "5. 소금과 후추를 뿌리고, 다른 식빵으로 덮어주세요.",
      "6. 가볍고 건강한 샌드위치를 맛있게 즐기세요!",
    ],
  },
};

// 사진 파일명 추출
</script>

<style scoped>
.all-font {
  font-family: "HSBombaram3.0";
}
</style>
