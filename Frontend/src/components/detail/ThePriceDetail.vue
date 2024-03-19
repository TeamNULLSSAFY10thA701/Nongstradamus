<template>
    <div class="all max-w-5xl mx-auto">
        <div class="grid grid-cols-5 gap-4 mt-8">
            <div class="col-start-2">
                <img src="../../assets/full_logo1.png" />
            </div>
            <div class="title col-start-3 col-span-3 flex items-center">
                농스트라다무스의 예측
            </div>
        </div>
        <!-- header -->
        <h3 class="mt-7 mb-4 font-semibold text-gray-900 dark:text-white w-4/5 mx-auto">농산물 카테고리</h3>
        <ul
            class="items-center text-sm font-medium text-gray-900 bg-white border border-gray-200 rounded-lg sm:flex dark:bg-gray-700 dark:border-gray-600 dark:text-white w-4/5 mx-auto">
            <li class="w-full border-b border-gray-200 sm:border-b-0 sm:border-r dark:border-gray-600">
                <div class="flex items-center ps-3">
                    <input id="VegetableReaf" type="radio" value="" name="list-radio"
                        class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-700 dark:focus:ring-offset-gray-700 focus:ring-2 dark:bg-gray-600 dark:border-gray-500"
                        @click="transferVegetableReaf">
                    <label for="VegetableReaf"
                        class="w-full py-3 ms-2 text-sm font-medium text-gray-900 dark:text-gray-300"
                        @click="transferVegetableReaf">채소-잎
                    </label>
                </div>
            </li>
            <li class="w-full border-b border-gray-200 sm:border-b-0 sm:border-r dark:border-gray-600">
                <div class="flex items-center ps-3">
                    <input id="VegetableFruit" type="radio" value="" name="list-radio"
                        class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-700 dark:focus:ring-offset-gray-700 focus:ring-2 dark:bg-gray-600 dark:border-gray-500"
                        @click="transferVegetableFruit">
                    <label for="VegetableFruit"
                        class="w-full py-3 ms-2 text-sm font-medium text-gray-900 dark:text-gray-300"
                        @click="transferVegetableFruit">채소-열매
                    </label>
                </div>
            </li>
            <li class="w-full border-b border-gray-200 sm:border-b-0 sm:border-r dark:border-gray-600">
                <div class="flex items-center ps-3">
                    <input id="transferVegetableRoot" type="radio" value="" name="list-radio"
                        class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-700 dark:focus:ring-offset-gray-700 focus:ring-2 dark:bg-gray-600 dark:border-gray-500"
                        @click="transferVegetableRoot">
                    <label for="transferVegetableRoot"
                        class="w-full py-3 ms-2 text-sm font-medium text-gray-900 dark:text-gray-300"
                        @click="transferVegetableRoot">채소-뿌리</label>
                </div>
            </li>
            <li class="w-full border-b border-gray-200 sm:border-b-0 sm:border-r dark:border-gray-600">
                <div class="flex items-center ps-3">
                    <input id="Grain" type="radio" value="" name="list-radio"
                        class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-700 dark:focus:ring-offset-gray-700 focus:ring-2 dark:bg-gray-600 dark:border-gray-500"
                        @click="transferGrain">
                    <label for="Grain" class="w-full py-3 ms-2 text-sm font-medium text-gray-900 dark:text-gray-300"
                        @click="transferGrain">곡물</label>
                </div>
            </li>
            <li class="w-full border-b border-gray-200 sm:border-b-0 sm:border-r dark:border-gray-600">
                <div class="flex items-center ps-3">
                    <input id="Fruit" type="radio" value="" name="list-radio"
                        class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-700 dark:focus:ring-offset-gray-700 focus:ring-2 dark:bg-gray-600 dark:border-gray-500"
                        @click="transferFruit">
                    <label for="Fruit" class="w-full py-3 ms-2 text-sm font-medium text-gray-900 dark:text-gray-300"
                        @click="transferFruit">과일</label>
                </div>
            </li>
            <li class="w-full dark:border-gray-600">
                <div class="flex items-center ps-3">
                    <input id="YellowCrop" type="radio" value="" name="list-radio"
                        class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-700 dark:focus:ring-offset-gray-700 focus:ring-2 dark:bg-gray-600 dark:border-gray-500"
                        @click="transferYellowCrop">
                    <label for="YellowCrop"
                        class="w-full py-3 ms-2 text-sm font-medium text-gray-900 dark:text-gray-300"
                        @click="transferYellowCrop">구황작물</label>
                </div>
            </li>

        </ul>
        <!-- 카테고리 라디오버튼 -->

        <div class="relative overflow-x-auto shadow-md sm:rounded-lg mt-4 w-4/5 h-3/5 mx-auto">
            <table class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
                <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
                    <tr>
                        <th scope="col" class="px-16 py-3 ">
                            <span class="sr-only">이미지</span>
                        </th>
                        <th scope="col" class="px-6 py-3 text-center">
                            농산물이름
                        </th>
                        <th scope="col" class="px-6 py-3 text-center">
                            현재가격(중)
                        </th>
                        <th scope="col" class="px-6 py-3 text-center">
                            내일가격(중)
                        </th>
                        <th scope="col" class="px-6 py-3 text-center">
                            예측등락폭(%)
                        </th>
                        <th scope="col" class="px-6 py-3 text-center">
                            단위
                        </th>
                    </tr>
                </thead>
                <tbody v-if="categoryVegetableLeafState">
                    <tr v-for="category in 5" :key="category"
                        class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600"
                        @click="clickEvent">
                        <td class="p-4 w-10">
                            <img src="../../assets/sesame.png" class="w-10 max-w-full max-h-full mx-auto">
                        </td>
                        <!-- 이미지 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            깻잎
                        </td>
                        <!-- 농산물이름 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            2000원
                        </td>
                        <!-- 현재가격 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            3000원
                        </td>
                        <!-- 다음 주 가격 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            50%
                        </td>
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            개당
                        </td>
                    </tr>
                </tbody>
                <tbody v-if="categoryVegetableFruitState">
                    <tr v-for="category in 5" :key="category"
                        class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600"
                        @click="clickEvent">
                        <td class="p-4 w-10">
                            <img src="../../assets/pepper.png" class="w-10 max-w-full max-h-full mx-auto">
                        </td>
                        <!-- 이미지 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            고추
                        </td>
                        <!-- 농산물이름 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            2000원
                        </td>
                        <!-- 현재가격 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            3000원
                        </td>
                        <!-- 다음 주 가격 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            50%
                        </td>
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            개당
                        </td>
                    </tr>
                </tbody>
                <tbody v-if="categoryVegetableRootState">
                    <tr v-for="category in 5" :key="category"
                        class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600"
                        @click="clickEvent">
                        <td class="p-4 w-10">
                            <img src="../../assets/potato.png" class="w-10 max-w-full max-h-full mx-auto">
                        </td>
                        <!-- 이미지 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            감자
                        </td>
                        <!-- 농산물이름 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            2000원
                        </td>
                        <!-- 현재가격 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            3000원
                        </td>
                        <!-- 다음 주 가격 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            50%
                        </td>
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            개당
                        </td>
                    </tr>
                </tbody>
                <tbody v-if="categoryGrainState">
                    <tr v-for="category in 5" :key="category"
                        class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600"
                        @click="clickEvent">
                        <td class="p-4 w-10">
                            <img src="../../assets/rice.png" class="w-10 max-w-full max-h-full mx-auto">
                        </td>
                        <!-- 이미지 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            쌀
                        </td>
                        <!-- 농산물이름 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            2000원
                        </td>
                        <!-- 현재가격 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            3000원
                        </td>
                        <!-- 다음 주 가격 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            50%
                        </td>
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            개당
                        </td>
                    </tr>
                </tbody>
                <tbody v-if="categoryFruitState">
                    <tr v-for="category in 5" :key="category"
                        class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600"
                        @click="clickEvent">
                        <td class="p-4 w-10">
                            <img src="../../assets/apple.png" class="w-10 max-w-full max-h-full mx-auto">
                        </td>
                        <!-- 이미지 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            사과
                        </td>
                        <!-- 농산물이름 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            2000원
                        </td>
                        <!-- 현재가격 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            3000원
                        </td>
                        <!-- 다음 주 가격 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            50%
                        </td>
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            개당
                        </td>
                    </tr>
                </tbody>
                <tbody v-if="categoryYellowCropState">
                    <tr v-for="category in 5" :key="category"
                        class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600"
                        @click="clickEvent">
                        <td class="p-4 w-10">
                            <img src="../../assets/sweetPotato.png" class="w-10 max-w-full max-h-full mx-auto">
                        </td>
                        <!-- 이미지 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            고구마
                        </td>
                        <!-- 농산물이름 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            2000원
                        </td>
                        <!-- 현재가격 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            3000원
                        </td>
                        <!-- 다음 주 가격 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            50%
                        </td>
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            개당
                        </td>
                    </tr>
                </tbody>

            </table>
        </div>


        <!-- 표에 표시될 내용들 -->

        <div v-if="clickState">
            <div class="flex justify-center items-center mt-12">
                <div
                    class="w-4/5 h-3/5 max-w-full p-6 bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">
                    <a>
                        <h5 class="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white">해당 품목의 가격 정보
                        </h5>
                        <button @click="transValue">값 변경</button>
                    </a>
                    <div class="mt-10 h-4/5">
                        <Line :data="chartData" :options="chartOptions" />
                    </div>

                </div>
            </div>
        </div>
        <!-- 추후 라디오버튼 필요 -->
    </div>



