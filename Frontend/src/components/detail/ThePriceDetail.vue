<template>
    <div class="all max-w-5xl mx-auto">

        <div class="grid grid-cols-5 gap-4 mt-8">
            <div class="w-4/5 mx-auto col-start-2">
                <img src="/src/assets/full_logo1.png" />
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
                    <tr v-for="category in FutureVegetableLeafPricesAllData.table" :key="category"
                        class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600"
                        @click="vegetableLeafclickEvent(category.nickname, category.name)">
                        <td class="p-4 w-10">
                            <img :src="getImageUrl(category.nickname)" class="w-10 max-w-full max-h-full mx-auto">
                        </td>
                        <!-- 이미지 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            {{ category.name }}
                        </td>
                        <!-- 농산물이름 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            {{ category.present }}
                        </td>
                        <!-- 현재가격 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            {{ category.future }}
                        </td>
                        <!-- 다음 주 가격 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            {{ category.ratio }}
                        </td>
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            {{ category.unit }}
                        </td>
                    </tr>
                </tbody>
                <tbody v-if="categoryVegetableFruitState">
                    <tr v-for="category in FutureVegetableFruitPricesAllData.table" :key="category"
                        class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600"
                        @click="vegetableFruitclickEvent(category.nickname, category.name)">
                        <td class="p-4 w-10">
                            <img :src="getImageUrl(category.nickname)" class="w-10 max-w-full max-h-full mx-auto">
                        </td>
                        <!-- 이미지 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            {{ category.name }}
                        </td>
                        <!-- 농산물이름 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            {{ category.present }}
                        </td>
                        <!-- 현재가격 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            {{ category.future }}
                        </td>
                        <!-- 다음 주 가격 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            {{ category.ratio }}
                        </td>
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            {{ category.unit }}
                        </td>
                    </tr>
                </tbody>
                <tbody v-if="categoryVegetableRootState">
                    <tr v-for="category in FutureVegetableRootPricesAllData.table" :key="category"
                        class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600"
                        @click="vegetableRootclickEvent(category.nickname, category.name)">
                        <td class="p-4 w-10">
                            <img :src="getImageUrl(category.nickname)" class="w-10 max-w-full max-h-full mx-auto">
                        </td>
                        <!-- 이미지 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            {{ category.name }}
                        </td>
                        <!-- 농산물이름 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            {{ category.present }}
                        </td>
                        <!-- 현재가격 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            {{ category.future }}
                        </td>
                        <!-- 다음 주 가격 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            {{ category.ratio }}
                        </td>
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            {{ category.unit }}
                        </td>
                    </tr>
                </tbody>
                <tbody v-if="categoryGrainState">
                    <tr v-for="category in FutureGrainPricesAllData.table" :key="category"
                        class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600"
                        @click="grainclickEvent(category.nickname, category.name)">
                        <td class="p-4 w-10">
                            <img :src="getImageUrl(category.nickname)" class="w-10 max-w-full max-h-full mx-auto">
                        </td>
                        <!-- 이미지 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            {{ category.name }}
                        </td>
                        <!-- 농산물이름 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            {{ category.present }}
                        </td>
                        <!-- 현재가격 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            {{ category.future }}
                        </td>
                        <!-- 다음 주 가격 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            {{ category.ratio }}
                        </td>
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            {{ category.unit }}
                        </td>
                    </tr>
                </tbody>
                <tbody v-if="categoryFruitState">
                    <tr v-for="category in FutureFruitPricesAllData.table" :key="category"
                        class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600"
                        @click="fruitclickEvent(category.nickname, category.name)">
                        <td class="p-4 w-10">
                            <img :src="getImageUrl(category.nickname)" class="w-10 max-w-full max-h-full mx-auto">
                        </td>
                        <!-- 이미지 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            {{ category.name }}
                        </td>
                        <!-- 농산물이름 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            {{ category.present }}
                        </td>
                        <!-- 현재가격 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            {{ category.future }}
                        </td>
                        <!-- 다음 주 가격 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            {{ category.ratio }}
                        </td>
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            {{ category.unit }}
                        </td>
                    </tr>
                </tbody>
                <tbody v-if="categoryYellowCropState">
                    <tr v-for="category in FutureYellowCropPricesAllData.table" :key="category"
                        class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600"
                        @click="yellowCropclickEvent(category.nickname, category.name)">
                        <td class="p-4 w-10">
                            <img :src="getImageUrl(category.nickname)" class="w-10 max-w-full max-h-full mx-auto">
                        </td>
                        <!-- 이미지 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            {{ category.name }}
                        </td>
                        <!-- 농산물이름 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            {{ category.present }}
                        </td>
                        <!-- 현재가격 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            {{ category.future }}
                        </td>
                        <!-- 다음 주 가격 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            {{ category.ratio }}
                        </td>
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            {{ category.unit }}
                        </td>
                    </tr>
                </tbody>

            </table>
        </div>

        <div v-if="clickState" class="mb-16">
            <div class="flex justify-center items-center mt-12">
                <div
                    class="w-4/5 h-3/5 max-w-full p-6 bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">
                    <a>
                        <h5 class="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white">해당 품목의 가격 정보
                        </h5>
                    </a>
                    <ul
                        class="mt-6 items-center text-sm font-medium text-gray-900 bg-white border border-gray-200 rounded-lg sm:flex dark:bg-gray-700 dark:border-gray-600 dark:text-white w-4/5 mx-auto">
                        <li class="w-full border-b border-gray-200 sm:border-b-0 sm:border-r dark:border-gray-600">
                            <div class="flex items-center ps-3">
                                <input id="bestClick" type="radio" name="list-radio"
                                    class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-700 dark:focus:ring-offset-gray-700 focus:ring-2 dark:bg-gray-600 dark:border-gray-500"
                                    @click="bestClick">
                                <label for="bestClick"
                                    class="w-full py-3 ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">특상
                                </label>
                            </div>
                        </li>
                        <li class="w-full border-b border-gray-200 sm:border-b-0 sm:border-r dark:border-gray-600">
                            <div class="flex items-center ps-3">
                                <input id="goodClick" type="radio" name="list-radio"
                                    class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-700 dark:focus:ring-offset-gray-700 focus:ring-2 dark:bg-gray-600 dark:border-gray-500"
                                    @click="goodClick">
                                <label for="goodClick"
                                    class="w-full py-3 ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">상
                                </label>
                            </div>
                        </li>
                        <li class="w-full border-b border-gray-200 sm:border-b-0 sm:border-r dark:border-gray-600">
                            <div class="flex items-center ps-3">
                                <input id="midClick" type="radio" name="list-radio"
                                    class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-700 dark:focus:ring-offset-gray-700 focus:ring-2 dark:bg-gray-600 dark:border-gray-500"
                                    @click="midClick">
                                <label for="midClick"
                                    class="w-full py-3 ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">중</label>
                            </div>
                        </li>
                        <li class="w-full border-b border-gray-200 sm:border-b-0 sm:border-r dark:border-gray-600">
                            <div class="flex items-center ps-3">
                                <input id="lowClick" type="radio" name="list-radio"
                                    class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500 dark:focus:ring-blue-600 dark:ring-offset-gray-700 dark:focus:ring-offset-gray-700 focus:ring-2 dark:bg-gray-600 dark:border-gray-500"
                                    @click="lowClick">
                                <label for="lowClick"
                                    class="w-full py-3 ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">하</label>
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
                        @click="clickEvent">닫기</button>

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

