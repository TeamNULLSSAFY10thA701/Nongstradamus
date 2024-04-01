<!-- TheRecipeBody.vue -->

<template>
  <!-- 라디오버튼 start -->
  <div class="flex-column justify-center my-8">
    <div class="flex">
      <div class="font-semibold ms-5 mb-2 me-2 text-gray-900 dark:text-white">
        정렬
      </div>
      <div>
          <svg class="uparrow w-2.5 h-2.5" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 16 10">
          <path d="M9.207 1A2 2 0 0 0 6.38 1L.793 6.586A2 2 0 0 0 2.207 10H13.38a2 2 0 0 0 1.414-3.414L9.207 1Z"/>
          </svg>

          <svg class="downarrow w-2.5 h-2.5" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 16 10">
          <path d="M15.434 1.235A2 2 0 0 0 13.586 0H2.414A2 2 0 0 0 1 3.414L6.586 9a2 2 0 0 0 2.828 0L15 3.414a2 2 0 0 0 .434-2.179Z"/>
          </svg>
        </div>
          <div v-if="selectedIngredient && selectedState">
            <div :selectedIngredient :selectedState class="text-xs ml-6">
            {{ selectedIngredient }} {{ selectedState }} 순으로 정렬된 결과입니다.
            </div> 
          </div>
      </div>
    <ul
      class="grid grid-cols-4 ps-3 pe-3 jeongryeol-block items-center font-medium text-gray-900 bg-white "
    >
      <li
        class="text-center"
      >
          <input
            id="ingredient1"
            type="radio"
            value=""
            name="list-radio"
            class="radio-input hidden"
            @click="handleRadioClick(0)"
          />
          <label
            for="ingredient1"
            class="radio-label py-3 text-xs font-medium text-gray-900 dark:text-gray-300"
            >칼로리</label
          >
        
      </li>
      <li
        class="text-center"
      >
        
          <input
            id="ingredient2"
            type="radio"
            value=""
            name="list-radio"
            class="radio-input hidden"
            @click="handleRadioClick(1)"
          />
          <label
            for="ingredient2"
            class="radio-label py-3 text-xs font-medium text-gray-900 dark:text-gray-300"
            >단백질</label
          >
        
      </li>
      <li
        class="text-center"
      > 
          <input
            id="ingredient3"
            type="radio"
            value=""
            name="list-radio"
            class="radio-input hidden"
            @click="handleRadioClick(2)"
          />
          <label
            for="ingredient3"
            class="radio-label py-3 text-xs font-medium text-gray-900 dark:text-gray-300"
            >지방</label
          >    
      </li>
      <li class="text-center">
          <input
            id="ingredient4"
            type="radio"
            value=""
            name="list-radio"
            class="radio-input hidden"
            @click="handleRadioClick(3)"
          />
          <label
            for="ingredient4"
            class="radio-label py-3 ms-2 text-xs font-medium text-gray-900 dark:text-gray-300"
            >나트륨</label
          >
      </li>
    </ul>
  </div>
  <!-- 라디오버튼 end -->

  <div class="recipe-list grid grid-cols-2">
    <ul v-for="recipe in AllRecipes" :key="recipe">
      <!-- 각 레시피 카드 -->
      <div
        class="m-3 bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700"
      >
        <!-- 레시피 정보 표시 -->
        <div class="flex flex-col items-center py-3 bg-yellow-100/50">
          <div class="relative">
            <!-- Youtube 아이콘 -->
              <img  
                src="@/image/youtube.png"
                class="youtube-icon absolute top-0 left-0 w-5 h-5 rounded-full shadow-lg"
                @click="searchYoutube(recipe.title)"
                alt="Youtube icon">
            <!-- 레시피 이미지 -->
            <img class="recipe-img w-20 h-20 mb-3 rounded-full shadow-lg" :src="recipe.image" alt="Recipe image">
          </div>
          <div class="m-1 text-sm text-center font-medium text-gray-900 dark:text-white">
            {{ recipe.title.length > 9 ? recipe.title.slice(0, 8) + '...' : recipe.title }}
          </div> 
          <!-- 성분 버튼 및 모달 start -->
          <div class="flex mt-4 md:mt-6">
            <!-- 성분 버튼 -->
            <button
              data-modal-target="modalEl" 
              data-modal-toggle="modalEl"
              @touchstart.prevent="toggleModal('modalEl', recipe)"
              class="block text-white bg-yellow-400 hover:bg-yellow-500 focus:ring-4 focus:outline-none focus:ring-yellow-300 rounded-lg text-xs mx-2 px-2 py-2 text-center"
              type="button"
            >
              재료  
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
                    <div v-if="selectedData">
                      {{ selectedData.title }}
                    </div>
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
                      @touchstart.prevent="toggleModal('modalEl', recipe)"
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
              @touchstart.prevent="toggleModal('modalEl2', recipe)"
              class="block text-white bg-yellow-500 hover:bg-yellow-600 focus:ring-4 focus:outline-none focus:ring-yellow-300 font-medium rounded-lg text-xs mx-1 px-1 py-1 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
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
                    <div v-if="selectedData">
                      {{ selectedData.title }}
                    </div>
                    </h3>
                  </div>
                  <!-- Modal body -->
                  <div class="p-4 md:p-5 space-y-4">
                    <p
                      class="text-base leading-relaxed text-gray-500 dark:text-gray-400"
                    > 
                    <div v-if="selectedData">
                      <div v-html="dynamicHTML"></div>
                    </div>
                    </p>
                  </div>
                  <!-- Modal footer -->
                  <div
                    class="flex items-center justif y-end p-4 md:p-5 border-t border-gray-200 rounded-b dark:border-gray-600"
                  >
                    <!-- 모달 닫기 버튼 -->
                    <button
                      @touchstart.prevent="toggleModal('modalEl2', recipe)"
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
  