</template>

<script setup>
import { Chart as ChartJS, CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend } from 'chart.js'
import { Line } from 'vue-chartjs'
import { ref } from 'vue';


const clickState = ref(false)
//현재 클릭 상태를 나타내는 변수

const clickEvent = () => {
    clickState.value = !clickState.value
}
//클릭 시, 클릭 상태 변수를 switch하는 매서드.

const categoryVegetableLeafState = ref(false);
//채소-잎의 라디오버튼이 눌렸을 때, 상태

const categoryVegetableFruitState = ref(false);
//채소-열매의 라디오버튼이 눌렸을 때, 상태

const categoryVegetableRootState = ref(false);
//채소-뿌리의 라디오버튼이 눌렸을 떼, 상태

const categoryFruitState = ref(false);
//과일의 라디오버튼이 눌렸을 때, 상태

const categoryGrainState = ref(false);
//곡물의 라디오버튼이 눌렸을 때, 상태

const categoryYellowCropState = ref(false);
//구황작물의 라디오버튼이 눌렸을 때, 상태

const transferVegetableReaf = () => {
    categoryVegetableLeafState.value = true;
    categoryVegetableFruitState.value = false;
    categoryVegetableRootState.value = false;
    categoryGrainState.value = false;
    categoryFruitState.value = false;
    categoryYellowCropState.value = false;
};

