<template>
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
                <label for="VegetableReaf" class="w-full py-3 ms-2 text-sm font-medium text-gray-900 dark:text-gray-300"
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
                <label for="YellowCrop" class="w-full py-3 ms-2 text-sm font-medium text-gray-900 dark:text-gray-300"
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
</template>
<script setup>

import { useVegetableTableStore } from '@/stores/VegetableTable';
const store = useVegetableTableStore();

</script>
<style scoped></style>