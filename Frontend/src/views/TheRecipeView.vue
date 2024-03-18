<template>
  <div class="all-font max-w-md mx-auto">
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

.recipe-component {
  margin-bottom: 7%;
}
</style>