const getImageUrl = (nickname) => {
    return `/${nickname}.png`;
};
//이미지를 불러오는 메서드

const clickState = ref(false)

const bestClickState = ref(false)

const goodClickState = ref(false)

const midClickState = ref(false)

const lowClickState = ref(false)

const clickEvent = () => {
    clickState.value = !clickState.value
    bestClickState.value = false
    goodClickState.value = false
    midClickState.value = false
    lowClickState.value = false
}

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

const updateChartData = () => {
    chartData.datasets[0].label = `${nameBasket.value}의 과거가격`;
    chartData.datasets[1].label = `${nameBasket.value}의 미래가격`;
    chartData.datasets[0].data = [fourWeekAgoPrice.value, threeWeekAgoPrice.value, twoWeekAgoPrice.value, oneWeekAgoPrice.value, toDayPrice.value, null, null];
    chartData.datasets[1].data = [null, null, null, null, toDayPrice.value, oneWeekAfterPrice.value, twoWeekAfterPrice.value];
}
//해당 차트의 요소를 업데이트 시키는 메서드

// --------------------------------------------------------------------------공통-------------------------------------------------------

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




ChartJS.register(CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend)


const chartData = {
    labels: ['4주전', '3주전', '2주전', '1주전', '오늘', '1주 후', '2주 후'],
    datasets: [
        {
            pointStyle: 'circle',
            backgroundColor: '#000000',
            radius: 5,
        },
        {
            pointStyle: 'circle',
            backgroundColor: '#f87979',
            borderDash: [5, 5], //점선
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
@media (max-width: 1024px) {

    /* 창 폭이 1024px 이하로 줄어들 때 적용될 스타일 */
    .all {
        min-width: 1024px;
        /* 최소 폭을 1024px로 설정 */
        margin: 0 auto;
    }
}


.all {
    font-family: "HSBombaram3.0";
}

.title {
    font-size: 38px;
}
</style>