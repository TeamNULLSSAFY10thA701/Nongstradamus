<template>
  <div class="all-font max-w-md mx-auto">
    <!-- startheader -->
    <div class="grid grid-cols-3 gap-4 ml-4 mr-4 mt-8">
      <div class="">
        <img
          src="@/assets/full_logo1.png"
          @mouseover="changeCursor"
          @mouseleave="resetCursor"
          @click="goToMainPage"
        />
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
          <!-- 성분 버튼 및 모달 start -->
          <div class="flex mt-4 md:mt-6">
            <!-- Modal toggle -->
            <button
              data-modal-target="modalEl"
              data-modal-toggle="modalEl"
              @click="goToModal(data, '성분')"
              class="block text-white bg-yellow-400 hover:bg-yellow-500 focus:ring-4 focus:outline-none focus:ring-yellow-300 font-medium rounded-lg text-sm mr-2 px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
              type="button"
            >
              성분
            </button>

            <!-- Main modal -->
            <div
              id="modalEl"
              tabindex="-1"
              aria-hidden="true"
              class="hidden overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 justify-center items-center w-full md:inset-0 h-[calc(100%-1rem)] max-h-full"
            >
              <div class="relative p-4 w-full max-w-2xl max-h-full">
                <!-- Modal content -->
                <div
                  class="relative bg-white rounded-lg shadow dark:bg-gray-700"
                >
                  <!-- Modal header -->
                  <div
                    class="flex items-center justify-between p-4 md:p-5 border-b rounded-t dark:border-gray-600"
                  >
                    <h3
                      class="text-xl font-semibold text-gray-900 dark:text-white"
                    >
                      재료
                    </h3>
                  </div>
                  <!-- Modal body -->
                  <div class="p-4 md:p-5 space-y-4">
                    <p
                      class="text-base leading-relaxed text-gray-500 dark:text-gray-400"
                    >
                    <div v-if="selectedData">
                      <div v-for="item, source in selectedData['성분']">
                        {{ source }} : {{ item }}
                      </div>
                    </div>
                      
                    </p>
                  </div>
                  <!-- Modal footer -->
                  <div
                    class="flex items-center justify-end p-4 md:p-5 border-t border-gray-200 rounded-b dark:border-gray-600"
                  >
                    <button
                      data-modal-hide="modalEl"
                      type="button"
                      class="text-white bg-yellow-300 hover:bg-yellow-400 focus:ring-4 focus:outline-none focus:ring-yellow-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
                    >
                      확인
                    </button>
                  </div>
                </div>
              </div>
            </div>
            <!-- 성분 버튼 및 모달 end -->

            <!-- 조리방법 버튼 및 모달 start -->

            <!-- Modal toggle -->
            <button
              data-modal-target="modalEl2"
              data-modal-toggle="modalEl2"
              @click="goToModal(data, '조리방법')"
              class="block text-white bg-yellow-500 hover:bg-yellow-600 focus:ring-4 focus:outline-none focus:ring-yellow-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
              type="button"
            >
              조리방법
            </button>

            <!-- Main modal -->
            <div
              id="modalEl2"
              tabindex="-1"
              aria-hidden="true"
              class="hidden overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 justify-center items-center w-full md:inset-0 h-[calc(100%-1rem)] max-h-full"
            >
              <div class="relative p-4 w-full max-w-2xl max-h-full">
                <!-- Modal content -->
                <div
                  class="relative bg-white rounded-lg shadow dark:bg-gray-700"
                >
                  <!-- Modal header -->
                  <div
                    class="flex items-center justify-between p-4 md:p-5 border-b rounded-t dark:border-gray-600"
                  >
                    <h3
                      class="text-xl font-semibold text-gray-900 dark:text-white"
                    >
                      조리방법
                    </h3>
                    <button
                      type="button"
                      class="text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm w-8 h-8 ms-auto inline-flex justify-center items-center dark:hover:bg-gray-600 dark:hover:text-white"
                      data-modal-hide="modalEl2"
                    >
                      <svg
                        class="w-3 h-3"
                        aria-hidden="true"
                        xmlns="http://www.w3.org/2000/svg"
                        fill="none"
                        viewBox="0 0 14 14"
                      >
                        <path
                          stroke="currentColor"
                          stroke-linecap="round"
                          stroke-linejoin="round"
                          stroke-width="2"
                          d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6"
                        />
                      </svg>
                      <span class="sr-only">Close modal</span>
                    </button>
                  </div>
                  <!-- Modal body -->
                  <div class="p-4 md:p-5 space-y-4">
                    <p
                      class="text-base leading-relaxed text-gray-500 dark:text-gray-400"
                    >
                    <div v-if="selectedData">
                      <div v-for="item in selectedData['조리법']">
                        {{ item }}
                      </div>
                    </div>
                    </p>
                  </div>
                  <!-- Modal footer -->
                  <div
                    class="flex items-center justify-end p-4 md:p-5 border-t border-gray-200 rounded-b dark:border-gray-600"
                  >
                    <button
                      data-modal-hide="modalEl2"
                      type="button"
                      class="text-white bg-yellow-300 hover:bg-yellow-400 focus:ring-4 focus:outline-none focus:ring-yellow-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
                    >
                      확인
                    </button>
                  
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- end body -->
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { Modal } from "flowbite";

const goToModal = (data, type) => {
  selectedData.value = data;
  selectedType.value = type;
};

const selectedData = ref(null);
const selectedType = ref(null);

const foodDatas = {
  1: {
    제목: "맛있는 초콜릿 케이크",
    "사진 파일명": "food1.png",
    작성자: "뤼튼1",
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
    작성자: "뤼튼2",
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
    작성자: "뤼튼3",
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
    작성자: "뤼튼4",
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

// 마우스 모양을 변경하기 위한 변수
const cursorStyle = ref("default");

// 마우스를 올렸을 때 커서 모양 변경
const changeCursor = () => {
  cursorStyle.value = "pointer";
};

// 마우스를 벗어났을 때 커서 모양 초기화
const resetCursor = () => {
  cursorStyle.value = "default";
};

// 이미지 클릭 시 메인 페이지로 이동
const goToMainPage = () => {
  // 메인 페이지로 이동하는 코드 작성
  // 예시로 window.location.href를 사용하였습니다.
  window.location.href = "/";
};
</script>

<style scoped>
.all-font {
  font-family: "HSBombaram3.0";
}
</style>
