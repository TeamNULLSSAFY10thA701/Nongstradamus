<template>
  <div class="all-font max-w-5xl mx-auto">
    <div class="grid grid-cols-5 gap-4 ml-4 mr-4 mt-8">
      <div class="col-start-2">
        <img src="../../assets/full_logo1.png" />
      </div>
      <div class="title col-start-3 col-span-3 flex items-center">
        오늘의 꿀 품목
      </div>
    </div>
    <!-- header -->
    <div class="flex items-center justify-center mt-12">
      <div
        class="bestChoice block w-2/5 p-6 border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">
        <h5 class="mb-2 text-2xl tracking-tight text-white">
          내일 가격이 가장 많이 떨어질거에요!
        </h5>
        <div class="grid grid-cols-5 gap-4 ">
          <div class="h-4/5">
            <img src="../../assets/apple.png" />
          </div>
          <div class="col-span-2 text-2xl text-white flex justify-center items-center">
            방울토마토
          </div>
          <div class="col-span-2 rounded-lg bg-white border flex justify-center items-center">
            <div class="font-bold text-2xl text-center">
              2500원
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- 가격이 가장 많이 떨어진 품목 조회 -->
    <div class="flex items-center justify-center mt-8">
      <div
        class="bestChoice block w-2/5 p-6 border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">
        <h5 class="mb-2 text-2xl tracking-tight text-white">
          내일 가격이 가장 많이 오를거에요!
        </h5>
        <div class="grid grid-cols-5 gap-4">
          <div class="h-4/5">
            <img src="../../assets/moo.png" />
          </div>
          <div class="col-span-2 text-2xl text-white flex justify-center items-center">
            무
          </div>
          <div class="col-span-2 rounded-lg bg-white border flex justify-center items-center">
            <div class="font-bold text-2xl text-center">
              5000원
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- 가격이 가장 많이 오를것 같은 품목 조회 -->
    <div class="flex items-center justify-center mt-8">
      <a href="/recipe"
        class="todayRecipe block w-2/5 p-6 bg-indigo-400 border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">
        <div class="grid grid-cols-3 gap-4">
          <div class="flex items-center justify-center">
            <img src="../../assets/recipe.png" />
          </div>
          <div class="col-span-2 tracking-tight text-white flex items-center justify-center text-2xl">
            오늘의 추천 레시피
          </div>
        </div>
      </a>
    </div>
    <!-- 오늘의 추천 레시피 이동 -->
    <div class="flex items-center justify-center mt-8">
      <a href="/pricedetail"
        class="todayRecipe block w-2/5 p-6 bg-indigo-400 border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">
        <div class="grid grid-cols-3 gap-4">
          <div class="flex items-center justify-center">
            <img src="../../assets/crystal ball.png" />
          </div>
          <div class="col-span-2 tracking-tight text-white flex items-center justify-center text-2xl">
            예측가격 보러 가기
          </div>
        </div>
      </a>
    </div>
    <!--예측 가격 세부 페이지 이동-->

    <div class="mt-12 w-3/5 mx-auto">
      <button @click="toggleDropdown" id="dropdownDefaultButton" data-dropdown-toggle="dropdown"
        class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center inline-flex items-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
        type="button">
        {{ nowInfo }}
        <svg class="w-2.5 h-2.5 ms-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none"
          viewBox="0 0 10 6">
          <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
            d="m1 1 4 4 4-4" />
        </svg>
      </button>

      <div id="dropdown" :class="{ 'hidden': !isDropdownOpen }"
        class="z-10 bg-white divide-y divide-gray-100 rounded-lg shadow w-44 dark:bg-gray-700">
        <ul class="py-2 text-sm text-gray-700 dark:text-gray-200" aria-labelledby="dropdownDefaultButton">
          <li>
            <div class="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white"
              @click="transferLastWeekState">지난 주</div>
          </li>
          <li>
            <div class="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white"
              @click="transferToDayState">오늘</div>
          </li>
          <li>
            <div class="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white"
              @click="trnasferTommorowState">내일</div>
          </li>
        </ul>
      </div>

      <div class="mt-4" v-if="lastWeekState">
        <Carousel :itemsToShow="3.95" :wrapAround="true" :transition="500">
          <Slide v-for="slide in lastWeekPrice.data" :key="slide">
            <div>
              <a
                class="block h-36 p-4 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 dark:bg-gray-800 dark:border-gray-700 dark:hover:bg-gray-700">
                <div class="w-3/5 mx-auto">
                  <img :src="getImageUrl(slide.nickname)" />
                </div>
                <div class="w-full mx-auto mt-2 text-sm">
                  {{ slide.name }}
                </div>
                <div class="w-full mx-auto mt-1 text-sm">
                  {{ slide.price }}원입니다.
                </div>
                <div class="w-full mx-auto mt-1 text-xs">
                  {{ (slide.ratio >= 0 ? slide.ratio : -slide.ratio) + (slide.ratio >= 0 ? '%상승' : '%하락') }}
                </div>
              </a>
            </div>
          </Slide>
        </Carousel>
      </div>
      <div class="mt-4" v-if="toDayState">
        <Carousel :itemsToShow="3.95" :wrapAround="true" :transition="500">
          <Slide v-for="slide in 10" :key="slide">
            <div>
              <a
                class="block h-36 p-4 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 dark:bg-gray-800 dark:border-gray-700 dark:hover:bg-gray-700">
                <div class="w-3/5 mx-auto">
                  <img src="../../assets/apple.png" />
                </div>
                <div class="w-full mx-auto mt-2 text-sm">
                  사과
                </div>
                <div class="w-full mx-auto mt-1 text-sm">
                  2500원
                </div>
                <div class="w-full mx-auto mt-1 text-xs">
                  100g
                </div>
              </a>
            </div>
          </Slide>
        </Carousel>
      </div>
      <div class="mt-4" v-if="tommorowState">
        <Carousel :itemsToShow="3.95" :wrapAround="true" :transition="500">
          <Slide v-for="slide in 10" :key="slide">
            <div>
              <a
                class="block h-36 p-4 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 dark:bg-gray-800 dark:border-gray-700 dark:hover:bg-gray-700">
                <div class="w-3/5 mx-auto">
                  <img src="../../assets/sweetPotato.png" />
                </div>
                <div class="w-full mx-auto mt-2 text-sm">
                  고구마
                </div>
                <div class="w-full mx-auto mt-1 text-sm">
                  2500원
                </div>
                <div class="w-full mx-auto mt-1 text-xs">
                  100g
                </div>
              </a>
            </div>
          </Slide>
        </Carousel>
      </div>
    </div>
    <!-- 내일가격 carousel -->
  </div>

