<!-- TheRecipeBody.vue -->

<template>
  <div class="searchbar">
 
  <form class="max-w-lg mx-auto">
    <div class="flex">
        <label for="search-dropdown" class="mb-2 text-sm font-medium text-gray-900 sr-only dark:text-white"></label>
        <button id="dropdown-button" data-dropdown-toggle="categories" class="flex-shrink-0 z-10 inline-flex items-center py-2.5 px-4 text-sm font-medium text-center text-gray-900 bg-gray-100 border border-gray-300 rounded-s-lg hover:bg-gray-200 focus:ring-4 focus:outline-none focus:ring-gray-100 dark:bg-gray-700 dark:hover:bg-gray-600 dark:focus:ring-gray-700 dark:text-white dark:border-gray-600" type="button">All categories <svg class="w-2.5 h-2.5 ms-2.5" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 10 6">
        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m1 1 4 4 4-4"/>
            </svg>
        </button>
        <div id="categories" class="z-10 hidden bg-white divide-y divide-gray-100 rounded-lg shadow w-44 dark:bg-gray-700">
            <ul class="py-2 text-sm text-gray-700 dark:text-gray-200" aria-labelledby="dropdown-button">
            <li>
                <button type="button" class="inline-flex w-full px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">제목</button>
            </li>
            <li>
                <button type="button" class="inline-flex w-full px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">제작자</button>
            </li>
            <li>
                <button type="button" class="inline-flex w-full px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">성분</button>
            </li>
            <li>
                <button type="button" class="inline-flex w-full px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white">조리방법</button>
            </li>
            </ul>
        </div>
        <div class="relative w-full">
            <input v-model.lazy="query" type="search" id="search-dropdown" class="block p-2.5 w-full z-20 text-sm text-gray-900 bg-gray-50 rounded-e-lg border-s-gray-50 border-s-2 border border-gray-300 focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-s-gray-700  dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:border-blue-500" placeholder="Search" required />
            <button @click="filterRecipes($event)" type="submit" class="absolute top-0 end-0 p-2.5 text-sm font-medium h-full text-white bg-blue-700 rounded-e-lg border border-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
                <svg class="w-4 h-4" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 20">
                    <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m19 19-4-4m0-7A7 7 0 1 1 1 8a7 7 0 0 1 14 0Z"/>
                </svg>
                <span class="sr-only">Search</span>
            </button>
        </div>
    </div>
  </form>

</div>
    <div class="recipe-list">
      <div v-for="data in filteredRecipe" :key="data.id" class="m-12 photo">
        <!-- 각 레시피 카드 -->
        <div
          class="w-full max-w-sm bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700"
        >
          <!-- 레시피 정보 표시 -->
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
              <!-- 성분 버튼 -->
              <button
                data-modal-target="modalEl"
                data-modal-toggle="modalEl"
                @click="goToModal(data, '성분')"
                class="block text-white bg-yellow-400 hover:bg-yellow-500 focus:ring-4 focus:outline-none focus:ring-yellow-300 font-medium rounded-lg text-sm mr-2 px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
                type="button"
              >
                성분
              </button>
              <!-- 모달 -->
              <div
                id="modalEl"
                tabindex="-1"
                aria-hidden="true"
                class="hidden overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 justify-center items-center w-full md:inset-0 h-[calc(100%-1rem)] max-h-full"
              >
                <!-- 모달 내용 -->
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
                        <!-- 성분 출력 -->
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
                      <!-- 모달 닫기 버튼 -->
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
              <!-- 조리방법 버튼 -->
              <button
                data-modal-target="modalEl2"
                data-modal-toggle="modalEl2"
                @click="goToModal(data, '조리방법')"
                class="block text-white bg-yellow-500 hover:bg-yellow-600 focus:ring-4 focus:outline-none focus:ring-yellow-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
                type="button"
              >
                조리방법
              </button>
              <!-- 모달 -->
              <div
                id="modalEl2"
                tabindex="-1"
                aria-hidden="true"
                class="hidden overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 justify-center items-center w-full md:inset-0 h-[calc(100%-1rem)] max-h-full"
              >
                <!-- 모달 내용 -->
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
                      <!-- 모달 닫기 버튼 -->
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
                        <!-- 조리법 출력 -->
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
                      <!-- 모달 닫기 버튼 -->
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
              <!-- 조리방법 버튼 및 모달 end -->
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted, computed , watch } from 'vue'
  import { useRecipeStore } from '../../stores/recipe';
    
  const selectedData = ref(null);
  const selectedType = ref(null);
  const store = useRecipeStore();
  const recipes = ref(store.recipes)
  const query = ref('') // 사용자의 검색어(동적 바인딩)
  const filteredRecipe = ref([])

  onMounted(() => {
    filteredRecipe.value = store.recipes
    console.log(recipes.value)
})

  // 각 레시피의 정보들을 위한 변수
  const goToModal = (data, type) => {
    selectedData.value = data;
    selectedType.value = type;
  };

  // 레시피들을 필터링하는 함수
  const filterRecipes = (event) => {
    event.preventDefault();
    filteredRecipe.value = []
    if (!query.value || query.value.trim() === ''){
      console.log(store.recipes)
      filteredRecipe.value = store.recipes;
    
      } 
    else {
      filteredRecipe.value = recipes.value.filter(recipe => {
        console.log(recipe.성분)
        const ingredients = Object.keys(recipe['성분']);
        return ingredients.some(ingredient => ingredient.includes(query.value));
    })
    }
  }

    

  </script>
  
  <style scoped>
  /* 스타일링 */
  </style>
  