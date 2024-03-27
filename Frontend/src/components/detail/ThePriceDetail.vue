<template>
    <div class="all max-w-md mx-auto">
        <div class="grid grid-cols-6 gap-2 ml-4 mr-4 mt-8 mb-16 h-8">
            <div class="w-3/5 mx-auto col-span-2 ml-6" @click="goMainPage">
                <img src="/src/assets/full_logo1.png" />
            </div>
            <div class="title col-start-3 col-span-4 flex items-center">
                농스트라다무스의 예측
            </div>
        </div>
        <!-- header -->
        <ul
            class="grid grid-cols-3 items-center text-sm font-medium text-gray-900 bg-white border border-gray-200 rounded-lg sm:flex dark:bg-gray-700 dark:border-gray-600 dark:text-white w-4/5 mx-auto">
            <li class="dark:border-gray-600 flex items-center justify-center border-r border-b">
                <input id="VegetableReaf" type="radio" value="" name="list-radio"
                    class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-700 dark:focus:ring-offset-gray-700 focus:ring-2 dark:bg-gray-600 dark:border-gray-500 ml-2"
                    @click="transferVegetableReaf" checked>
                <label for="VegetableReaf"
                    class="w-full py-3 ms-2 text-xs font-medium text-gray-900 dark:text-gray-300 flex items-center justify-center"
                    @click="transferVegetableReaf">채소-잎
                </label>
            </li>
            <li class="dark:border-gray-600 flex items-center justify-center border-r border-b">
                <input id="VegetableFruit" type="radio" value="" name="list-radio"
                    class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-700 dark:focus:ring-offset-gray-700 focus:ring-2 dark:bg-gray-600 dark:border-gray-500 ml-2"
                    @click="transferVegetableFruit">
                <label for="VegetableFruit"
                    class="w-full py-3 ms-2 text-xs font-medium text-gray-900 dark:text-gray-300 flex items-center justify-center"
                    @click="transferVegetableFruit">채소-열매</label>
            </li>
            <li class="dark:border-gray-600 flex items-center justify-center border-r border-b">
                <input id="transferVegetableRoot" type="radio" value="" name="list-radio"
                    class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-700 dark:focus:ring-offset-gray-700 focus:ring-2 dark:bg-gray-600 dark:border-gray-500 ml-2"
                    @click="transferVegetableRoot">
                <label for="transferVegetableRoot"
                    class="w-full py-3 ms-1 text-xs font-medium text-gray-900 dark:text-gray-300 flex items-center justify-center"
                    @click="transferVegetableRoot">채소-뿌리</label>
            </li>
            <li class="dark:border-gray-600 flex items-center justify-center border-r border-b">
                <input id="Grain" type="radio" value="" name="list-radio"
                    class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-700 dark:focus:ring-offset-gray-700 focus:ring-2 dark:bg-gray-600 dark:border-gray-500 ml-2"
                    @click="transferGrain">
                <label for="Grain"
                    class="w-full py-3 ms-2 text-xs font-medium text-gray-900 dark:text-gray-300 flex items-center justify-center"
                    @click="transferGrain">곡물</label>
            </li>
            <li class="dark:border-gray-600 flex items-center justify-center border-r border-b">
                <input id="Fruit" type="radio" value="" name="list-radio"
                    class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-700 dark:focus:ring-offset-gray-700 focus:ring-2 dark:bg-gray-600 dark:border-gray-500 ml-2"
                    @click="transferFruit">
                <label for="Fruit"
                    class="w-full py-3 ms-2 text-xs font-medium text-gray-900 dark:text-gray-300 flex items-center justify-center"
                    @click="transferFruit">과일</label>
            </li>
            <li class="dark:border-gray-600 flex items-center justify-center border-r border-b">
                <input id="YellowCrop" type="radio" value="" name="list-radio"
                    class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-700 dark:focus:ring-offset-gray-700 focus:ring-2 dark:bg-gray-600 dark:border-gray-500 ml-2"
                    @click="transferYellowCrop">
                <label for="YellowCrop"
                    class="w-full py-3 ms-2 text-xs font-medium text-gray-900 dark:text-gray-300 flex items-center justify-center"
                    @click="transferYellowCrop">구황작물</label>
            </li>
        </ul>

        <!-- 카테고리 라디오버튼 -->

        <div class="relative overflow-x-auto shadow-md sm:rounded-lg mt-4 w-4/5 h-3/5 mx-auto"
            v-if="categoryVegetableLeafState || categoryVegetableFruitState || categoryVegetableRootState || categoryGrainState || categoryFruitState || categoryYellowCropState">
            <table class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
                <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
                    <tr>
                        <th scope="col" class="py-3 text-center w-24">
                            품목
                        </th>
                        <th scope="col" class="py-3 text-center w-20">
                            농산물이름
                        </th>
                        <th scope="col" class="py-3 text-center w-28">
                            현재가격(중품)
                        </th>
                    </tr>
                </thead>
                <tbody v-if="categoryVegetableLeafState">
                    <tr v-for="category in FutureVegetableLeafPricesAllData.table" :key="category"
                        class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600"
                        @click="vegetableLeafclickEvent(category.nickname, category.name)">
                        <td class="py-4 flex items-center justify-center">
                            <img :src="getImageUrl(category.nickname)" class="mx-auto w-2/5">
                        </td>
                        <!-- 이미지 -->
                        <td class="px-1 py-1 text-center font-semibold text-gray-900 dark:text-white">
                            {{ category.name }}<br>({{ category.unit }})
                        </td>
                        <!-- 농산물이름 -->
                        <td class="px-1 py-1 text-center font-semibold text-gray-900 dark:text-white">
                            <div v-if="category.ratio >= 0" class="text-red-600">
                                {{ category.present }}원<br>
                            </div>
                            <div v-else class="text-blue-600">
                                {{ category.present }}원<br>
                            </div>
                            <div v-if="category.ratio >= 0" class="flex items-center justify-center">
                                {{ category.ratio }}%
                                <img src="/src/assets/up.png" class="w-1/6 ml-1">
                            </div>
                            <div v-else class="flex items-center justify-center">
                                {{ -category.ratio }}%
                                <img src="/src/assets/down.png" class="w-1/6 ml-1">
                            </div>
                        </td>
                    </tr>
                </tbody>
                <tbody v-if="categoryVegetableFruitState">
                    <tr v-for="category in FutureVegetableFruitPricesAllData.table" :key="category"
                        class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600"
                        @click="vegetableFruitclickEvent(category.nickname, category.name)">
                        <td class="py-4 flex items-center justify-center">
                            <img :src="getImageUrl(category.nickname)" class="mx-auto w-2/5">
                        </td>
                        <!-- 이미지 -->
                        <td class="px-1 py-1 text-center font-semibold text-gray-900 dark:text-white">
                            {{ category.name }}<br>({{ category.unit }})
                        </td>
                        <!-- 농산물이름 -->
                        <td class="px-1 py-1 text-center font-semibold text-gray-900 dark:text-white">
                            <div v-if="category.ratio >= 0" class="text-red-600">
                                {{ category.present }}원<br>
                            </div>
                            <div v-else class="text-blue-600">
                                {{ category.present }}원<br>
                            </div>
                            <div v-if="category.ratio >= 0" class="flex items-center justify-center">
                                {{ category.ratio }}%
                                <img src="/src/assets/up.png" class="w-1/6 ml-1">
                            </div>
                            <div v-else class="flex items-center justify-center">
                                {{ -category.ratio }}%
                                <img src="/src/assets/down.png" class="w-1/6 ml-1">
                            </div>
                        </td>
                    </tr>
                </tbody>
                <tbody v-if="categoryVegetableRootState">
                    <tr v-for="category in FutureVegetableRootPricesAllData.table" :key="category"
                        class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600"
                        @click="vegetableRootclickEvent(category.nickname, category.name)">
                        <td class="py-4 flex items-center justify-center">
                            <img :src="getImageUrl(category.nickname)" class="mx-auto w-2/5">
                        </td>
                        <!-- 이미지 -->
                        <td class="px-1 py-1 text-center font-semibold text-gray-900 dark:text-white">
                            {{ category.name }}<br>({{ category.unit }})
                        </td>
                        <!-- 농산물이름 -->
                        <td class="px-1 py-1 text-center font-semibold text-gray-900 dark:text-white">
                            <div v-if="category.ratio >= 0" class="text-red-600">
                                {{ category.present }}원<br>
                            </div>
                            <div v-else class="text-blue-600">
                                {{ category.present }}원<br>
                            </div>
                            <div v-if="category.ratio >= 0" class="flex items-center justify-center">
                                {{ category.ratio }}%
                                <img src="/src/assets/up.png" class="w-1/6 ml-1">
                            </div>
                            <div v-else class="flex items-center justify-center">
                                {{ -category.ratio }}%
                                <img src="/src/assets/down.png" class="w-1/6 ml-1">
                            </div>
                        </td>
                    </tr>
                </tbody>
                <tbody v-if="categoryGrainState">
                    <tr v-for="category in FutureGrainPricesAllData.table" :key="category"
                        class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600"
                        @click="grainclickEvent(category.nickname, category.name)">
                        <td class="py-4 flex items-center justify-center">
                            <img :src="getImageUrl(category.nickname)" class="mx-auto w-2/5">
                        </td>
                        <!-- 이미지 -->
                        <td class="px-1 py-1 text-center font-semibold text-gray-900 dark:text-white">
                            {{ category.name }}<br>({{ category.unit }})
                        </td>
                        <!-- 농산물이름 -->
                        <td class="px-1 py-1 text-center font-semibold text-gray-900 dark:text-white">
                            <div v-if="category.ratio >= 0" class="text-red-600">
                                {{ category.present }}원<br>
                            </div>
                            <div v-else class="text-blue-600">
                                {{ category.present }}원<br>
                            </div>
                            <div v-if="category.ratio >= 0" class="flex items-center justify-center">
                                {{ category.ratio }}%
                                <img src="/src/assets/up.png" class="w-1/6 ml-1">
                            </div>
                            <div v-else class="flex items-center justify-center">
                                {{ -category.ratio }}%
                                <img src="/src/assets/down.png" class="w-1/6 ml-1">
                            </div>
                        </td>
                    </tr>
                </tbody>
                <tbody v-if="categoryFruitState">
                    <tr v-for="category in FutureFruitPricesAllData.table" :key="category"
                        class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600"
                        @click="fruitclickEvent(category.nickname, category.name)">
                        <td class="py-4 flex items-center justify-center">
                            <img :src="getImageUrl(category.nickname)" class="mx-auto w-2/5">
                        </td>
                        <!-- 이미지 -->
                        <td class="px-1 py-1 text-center font-semibold text-gray-900 dark:text-white">
                            {{ category.name }}<br>({{ category.unit }})
                        </td>
                        <!-- 농산물이름 -->
                        <td class="px-1 py-1 text-center font-semibold text-gray-900 dark:text-white">
                            <div v-if="category.ratio >= 0" class="text-red-600">
                                {{ category.present }}원<br>
                            </div>
                            <div v-else class="text-blue-600">
                                {{ category.present }}원<br>
                            </div>
                            <div v-if="category.ratio >= 0" class="flex items-center justify-center">
                                {{ category.ratio }}%
                                <img src="/src/assets/up.png" class="w-1/6 ml-1">
                            </div>
                            <div v-else class="flex items-center justify-center">
                                {{ -category.ratio }}%
                                <img src="/src/assets/down.png" class="w-1/6 ml-1">
                            </div>
                        </td>
                    </tr>
                </tbody>
                <tbody v-if="categoryYellowCropState">
                    <tr v-for="category in FutureYellowCropPricesAllData.table" :key="category"
                        class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600"
                        @click="yellowCropclickEvent(category.nickname, category.name)">
                        <td class="py-4 flex items-center justify-center">
                            <img :src="getImageUrl(category.nickname)" class="mx-auto w-2/5">
                        </td>
                        <!-- 이미지 -->
                        <td class="px-1 py-1 text-center font-semibold text-gray-900 dark:text-white">
                            {{ category.name }}<br>({{ category.unit }})
                        </td>
                        <!-- 농산물이름 -->
                        <td class="px-1 py-1 text-center font-semibold text-gray-900 dark:text-white">
                            <div v-if="category.ratio >= 0" class="text-red-600">
                                {{ category.present }}원<br>
                            </div>
                            <div v-else class="text-blue-600">
                                {{ category.present }}원<br>
                            </div>
                            <div v-if="category.ratio >= 0" class="flex items-center justify-center">
                                {{ category.ratio }}%
                                <img src="/src/assets/up.png" class="w-1/6 ml-1">
                            </div>
                            <div v-else class="flex items-center justify-center">
                                {{ -category.ratio }}%
                                <img src="/src/assets/down.png" class="w-1/6 ml-1">
                            </div>
                        </td>
                    </tr>
                </tbody>

            </table>
        </div>

        <div v-if="clickState" class="mb-16">
            <div class="flex justify-center items-center mt-12">
                <div
                    class="w-full h-3/5 max-w-full p-6 bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">
                    <a>
                        <h5 class="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white">{{
                nameBasket }}의 가격 정보
                        </h5>
                    </a>
                    <ul
                        class="grid grid-cols-4 mt-6 items-center text-sm font-medium text-gray-900 bg-white border border-gray-200 rounded-lg sm:flex dark:bg-gray-700 dark:border-gray-600 dark:text-white w-4/5 mx-auto">
                        <li class="w-full dark:border-gray-600 border-r">
                            <div class="flex items-center justify-center ps-1">
                                <input id="bestClick" type="radio" name="list-radio2"
                                    class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-700 dark:focus:ring-offset-gray-700 focus:ring-2 dark:bg-gray-600 dark:border-gray-500"
                                    @click="bestClick" :checked="bestClickState">
                                <label for="bestClick"
                                    class="w-full py-3 ms-2 text-xs font-medium text-gray-900 dark:text-gray-300">특
                                </label>
                            </div>
                        </li>
                        <li class="w-full dark:border-gray-600 border-r">
                            <div class="flex items-center justify-center ps-1">
                                <input id="goodClick" type="radio" name="list-radio2"
                                    class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-700 dark:focus:ring-offset-gray-700 focus:ring-2 dark:bg-gray-600 dark:border-gray-500"
                                    @click="goodClick" :checked="goodClickState">
                                <label for="goodClick"
                                    class="w-full py-3 ms-2 text-xs font-medium text-gray-900 dark:text-gray-300">상
                                </label>
                            </div>
                        </li>
                        <li class="w-full dark:border-gray-600 border-r">
                            <div class="flex items-center justify-center ps-1">
                                <input id="midClick" type="radio" name="list-radio2"
                                    class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-700 dark:focus:ring-offset-gray-700 focus:ring-2 dark:bg-gray-600 dark:border-gray-500"
                                    @click="midClick" :checked="midClickState">
                                <label for="midClick"
                                    class="w-full py-3 ms-2 text-xs font-medium text-gray-900 dark:text-gray-300">중</label>
                            </div>
                        </li>
                        <li class="w-full dark:border-gray-600 border-r">
                            <div class="flex items-center justify-center ps-1">
                                <input id="lowClick" type="radio" name="list-radio2"
                                    class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-700 dark:focus:ring-offset-gray-700 focus:ring-2 dark:bg-gray-600 dark:border-gray-500"
                                    @click="lowClick" :checked="lowClickState">
                                <label for="lowClick"
                                    class="w-full py-3 ms-2 text-xs font-medium text-gray-900 dark:text-gray-300">하</label>
                            </div>
                        </li>
                    </ul>
                    <div class="mt-6 h-4/5" v-if="bestClickState">
                        <Line :data="chartData" :options="chartOptions" />
                    </div>
                    <div class="mt-6 h-4/5" v-if="goodClickState">
                        <Line :data="chartData" :options="chartOptions" />
                    </div>
                    <div class="mt-6 h-4/5" v-if="midClickState">
                        <Line :data="chartData" :options="chartOptions" />
                    </div>
                    <div class="mt-6 h-4/5" v-if="lowClickState">
                        <Line :data="chartData" :options="chartOptions" />

                    </div>
                    <button type="button"
                        class="flex items-center justify-center mx-auto mt-8 px-3 py-2 text-sm font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
                        @click="cancelClickEvent">닫기</button>

                </div>

            </div>
        </div>

    </div>



