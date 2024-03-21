import { localAxios } from "@/util/http-commons";
const url = `http://j10a701.p.ssafy.io/api/all`;

const local = localAxios();

// 예상 전체 가격(표) 조회
function getFuturePrices(success, fail) {
  local.get(`${url}`).then(success).catch(fail);
}

export { getFuturePrices };
