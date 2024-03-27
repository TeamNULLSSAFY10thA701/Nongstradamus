import { localAxios } from "@/util/http-commons";
const url = `${import.meta.env.VITE_APP_SERVER_URL}/api/all`;

const local = localAxios();

function getFutureVegetableLeafPrices(success, fail) {
  local.get(`${url}/1`).then(success).catch(fail);
}
//채소-잎

function getFutureVegetableFruitPrices(success, fail) {
  local.get(`${url}/2`).then(success).catch(fail);
}
//채소-열매

function getFutureVegetableRootPrices(success, fail) {
  local.get(`${url}/3`).then(success).catch(fail);
}
//채소-뿌리

function getFutureGrainPrices(success, fail) {
  local.get(`${url}/4`).then(success).catch(fail);
}
//곡물

function getFutureFruitPrices(success, fail) {
  local.get(`${url}/5`).then(success).catch(fail);
}
//과일

function getFutureYellowCropPrices(success, fail) {
  local.get(`${url}/6`).then(success).catch(fail);
}
//구황작물

export {
  getFutureVegetableLeafPrices,
  getFutureVegetableFruitPrices,
  getFutureVegetableRootPrices,
  getFutureGrainPrices,
  getFutureFruitPrices,
  getFutureYellowCropPrices,
};
