<!-- TheRecipeBody.vue -->

<template>
  <!-- 라디오버튼 start -->
  
<h3 class="mb-4 font-semibold text-gray-900 dark:text-white">정렬 기준</h3>
<ul class="items-center w-full text-sm font-medium text-gray-900 bg-white border border-gray-200 rounded-lg sm:flex">
    <li class="w-full border-b border-gray-200 sm:border-b-0 sm:border-r dark:border-gray-600">
        <div class="flex items-center ps-3">
            <input id="horizontal-list-radio-license" type="radio" value="" name="list-radio" class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300">
            <label for="horizontal-list-radio-license" class="w-full py-3 ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">칼로리</label>
        </div>
    </li>
    <li class="w-full border-b border-gray-200 sm:border-b-0 sm:border-r dark:border-gray-600">
        <div class="flex items-center ps-3">
            <input id="horizontal-list-radio-id" type="radio" value="" name="list-radio" class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300">
            <label for="horizontal-list-radio-id" class="w-full py-3 ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">단백질</label>
        </div>
    </li>
    <li class="w-full border-b border-gray-200 sm:border-b-0 sm:border-r dark:border-gray-600">
        <div class="flex items-center ps-3">
            <input id="horizontal-list-radio-military" type="radio" value="" name="list-radio" class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300">
            <label for="horizontal-list-radio-military" class="w-full py-3 ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">지방</label>
        </div>
    </li>
    <li class="w-full dark:border-gray-600">
        <div class="flex items-center ps-3">
            <input id="horizontal-list-radio-passport" type="radio" value="" name="list-radio" class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300">
            <label for="horizontal-list-radio-passport" class="w-full py-3 ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">나트륨</label>
        </div>
    </li>
</ul>

  <!-- 라디오버튼 end -->

  <div class="recipe-list">
    <div v-for="data in filteredRecipe" :key="data.id" class="m-12 photo">
      <!-- 각 레시피 카드 -->
      <div
        class="w-full max-w-sm bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">
        <!-- 레시피 정보 표시 -->
        <div class="flex flex-col items-center py-10">
          <img class="w-24 h-24 mb-3 rounded-full shadow-lg" :src="`../../src/assets/${data['사진 파일명']}`"
            alt="Bonnie image" />
          <h5 class="mb-1 text-xl font-medium text-gray-900 dark:text-white">
            {{ data["제목"] }}
          </h5>
          <span class="text-sm text-gray-500 dark:text-gray-400">{{
            data["작성자"]
          }}</span>
          <!-- 성분 버튼 및 모달 start -->
          <div class="flex mt-4 md:mt-6">
            <!-- 성분 버튼 -->
            <button data-modal-target="modalEl" data-modal-toggle="modalEl" @click="goToModal('modalEl', data, '성분')"
              class="block text-white bg-yellow-400 hover:bg-yellow-500 focus:ring-4 focus:outline-none focus:ring-yellow-300 font-medium rounded-lg text-sm mr-2 px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
              type="button">
              성분
            </button>
            <!-- 모달 -->
            <div id="modalEl" tabindex="-1" aria-hidden="true"
              class="hidden overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 justify-center items-center w-full md:inset-0 h-[calc(100%-1rem)] max-h-full">
              <!-- 모달 내용 -->
              <div class="relative p-4 w-full max-w-2xl max-h-full">
                <!-- Modal content -->
                <div class="relative bg-white rounded-lg shadow dark:bg-gray-700">
                  <!-- Modal header -->
                  <div class="flex items-center justify-between p-4 md:p-5 border-b rounded-t dark:border-gray-600">
                    <h3 class="text-xl font-semibold text-gray-900 dark:text-white">
                      재료
                    </h3>
                  </div>
                  <!-- Modal body -->
                  <div class="p-4 md:p-5 space-y-4">
                    <p class="text-base leading-relaxed text-gray-500 dark:text-gray-400">
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
                    class="flex items-center justify-end p-4 md:p-5 border-t border-gray-200 rounded-b dark:border-gray-600">
                    <!-- 모달 닫기 버튼 -->
                    <button @click="toggleModal('modalEl')" type="button"
                      class="text-white bg-yellow-300 hover:bg-yellow-400 focus:ring-4 focus:outline-none focus:ring-yellow-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
                      확인
                    </button>
                  </div>
                </div>
              </div>
            </div>
            <!-- 성분 버튼 및 모달 end -->

            <!-- 조리방법 버튼 및 모달 start -->
            <!-- 조리방법 버튼 -->
            <button data-modal-target="modalEl2" data-modal-toggle="modalEl2"
              @click="goToModal('modalEl2', data, '조리방법')"
              class="block text-white bg-yellow-500 hover:bg-yellow-600 focus:ring-4 focus:outline-none focus:ring-yellow-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
              type="button">
              조리방법
            </button>
            <!-- 모달 -->
            <div id="modalEl2" tabindex="-1" aria-hidden="true"
              class="hidden overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 justify-center items-center w-full md:inset-0 h-[calc(100%-1rem)] max-h-full">
              <!-- 모달 내용 -->
              <div class="relative p-4 w-full max-w-2xl max-h-full">
                <!-- Modal content -->
                <div class="relative bg-white rounded-lg shadow dark:bg-gray-700">
                  <!-- Modal header -->
                  <div class="flex items-center justify-between p-4 md:p-5 border-b rounded-t dark:border-gray-600">
                    <h3 class="text-xl font-semibold text-gray-900 dark:text-white">
                      조리방법
                    </h3>

                  </div>
                  <!-- Modal body -->
                  <div class="p-4 md:p-5 space-y-4">
                    <p class="text-base leading-relaxed text-gray-500 dark:text-gray-400">
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
                    class="flex items-center justify-end p-4 md:p-5 border-t border-gray-200 rounded-b dark:border-gray-600">
                    <!-- 모달 닫기 버튼 -->
                    <button @click="toggleModal('modalEl2')" type="button"
                      class="text-white bg-yellow-300 hover:bg-yellow-400 focus:ring-4 focus:outline-none focus:ring-yellow-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
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
import { ref, onMounted, computed, watch } from 'vue'
import { useRecipeStore } from '../../stores/recipe';
// import Swal from 'sweetalert2'