</template>

<script setup>
import { Carousel, Pagination, Slide } from "vue3-carousel";
import "vue3-carousel/dist/carousel.css";
import { ref, onMounted } from 'vue';
import { getBiggestDropped, getBiggestIncreased, getLastWeekPrices, getToDayPrices, getTommorrowPrices } from "@/api/mainhome";
import { random } from "lodash";

const name = "Autoplay";
//무한 캐러셀을 가능하게 만들어줌.

const components = {
  Carousel,
  Slide,
  Pagination,
};
//캐러셀 관련 변수(건들지마셈)

const isDropdownOpen = ref(false);

const toggleDropdown = () => {
  isDropdownOpen.value = !isDropdownOpen.value;
};

//드롭다운 스위치 겸 스위치 기능  

const lastWeekState = ref(false)

const toDayState = ref(false)

const tommorowState = ref(false)

//현재 드롭다운 클릭 상태를 나타내는 변수

const transferLastWeekState = () => {
  lastWeekState.value = true
  toDayState.value = false
  tommorowState.value = false
  nowInfo.value = '지난주'
}

const transferToDayState = () => {
  lastWeekState.value = false
  toDayState.value = true
  tommorowState.value = false
  nowInfo.value = '오늘'
}

const trnasferTommorowState = () => {
  lastWeekState.value = false
  toDayState.value = false
  tommorowState.value = true
  nowInfo.value = '내일'
}
//드롭다운 상태를 변환시키는 매서드

const nowInfo = ref('날짜를 선택해주세요')


onMounted(() => {
  callLastWeekPrices()
})

const lastWeekPrice = ref({
  data: [
  ],
  msg: '',
  code: '',
})
//지난 주 가격정보를 담을 변수

const getImageUrl = (nickname) => {
  return `src/assets/${nickname}.png`;
};
//지난 주 사진을 가져올 변수.

const callLastWeekPrices = () => {
  getLastWeekPrices((data) => {
    lastWeekPrice.value.data = data.data.data
    lastWeekPrice.value.msg = data.data.msg
    lastWeekPrice.value.code = data.data.code
    // console.log(lastWeekPrice.value)`
    // console.log(lastWeekPrice.value.data[0].nickname)
  })
}

//지난 주 가격정보를 불러오는 메서드



</script>

<style scoped>
@media (max-width: 1024px) {

  /* 창 폭이 845px 이하로 줄어들 때 적용될 스타일 */
  .all-font {
    overflow-x: hidden;
    /* 가로 스크롤을 숨김 */
    width: 1024px !important;
    /* 최소 폭을 845px로 설정 */
  }
}

.button {
  font-size: 10px;
}

.title {
  font-size: 56px;
}

.custom {
  font-size: 22.3px;
}

.bestChoice {
  background-color: #C6AC8F;
}

.predictGood {
  background-color: #927E67;
}

.todayRecipe {
  background-color: #5E503F;
}

.all-font {
  font-family: "HSBombaram3.0";
}

.price-font {
  font-family: sans-serif;
}

.carousel__slide {
  padding: 5px;
}

.carousel__viewport {
  perspective: 2000px;
}

.carousel__track {
  transform-style: preserve-3d;
}

.carousel__slide--sliding {
  transition: 0.5s;
}

.carousel__slide {
  opacity: 0.9;
  transform: rotateY(-20deg) scale(0.9);
}

.carousel__slide--active~.carousel__slide {
  transform: rotateY(20deg) scale(0.9);
}

.carousel__slide--prev {
  opacity: 1;
  transform: rotateY(-10deg) scale(0.95);
}

.carousel__slide--next {
  opacity: 1;
  transform: rotateY(10deg) scale(0.95);
}

.carousel__slide--active {
  opacity: 1;
  transform: rotateY(0) scale(1.1);
}
</style>
