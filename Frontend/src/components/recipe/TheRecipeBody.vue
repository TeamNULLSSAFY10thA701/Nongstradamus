<!-- TheRecipeBody.vue -->

<template>
  <!-- 라디오버튼 start -->
  <div class="flex-column m-12 justify-center">
    <div class="font-semibold mb-4 text-gray-900 dark:text-white">
      정렬 기준
    </div>
    <ul
      class="jeongryeol-block items-center w-3/5 text-sm font-medium text-gray-900 bg-white border border-gray-200 rounded-lg sm:flex"
    >
      <li
        class="w-full border-b border-gray-200 sm:border-b-0 sm:border-r dark:border-gray-600"
      >
        <div class="flex items-center ps-3 pe-3">
          <input
            id="ingredient1"
            type="radio"
            value=""
            name="list-radio"
            class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300"
            @click="handleRadioClick(0)"
          />
          <label
            for="ingredient1"
            class="w-full py-3 ms-2 text-sm font-medium text-gray-900 dark:text-gray-300"
            >칼로리</label
          >
          <svg
            class="down0 w-7 h-7 ms-3 -rotate-90"
            aria-hidden="true"
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 14 10"
          >
            <path
              stroke="currentColor"
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M1 5h12m0 0L9 1m4 4L9 9"
            />
          </svg>

          <svg
            class="up0 w-7 h-7 ms-3 rotate-90"
            aria-hidden="true"
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 14 10"
          >
            <path
              stroke="currentColor"
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M1 5h12m0 0L9 1m4 4L9 9"
            />
          </svg>
        </div>
      </li>
      <li
        class="w-full border-b border-gray-200 sm:border-b-0 sm:border-r dark:border-gray-600"
      >
        <div class="flex items-center ps-3 pe-3">
          <input
            id="ingredient2"
            type="radio"
            value=""
            name="list-radio"
            class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300"
            @click="handleRadioClick(1)"
          />
          <label
            for="ingredient2"
            class="w-full py-3 ms-2 text-sm font-medium text-gray-900 dark:text-gray-300"
            >단백질</label
          >
          <svg
            class="down1 w-7 h-7 ms-3 -rotate-90"
            aria-hidden="true"
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 14 10"
          >
            <path
              stroke="currentColor"
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M1 5h12m0 0L9 1m4 4L9 9"
            />
          </svg>

          <svg
            class="up1 w-7 h-7 ms-3 rotate-90"
            aria-hidden="true"
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 14 10"
          >
            <path
              stroke="currentColor"
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M1 5h12m0 0L9 1m4 4L9 9"
            />
          </svg>
        </div>
      </li>
      <li
        class="w-full border-b border-gray-200 sm:border-b-0 sm:border-r dark:border-gray-600"
      >
        <div class="flex items-center ps-3 pe-3">
          <input
            id="ingredient3"
            type="radio"
            value=""
            name="list-radio"
            class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300"
            @click="handleRadioClick(2)"
          />
          <label
            for="ingredient3"
            class="w-full py-3 ms-2 text-sm font-medium text-gray-900 dark:text-gray-300"
            >지방</label
          >
          <svg
            class="down2 w-7 h-7 ms-3 -rotate-90"
            aria-hidden="true"
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 14 10"
          >
            <path
              stroke="currentColor"
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M1 5h12m0 0L9 1m4 4L9 9"
            />
          </svg>

          <svg
            class="up2 w-7 h-7 ms-3 rotate-90"
            aria-hidden="true"
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 14 10"
          >
            <path
              stroke="currentColor"
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M1 5h12m0 0L9 1m4 4L9 9"
            />
          </svg>
        </div>
      </li>
      <li class="w-full dark:border-gray-600">
        <div class="flex items-center ps-3 pe-3">
          <input
            id="ingredient4"
            type="radio"
            value=""
            name="list-radio"
            class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300"
            @click="handleRadioClick(3)"
          />
          <label
            for="ingredient4"
            class="w-full py-3 ms-2 text-sm font-medium text-gray-900 dark:text-gray-300"
            >나트륨</label
          >
          <svg
            class="down3 w-7 h-7 ms-3 background-color-gray -rotate-90"
            aria-hidden="true"
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 14 10"
          >
            <path
              stroke="currentColor"
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M1 5h12m0 0L9 1m4 4L9 9"
            />
          </svg>

          <svg
            class="up3 w-7 h-7 ms-3 rotate-90"
            aria-hidden="true"
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 14 10"
          >
            <path
              stroke="currentColor"
              stroke-linecap="round"
              stroke-linejoin="round"
              stroke-width="2"
              d="M1 5h12m0 0L9 1m4 4L9 9"
            />
          </svg>
        </div>
      </li>
    </ul>
  </div>
  <!-- 라디오버튼 end -->

  <div class="recipe-list grid grid-cols-2">
    <ul v-for="recipe in AllRecipes[currentPage - 1]" :key="recipe">
      <!-- 각 레시피 카드 -->
      <div
        class="w-full max-w-sm m-6 photo bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700"
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
              @click="toggleModal('modalEl', recipe)"
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
                    <div v-if="selectedData">
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
                      @click="toggleModal('modalEl', recipe)"
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
              @click="toggleModal('modalEl2', recipe)"
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
                    <div v-if="selectedData">
                      {{ selectedData.content }}
                    </div>
                    </p>
                  </div>
                  <!-- Modal footer -->
                  <div
                    class="flex items-center justif y-end p-4 md:p-5 border-t border-gray-200 rounded-b dark:border-gray-600"
                  >
                    <!-- 모달 닫기 버튼 -->
                    <button
                      @click="toggleModal('modalEl2', recipe)"
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
    </ul>
  </div>

  <!-- 페이지네이션 컨트롤 -->
  <button @click="prevPage" :disabled="currentPage === 1" class="m-8">
    이전
  </button>
  <span>페이지 {{ currentPage }} / {{ totalPages }}</span>
  <button @click="nextPage" :disabled="currentPage >= totalPages" class="m-8">
    다음
  </button>
