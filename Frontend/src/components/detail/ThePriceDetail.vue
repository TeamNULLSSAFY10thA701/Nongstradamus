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
    labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
    datasets: [
        {
            label: 'Data One',
            backgroundColor: '#f87979',
            data: [40, 39, 10, 40, 39, 80, 40]
        }
    ]
}

const chartOptions = {
    responsive: true,
    maintainAspectRatio: false
}
</script>

<style scoped></style>