<nav aria-label="Page navigation" class="flex justify-center mb-20">
  <ul class="flex items-center -space-x-px h-8 text-sm m-3">
    <li>
      <a href="#" @touchstart.prevent="prevPage" :disabled="currentPage === 1" class="flex items-center justify-center px-3 h-8 ms-0 leading-tight text-gray-500 bg-white border border-e-0 border-gray-300 rounded-s-lg hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">
        <span class="sr-only">Previous</span>
        <svg class="w-2.5 h-2.5 rtl:rotate-180" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 6 10">
          <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 1 1 5l4 4"/>
        </svg>
      </a>
    </li>

    <div v-for="page in pages" :key="page">
      <li>
        <a href="#" @touchstart.prevent="curPage(page)" class="curpage flex items-center justify-center px-3 h-8 leading-tight text-gray-500 bg-white border border-gray-300">{{ page }}</a>
      </li>
    </div>
    <li>
      <a href="#" @touchstart.prevent="nextPage" :disabled="currentPage >= totalPages" class="flex items-center justify-center px-3 h-8 leading-tight text-gray-500 bg-white border border-gray-300 rounded-e-lg hover:bg-gray-200 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white">
        <span class="sr-only">Next</span>
        <svg class="w-2.5 h-2.5 rtl:rotate-180" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 6 10">
          <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m1 9 4-4-4-4"/>
        </svg>
      </a>
    </li>
  </ul>
</nav>
</template>

<script setup>
import { ref, onMounted, computed, watch } from "vue";
import { getRecipeData, getRecipeDetailData, youtubeApiKey } from "@/api/recipe";
import axios from 'axios'

const selectedData = ref(null);
const AllRecipes = ref([]); // 각 페이지 레시피들의 정보
const dynamicHTML = ref()
const pages = ref([1,2,3,4,5])
const selectedIngredient = ref()
const selectedState = ref()


onMounted(() => {
  getAllRecipeData();
});


// ---------------pagination--------------
const currentPage = ref(1);
const totalPages = 5;

function nextPage() {
  if (currentPage.value < totalPages) {
    currentPage.value++;
    highlightcurPage(currentPage.value)
  }
}

function curPage(page) {
  currentPage.value = page 
  highlightcurPage(currentPage.value)
}

function highlightcurPage(page) {
  const curp = document.getElementsByClassName("curpage");
  Array.from(curp).forEach((element) => {
    if (element.innerText == page) {
      element.classList.remove("bg-white");
      element.classList.add("bg-yellow-100/50");
    } else {
      element.classList.remove("bg-yellow-100/50");
      element.classList.add("bg-white");
    }
  });
}

function prevPage() {
  if (currentPage.value > 1) {
    currentPage.value--;
    highlightcurPage(currentPage.value)
  }
}

//------------------------------데이터 추출------------------------------------

watch(currentPage, (newPage, oldPage) => {
  getAllRecipeData()
}, { deep: true });


// 가격이 가장 많이 하락한 재료를 기반으로 한 20개의 레시피 정보를 가져옴
// 페이지네이션으로 인해 총 5페이지가 존재하며, 각 페이지에는 4개의 레시피가 존재
const getAllRecipeData = () => {
      getRecipeData(
      currentPage.value - 1,
      async (response) => {
        AllRecipes.value = []

        for (let j = 0; j < response.data.data.length; j++) {
          try {
            const jsonrecipe = response.data.data[j];
            // 각 레시피의 상세 정보를 비동기적으로 가져옴
            const detaildata = await getRecipeDetail(jsonrecipe.idx);

            // 페이지네이션 적용하지 않은 레시피들 정리용 json 형식
            const tempRecipe = {
              title: jsonrecipe.title,
              image: jsonrecipe.image,
              index: jsonrecipe.idx,
              ingredient: detaildata.ingredient,
              content: detaildata.content,
              carbohydrate: detaildata.carbohydrate,
              energy: detaildata.energy,
              fat: detaildata.fat,
              natrium: detaildata.natrium,
              protein: detaildata.protein,
            };
            
            AllRecipes.value.push(tempRecipe);
          } catch (error) {
            console.error("Error fetching recipe detail:", error);
          }
        }
      },
      (error) => {
        console.log(error);
      }
    );
};

