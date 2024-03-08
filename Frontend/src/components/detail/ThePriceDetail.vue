<template>
    <div class="max-w-md mx-auto">
        <Line :data="chartData" :options="chartOptions" />
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
            backgroundColor: '#f87979',
            data: [40, 39, 10, 40, 39, 80, 40]
        }
    ]
}

const chartOptions = {
    responsive: true,
    maintainAspectRatio: false,
    scales: {
        x: {
            offset: true,
            grid: {
                drawOnChartArea: false,
            }
        },
        y: {
            grid: {
                drawOnChartArea: false, // x축의 그리드 라인 숨김
            }
        }
    },

}
</script>

<style scoped></style>