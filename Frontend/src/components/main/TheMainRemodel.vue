<template>
    <div class="all-font max-w-md mx-auto">
        <div class="ml-4 mr-4 mt-8 mb-8 h-8">
            <div class="flex items-center justify-center mx-auto text-4xl">
                <span class="text-red-600">오늘</span>의 꿀 품목
            </div>
            <div class="text-xs text-right mt-2">
                매일 오전 12시에 갱신됩니다.
            </div>
            <div class="text-xs text-right">
                갱신까지 앞으로 {{ remainingTime }} ({{ currentTime }}기준)
            </div>
        </div>
        <div class="mt-12 text-2xl w-4/5 mx-auto">
            <span class="text-red-600 text-4xl">오늘</span> 꼭! 사세요
        </div>
        <Carousel :autoplay="3000" :wrap-around="true" class="mt-4" v-if="notTodayProduct != 3">
            <Slide v-for="(info, todayIndex) in todaySlide" :key="todayIndex">
                <!-- info:배열의 각요소 index:배열의 인덱스 -->
                <div class="bg-orange-300 p-2 border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700 w-4/5 h-36 text-sm grid grid-cols-4 "
                    v-if="todayIndex == 0">
                    <div class="col-span-1 flex items-center justify-center rounded-lg bg-white h-24 my-auto">
                        <div class="mx-auto">
                            <img :src="info.img" class="w-14" />
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
                <div class="bg-orange-300 p-2 border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700 w-4/5 h-36 text-sm flex items-center justify-center"
                    v-else>
                    {{ info }}
                </div>
            </Slide>
        </Carousel>
        <div v-else class="mt-4 flex items-center justify-center">
            <div
                class="bg-orange-300 p-2 border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700 w-4/5 h-36 text-sm flex items-center justify-center">
                {{ todayMention[3] }}
            </div>
        </div>
        <div class="mt-8 text-2xl w-4/5 mx-auto">
            <span class="text-blue-600 text-4xl">내일</span> 꼭! 사세요
        </div>
        <Carousel :autoplay="3000" :wrap-around="true" class="mt-4" v-if="notTomorrowProduct != 3">
            <Slide v-for="(info2, tomorrowIndex) in tomorrowSlide" :key="tomorrowIndex">
                <!-- slide:배열의 각요소 index:배열의 인덱스 -->
                <div class="bg-orange-300 p-2 border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700 w-4/5 h-36 text-sm grid grid-cols-4 "
                    v-if="tomorrowIndex == 0">
                    <div class="col-span-1 flex items-center justify-center rounded-lg bg-white h-24 my-auto">
                        <div class="mx-auto">
                            <img :src="info2.img" class="w-14" />
                        </div>
                    </div>
                    <div class="col-span-3 flex items-center justify-center flex-col">
                        <div class="text-center text-lg">
                            <span class="text-lg">{{ info2.name }}({{ info2.unit }})</span>
                        </div>
                        <div class="text-center text-lg">
                            <div class="inline-flex items-center">
                                <div class="w-1/2">
                                    <div><span class="text-red-600">오늘</span>가격</div>
                                    <div>{{ info2.priceToday }}원</div>
                                </div>
                                <img src="/src/assets/arrow.png" class="inline-block mx-1 w-1/12">
                                <div class="w-1/2">
                                    <div><span class="text-blue-600">내일</span>가격</div>
                                    <div>{{ info2.priceTomorrow }}원</div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="bg-orange-300 p-2 border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700 w-4/5 h-36 text-sm flex items-center justify-center"
                    v-else>
                    {{ info2 }}
                </div>
            </Slide>
        </Carousel>
        <div v-else class="mt-4 flex items-center justify-center">
            <div
                class="bg-orange-300 p-2 border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700 w-4/5 h-36 text-sm flex items-center justify-center">
                {{ todayMention[3] }}
            </div>
        </div>
        <div class="mt-8 text-2xl w-4/5 mx-auto">
            <span class="text-red-600 text-4xl">오늘!</span> 구매하기좋아요
        </div>
        <Carousel :autoplay="2500" :wrap-around="true" class="mt-4 mb-24">
            <Slide v-for="(content, cardIndex) in cardInfo.data" :key="cardIndex">
                <div
                    class="bg-orange-300 p-2 border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700 w-4/5 h-48 text-sm grid grid-cols-3">
                    <div class="col-span-1 flex items-center justify-center rounded-lg bg-white h-28 my-auto relative">
                        <div class="image-container relative">
                            <img :src="`${content.nickname}.png`" class="w-14" />
                            <img v-if="cardIndex == 0" src="/src/assets/gold.png" class="absolute top-0 right-0 w-6"
                                style="z-index: 1;" />
                            <img v-else-if="cardIndex == 1" src="/src/assets/sliver.png"
                                class="absolute top-0 right-0 w-6" style="z-index: 1;" />
                            <img v-else-if="cardIndex == 2" src="/src/assets/bronze.png"
                                class="absolute top-0 right-0 w-6" style="z-index: 1;" />
                        </div>
                    </div>
                    <div class="col-span-2 flex items-center justify-center flex-col h-28 my-auto">
                        <div class="text-center text-lg">
                            <div class="text-center text-lg">
                                <div class="inline-flex flex-col items-center">
                                    <div class="h-1/3 rounded-lg bg-white w-40 mx-auto">
                                        {{ content.name }} ({{ content.unit }})
                                    </div>

                                    <div class="h-1/3 rounded-lg bg-white mt-3 w-40 mx-auto">
                                        <span class="text-black text-sm">{{ content.priceTomorrow }}원</span>
                                        <span class="mx-1 text-sm">&gt;</span>
                                        <span class="text-red-600 text-sm">{{ content.priceToday }}원</span>
                                    </div>
                                    <div class="h-1/3 rounded-lg bg-white mt-3  w-40 mx-auto text-red-600"
                                        v-if="content.ratio >= 0">
                                        {{ content.ratio }}%증가
                                    </div>
                                    <div class="h-1/3 rounded-lg bg-white mt-3  w-40 mx-auto text-blue-600" v-else>
                                        {{ -content.ratio }}%감소
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>

                </div>
            </Slide>
        </Carousel>
    </div>