// 모달 창의 상태 변경
const toggleModal = (modalId, data) => {
  const modal = document.getElementById(modalId);
  if (modal) {
    selectedData.value = data;
    dynamicHTML.value = data.content; // 조리방법 br태그 기준 개행처리
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
const ingredients = ['칼로리', '단백질', '지방', '나트륨']



// 페이지 이동시 정렬 초기화
const initRadio = () => {
  radioClickCount.value = [0, 0, 0, 0]
  upArrow.classList.remove("text-red-500");
  upArrow.classList.add("text-black");
  downArrow.classList.remove("text-blue-500");
  downArrow.classList.add("text-black");
  selectedState.value=null
}

// 라디오 버튼 클릭 시 색 변화
const handleRadioClick = (index) => {
  // 클릭 상태 변경
  radioClickCount.value[index]++;
  selectedIngredient.value=ingredients[index]

  for (let i = 0; i < 4; i++) {
    if (i !== index) {
      radioClickCount.value[i] = 0;
    }
  }

  const upArrow = document.querySelectorAll('.uparrow')[0];
  const downArrow = document.querySelectorAll('.downarrow')[0];

  if (radioClickCount.value[index] % 2 === 1) {
    upArrow.classList.remove("text-black");
    upArrow.classList.add("text-red-500");
    downArrow.classList.remove("text-blue-500");
    downArrow.classList.add("text-black");
    selectedState.value="많은"
  } else {
    upArrow.classList.remove("text-red-500");
    upArrow.classList.add("text-black");
    downArrow.classList.remove("text-black");
    downArrow.classList.add("text-blue-500");
    selectedState.value="적은"
  }

  alignRecipe(index, radioClickCount.value[index] % 2 === 1 ? 'asc' : 'desc');
};

//------------ 성분 기준 데이터 정렬 ------------------

// 레시피의 정렬(성분, 차순)
const alignRecipe = (radioidx, order) => {
  
  const tempIngredients  = ['energy', 'protein', 'fat', 'natrium'] // 정렬 기준
  
  AllRecipes.value.sort((a, b) => {
    const ingredA = calculateIngredients(a, tempIngredients[radioidx]);
    const ingredB = calculateIngredients(b, tempIngredients[radioidx]);

    if (order === 'desc') {
      return ingredB - ingredA;
    }
    else {
      return ingredA - ingredB;
    }
    })
    
};

// 각 레시피의 단백질 함량을 계산하는 함수
const calculateIngredients = (recipe, ingredient) => {
    let ingredValue = recipe[ingredient]
    return ingredValue;
}

//------------------------------ youtube ---------------------------
async function searchYoutube(title) {
  try {
    const response = await axios.get('https://www.googleapis.com/youtube/v3/search', {
      params: {
        q: `${title} 레시피`,
        key: youtubeApiKey,
        order:'relevance',
        part: 'snippet',
        type: 'video'
      } 
    });
    const videos = response.data.items;
    console.log(videos)
    if (videos.length > 0) {
      return gotoVideo(`https://www.youtube.com/embed/${videos[0].id.videoId}`);
    } else {
      showAlert("이 레시피와 관련된 영상을 찾을 수 없습니다.");
      return 0;
    }
  } catch (error) {
    showAlert("이 레시피와 관련된 영상을 찾을 수 없습니다.");
    return 0;
  }
}

function showAlert(message) {
  // 알림창을 표시하는 코드를 작성합니다.
  alert(message);
}

async function gotoVideo(link) {
  if (link) {
    // videoLink 값이 존재할 때만 이동
    window.open(link, '_blank');
  } else {
    // videoLink 값이 없을 때 처리할 내용 추가
    console.log('No video link available');
  }
}
</script>

<style scoped>
.relative {
  position: relative;
}

.youtube-icon {
  z-index: 1; /* Youtube 아이콘을 위로 올리기 위해 */
}

.recipe-img {
  z-index: 0; /* 레시피 이미지를 뒤로 보내기 위해 */
}

/* 라디오 버튼 숨기기 */
.radio-input {
    position: absolute;
    opacity: 0;
  }

/* 버튼 스타일링 */
.radio-label {
  display: inline-block;
  width: 60px;
  height : 35px;
  padding: 0.5rem 0.5rem;
  cursor: pointer;
  border: 0.5px solid #ccc;
  border-radius: 20px;
  background-color: #faeed4;
  transition: background-color 0.3s ease;
  border-radius: 10px;
}

  /* 선택된 버튼 스타일링 */
  .radio-input:checked + .radio-label {
    background-color: #fada91;
  }
</style>