</template>

<script setup>
import { ref, onMounted, computed } from "vue";
import { getRecipeData, getRecipeDetailData } from "@/api/recipe";

const selectedData = ref(null);
const AllRecipes = ref([]); // 모든 레시피들의 정보(페이지네이션 o)
const unpagedRecipes = ref([]); // 모든 레시피들의 정보(페이지네이션 x)


onMounted(() => {
  getAllRecipeData();
  console.log(AllRecipes.value);
  console.log(unpagedRecipes.value);
});

// 가격이 가장 많이 하락한 재료를 기반으로 한 20개의 레시피 정보를 가져옴
// 페이지네이션으로 인해 총 5페이지가 존재하며, 각 페이지에는 4개의 레시피가 존재
const getAllRecipeData = () => {
  for (let i = 0; i < 5; i++) {
    getRecipeData(
      i,
      async (response) => {
        const tempData = []; // 페이지네이션을 위한 배열

        for (let j = 0; j < response.data.data.length; j++) {
          const jsonrecipe = response.data.data[j];

          // 페이지네이션 적용하지 않은 레시피들 정리용 json 형식
          const tempRecipe = {
            title: String,
            image: String,
            index: "",
            ingredient: String,
            content: String,
            carbohydrate: " Integer",
            energy: "Integer",
            fat: "Integer",
            natrium: "Integer",
            protein: "Integer",
          };

          try {
            // 각 레시피의 상세 정보를 비동기적으로 가져옴
            const detaildata = await getRecipeDetail(jsonrecipe.idx);

            // 상세 정보를 레시피 객체에 추가
            tempRecipe.title = jsonrecipe.title;
            tempRecipe.image = jsonrecipe.image;
            tempRecipe.index = jsonrecipe.idx;
            tempRecipe.ingredient = detaildata.ingredient;
            tempRecipe.content = detaildata.content;
            tempRecipe.carbohydrate = detaildata.carbohydrate;
            tempRecipe.energy = detaildata.energy;
            tempRecipe.fat = detaildata.fat;
            tempRecipe.natrium = detaildata.natrium;
            tempRecipe.protein = detaildata.protein;

            tempData.push(tempRecipe);
            unpagedRecipes.value.push(tempRecipe);
          } catch (error) {
            console.error("Error fetching recipe detail:", error);
          }
        }
        // 모든 레시피 정보를 처리한 후에 AllRecipes에 추가
        AllRecipes.value.push(tempData);
      },
      (error) => {
        console.log(error);
      }
    );
  }
};

// 모달 창의 상태 변경
const toggleModal = (modalId, data) => {
  const modal = document.getElementById(modalId);
  if (modal) {
    selectedData.value = data;
    modal.classList.toggle("hidden");
    modal.classList.toggle("flex");
  }
};

