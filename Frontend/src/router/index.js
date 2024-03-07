import { createRouter, createWebHistory } from "vue-router";
import TheMainView from "/src/views/TheMainView.vue";
import TheRecipeView from "@/views/TheRecipeView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: TheMainView,
    },
    {
      path: "/recipe",
      name: "recipe",
      component: TheRecipeView,
    },
  ],
});

export default router;
