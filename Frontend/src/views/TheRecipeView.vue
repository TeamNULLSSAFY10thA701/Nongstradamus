<template>
  <div class="all-font max-w-md mx-auto">
    <TheHeader></TheHeader>
    <TheRecipeBody></TheRecipeBody>
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
</style>