</template>

<script setup>
import { Chart as ChartJS, CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend } from 'chart.js'
import { Line } from 'vue-chartjs'
import { onMounted, ref } from 'vue';
import {
    getFutureVegetableLeafPrices,
    getFutureVegetableFruitPrices,
    getFutureVegetableRootPrices,
    getFutureGrainPrices,
    getFutureFruitPrices,
    getFutureYellowCropPrices,
} from "@/api/predictAllPrices";
import router from '@/router';


// --------------------------------------------------------------------------------import-------------------------------------------------------

onMounted(() => {
    callFutureVegetableLeafPricesAllData()
    callFutureVegetableFruitPricesAllData()
    callFutureVegetableRootPricesAllData()
    callFutureGrainPricesAllData()
    callFutureFruitPricesAllData()
    callFutureYellowCropPricesAllData()
});

// --------------------------------------------------------------------------------onMounted-------------------------------------------------------
const tableSwitchState = ref(false)

const check = (a) => {
    console.log(a)
}

const getImageUrl = (nickname) => {
    return `/${nickname}.png`;
};
//이미지를 불러오는 메서드

const goMainPage = () => {
    router.push('/')
}
//로고를 클릭 시, 메인 페이지로 돌아가는 메서드.

const clickState = ref(false)
//클릭 시, 해당 품목의 세부 그래프를 볼 수 있게 만들어주는 변수.

