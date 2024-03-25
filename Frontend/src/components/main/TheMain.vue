<template>
  <div class="all-font max-w-5xl mx-auto">
    <div class="grid grid-cols-5 gap-4 ml-4 mr-4 mt-8">
      <div class="w-4/5 mx-auto col-start-2">
        <img src="/src/assets/full_logo1.png" />
      </div>
      <div class="title col-start-3 col-span-3 flex items-center">
        오늘의 꿀 품목
      </div>
    </div>
    <!-- header -->

    <div class="w-2/5 mx-auto p-4 mt-8 text-sm text-red-800 rounded-lg bg-red-50 dark:bg-gray-800 dark:text-red-400"
      role="alert" v-if="isMaintenanceTime()">
      <span class="font-medium">점검 공지</span> 점검시간(오전12시~오전1시30분)에는 서비스 이용이 원활하지 않습니다.
    </div>
    <!-- 서비스 점검 시간을 알려주는 alert bar -->

    <div class="flex items-center justify-center mt-12">
      <div
        class="bestChoice block w-2/5 p-6 border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700"
        @mouseover="BigImage()" @mouseleave="SmallImage()">
        <h5 class="mb-2 text-2xl tracking-tight text-white">
          내일 가격이 가장 많이 떨어질거에요!
        </h5>
        <div class="grid grid-cols-5 gap-4">
          <div class="h-4/5">
            <img :src="getImageUrl(BiggestDroppedPrice.data.nickname)" :class=bindClass />
          </div>
          <div class="col-span-2 text-2xl text-white flex justify-center items-center">
            {{ BiggestDroppedPrice.data.name }}
          </div>
          <div class="col-span-2 rounded-lg bg-white border flex justify-center items-center" @mouseover="showOne()"
            @mouseleave="resetPrice()">
            <div class="font-bold text-2xl text-center">
              {{ showDroppedPrice }}
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- 가격이 가장 많이 떨어진 품목 조회 -->

    <div class="flex items-center justify-center mt-8">
      <div
        class="bestChoice block w-2/5 p-6 border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700"
        @mouseover="BigImage2()" @mouseleave="SmallImage2()">
        <h5 class="mb-2 text-2xl tracking-tight text-white">
          내일 가격이 가장 많이 오를거에요!
        </h5>
        <div class="grid grid-cols-5 gap-4">
          <div class="h-4/5">
            <img :src=getImageUrl(BiggestIncreasedPrice.data.nickname) :class=bindClass2 />
          </div>
          <div class="col-span-2 text-2xl text-white flex justify-center items-center">
            {{ BiggestIncreasedPrice.data.name }}
          </div>
          <div class="col-span-2 rounded-lg bg-white border flex justify-center items-center"
            @mouseover="showOneIncrease()" @mouseleave="resetPriceIncrease()">
            <div class="font-bold text-2xl text-center">
              {{ showIncreasedPrice }}
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
            <img src="/src/assets/recipe.png" />
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
            <img src="/src/assets/crystal ball.png" />
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
              <a class="block h-48 p-4 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 dark:bg-gray-800 dark:border-gray-700 dark:hover:bg-gray-700"
                @mouseover="BigImage3(slide)" @mouseleave="SmallImage3(slide)">
                <div class="w-3/5 mx-auto">
                  <img :src="getImageUrl(slide.nickname)"
                    :class="thisIsHoveredCard == slide.name ? bindClass3 : bindClass4" />
                </div>
                <div class="w-full mx-auto mt-2 text-sm">
                  품목: {{ slide.name }}
                </div>
                <div class="w-full mx-auto mt-1 text-sm">
                  단위: {{ slide.unit }}
                </div>
                <div class="w-full mx-auto mt-1 text-sm">
                  가격: {{ slide.price }}원
                </div>
                <div class="w-full mx-auto mt-1 text-xs">
                  전날대비 {{ (slide.ratio >= 0 ? slide.ratio : -slide.ratio) + (slide.ratio >= 0 ? '%상승' : '%하락') }}
                </div>
              </a>
            </div>
          </Slide>
        </Carousel>
      </div>
      <!-- 지난주 가격 캐러셀 -->

      <div class="mt-4" v-if="toDayState">
        <Carousel :itemsToShow="3.95" :wrapAround="true" :transition="500">
          <Slide v-for="slide in toDayPrice.data" :key="slide">
            <div>
              <a class="block h-48 p-4 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 dark:bg-gray-800 dark:border-gray-700 dark:hover:bg-gray-700"
                @mouseover="BigImage4(slide)" @mouseleave="SmallImage4(slide)">
                <div class="w-3/5 mx-auto">
                  <img :src="getImageUrl(slide.nickname)"
                    :class="thisIsHoveredCard == slide.name ? bindClass3 : bindClass4" />
                </div>
                <div class="w-full mx-auto mt-2 text-sm">
                  품목: {{ slide.name }}
                </div>
                <div class="w-full mx-auto mt-1 text-sm">
                  단위: {{ slide.unit }}
                </div>
                <div class="w-full mx-auto mt-1 text-sm">
                  가격: {{ slide.price }}원
                </div>
                <div class="w-full mx-auto mt-1 text-xs">
                  전날대비 {{ (slide.ratio >= 0 ? slide.ratio : -slide.ratio) + (slide.ratio >= 0 ? '%상승' : '%하락') }}
                </div>
              </a>
            </div>
          </Slide>
        </Carousel>
      </div>
      <!-- 오늘 가격 예측 캐러셀 -->

      <div class="mt-4" v-if="tommorowState">
        <Carousel :itemsToShow="3.95" :wrapAround="true" :transition="500">
          <Slide v-for="slide in tomorrowPrice.data" :key="slide">
            <div>
              <a class="block h-48 p-4 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 dark:bg-gray-800 dark:border-gray-700 dark:hover:bg-gray-700"
                @mouseover="BigImage5(slide)" @mouseleave="SmallImage5(slide)">
                <div class="w-3/5 mx-auto">
                  <img :src="getImageUrl(slide.nickname)"
                    :class="thisIsHoveredCard == slide.name ? bindClass3 : bindClass4" />
                </div>
                <div class="w-full mx-auto mt-2 text-sm">
                  품목: {{ slide.name }}
                </div>
                <div class="w-full mx-auto mt-1 text-sm">
                  단위: {{ slide.unit }}
                </div>
                <div class="w-full mx-auto mt-1 text-sm">
                  가격: {{ slide.price }}원
                </div>
                <div class="w-full mx-auto mt-1 text-xs">
                  전날대비 {{ (slide.ratio >= 0 ? slide.ratio : -slide.ratio) + (slide.ratio >= 0 ? '%상승' : '%하락') }}
                </div>
              </a>
            </div>
          </Slide>
        </Carousel>
      </div>
    </div>
    <!-- 내일가격 캐러셀 -->

  </div>

