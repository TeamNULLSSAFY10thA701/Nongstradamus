import { localAxios } from "@/util/http-commons";
// const url = `http://70.12.115.41:8080/api/main`;
const url = "http://j10a701.p.ssafy.io/api/main";

const local = localAxios();

// 내일 가격 하락율이 가장 큰 농산물 조회
function getBiggestDropped(success, fail) {
  local.get(`${url}/best-decrease`).then(success).catch(fail);
}

// 내일 가격 상승율이 가장 클 농산물 조회
function getBiggestIncreased(success, fail) {
  local.get(`${url}/best-increase`).then(success).catch(fail);
}

// 지난주 가격(card) 조회
function getLastWeekPrices(success, fail) {
  local.get(`${url}/past/card`).then(success).catch(fail);
}

// 오늘 가격(card) 조회
function getToDayPrices(success, fail) {
  local.get(`${url}/present/card`).then(success).catch(fail);
}

// 내일 가격(card) 조회
function getTommorrowPrices(success, fail) {
  local.get(`${url}/predict/card`).then(success).catch(fail);
}

export {
  getBiggestDropped,
  getBiggestIncreased,
  getLastWeekPrices,
  getToDayPrices,
  getTommorrowPrices,
};