const bestClickState = ref(false)

const goodClickState = ref(false)

const midClickState = ref(false)

const lowClickState = ref(false)
//특상,상,중,하 품목의 카테고리를 선택했는지 상태를 나타내는 변수.

const clickEvent = () => {
    if (clickState.value == false) {
        clickState.value = !clickState.value
    }
    bestClickState.value = false
    goodClickState.value = false
    midClickState.value = false
    lowClickState.value = false
}
//클릭 시, 세부 그래프를 나타내는 메서드.
//초기상태에서 클릭 시, 세부 그래프를 랜더링함.
//이미 세부 그래프가 나타난 상황에서 같은 계열, 다른 품목을 고를 경우, 세부 그래프를 끄지 않기 위하여 if문 설정.

const cancelClickEvent = () => {
    clickState.value = !clickState.value
    bestClickState.value = false
    goodClickState.value = false
    midClickState.value = false
    lowClickState.value = false
}
//"닫기"버튼이나 다른계열로 넘어갈 시, 스위치 기능을 하는 메서드.

const categoryBasket = ref('')
//특정 농산물 클릭 시, 해당 카테고리를 담을 변수.

const nickNameBasket = ref('')
//특정 농산물 클릭 시, 해당 농산물의 닉네임을 담을 변수

const nameBasket = ref('')
//특정 농산물 클릭 시, 해당 농산물의 이름을 담을 변수

const fourWeekAgoPrice = ref('')

const threeWeekAgoPrice = ref('')

const twoWeekAgoPrice = ref('')

const oneWeekAgoPrice = ref('')

const toDayPrice = ref('')

const oneWeekAfterPrice = ref('')

const twoWeekAfterPrice = ref('')

//각 그래프에 담을 변수