</template>

<script setup>
import { Carousel, Pagination, Slide } from "vue3-carousel";
import "vue3-carousel/dist/carousel.css";
import { ref, onMounted } from 'vue';
import { getBiggestDropped, getBiggestIncreased, getLastWeekPrices, getToDayPrices, getTommorrowPrices } from "@/api/mainhome";
import { random } from "lodash";

const isMaintenanceTime = () => {
  const now = new Date();
  const startMaintenance = new Date(now.getFullYear(), now.getMonth(), now.getDate(), 0, 0, 0); // 12:00 AM
  const endMaintenance = new Date(now.getFullYear(), now.getMonth(), now.getDate(), 1, 30, 0); // 01:30 AM
  return now >= startMaintenance && now <= endMaintenance;
}
//시스템 점검시간을 확인하여 T/F로 알려주는 메서드.

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


const bindClass3 = ref('scale-125')

const bindClass4 = ref('')

const thisIsHoveredCard = ref('')



onMounted(() => {
  callLastWeekPrices()
  callToDayPrices()
  callTomorrowPrices()
  callBiggestDroppedPrice()
  callBiggestIncreasedPrice()
})

const getImageUrl = (nickname) => {
  return `/src/assets/${nickname}.png`;
};
//사진을 가져올 메서드

// -----------------------------------------------------------------------공통---------------------------------------------------------------


const lastWeekPrice = ref({
  data: [
  ],
  msg: '',
  code: '',
})
//지난 주 가격정보를 담을 변수


const callLastWeekPrices = () => {
  getLastWeekPrices((data) => {
    lastWeekPrice.value.data = data.data.data
    lastWeekPrice.value.msg = data.data.msg
    lastWeekPrice.value.code = data.data.code
    // console.log(lastWeekPrice.value)`
    // console.log(lastWeekPrice.value.data[0].nickname)
  },
    (error) => {
      console.error(error)
    }
  )
}
//지난 주 가격정보를 불러오는 메서드

function BigImage3(slide) {
  thisIsHoveredCard.value = slide.name
}

