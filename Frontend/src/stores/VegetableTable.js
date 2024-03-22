import { defineStore } from "pinia";
import { ref, onMounted } from "vue";
import {
  getFutureVegetableLeafPrices,
  getFutureVegetableFruitPrices,
  getFutureVegetableRootPrices,
  getFutureGrainPrices,
  getFutureFruitPrices,
  getFutureYellowCropPrices,
} from "@/api/predictAllPrices";
import { data } from "autoprefixer";

export const useVegetableTableStore = defineStore("vegetableStore", () => {
  //difineStore를 시키고, store식별 id를 붙임.

  onMounted(() => {
    callFutureVegetableLeafPricesAllData();
  });

  const basket = ref("");

  const bestClick = () => {};

  const goodClick = () => {};

  const midClick = () => {};

  const lowClick = () => {};

  const clickEvent = () => {
    clickState.value = !clickState.value;
  };
  //클릭 시, 클릭 상태 변수를 switch하는 매서드.

  const categoryVegetableLeafState = ref(false);
  //채소-잎의 라디오버튼이 눌렸을 때, 상태

  const categoryVegetableFruitState = ref(false);
  //채소-열매의 라디오버튼이 눌렸을 때, 상태

  const categoryVegetableRootState = ref(false);
  //채소-뿌리의 라디오버튼이 눌렸을 떼, 상태

  const categoryFruitState = ref(false);
  //과일의 라디오버튼이 눌렸을 때, 상태

  const categoryGrainState = ref(false);
  //곡물의 라디오버튼이 눌렸을 때, 상태

  const categoryYellowCropState = ref(false);
  //구황작물의 라디오버튼이 눌렸을 때, 상태

  const transferVegetableReaf = () => {
    categoryVegetableLeafState.value = true;
    categoryVegetableFruitState.value = false;
    categoryVegetableRootState.value = false;
    categoryGrainState.value = false;
    categoryFruitState.value = false;
    categoryYellowCropState.value = false;
  };

  const transferVegetableFruit = () => {
    categoryVegetableLeafState.value = false;
    categoryVegetableFruitState.value = true;
    categoryVegetableRootState.value = false;
    categoryGrainState.value = false;
    categoryFruitState.value = false;
    categoryYellowCropState.value = false;
  };

  const transferVegetableRoot = () => {
    categoryVegetableLeafState.value = false;
    categoryVegetableFruitState.value = false;
    categoryVegetableRootState.value = true;
    categoryGrainState.value = false;
    categoryFruitState.value = false;
    categoryYellowCropState.value = false;
  };

  const transferGrain = () => {
    categoryVegetableLeafState.value = false;
    categoryVegetableFruitState.value = false;
    categoryVegetableRootState.value = false;
    categoryGrainState.value = true;
    categoryFruitState.value = false;
    categoryYellowCropState.value = false;
  };

  const transferFruit = () => {
    categoryVegetableFruitState.value = false;
    categoryVegetableLeafState.value = false;
    categoryVegetableRootState.value = false;
    categoryGrainState.value = false;
    categoryFruitState.value = true;
    categoryYellowCropState.value = false;
  };

  const transferYellowCrop = () => {
    categoryVegetableFruitState.value = false;
    categoryVegetableLeafState.value = false;
    categoryVegetableRootState.value = false;
    categoryGrainState.value = false;
    categoryFruitState.value = false;
    categoryYellowCropState.value = true;
  };

  const FutureVegetableLeafPricesAllData = ref({
    data: "",
    msg: "",
    code: "",
    table: [],
  });
  //채소-잎의 모든 정보를 담아올 변수.

  const FutureVegetableFruitPricesAllData = ref({
    data: "",
    msg: "",
    code: "",
    table: [],
  });
  //채소-과일의 모든 정보를 담아올 변수

  const FutureVegetableRootPricesAllData = ref({
    data: "",
    msg: "",
    code: "",
    table: [],
  });
  //채소-뿌리의 모든 정보를 담아올 변수

  const FutureGrainPricesAllData = ref({
    data: "",
    msg: "",
    code: "",
    table: [],
  });
  //곡물의 모든 정보를 담아올 변수

  const FutureFruitPricesAllData = ref({
    data: "",
    msg: "",
    code: "",
    table: [],
  });
  //과일의 모든 정보를 담아올 변수

  const FutureYellowCropPricesAllData = ref({
    data: "",
    msg: "",
    code: "",
    table: [],
  });
  //구황작물의 모든 정보를 담아올 변수

  const callFutureVegetableLeafPricesAllData = () => {
    getFutureVegetableLeafPrices(
      (data) => {
        FutureVegetableLeafPricesAllData.value.data = data.data.data;
        FutureVegetableLeafPricesAllData.value.msg = data.data.msg;
        FutureVegetableLeafPricesAllData.value.code = data.data.code;
        FutureVegetableLeafPricesAllData.value.table = data.data.data.table;
      },
      (error) => {
        console.error(error);
      }
    );
  };

  return {
    categoryVegetableLeafState,
    categoryVegetableFruitState,
    categoryVegetableRootState,
    categoryFruitState,
    categoryGrainState,
    categoryYellowCropState,
    FutureVegetableLeafPricesAllData,
    FutureVegetableFruitPricesAllData,
    FutureVegetableRootPricesAllData,
    FutureGrainPricesAllData,
    FutureFruitPricesAllData,
    FutureYellowCropPricesAllData,
    basket,
    clickEvent,
    transferVegetableReaf,
    transferVegetableFruit,
    transferVegetableRoot,
    transferGrain,
    transferFruit,
    transferYellowCrop,
    callFutureVegetableLeafPricesAllData,
    bestClick,
    goodClick,
    midClick,
    lowClick,
  };
  //store는 return을 해줘야함!!!
});