const bestClick = () => {
    bestClickState.value = true
    goodClickState.value = false
    midClickState.value = false
    lowClickState.value = false
    if (categoryBasket.value == 'FutureVegetableLeafPricesAllData') {
        fourWeekAgoPrice.value = FutureVegetableLeafPricesAllData.value.data[nickNameBasket.value].best[-4].price
        threeWeekAgoPrice.value = FutureVegetableLeafPricesAllData.value.data[nickNameBasket.value].best[-3].price
        twoWeekAgoPrice.value = FutureVegetableLeafPricesAllData.value.data[nickNameBasket.value].best[-2].price
        oneWeekAgoPrice.value = FutureVegetableLeafPricesAllData.value.data[nickNameBasket.value].best[-1].price
        toDayPrice.value = FutureVegetableLeafPricesAllData.value.data[nickNameBasket.value].best[0].price
        oneWeekAfterPrice.value = FutureVegetableLeafPricesAllData.value.data[nickNameBasket.value].best[1].price
        twoWeekAfterPrice.value = FutureVegetableLeafPricesAllData.value.data[nickNameBasket.value].best[2].price
    }
    else if (categoryBasket.value == 'FutureVegetableFruitPricesAllData') {
        fourWeekAgoPrice.value = FutureVegetableFruitPricesAllData.value.data[nickNameBasket.value].best[-4].price
        threeWeekAgoPrice.value = FutureVegetableFruitPricesAllData.value.data[nickNameBasket.value].best[-3].price
        twoWeekAgoPrice.value = FutureVegetableFruitPricesAllData.value.data[nickNameBasket.value].best[-2].price
        oneWeekAgoPrice.value = FutureVegetableFruitPricesAllData.value.data[nickNameBasket.value].best[-1].price
        toDayPrice.value = FutureVegetableFruitPricesAllData.value.data[nickNameBasket.value].best[0].price
        oneWeekAfterPrice.value = FutureVegetableFruitPricesAllData.value.data[nickNameBasket.value].best[1].price
        twoWeekAfterPrice.value = FutureVegetableFruitPricesAllData.value.data[nickNameBasket.value].best[2].price
    }
    else if (categoryBasket.value == 'FutureVegetableRootPricesAllData') {
        fourWeekAgoPrice.value = FutureVegetableRootPricesAllData.value.data[nickNameBasket.value].best[-4].price
        threeWeekAgoPrice.value = FutureVegetableRootPricesAllData.value.data[nickNameBasket.value].best[-3].price
        twoWeekAgoPrice.value = FutureVegetableRootPricesAllData.value.data[nickNameBasket.value].best[-2].price
        oneWeekAgoPrice.value = FutureVegetableRootPricesAllData.value.data[nickNameBasket.value].best[-1].price
        toDayPrice.value = FutureVegetableRootPricesAllData.value.data[nickNameBasket.value].best[0].price
        oneWeekAfterPrice.value = FutureVegetableRootPricesAllData.value.data[nickNameBasket.value].best[1].price
        twoWeekAfterPrice.value = FutureVegetableRootPricesAllData.value.data[nickNameBasket.value].best[2].price
    }
    else if (categoryBasket.value == 'FutureGrainPricesAllData') {
        fourWeekAgoPrice.value = FutureGrainPricesAllData.value.data[nickNameBasket.value].best[-4].price
        threeWeekAgoPrice.value = FutureGrainPricesAllData.value.data[nickNameBasket.value].best[-3].price
        twoWeekAgoPrice.value = FutureGrainPricesAllData.value.data[nickNameBasket.value].best[-2].price
        oneWeekAgoPrice.value = FutureGrainPricesAllData.value.data[nickNameBasket.value].best[-1].price
        toDayPrice.value = FutureGrainPricesAllData.value.data[nickNameBasket.value].best[0].price
        oneWeekAfterPrice.value = FutureGrainPricesAllData.value.data[nickNameBasket.value].best[1].price
        twoWeekAfterPrice.value = FutureGrainPricesAllData.value.data[nickNameBasket.value].best[2].price
    }
    else if (categoryBasket.value == 'FutureFruitPricesAllData') {
        fourWeekAgoPrice.value = FutureFruitPricesAllData.value.data[nickNameBasket.value].best[-4].price
        threeWeekAgoPrice.value = FutureFruitPricesAllData.value.data[nickNameBasket.value].best[-3].price
        twoWeekAgoPrice.value = FutureFruitPricesAllData.value.data[nickNameBasket.value].best[-2].price
        oneWeekAgoPrice.value = FutureFruitPricesAllData.value.data[nickNameBasket.value].best[-1].price
        toDayPrice.value = FutureFruitPricesAllData.value.data[nickNameBasket.value].best[0].price
        oneWeekAfterPrice.value = FutureFruitPricesAllData.value.data[nickNameBasket.value].best[1].price
        twoWeekAfterPrice.value = FutureFruitPricesAllData.value.data[nickNameBasket.value].best[2].price
    }
    else if (categoryBasket.value == 'FutureYellowCropPricesAllData') {
        fourWeekAgoPrice.value = FutureYellowCropPricesAllData.value.data[nickNameBasket.value].best[-4].price
        threeWeekAgoPrice.value = FutureYellowCropPricesAllData.value.data[nickNameBasket.value].best[-3].price
        twoWeekAgoPrice.value = FutureYellowCropPricesAllData.value.data[nickNameBasket.value].best[-2].price
        oneWeekAgoPrice.value = FutureYellowCropPricesAllData.value.data[nickNameBasket.value].best[-1].price
        toDayPrice.value = FutureYellowCropPricesAllData.value.data[nickNameBasket.value].best[0].price
        oneWeekAfterPrice.value = FutureYellowCropPricesAllData.value.data[nickNameBasket.value].best[1].price
        twoWeekAfterPrice.value = FutureYellowCropPricesAllData.value.data[nickNameBasket.value].best[2].price
    }
    else {
        console.log('오류')
    }
    updateChartData();
}
//