function SmallImage3() {
  thisIsHoveredCard.value = ''
}

// -----------------------------------------------------------------------지난주---------------------------------------------------------------

const toDayPrice = ref({
  data: [
  ],
  msg: '',
  code: '',
})
//오늘 가격정보를 담을 변수.


const callToDayPrices = () => {
  getToDayPrices((data) => {
    toDayPrice.value.data = data.data.data
    toDayPrice.value.msg = data.data.msg
    toDayPrice.value.code = data.data.code
  },
    (error) => {
      console.error(error)
    }
  )
}
//오늘 가격정보를 불러올 메서드

function BigImage4(slide) {
  thisIsHoveredCard.value = slide.name
}

function SmallImage4() {
  thisIsHoveredCard.value = ''
}

// -----------------------------------------------------------------------오늘---------------------------------------------------------------

const tomorrowPrice = ref({
  data: [
  ],
  msg: '',
  code: '',
})
//내일 가격정보를 담을 변수.


const callTomorrowPrices = () => {
  getTommorrowPrices((data) => {
    tomorrowPrice.value.data = data.data.data
    tomorrowPrice.value.msg = data.data.msg
    tomorrowPrice.value.code = data.data.code
  },
    (error) => {
      console.error(error)
    }
  )
}
//내일 가격정보를 불러올 메서드

function BigImage5(slide) {
  thisIsHoveredCard.value = slide.name
}

function SmallImage5() {
  thisIsHoveredCard.value = ''
}
// -----------------------------------------------------------------------내일---------------------------------------------------------------

const BiggestDroppedPrice = ref({
  data: '',
  msg: '',
  code: '',
})
//내일 가장 가격하락폭이 큰 품목을 담을 변수.

const callBiggestDroppedPrice = () => {
  getBiggestDropped((data) => {
    BiggestDroppedPrice.value.data = data.data.data
    BiggestDroppedPrice.value.msg = data.data.msg
    BiggestDroppedPrice.value.code = data.data.code
    showDroppedPrice.value = `${data.data.data.price}원`
  },
    (error) => {
      console.error(error)
    }
  )
}
//내일 가장 가격하락폭이 큰 품목을 불러올 메서드.

const showDroppedPrice = ref('');
//보여줄 값 변수

function showOne() {
  if (BiggestDroppedPrice.value.data.ratio > 0) {
    showDroppedPrice.value = `${BiggestDroppedPrice.value.data.ratio}%상승`
  }
  else {
    showDroppedPrice.value = `${BiggestDroppedPrice.value.data.ratio * -1}%하락`
  }
}
//호버 시, 보여줄 값을 변환

function resetPrice() {
  showDroppedPrice.value = `${BiggestDroppedPrice.value.data.price}원`
}
//호버가 끝나면, 다시 원래값으로 변환

const bindClass = ref('')

function BigImage() {
  bindClass.value = "scale-125"
}

function SmallImage() {
  bindClass.value = ""
}



// -----------------------------------------------------------------------가장가격하락품목---------------------------------------------------------------

const BiggestIncreasedPrice = ref({
  data: '',
  msg: '',
  code: '',
})

const callBiggestIncreasedPrice = () => {
  getBiggestIncreased((data) => {
    BiggestIncreasedPrice.value.data = data.data.data
    BiggestIncreasedPrice.value.msg = data.data.msg
    BiggestIncreasedPrice.value.code = data.data.code
    showIncreasedPrice.value = `${data.data.data.price}원`
  },
    (error) => {
      console.error(error)
    }
  )
}

const showIncreasedPrice = ref('');
//보여줄 값 변수

function showOneIncrease() {
  if (BiggestIncreasedPrice.value.data.ratio > 0) {
    showIncreasedPrice.value = `${BiggestIncreasedPrice.value.data.ratio}%상승`
  }
  else {
    showIncreasedPrice.value = `${BiggestIncreasedPrice.value.data.ratio * -1}%하락`
  }
}
//호버 시, 보여줄 값을 변환

function resetPriceIncrease() {
  showIncreasedPrice.value = `${BiggestIncreasedPrice.value.data.price}원`
}
//호버가 끝나면, 다시 원래값으로 변환

const bindClass2 = ref('')

function BigImage2() {
  bindClass2.value = "scale-125"
}

function SmallImage2() {
  bindClass2.value = ""
}

// -----------------------------------------------------------------------가장가격상승품목---------------------------------------------------------------

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

.bestChoice:hover {
  background-color: #AD977D;
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
