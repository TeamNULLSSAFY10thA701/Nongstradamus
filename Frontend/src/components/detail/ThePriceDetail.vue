<template>
    <div class="all max-w-5xl mx-auto">

        <VegetableTable></VegetableTable>
        <!-- 테이블 컴포넌트화 -->

        <div v-if="store.clickState">
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

    </div>



</template>

<script setup>
import { Chart as ChartJS, CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend } from 'chart.js'
import { Line } from 'vue-chartjs'
import { ref } from 'vue';
import VegetableTable from './VegetableTable.vue'

import { useVegetableTableStore } from '@/stores/VegetableTable';

const store = useVegetableTableStore();

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


.all {
    font-family: "HSBombaram3.0";
}
</style>