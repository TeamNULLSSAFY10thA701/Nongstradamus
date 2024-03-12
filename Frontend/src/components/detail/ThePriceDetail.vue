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
        <div class="relative overflow-x-auto shadow-md sm:rounded-lg mt-12 w-4/5 h-3/5 mx-auto">
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
                            예측가격(중)
                        </th>
                        <th scope="col" class="px-6 py-3 text-center">
                            예측등락폭(%)
                        </th>
                        <th scope="col" class="px-6 py-3 text-center">
                            단위
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600"
                        @click="clickEvent">
                        <td class="p-4 w-10">
                            <img src="../../assets/food1.png" class="w-16 md:w-32 max-w-full max-h-full" alt="onion">
                        </td>
                        <!-- 이미지 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            양파
                        </td>
                        <!-- 농산물이름 -->
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            500원
                        </td>
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            700원
                        </td>
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            40%
                        </td>
                        <td class="px-6 py-4 text-center font-semibold text-gray-900 dark:text-white">
                            100g당
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>

        <div v-if="clickState">
            <div class="flex justify-center items-center mt-12">
                <div
                    class="w-4/5 h-3/5 max-w-full p-6 bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">
                    <a>
                        <h5 class="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white">해당 품목의 가격 정보
                        </h5>
                    </a>
                    <div class="mt-10 h-4/5">
                        <Line :data="chartData" :options="chartOptions" />
                    </div>

                </div>
            </div>
        </div>
    </div>



</template>

<script setup>
import { Chart as ChartJS, CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend } from 'chart.js'
import { Line } from 'vue-chartjs'
import { ref } from 'vue';

const clickState = ref(false)

const clickEvent = () => {
    clickState.value = !clickState.value
}

ChartJS.register(CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend)

const chartData = {
    labels: ['4주전', '3주전', '2주전', '1주전', '오늘', '1주 후', '2주 후'],
    datasets: [
        {
            label: '무언가의 가격정보(과거)',
            pointStyle: 'circle',
            backgroundColor: '#000000',
            radius: 5,
            data: [4200, 2500, 1000, 1500, 2300, null, null]  //실선영역(과거가격)
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