const goodClick = () => {
    bestClickState.value = false
    goodClickState.value = true
    midClickState.value = false
    lowClickState.value = false
    if (categoryBasket.value == 'FutureVegetableLeafPricesAllData') {
        fourWeekAgoPrice.value = FutureVegetableLeafPricesAllData.value.data[nickNameBasket.value].good[-4].price
        threeWeekAgoPrice.value = FutureVegetableLeafPricesAllData.value.data[nickNameBasket.value].good[-3].price
        twoWeekAgoPrice.value = FutureVegetableLeafPricesAllData.value.data[nickNameBasket.value].good[-2].price
        oneWeekAgoPrice.value = FutureVegetableLeafPricesAllData.value.data[nickNameBasket.value].good[-1].price
        toDayPrice.value = FutureVegetableLeafPricesAllData.value.data[nickNameBasket.value].good[0].price
        oneWeekAfterPrice.value = FutureVegetableLeafPricesAllData.value.data[nickNameBasket.value].good[1].price
        twoWeekAfterPrice.value = FutureVegetableLeafPricesAllData.value.data[nickNameBasket.value].good[2].price
    }
    else if (categoryBasket.value == 'FutureVegetableFruitPricesAllData') {
        fourWeekAgoPrice.value = FutureVegetableFruitPricesAllData.value.data[nickNameBasket.value].good[-4].price
        threeWeekAgoPrice.value = FutureVegetableFruitPricesAllData.value.data[nickNameBasket.value].good[-3].price
        twoWeekAgoPrice.value = FutureVegetableFruitPricesAllData.value.data[nickNameBasket.value].good[-2].price
        oneWeekAgoPrice.value = FutureVegetableFruitPricesAllData.value.data[nickNameBasket.value].good[-1].price
        toDayPrice.value = FutureVegetableFruitPricesAllData.value.data[nickNameBasket.value].good[0].price
        oneWeekAfterPrice.value = FutureVegetableFruitPricesAllData.value.data[nickNameBasket.value].good[1].price
        twoWeekAfterPrice.value = FutureVegetableFruitPricesAllData.value.data[nickNameBasket.value].good[2].price
    }
    else if (categoryBasket.value == 'FutureVegetableRootPricesAllData') {
        fourWeekAgoPrice.value = FutureVegetableRootPricesAllData.value.data[nickNameBasket.value].good[-4].price
        threeWeekAgoPrice.value = FutureVegetableRootPricesAllData.value.data[nickNameBasket.value].good[-3].price
        twoWeekAgoPrice.value = FutureVegetableRootPricesAllData.value.data[nickNameBasket.value].good[-2].price
        oneWeekAgoPrice.value = FutureVegetableRootPricesAllData.value.data[nickNameBasket.value].good[-1].price
        toDayPrice.value = FutureVegetableRootPricesAllData.value.data[nickNameBasket.value].good[0].price
        oneWeekAfterPrice.value = FutureVegetableRootPricesAllData.value.data[nickNameBasket.value].good[1].price
        twoWeekAfterPrice.value = FutureVegetableRootPricesAllData.value.data[nickNameBasket.value].good[2].price
    }
    else if (categoryBasket.value == 'FutureGrainPricesAllData') {
        fourWeekAgoPrice.value = FutureGrainPricesAllData.value.data[nickNameBasket.value].good[-4].price
        threeWeekAgoPrice.value = FutureGrainPricesAllData.value.data[nickNameBasket.value].good[-3].price
        twoWeekAgoPrice.value = FutureGrainPricesAllData.value.data[nickNameBasket.value].good[-2].price
        oneWeekAgoPrice.value = FutureGrainPricesAllData.value.data[nickNameBasket.value].good[-1].price
        toDayPrice.value = FutureGrainPricesAllData.value.data[nickNameBasket.value].good[0].price
        oneWeekAfterPrice.value = FutureGrainPricesAllData.value.data[nickNameBasket.value].good[1].price
        twoWeekAfterPrice.value = FutureGrainPricesAllData.value.data[nickNameBasket.value].good[2].price
    }
    else if (categoryBasket.value == 'FutureFruitPricesAllData') {
        fourWeekAgoPrice.value = FutureFruitPricesAllData.value.data[nickNameBasket.value].good[-4].price
        threeWeekAgoPrice.value = FutureFruitPricesAllData.value.data[nickNameBasket.value].good[-3].price
        twoWeekAgoPrice.value = FutureFruitPricesAllData.value.data[nickNameBasket.value].good[-2].price
        oneWeekAgoPrice.value = FutureFruitPricesAllData.value.data[nickNameBasket.value].good[-1].price
        toDayPrice.value = FutureFruitPricesAllData.value.data[nickNameBasket.value].good[0].price
        oneWeekAfterPrice.value = FutureFruitPricesAllData.value.data[nickNameBasket.value].good[1].price
        twoWeekAfterPrice.value = FutureFruitPricesAllData.value.data[nickNameBasket.value].good[2].price
    }
    else if (categoryBasket.value == 'FutureYellowCropPricesAllData') {
        fourWeekAgoPrice.value = FutureYellowCropPricesAllData.value.data[nickNameBasket.value].good[-4].price
        threeWeekAgoPrice.value = FutureYellowCropPricesAllData.value.data[nickNameBasket.value].good[-3].price
        twoWeekAgoPrice.value = FutureYellowCropPricesAllData.value.data[nickNameBasket.value].good[-2].price
        oneWeekAgoPrice.value = FutureYellowCropPricesAllData.value.data[nickNameBasket.value].good[-1].price
        toDayPrice.value = FutureYellowCropPricesAllData.value.data[nickNameBasket.value].good[0].price
        oneWeekAfterPrice.value = FutureYellowCropPricesAllData.value.data[nickNameBasket.value].good[1].price
        twoWeekAfterPrice.value = FutureYellowCropPricesAllData.value.data[nickNameBasket.value].good[2].price
    }
    else {
        console.log('오류')
    }
    updateChartData();
}

