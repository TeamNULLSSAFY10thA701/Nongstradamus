<template>
    <div class="all-font max-w-md mx-auto">
        <div class="mt-8 text-4xl w-4/5 mx-auto">
            <span class="text-red-600">오늘</span>꼭 사세요
        </div>
        <Carousel :autoplay="2500" :wrap-around="true" class="mt-4">
            <Slide v-for="(info, index) in todaySlide" :key="index">
                <!-- slide:배열의 각요소 index:배열의 인덱스 -->
                <div class="bg-orange-300 p-2 border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700 w-4/5 h-36 text-sm grid grid-cols-4 "
                    v-if="index == 0">
                    <div class="col-span-1 flex items-center justify-center">
                        <div class="mx-auto">
                            <img :src="getImageUrl(info.img)" class="w-14" />
                        </div>
                    </div>
                    <div class="col-span-3 flex items-center justify-center flex-col">
                        <div class="text-center text-lg">
                            <span class="text-lg">{{ info.name }}({{ info.unit }})</span>
                        </div>
                        <div class="text-center text-lg">
                            <div class="inline-flex items-center">
                                <div class="w-1/2">
                                    <div><span class="text-red-600">오늘</span>가격</div>
                                    <div>{{ info.priceToday }}원</div>
                                </div>
                                <img src="/src/assets/arrow.png" class="inline-block mx-1 w-1/12">
                                <div class="w-1/2">
                                    <div><span class="text-blue-600">내일</span>가격</div>
                                    <div>{{ info.priceTomorrow }}원</div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="bg-gray-400 p-2 border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700 w-4/5 h-36 text-sm flex items-center justify-center"
                    v-else>
                    {{ info }}
                </div>
            </Slide>
        </Carousel>
        <div class="mt-8 text-4xl w-4/5 mx-auto">
            <span class="text-blue-600">내일</span>꼭 사세요
        </div>
        <Carousel :autoplay="2500" :wrap-around="true" class="mt-4">
            <Slide v-for="(hi, index) in tomorrowSlide" :key="index">
                <!-- slide:배열의 각요소 index:배열의 인덱스 -->
                <div class="bg-orange-300 p-2 border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700 w-4/5 h-36 text-sm grid grid-cols-4 "
                    v-if="index == 0">
                    <div class="col-span-1 flex items-center justify-center">
                        <div class="mx-auto">
                            <img :src="getImageUrl(hi.img)" class="w-14" />
                        </div>
                    </div>
                    <div class="col-span-3 flex items-center justify-center flex-col">
                        <div class="text-center text-lg">
                            <span class="text-lg">방울토마토(100g)</span>
                        </div>
                        <div class="text-center text-lg">
                            <div class="inline-flex items-center">
                                <div class="w-1/2">
                                    <div><span class="text-red-600">오늘</span>가격</div>
                                    <div>{{ hi.priceToday }}원</div>
                                </div>
                                <img src="/src/assets/arrow.png" class="inline-block mx-1 w-1/12">
                                <div class="w-1/2">
                                    <div><span class="text-blue-600">내일</span>가격</div>
                                    <div>{{ hi.priceTomorrow }}원</div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="bg-gray-400 p-2 border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700 w-4/5 h-36 text-sm flex items-center justify-center"
                    v-else>
                    {{ hi }}
                </div>
            </Slide>
        </Carousel>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { Carousel, Slide } from 'vue3-carousel'
import 'vue3-carousel/dist/carousel.css'
import { getTodayInfo, getTomorrowInfo, getCardInfo } from '@/api/mainRemodel'

onMounted(() => {
    callToDayPrices()
});

const getImageUrl = (nickname) => {
    return `/${nickname}.png`;
};
//이미지를 넣는 메서드.

const todayInfo = ref({
    data: '',
    msg: '',
    code: ''
})

const todayMention = ref(['가격이 내일 오를 거에요. 오늘이 최저가!', '오늘 물품 중, 전날대비 가격이 가장 많이 떨어졌어요!', '앞으로 가격이 많이 오를거에요!', '추천할 만한 품목이 없어요...'])
const tomorrowMention = ref(['가격이 계속 하락세에 있어요. 내일 구매하세요!', '가격이 상승세에 있지만 내일부터 떨어질 거에요!', '내일 구매하시면, 가격이 떨어질 거에요!', '추천할 만한 품목이 없어요...'])
//받아오는 요청의 멘션 상태에 따라 보여줄 배열.
//현재 배열3번상태에 따른 v-if문 존재하지 않음. 나중에 바꾸기 필요.

const todaySlide = ref([{ img: todayInfo.value.data.nickname, priceToday: todayInfo.value.data.priceToday, priceTomorrow: todayInfo.value.data.priceTomorrow, name: todayInfo.value.data.name, unit: todayInfo.value.data.unit }, todayMention.value[todayInfo.value.data.mention]])
const tomorrowSlide = ref([{ img: 'tomato', priceToday: 1250, priceTomorrow: 1500 }, '해당 품목이 왜 여기 뜨는지 문구넣기'])
//슬라이드에 띄울 내용을 넣는 배열.



const callToDayPrices = () => {
    getTodayInfo((data) => {
        todayInfo.value.data = data.data.data
        todayInfo.value.msg = data.data.msg
        todayInfo.value.code = data.data.code
    },
        (error) => {
            console.error(error)
        }
    )
}

</script>

<style>
.all-font {
    font-family: "HSBombaram3.0";
}
</style>