const selectedData = ref(null);
const selectedType = ref(null);
const store = useRecipeStore();
const recipes = ref(store.recipes)
const dropdownText = ref('분류')  // 드롭다운 안의 텍스트
const query = ref('') // 사용자의 검색어(동적 바인딩)
const selectedquery = ref('')
const filteredRecipe = ref([])
const isVisible = ref(false)

onMounted(() => {
  filteredRecipe.value = store.recipes
})

// 분류 드롭다운 버튼의 보이기, 안보이기 설정
const toggleVisibility = () => {
  isVisible.value = !isVisible.value
}

// 드롭다운 메뉴 설정 시 드롭다운의 텍스트 변경 및 드롭다운 숨김 설정
const dropdownTextChange = (name) => {
  dropdownText.value = name
  isVisible.value = false
}

const toggleModal = (modalId) => {
  const modal = document.getElementById(modalId);
  if (modal) {
    modal.classList.toggle('hidden');
    modal.classList.toggle('flex');
  }
};

// 각 레시피의 정보들을 위한 변수
const goToModal = (modalId, data, type) => {
  const modal = document.getElementById(modalId);
  if (modal) {
    modal.classList.toggle('hidden');
    modal.classList.toggle('flex');
  }

  selectedData.value = data;
  selectedType.value = type;
};

// 레시피들을 필터링하는 함수
const filterRecipes = (event) => {
  event.preventDefault();
  filteredRecipe.value = []

  if (dropdownText.value === '제목') {
    if (!query.value || query.value.trim() === '') {
      filteredRecipe.value = store.recipes;

    }
    else {
      filteredRecipe.value = recipes.value.filter(recipe => {
        const title = recipe['제목']
        return title.includes(query.value);
      })
    }
    selectedquery.value = query.value
  }

  else if (dropdownText.value === '작성자') {
    if (!query.value || query.value.trim() === '') {
      filteredRecipe.value = store.recipes;

    }
    else {
      filteredRecipe.value = recipes.value.filter(recipe => {
        const writer = recipe['작성자']
        return writer.includes(query.value);;
      })
    }
    selectedquery.value = query.value
  }

  else if (dropdownText.value === '성분') {
    if (!query.value || query.value.trim() === '') {
      filteredRecipe.value = store.recipes;

    }
    else {
      filteredRecipe.value = recipes.value.filter(recipe => {
        const ingredients = Object.keys(recipe['성분']);
        return ingredients.some(ingredient => ingredient.includes(query.value));
      })
    }
    selectedquery.value = query.value
  }

  else if (dropdownText.value === '조리법') {
    if (!query.value || query.value.trim() === '') {
      filteredRecipe.value = store.recipes;

    }
    else {
      filteredRecipe.value = recipes.value.filter(recipe => {
        const method = Object.values(recipe['조리법']);
        return method.some(ingredient => ingredient.includes(query.value));
      })
    }
    selectedquery.value = query.value
  }
}

</script>

<style scoped></style>