const midClick = () => {
    bestClickState.value = false
    goodClickState.value = false
    midClickState.value = true
    lowClickState.value = false
    if (categoryBasket.value == 'FutureVegetableLeafPricesAllData') {
        fourWeekAgoPrice.value = FutureVegetableLeafPricesAllData.value.data[nickNameBasket.value].mid[-4].price
        threeWeekAgoPrice.value = FutureVegetableLeafPricesAllData.value.data[nickNameBasket.value].mid[-3].price
        twoWeekAgoPrice.value = FutureVegetableLeafPricesAllData.value.data[nickNameBasket.value].mid[-2].price
        oneWeekAgoPrice.value = FutureVegetableLeafPricesAllData.value.data[nickNameBasket.value].mid[-1].price
        toDayPrice.value = FutureVegetableLeafPricesAllData.value.data[nickNameBasket.value].mid[0].price
        oneWeekAfterPrice.value = FutureVegetableLeafPricesAllData.value.data[nickNameBasket.value].mid[1].price
        twoWeekAfterPrice.value = FutureVegetableLeafPricesAllData.value.data[nickNameBasket.value].mid[2].price
    }
    else if (categoryBasket.value == 'FutureVegetableFruitPricesAllData') {
        fourWeekAgoPrice.value = FutureVegetableFruitPricesAllData.value.data[nickNameBasket.value].mid[-4].price
        threeWeekAgoPrice.value = FutureVegetableFruitPricesAllData.value.data[nickNameBasket.value].mid[-3].price
        twoWeekAgoPrice.value = FutureVegetableFruitPricesAllData.value.data[nickNameBasket.value].mid[-2].price
        oneWeekAgoPrice.value = FutureVegetableFruitPricesAllData.value.data[nickNameBasket.value].mid[-1].price
        toDayPrice.value = FutureVegetableFruitPricesAllData.value.data[nickNameBasket.value].mid[0].price
        oneWeekAfterPrice.value = FutureVegetableFruitPricesAllData.value.data[nickNameBasket.value].mid[1].price
        twoWeekAfterPrice.value = FutureVegetableFruitPricesAllData.value.data[nickNameBasket.value].mid[2].price
    }
    else if (categoryBasket.value == 'FutureVegetableRootPricesAllData') {
        fourWeekAgoPrice.value = FutureVegetableRootPricesAllData.value.data[nickNameBasket.value].mid[-4].price
        threeWeekAgoPrice.value = FutureVegetableRootPricesAllData.value.data[nickNameBasket.value].mid[-3].price
        twoWeekAgoPrice.value = FutureVegetableRootPricesAllData.value.data[nickNameBasket.value].mid[-2].price
        oneWeekAgoPrice.value = FutureVegetableRootPricesAllData.value.data[nickNameBasket.value].mid[-1].price
        toDayPrice.value = FutureVegetableRootPricesAllData.value.data[nickNameBasket.value].mid[0].price
        oneWeekAfterPrice.value = FutureVegetableRootPricesAllData.value.data[nickNameBasket.value].mid[1].price
        twoWeekAfterPrice.value = FutureVegetableRootPricesAllData.value.data[nickNameBasket.value].mid[2].price
    }
    else if (categoryBasket.value == 'FutureGrainPricesAllData') {
        fourWeekAgoPrice.value = FutureGrainPricesAllData.value.data[nickNameBasket.value].mid[-4].price
        threeWeekAgoPrice.value = FutureGrainPricesAllData.value.data[nickNameBasket.value].mid[-3].price
        twoWeekAgoPrice.value = FutureGrainPricesAllData.value.data[nickNameBasket.value].mid[-2].price
        oneWeekAgoPrice.value = FutureGrainPricesAllData.value.data[nickNameBasket.value].mid[-1].price
        toDayPrice.value = FutureGrainPricesAllData.value.data[nickNameBasket.value].mid[0].price
        oneWeekAfterPrice.value = FutureGrainPricesAllData.value.data[nickNameBasket.value].mid[1].price
        twoWeekAfterPrice.value = FutureGrainPricesAllData.value.data[nickNameBasket.value].mid[2].price
    }
    else if (categoryBasket.value == 'FutureFruitPricesAllData') {
        fourWeekAgoPrice.value = FutureFruitPricesAllData.value.data[nickNameBasket.value].mid[-4].price
        threeWeekAgoPrice.value = FutureFruitPricesAllData.value.data[nickNameBasket.value].mid[-3].price
        twoWeekAgoPrice.value = FutureFruitPricesAllData.value.data[nickNameBasket.value].mid[-2].price
        oneWeekAgoPrice.value = FutureFruitPricesAllData.value.data[nickNameBasket.value].mid[-1].price
        toDayPrice.value = FutureFruitPricesAllData.value.data[nickNameBasket.value].mid[0].price
        oneWeekAfterPrice.value = FutureFruitPricesAllData.value.data[nickNameBasket.value].mid[1].price
        twoWeekAfterPrice.value = FutureFruitPricesAllData.value.data[nickNameBasket.value].mid[2].price
    }
    else if (categoryBasket.value == 'FutureYellowCropPricesAllData') {
        fourWeekAgoPrice.value = FutureYellowCropPricesAllData.value.data[nickNameBasket.value].mid[-4].price
        threeWeekAgoPrice.value = FutureYellowCropPricesAllData.value.data[nickNameBasket.value].mid[-3].price
        twoWeekAgoPrice.value = FutureYellowCropPricesAllData.value.data[nickNameBasket.value].mid[-2].price
        oneWeekAgoPrice.value = FutureYellowCropPricesAllData.value.data[nickNameBasket.value].mid[-1].price
        toDayPrice.value = FutureYellowCropPricesAllData.value.data[nickNameBasket.value].mid[0].price
        oneWeekAfterPrice.value = FutureYellowCropPricesAllData.value.data[nickNameBasket.value].mid[1].price
        twoWeekAfterPrice.value = FutureYellowCropPricesAllData.value.data[nickNameBasket.value].mid[2].price
    }
    else {
        console.log('오류')
    }
    updateChartData();

}

