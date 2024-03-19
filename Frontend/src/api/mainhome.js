import { localAxios } from "@/util/http-commons";
import { fail } from "./fail.js";
const url = `https://i10a701.p.ssafy.io/api/main`;

const local = localAxios();

// 가격 하락율이 가장 큰 농산물 조회
function getBiggestDropped(success) {
  local.get(`${url}/best-present`).then(success).catch(fail);
}

// 가격 상승율이 가장 클 농산물 조회
function getBiggestIncreased(success) {
  local.get(`${url}/best-predict`).then(success).catch(fail);
}

// 예측 가격(card) 조회
function getFuturePrices(success) {
  local.get(`${url}/predict/card`).then(success).catch(fail);
}

// 오늘 가격(card) 조회
function getFuturePrices(success) {
  local.get(`${url}/present/card`).then(success).catch(fail);
}

export { getBiggestDropped, getBiggestIncreased, getFuturePrices };
