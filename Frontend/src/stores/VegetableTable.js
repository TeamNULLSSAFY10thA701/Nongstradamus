import { defineStore } from "pinia";
import { ref } from "vue";

export const useVegetableTableStore = defineStore("vegetableStore", () => {
  //difineStore를 시키고, store식별 id를 붙임.

  const clickState = ref(false);
  //현재 클릭 상태를 나타내는 변수

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

  return {
    clickState,
    categoryVegetableLeafState,
    categoryVegetableFruitState,
    categoryVegetableRootState,
    categoryFruitState,
    categoryGrainState,
    categoryYellowCropState,
    clickEvent,
    transferVegetableReaf,
    transferVegetableFruit,
    transferVegetableRoot,
    transferGrain,
    transferFruit,
    transferYellowCrop,
  };
  //store는 return을 해줘야함!!!
});