const lowClick = () => {
    bestClickState.value = false
    goodClickState.value = false
    midClickState.value = false
    lowClickState.value = true
    if (categoryBasket.value == 'FutureVegetableLeafPricesAllData') {
        fourWeekAgoPrice.value = FutureVegetableLeafPricesAllData.value.data[nickNameBasket.value].low[-4].price
        threeWeekAgoPrice.value = FutureVegetableLeafPricesAllData.value.data[nickNameBasket.value].low[-3].price
        twoWeekAgoPrice.value = FutureVegetableLeafPricesAllData.value.data[nickNameBasket.value].low[-2].price
        oneWeekAgoPrice.value = FutureVegetableLeafPricesAllData.value.data[nickNameBasket.value].low[-1].price
        toDayPrice.value = FutureVegetableLeafPricesAllData.value.data[nickNameBasket.value].low[0].price
        oneWeekAfterPrice.value = FutureVegetableLeafPricesAllData.value.data[nickNameBasket.value].low[1].price
        twoWeekAfterPrice.value = FutureVegetableLeafPricesAllData.value.data[nickNameBasket.value].low[2].price
    }
    else if (categoryBasket.value == 'FutureVegetableFruitPricesAllData') {
        fourWeekAgoPrice.value = FutureVegetableFruitPricesAllData.value.data[nickNameBasket.value].low[-4].price
        threeWeekAgoPrice.value = FutureVegetableFruitPricesAllData.value.data[nickNameBasket.value].low[-3].price
        twoWeekAgoPrice.value = FutureVegetableFruitPricesAllData.value.data[nickNameBasket.value].low[-2].price
        oneWeekAgoPrice.value = FutureVegetableFruitPricesAllData.value.data[nickNameBasket.value].low[-1].price
        toDayPrice.value = FutureVegetableFruitPricesAllData.value.data[nickNameBasket.value].low[0].price
        oneWeekAfterPrice.value = FutureVegetableFruitPricesAllData.value.data[nickNameBasket.value].low[1].price
        twoWeekAfterPrice.value = FutureVegetableFruitPricesAllData.value.data[nickNameBasket.value].low[2].price
    }
    else if (categoryBasket.value == 'FutureVegetableRootPricesAllData') {
        fourWeekAgoPrice.value = FutureVegetableRootPricesAllData.value.data[nickNameBasket.value].low[-4].price
        threeWeekAgoPrice.value = FutureVegetableRootPricesAllData.value.data[nickNameBasket.value].low[-3].price
        twoWeekAgoPrice.value = FutureVegetableRootPricesAllData.value.data[nickNameBasket.value].low[-2].price
        oneWeekAgoPrice.value = FutureVegetableRootPricesAllData.value.data[nickNameBasket.value].low[-1].price
        toDayPrice.value = FutureVegetableRootPricesAllData.value.data[nickNameBasket.value].low[0].price
        oneWeekAfterPrice.value = FutureVegetableRootPricesAllData.value.data[nickNameBasket.value].low[1].price
        twoWeekAfterPrice.value = FutureVegetableRootPricesAllData.value.data[nickNameBasket.value].low[2].price
    }
    else if (categoryBasket.value == 'FutureGrainPricesAllData') {
        fourWeekAgoPrice.value = FutureGrainPricesAllData.value.data[nickNameBasket.value].low[-4].price
        threeWeekAgoPrice.value = FutureGrainPricesAllData.value.data[nickNameBasket.value].low[-3].price
        twoWeekAgoPrice.value = FutureGrainPricesAllData.value.data[nickNameBasket.value].low[-2].price
        oneWeekAgoPrice.value = FutureGrainPricesAllData.value.data[nickNameBasket.value].low[-1].price
        toDayPrice.value = FutureGrainPricesAllData.value.data[nickNameBasket.value].low[0].price
        oneWeekAfterPrice.value = FutureGrainPricesAllData.value.data[nickNameBasket.value].low[1].price
        twoWeekAfterPrice.value = FutureGrainPricesAllData.value.data[nickNameBasket.value].low[2].price
    }
    else if (categoryBasket.value == 'FutureFruitPricesAllData') {
        fourWeekAgoPrice.value = FutureFruitPricesAllData.value.data[nickNameBasket.value].low[-4].price
        threeWeekAgoPrice.value = FutureFruitPricesAllData.value.data[nickNameBasket.value].low[-3].price
        twoWeekAgoPrice.value = FutureFruitPricesAllData.value.data[nickNameBasket.value].low[-2].price
        oneWeekAgoPrice.value = FutureFruitPricesAllData.value.data[nickNameBasket.value].low[-1].price
        toDayPrice.value = FutureFruitPricesAllData.value.data[nickNameBasket.value].low[0].price
        oneWeekAfterPrice.value = FutureFruitPricesAllData.value.data[nickNameBasket.value].low[1].price
        twoWeekAfterPrice.value = FutureFruitPricesAllData.value.data[nickNameBasket.value].low[2].price
    }
    else if (categoryBasket.value == 'FutureYellowCropPricesAllData') {
        fourWeekAgoPrice.value = FutureYellowCropPricesAllData.value.data[nickNameBasket.value].low[-4].price
        threeWeekAgoPrice.value = FutureYellowCropPricesAllData.value.data[nickNameBasket.value].low[-3].price
        twoWeekAgoPrice.value = FutureYellowCropPricesAllData.value.data[nickNameBasket.value].low[-2].price
        oneWeekAgoPrice.value = FutureYellowCropPricesAllData.value.data[nickNameBasket.value].low[-1].price
        toDayPrice.value = FutureYellowCropPricesAllData.value.data[nickNameBasket.value].low[0].price
        oneWeekAfterPrice.value = FutureYellowCropPricesAllData.value.data[nickNameBasket.value].low[1].price
        twoWeekAfterPrice.value = FutureYellowCropPricesAllData.value.data[nickNameBasket.value].low[2].price
    }
    else {
        console.log('오류')
    }
    updateChartData();

}
//특상,상,중,하의 가격 정보를 그래프에 불러오는 매서드.
//불러온 데이터에서 데이터의 형식에 맞춰 바구니에 담은 닉네임을 기준으로 가격을 찾음.
//저장 후, 해당 데이터를 그래프에 반영하기 위하여 updateChartData();메서드를 사용.

const vegetableLeafclickEvent = (nickname, aname) => {
    nameBasket.value = aname
    nickNameBasket.value = nickname
    categoryBasket.value = 'FutureVegetableLeafPricesAllData'
    clickEvent()
}

const vegetableFruitclickEvent = (nickname, aname) => {
    nameBasket.value = aname
    nickNameBasket.value = nickname
    categoryBasket.value = 'FutureVegetableFruitPricesAllData'
    clickEvent()
}

const vegetableRootclickEvent = (nickname, aname) => {
    nameBasket.value = aname
    nickNameBasket.value = nickname
    categoryBasket.value = 'FutureVegetableRootPricesAllData'
    clickEvent()
}

const grainclickEvent = (nickname, aname) => {
    nameBasket.value = aname
    nickNameBasket.value = nickname
    categoryBasket.value = 'FutureGrainPricesAllData'
    clickEvent()
}

const fruitclickEvent = (nickname, aname) => {
    nameBasket.value = aname
    nickNameBasket.value = nickname
    categoryBasket.value = 'FutureFruitPricesAllData'
    clickEvent()
}

const yellowCropclickEvent = (nickname, aname) => {
    nameBasket.value = aname
    nickNameBasket.value = nickname
    categoryBasket.value = 'FutureYellowCropPricesAllData'
    clickEvent()
}
//해당 계열에서 품목을 결정했을 때, 요소를 특정짓는 메서드.
//각 바구니로 현재 계열,품목,표시할이름을 특정지어 사용.

const updateChartData = () => {
    chartData.datasets[1].label = `${nameBasket.value}의 과거가격`;
    chartData.datasets[0].label = `${nameBasket.value}의 미래가격`;
    chartData.datasets[1].data = [fourWeekAgoPrice.value, threeWeekAgoPrice.value, twoWeekAgoPrice.value, oneWeekAgoPrice.value, toDayPrice.value, null, null];
    chartData.datasets[0].data = [null, null, null, null, toDayPrice.value, oneWeekAfterPrice.value, twoWeekAfterPrice.value];
}
//해당 차트의 요소를 업데이트 시키는 메서드

