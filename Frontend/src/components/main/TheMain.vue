<template>
  <div class="all-font max-w-md mx-auto">
    <div class="grid grid-cols-3 gap-4 ml-4 mr-4 mt-8">
      <div>
        <img src="../../assets/full_logo1.png" />
      </div>
      <div class="title col-span-2 flex items-center justify-center text-3xl">
        오늘의 꿀 품목
      </div>
    </div>
    <!-- header -->
    <div class="flex items-center justify-center mt-12">
      <a
        class="bestChoice block max-w-sm p-6 border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">
        <h5 class="mb-2 text-2xl tracking-tight text-white">
          가격이 가장 많이 떨어졌어요!
        </h5>
        <div class="grid grid-cols-5 gap-4">
          <div class="col-span-3 text-center text-3xl text-white mt-6 mr-4">
            품목
          </div>
          <div class="col-span-2 text-center price-font font-bold text-xl rounded-lg bg-white border mt-8 mr-2">
            가격
          </div>
        </div>
      </a>
    </div>
    <!-- 가격이 가장 많이 떨어진 품목 조회 -->
    <div class="flex items-center justify-center mt-8">
      <a
        class="predictGood block max-w-sm p-6 bg-indigo-400 border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">
        <h5 class="custom mb-2 tracking-tight text-white">
          가격이 가장 많이 떨어질거에요!
        </h5>
        <div class="grid grid-cols-5 gap-4">
          <div class="col-span-3 text-center text-3xl text-white mt-6 mr-4">
            품목
          </div>
          <div class="col-span-2 text-center price-font font-bold text-xl rounded-lg bg-white border mt-8 mr-2">
            가격
          </div>
        </div>
      </a>
    </div>
    <!-- 가격이 가장 많이 떨어질 것 같은 품목 조회 -->
    <div class="flex items-center justify-center mt-8">
      <a href="/recipe"
        class="todayRecipe block max-w-sm p-6 bg-indigo-400 border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">
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
    <div class="mt-12">
      <Carousel :itemsToShow="3.95" :wrapAround="true" :transition="500">
        <Slide v-for="slide in 10" :key="slide">
          <div>
            <a
              class="block max-w-sm p-6 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 dark:bg-gray-800 dark:border-gray-700 dark:hover:bg-gray-700">
              <h5 class="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white">
                {{ slide }}
              </h5>
              <p class="text-sm text-gray-700 dark:text-gray-400">
                현재 가격
              </p>
              <a href="/priceDetail">
                <button type="button"
                  class="button px-3 py-2 font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">더보기</button>
              </a>

            </a>
          </div>
        </Slide>
      </Carousel>
    </div>
    <!-- 현재가격 carousel -->

    <div class="mt-12">
      <Carousel :itemsToShow="3.95" :wrapAround="true" :transition="500">
        <Slide v-for="slide in 10" :key="slide">
          <div>
            <a
              class="block max-w-sm p-6 bg-white border border-gray-200 rounded-lg shadow hover:bg-gray-100 dark:bg-gray-800 dark:border-gray-700 dark:hover:bg-gray-700">
              <h5 class="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white">
                {{ slide }}
              </h5>
              <p class="text-sm text-gray-700 dark:text-gray-400">
                예측 가격
              </p>
            </a>
          </div>
        </Slide>
      </Carousel>
    </div>
    <!-- 예측 가격 carousel -->
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

/* .carousel__item{
} */
.custom {
  font-size: 22.3px;
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

.button {
  font-size: 10px;
}
</style>
