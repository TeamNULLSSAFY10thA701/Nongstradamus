<template>
    <div class="max-w-md mx-auto">
        <div class="grid grid-cols-3 gap-4 ml-4 mr-4 mt-8">
            <div>
                <img src="../../assets/full_logo1.png">
            </div>
            <div class="col-span-2 flex items-center justify-center text-3xl">
                오늘의 꿀 품목
            </div>

        </div>
        <!-- header -->
        <div class="flex items-center justify-center mt-12 ">
            <a
                class="block max-w-sm p-6 bg-sky-300 border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">

                <h5 class="mb-2 text-2xl font-bold tracking-tight text-white">가격이 가장 많이 떨어졌어요!</h5>
                <div class="grid grid-cols-5 gap-4">
                    <div class="col-span-3 text-center font-bold text-3xl text-white mt-6 mr-4">
                        품목
                    </div>
                    <div class="col-span-2 text-center font-bold text-xl rounded-lg bg-white border mt-8 mr-2">
                        가격
                    </div>
                </div>
            </a>
        </div>
        <!-- 가격이 가장 많이 떨어진 품목 조회 -->
        <div class="flex items-center justify-center mt-8">
            <a
                class="block max-w-sm p-6 bg-indigo-400 border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">

                <h5 class="custom mb-2 font-bold tracking-tight text-white">가격이 가장 많이 떨어질거에요!</h5>
                <div class="grid grid-cols-5 gap-4">
                    <div class="col-span-3 text-center font-bold text-3xl text-white mt-6 mr-4">
                        품목
                    </div>
                    <div class="col-span-2 text-center font-bold text-xl rounded-lg bg-white border mt-8 mr-2">
                        가격
                    </div>
                </div>
            </a>
        </div>
        <!-- 가격이 가장 많이 떨어질 것 같은 품목 조회 -->
        <div class="flex items-center justify-center mt-8">
            <a href="#"
                class="block max-w-sm p-6 bg-indigo-400 border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">
                <div class="grid grid-cols-3 gap-4">
                    <div class="flex items-center justify-center">
                        <img src="../../assets/recipe.png">
                    </div>
                    <div
                        class="col-span-2 font-bold tracking-tight text-white flex items-center justify-center text-2xl">
                        오늘의 추천 레시피</div>
                </div>
            </a>
        </div>
        <!-- 오늘의 추천 레시피 이동 -->

        <div>

        </div>
    </div>

</template>

<script setup>

</script>

<style scoped>
.custom {
    font-size: 22.3px;
}
</style>