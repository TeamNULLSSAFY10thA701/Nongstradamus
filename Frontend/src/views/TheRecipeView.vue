<template>
  <div class="parent all-font mx-auto">
    <TheHeader class="recipe-component"></TheHeader>
    <TheRecipeBody class="recipe-component"></TheRecipeBody>
  </div>
</template>

<script setup>
import TheHeader from "@/components/recipe/TheHeader.vue";
import TheRecipeBody from "@/components/recipe/TheRecipeBody.vue";
</script>

<style scoped>
.all-font {
  font-family: "HSBombaram3.0";
}

.parent {
  width: 80%; /* 부모 요소의 가로 길이 설정 */
  margin: 0 auto; /* 가운데 정렬을 위한 마진 설정 */
}

.recipe-component {
  margin-bottom: 7%;
}
</style>
