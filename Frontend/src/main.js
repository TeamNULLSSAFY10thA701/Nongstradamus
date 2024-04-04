import { createApp } from "vue";
import { createPinia } from "pinia";
import piniaPluginPersistedstate from "pinia-plugin-persistedstate";
import App from "./App.vue";
import router from "./router";
import "../src/input.css";

const app = createApp(App);
const pinia = createPinia(); // Pinia 인스턴스 생성

pinia.use(piniaPluginPersistedstate);
app.use(pinia); // Pinia 인스턴스를 앱에 사용
app.use(router);

app.mount("#app");