// 각 레시피의 상세정보를 가져옴과 동시에, 모달의 상태를 전환
const getRecipeDetail = (idx) => {
  return new Promise((resolve, reject) => {
    getRecipeDetailData(
      idx,
      (response) => {
        resolve(response.data.data);
      },
      (error) => {
        reject(error);
      }
    );
  });
};

// ---------------- 라디오버튼 조작 ------------------

// 라디오 버튼 클릭 상태 저장 변수(칼로리(energy), 단백질, 지방, 나트륨)
const radioClickCount = ref([0, 0, 0, 0]);
// 라디오 버튼 클릭에 따라 정렬시킬 기준 정보(칼로리(energy), 단백질, 지방, 나트륨)
const alignStandard = ref("");

// 라디오 버튼 클릭 시 색 변화
const handleRadioClick = (index) => {
  // 클릭 상태 변경
  radioClickCount.value[index]++;

  for (let i = 0; i < 4; i++) {
    if (i !== index) {
      radioClickCount.value[i] = 0;
      const downArrow = document.querySelectorAll(`.down${i}`)[0];
      const upArrow = document.querySelectorAll(`.up${i}`)[0];
      downArrow.classList.remove("text-red-500");
      downArrow.classList.add("text-gray-900");
      upArrow.classList.remove("text-blue-500");
      upArrow.classList.add("text-gray-900");
    } else {
      const downArrow = document.querySelectorAll(`.down${index}`)[0];
      const upArrow = document.querySelectorAll(`.up${index}`)[0];
      // 홀수번 클릭된 경우
      if (radioClickCount.value[index] % 2 === 1) {
        alignRecipeAscend(index) // 오름차순 정렬
        downArrow.classList.add("text-red-500");
        downArrow.classList.remove("text-gray-900");
        upArrow.classList.add("text-gray-900");
        upArrow.classList.remove("text-blue-500");
      }
      // 짝수번 클릭된 경우
      else {
        alignRecipeDescend(index) // 내림차순 정렬
        downArrow.classList.remove("text-red-500");
        downArrow.classList.add("text-gray-900");
        upArrow.classList.remove("text-gray-900");
        upArrow.classList.add("text-blue-500");
      }
    }
  }
};

//------------ 성분 기준 데이터 정렬 ------------------

// 레시피의 오름차순 정렬(성분, 오름차순)
const alignRecipeAscend = (radioidx) => {
  
  const tempIngredients  = ['energy', 'protein', 'fat', 'natrium'] // 정렬 기준
  const tempRecipes = []
  const alignedRecipes = []
  
  unpagedRecipes.value.forEach((jsonData, index) => {
    console.log(`JSON 데이터 ${index}에 접근:`);
    console.log(jsonData); // 해당 JSON 데이터 출력

    // JSON 데이터의 특정 속성에 접근하는 예시
    console.log(`제목: ${jsonData.title}`);
    console.log(`이미지: ${jsonData.image}`);
    console.log(`인덱스: ${jsonData.index}`);
    console.log(`에너지: ${jsonData['energy']}`);
    console.log(jsonData[tempIngredients[radioidx]])
    // 다른 속성들에 대해서도 위와 같이 접근할 수 있음

    if (tempRecipes.length === 0) 
    {
      tempRecipes.push(jsonData)
    }

    else if (jsonData[tempIngredients[radioidx]] < tempRecipes[-1][tempIngredients[radioidx]]) {
      tempRecipes.push(jsonData)
    }

    else {
      tempRecipes.unshift(jsonData)
    }
  });
  // -
  
  for (let i=0; i<5; i++) {
    const tempArray = []
    for (let j=0; j<4; j++) {
      tempArray.push(tempRecipes.shift())
    }
    alignedRecipes.push(tempArray)
  }
  
  AllRecipes.value = alignedRecipes 
  console.log(AllRecipes.value)
};

// 레시피의 내림차순 정렬
const alignRecipeDescend = (radioidx) => {
  const tempDatas = [];
  for (let i = 0; i < justifiedRecipes.length; i++) {
    tempDatas
  }
};


// ---------------pagination--------------
const currentPage = ref(1);
const totalPages = 5;

function nextPage() {
  if (currentPage.value < totalPages) {
    currentPage.value++;
  }
}

function prevPage() {
  if (currentPage.value > 1) {
    currentPage.value--;
  }
}
</script>

<style scoped></style>