</template>

<script setup>
import { ref, onMounted, watchEffect } from 'vue'
import { Carousel, Slide } from 'vue3-carousel'
import 'vue3-carousel/dist/carousel.css'
import { getTodayInfo, getTomorrowInfo, getCardInfo } from '@/api/mainRemodel'

onMounted(() => {
    callToDayPrices()
    callTomorrowPrices()
    callCardInfo()
});

const currentTime = ref('');
const remainingTime = ref('');

const updateRemainingTime = () => {
    // 현재 시간 객체 생성
    const now = new Date();
    const currentHours = now.getHours()
    const currentMinutes = now.getMinutes()
    const period = currentHours >= 12 ? '오후' : '오전'
    const hour = currentHours > 12 ? currentHours - 12 : currentHours

    // 갱신 시간 설정 (오전 12시)
    const refreshTime = new Date(now);
    refreshTime.setHours(24, 0, 0, 0); // 24시로 설정하여 다음 날 오전 12시로 설정

    // 현재 시간부터 갱신까지 남은 시간 계산 (분 단위)
    const timeDiff = Math.ceil((refreshTime.getTime() - now.getTime()) / (1000 * 60));

    // 분 단위로 계산된 남은 시간을 시간과 분으로 분리
    const hours = Math.floor(timeDiff / 60);
    const minutes = timeDiff % 60;

    // 표시할 형식에 맞게 문자열 생성
    currentTime.value = `${period} ${hour}시 ${currentMinutes}분`
    remainingTime.value = `${hours}시간 ${minutes}분`;
};


watchEffect(() => {
    updateRemainingTime();
    setTimeout(updateRemainingTime, 60000);
});


const todayMention = ref(['가격이 내일 오를 거에요. 오늘이 최저가!', '가격이 계속 떨어지고 있어요!', '앞으로 가격이 많이 오를거에요!', '추천할 만한 품목이 없어요...'])
const tomorrowMention = ref(['가격이 계속 하락세에 있어요!(가격이 오르더라도, 앞으론 더오를거에요...)', '가격이 상승세에 있지만, 내일부터 떨어질 거에요!', '내일 구매하시면, 가격이 떨어질 거에요!', '추천할 만한 품목이 없어요...'])
//받아오는 요청의 멘션 상태에 따라 보여줄 배열.
//현재 배열3번상태에 따른 템플릿코드에 v-if문 존재하지 않음. 나중에 바꾸기 필요.

const todaySlide = ref([])
const tomorrowSlide = ref([])
//슬라이드에 띄울 내용을 넣는 배열.

const cardInfo = ref({
    data: [],
    msg: '',
    code: ''
})

const notTodayProduct = ref('')

const notTomorrowProduct = ref('')

const callToDayPrices = () => {
    getTodayInfo((data) => {
        todaySlide.value = [{
            img: `/${data.data.data.nickname}.png`,
            priceToday: data.data.data.priceToday,
            priceTomorrow: data.data.data.priceTomorrow,
            name: data.data.data.name,
            unit: data.data.data.unit
        },
        todayMention.value[data.data.data.mention]]
        notTodayProduct.value = data.data.data.mention
    },
        (error) => {
            console.error(error)
        })
}

const callTomorrowPrices = () => {
    getTomorrowInfo((data) => {
        tomorrowSlide.value = [{
            img: `/${data.data.data.nickname}.png`,
            priceToday: data.data.data.priceToday,
            priceTomorrow: data.data.data.priceTomorrow,
            name: data.data.data.name,
            unit: data.data.data.unit
        },
        tomorrowMention.value[data.data.data.mention]]
        notTomorrowProduct.value = data.data.data.mention
    },
        (error) => {
            console.error(error)
        })
}

const callCardInfo = () => {
    getCardInfo((data) => {
        cardInfo.value.data = data.data.data
        cardInfo.value.msg = data.data.msg
        cardInfo.value.code = data.data.code

    }, (error) => {
        console.error(error)
    })

}

</script>

<style>
.all-font {
    font-family: "HSBombaram3.0";
}
</style>