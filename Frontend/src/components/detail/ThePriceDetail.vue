<template>
    <div class="a flex justify-center items-center h-screen">
        <div
            class=" w-2/5 h-3/5 max-w-full p-6 bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">
            <a>
                <h5 class="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white">해당 품목의 가격 정보</h5>
            </a>
            <div class="mt-10 h-3/5">
                <Line :data="chartData" :options="chartOptions" />
            </div>
            <div class="mt-10 grid grid-cols-3 gap-4">
                <div class="col-start-3 flex items-center justify-end">
                    <a href="/"
                        class="inline-flex items-center px-6 py-3.5 text-sm font-medium text-center text-white bg-blue-700 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
                        돌아가기
                        <svg class="rtl:rotate-180 w-3.5 h-3.5 ms-2" aria-hidden="true"
                            xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 14 10">
                            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                d="M1 5h12m0 0L9 1m4 4L9 9" />
                        </svg>
                    </a>
                </div>
            </div>
        </div>
    </div>




</template>

<script setup>
import { Chart as ChartJS, CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend } from 'chart.js'
import { Line } from 'vue-chartjs'

ChartJS.register(CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend)

const chartData = {
    labels: ['4주전', '3주전', '2주전', '1주전', '오늘', '1주 후', '2주 후'],
    datasets: [
        {
            label: '무언가의 가격정보',
            pointStyle: 'circle',
            backgroundColor: '#f87979',
            radius: 5,
            data: [4200, 2500, 1000, 1500, 2300, 3500, 5000]
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
                // 원의 반지름 크기를 조절
                usePointStyle: true,
            },
        },
    },

}
</script>

<style scoped></style>