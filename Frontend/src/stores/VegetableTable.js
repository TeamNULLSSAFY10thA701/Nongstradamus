import { defineStore } from "pinia";
import { ref } from "vue";

export const useVegetableTableStore = defineStore("vegetableStore", () => {
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
});
