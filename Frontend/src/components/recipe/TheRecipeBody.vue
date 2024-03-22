<!-- TheRecipeBody.vue -->

<template>
  <!-- 라디오버튼 start -->

  <h3 class="mb-4 font-semibold text-gray-900 dark:text-white">정렬 기준</h3>
  <ul
    class="items-center w-full text-sm font-medium text-gray-900 bg-white border border-gray-200 rounded-lg sm:flex"
  >
    <li
      class="w-full border-b border-gray-200 sm:border-b-0 sm:border-r dark:border-gray-600"
    >
      <div class="flex items-center ps-3">
        <input
          id="horizontal-list-radio-license"
          type="radio"
          value=""
          name="list-radio"
          class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300"
        />
        <label
          for="horizontal-list-radio-license"
          class="w-full py-3 ms-2 text-sm font-medium text-gray-900 dark:text-gray-300"
          >칼로리</label
        >
      </div>
    </li>
    <li
      class="w-full border-b border-gray-200 sm:border-b-0 sm:border-r dark:border-gray-600"
    >
      <div class="flex items-center ps-3">
        <input
          id="horizontal-list-radio-id"
          type="radio"
          value=""
          name="list-radio"
          class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300"
        />
        <label
          for="horizontal-list-radio-id"
          class="w-full py-3 ms-2 text-sm font-medium text-gray-900 dark:text-gray-300"
          >단백질</label
        >
      </div>
    </li>
    <li
      class="w-full border-b border-gray-200 sm:border-b-0 sm:border-r dark:border-gray-600"
    >
      <div class="flex items-center ps-3">
        <input
          id="horizontal-list-radio-military"
          type="radio"
          value=""
          name="list-radio"
          class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300"
        />
        <label
          for="horizontal-list-radio-military"
          class="w-full py-3 ms-2 text-sm font-medium text-gray-900 dark:text-gray-300"
          >지방</label
        >
      </div>
    </li>
    <li class="w-full dark:border-gray-600">
      <div class="flex items-center ps-3">
        <input
          id="horizontal-list-radio-passport"
          type="radio"
          value=""
          name="list-radio"
          class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300"
        />
        <label
          for="horizontal-list-radio-passport"
          class="w-full py-3 ms-2 text-sm font-medium text-gray-900 dark:text-gray-300"
          >나트륨</label
        >
      </div>
    </li>
  </ul>

  <!-- 라디오버튼 end -->

  <div class="recipe-list">
    <ul v-for="recipes in AllRecipes" :key="recipes">
      <div v-for="recipe in recipes[0]" :key="recipe" class="m-12 photo">
        <!-- 각 레시피 카드 -->
        <div
          class="w-full max-w-sm bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700"
        >
          <!-- 레시피 정보 표시 -->
          <div class="flex flex-col items-center py-10">
            <img
              class="w-24 h-24 mb-3 rounded-full shadow-lg"
              :src="recipe.image"
              alt="Bonnie image"
            />
            <h5 class="mb-1 text-xl font-medium text-gray-900 dark:text-white">
              {{ recipe.title }}
            </h5>
            <!-- 성분 버튼 및 모달 start -->
            <div class="flex mt-4 md:mt-6">
              <!-- 성분 버튼 -->
              <button
                data-modal-target="modalEl"
                data-modal-toggle="modalEl"
                @click="getRecipeDetail(recipe.idx, 'modalEl')"
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
                        <div v-if ="selectedData">
                          {{ selectedData.ingredient }}
                        </div>
                      </p>
                    </div>
                    <!-- Modal footer -->
                    <div
                      class="flex items-center justify-end p-4 md:p-5 border-t border-gray-200 rounded-b dark:border-gray-600"
                    >
                      <!-- 모달 닫기 버튼 -->
                      <button
                        @click="toggleModal('modalEl')"
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
                @click="getRecipeDetail(recipe.idx, 'modalEl2')"
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
                    </div>
                    <!-- Modal body -->
                    <div class="p-4 md:p-5 space-y-4">
                      <p
                        class="text-base leading-relaxed text-gray-500 dark:text-gray-400"
                      >
                        <!-- 조리법 출력 -->
                        <div v-if ="selectedData">
                          {{ selectedData.content}}
                        </div>
                      </p>
                    </div>
                    <!-- Modal footer -->
                    <div
                      class="flex items-center justify-end p-4 md:p-5 border-t border-gray-200 rounded-b dark:border-gray-600"
                    >
                      <!-- 모달 닫기 버튼 -->
                      <button
                        @click="toggleModal('modalEl2')"
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
    </ul>
  </div>

  <!-- 페이지네이션 컨트롤 -->
  <button @click="prevPage" :disabled="currentPage === 1">이전</button>
  <span>페이지 {{ currentPage }} / {{ totalPages }}</span>
  <button @click="nextPage" :disabled="currentPage >= totalPages">다음</button>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { getRecipeData, getRecipeDetailData } from "@/api/recipe";

const selectedData = ref(null);
const AllRecipes = ref([]);

onMounted(() => {
  getAllRecipeData();
});

// 가격이 가장 많이 하락한 재료를 기반으로 한 20개의 레시피 정보를 가져옴
// 페이지네이션으로 인해 총 5페이지가 존재하며, 각 페이지에는 4개의 레시피가 존재
const getAllRecipeData = () => {
  for (let i = 0; i < 5; i++) {
    getRecipeData(
      i,
      (response) => {
        const tempData = [];
        tempData.push(response.data.data);
        AllRecipes.value.push(tempData);
      },
      (error) => {
        console.log(error);
      }
    );
  }
};

const toggleModal = (modalId) => {
  const modal = document.getElementById(modalId);
  if (modal) {
    modal.classList.toggle("hidden");
    modal.classList.toggle("flex");
  }
};

// 각 레시피의 상세정보를 가져옴과 동시에, 모달의 상태를 전환
const getRecipeDetail = (idx, modalname) => {
  getRecipeDetailData(
    idx,
    (response) => {
      selectedData.value = response.data.data;
      console.log(selectedData.value);
      toggleModal(modalname);
    },
    (error) => {
      console.log(error);
    }
  );
};
</script>

<style scoped></style>