// --------------------------------------------------------------------------공통-------------------------------------------------------

const categoryVegetableLeafState = ref(true);
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
    clickState.value = false;
};

const transferVegetableFruit = () => {
    categoryVegetableLeafState.value = false;
    categoryVegetableFruitState.value = true;
    categoryVegetableRootState.value = false;
    categoryGrainState.value = false;
    categoryFruitState.value = false;
    categoryYellowCropState.value = false;
    clickState.value = false;
};

const transferVegetableRoot = () => {
    categoryVegetableLeafState.value = false;
    categoryVegetableFruitState.value = false;
    categoryVegetableRootState.value = true;
    categoryGrainState.value = false;
    categoryFruitState.value = false;
    categoryYellowCropState.value = false;
    clickState.value = false;
};

const transferGrain = () => {
    categoryVegetableLeafState.value = false;
    categoryVegetableFruitState.value = false;
    categoryVegetableRootState.value = false;
    categoryGrainState.value = true;
    categoryFruitState.value = false;
    categoryYellowCropState.value = false;
    clickState.value = false;
};

const transferFruit = () => {
    categoryVegetableFruitState.value = false;
    categoryVegetableLeafState.value = false;
    categoryVegetableRootState.value = false;
    categoryGrainState.value = false;
    categoryFruitState.value = true;
    categoryYellowCropState.value = false;
    clickState.value = false;
};

const transferYellowCrop = () => {
    categoryVegetableFruitState.value = false;
    categoryVegetableLeafState.value = false;
    categoryVegetableRootState.value = false;
    categoryGrainState.value = false;
    categoryFruitState.value = false;
    categoryYellowCropState.value = true;
    clickState.value = false;
};
// 농산물 종류 라디오 버튼을 눌렀을 때, 상태를 나타내는 메서드들

// --------------------------------------------------------------------------TableClick-------------------------------------------------------

const FutureVegetableLeafPricesAllData = ref({
    data: "",
    msg: "",
    code: "",
    table: [],
});
//채소-잎의 모든 정보를 담아올 변수.

const FutureVegetableFruitPricesAllData = ref({
    data: "",
    msg: "",
    code: "",
    table: [],
});
//채소-과일의 모든 정보를 담아올 변수

const FutureVegetableRootPricesAllData = ref({
    data: "",
    msg: "",
    code: "",
    table: [],
});
//채소-뿌리의 모든 정보를 담아올 변수

const FutureGrainPricesAllData = ref({
    data: "",
    msg: "",
    code: "",
    table: [],
});
//곡물의 모든 정보를 담아올 변수

const FutureFruitPricesAllData = ref({
    data: "",
    msg: "",
    code: "",
    table: [],
});
//과일의 모든 정보를 담아올 변수

const FutureYellowCropPricesAllData = ref({
    data: "",
    msg: "",
    code: "",
    table: [],
});
//구황작물의 모든 정보를 담아올 변수

const callFutureVegetableLeafPricesAllData = () => {
    getFutureVegetableLeafPrices(
        (data) => {
            FutureVegetableLeafPricesAllData.value.data = data.data.data;
            FutureVegetableLeafPricesAllData.value.msg = data.data.msg;
            FutureVegetableLeafPricesAllData.value.code = data.data.code;
            FutureVegetableLeafPricesAllData.value.table = data.data.data.table;
        },
        (error) => {
            console.error(error);
        }
    );
}

const callFutureVegetableFruitPricesAllData = () => {
    getFutureVegetableFruitPrices(
        (data) => {
            FutureVegetableFruitPricesAllData.value.data = data.data.data;
            FutureVegetableFruitPricesAllData.value.msg = data.data.msg;
            FutureVegetableFruitPricesAllData.value.code = data.data.code;
            FutureVegetableFruitPricesAllData.value.table = data.data.data.table;
        },
        (error) => {
            console.error(error);
        }
    );

}

const callFutureVegetableRootPricesAllData = () => {
    getFutureVegetableRootPrices(
        (data) => {
            FutureVegetableRootPricesAllData.value.data = data.data.data;
            FutureVegetableRootPricesAllData.value.msg = data.data.msg;
            FutureVegetableRootPricesAllData.value.code = data.data.code;
            FutureVegetableRootPricesAllData.value.table = data.data.data.table;
        },
        (error) => {
            console.error(error);
        }
    );

}

const callFutureGrainPricesAllData = () => {
    getFutureGrainPrices(
        (data) => {
            FutureGrainPricesAllData.value.data = data.data.data;
            FutureGrainPricesAllData.value.msg = data.data.msg;
            FutureGrainPricesAllData.value.code = data.data.code;
            FutureGrainPricesAllData.value.table = data.data.data.table;
        },
        (error) => {
            console.error(error);
        }
    );

}

const callFutureFruitPricesAllData = () => {
    getFutureFruitPrices(
        (data) => {
            FutureFruitPricesAllData.value.data = data.data.data;
            FutureFruitPricesAllData.value.msg = data.data.msg;
            FutureFruitPricesAllData.value.code = data.data.code;
            FutureFruitPricesAllData.value.table = data.data.data.table;
        },
        (error) => {
            console.error(error);
        }
    );

}

const callFutureYellowCropPricesAllData = () => {
    getFutureYellowCropPrices(
        (data) => {
            FutureYellowCropPricesAllData.value.data = data.data.data;
            FutureYellowCropPricesAllData.value.msg = data.data.msg;
            FutureYellowCropPricesAllData.value.code = data.data.code;
            FutureYellowCropPricesAllData.value.table = data.data.data.table;
        },
        (error) => {
            console.error(error);
        }
    );

}
//axios를 이용하여 데이터를 가져오는 메서드.
//data.data가 원본데이터이며, 세부적인 데이터를 원할경우, 추가로 진입해야함.




ChartJS.register(CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend)


const chartData = {
    labels: ['4주전', '3주전', '2주전', '1주전', '오늘', '1주 후', '2주 후'],
    datasets: [
        {
            //미래 그래프(오늘,1주,2주)
            pointStyle: 'circle',
            backgroundColor: '#f87979',
            borderDash: [5, 5], //점선
            radius: 5,
        },
        {
            //과거 그래프(1주전~4주전)
            pointStyle: 'circle',
            backgroundColor: '#000000',
            radius: 5,
        },

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
            // beginAtZero: true, //y축의 최솟값을 0으로 만들어줌.
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
.all {
    font-family: "HSBombaram3.0";
}

.title {
    font-size: 22px;
}
</style>