const transferVegetableFruit = () => {
    categoryVegetableLeafState.value = false;
    categoryVegetableFruitState.value = true;
    categoryVegetableRootState.value = false;
    categoryGrainState.value = false;
    categoryFruitState.value = false;
    categoryYellowCropState.value = false;
};

const transferVegetableRoot = () => {
    categoryVegetableLeafState.value = false;
    categoryVegetableFruitState.value = false;
    categoryVegetableRootState.value = true;
    categoryGrainState.value = false;
    categoryFruitState.value = false;
    categoryYellowCropState.value = false;
};

const transferGrain = () => {
    categoryVegetableLeafState.value = false;
    categoryVegetableFruitState.value = false;
    categoryVegetableRootState.value = false;
    categoryGrainState.value = true;
    categoryFruitState.value = false;
    categoryYellowCropState.value = false;
};

const transferFruit = () => {
    categoryVegetableFruitState.value = false;
    categoryVegetableLeafState.value = false;
    categoryVegetableRootState.value = false;
    categoryGrainState.value = false;
    categoryFruitState.value = true;
    categoryYellowCropState.value = false;
};

const transferYellowCrop = () => {
    categoryVegetableFruitState.value = false;
    categoryVegetableLeafState.value = false;
    categoryVegetableRootState.value = false;
    categoryGrainState.value = false;
    categoryFruitState.value = false;
    categoryYellowCropState.value = true;
};

const fourWeeksAgoPrice = ref(5500);

const labelValue = ref('무언가의 가격정보(과거)')

const transValue = () => {
    fourWeeksAgoPrice.value = 10000;
    chartData.datasets[0].data[0] = fourWeeksAgoPrice.value; // 그래프 데이터에 변경된 값 반영
    console.log(fourWeeksAgoPrice.value)
}

//flow(저장용) 
// get axios로 정보를 받아옴
// 각각의 정보를 json형식으로 담고 리스트에 add
// 리스트를 1번부터 돌며 정보를 저장
// 클릭 이벤트가 개시되었을 때, 거기에 있는 정보를 변수에 담음
// 그 정보에 있는 과거가격,현재가격,미래가격을 그래프에 넣음
// 저장한 값으로 그래프에 표시


ChartJS.register(CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend)

const chartData = {
    labels: ['4주전', '3주전', '2주전', '1주전', '오늘', '1주 후', '2주 후'],
    datasets: [
        {
            label: labelValue.value,
            pointStyle: 'circle',
            backgroundColor: '#000000',
            radius: 5,
            data: [fourWeeksAgoPrice.value, 2500, 1000, 1500, 2300, null, null]  //실선영역(과거가격)
        },
        {
            label: '무언가의 가격정보(미래)',
            pointStyle: 'circle',
            backgroundColor: '#f87979',
            borderDash: [5, 5], //점선
            radius: 5,
            data: [null, null, null, null, 2300, 3500, 5000],  //점선영역(예측가격)
        }
    ]
}

const chartOptions = {
    responsive: true,
    maintainAspectRatio: false,
    scales: {
        x: {
            offset: true, //해당 요소가 반대축 절편에 붙지 않도록 만듬.
            grid: {
                drawOnChartArea: false, //해당 축의 격자선만 숨김.
            }
        },
        y: {
            beginAtZero: true, //y축의 최솟값을 0으로 만들어줌.
            grid: {
                drawOnChartArea: false,
            }
        }
    },
    plugins: {
        legend: {
            labels: {
                // pointStyle 속성을 설정하여 원으로 변경
                pointStyle: 'circle',
                usePointStyle: true,
            },
        },
    },

}
</script>

<style scoped>
@media (max-width: 1024px) {

    /* 창 폭이 1024px 이하로 줄어들 때 적용될 스타일 */
    .all {
        min-width: 1024px;
        /* 최소 폭을 1024px로 설정 */
        margin: 0 auto;
    }
}

.title {
    font-size: 38px;
}

.all {
    font-family: "HSBombaram3.0";
}
</style>