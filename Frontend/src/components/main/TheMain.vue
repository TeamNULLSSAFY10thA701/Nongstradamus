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
          가격이 가장 많이 떨어질거에요!
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
          가격이 가장 많이 오를거에요!
        </h5>
        <div class="grid grid-cols-5 gap-4">
          <div class="h-4/5">
            <img src="../../assets/redish.png" />
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
      <Carousel :itemsToShow="3.95" :wrapAround="true" :transition="500">
        <Slide v-for="slide in 10" :key="slide">
          <div>
            <a
              class="block h-36 p-4 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 dark:bg-gray-800 dark:border-gray-700 dark:hover:bg-gray-700">
              <div class="w-3/5 mx-auto">
                <img src="../../assets/apple.png" />
              </div>
              <div class="w-full mx-auto mt-2 text-sm">
                방울토마토
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
    <!-- 내일가격 carousel -->

    <div class="mt-12 w-3/5 mx-auto mb-12">
      <Carousel :itemsToShow="3.95" :wrapAround="true" :transition="500">
        <Slide v-for="slide in 10" :key="slide">
          <div>
            <a
              class="block h-36 p-4 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 dark:bg-gray-800 dark:border-gray-700 dark:hover:bg-gray-700">
              <div class="w-3/5 mx-auto">
                <img src="../../assets/redish.png" />
              </div>
              <div class="w-full mx-auto mt-2 text-sm">
                무
              </div>
              <div class="w-full mx-auto mt-1 text-sm">
                5000원
              </div>
              <div class="w-full mx-auto mt-1 text-xs">
                20키로상자
              </div>
            </a>
          </div>
        </Slide>
      </Carousel>
    </div>
    <!-- 지난 주 가격 carousel -->
  </div>

</template>

<script setup>
import { Carousel, Pagination, Slide } from "vue3-carousel";
import "vue3-carousel/dist/carousel.css";

const name = "Autoplay";

const components = {
  Carousel,
  Slide,
  Pagination,
};

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
