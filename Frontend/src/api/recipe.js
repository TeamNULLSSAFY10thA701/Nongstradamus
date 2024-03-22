import { localAxios } from "@/util/http-commons";
const url = `http://j10a701.p.ssafy.io:8080/api/recipe`;

const local = localAxios();

// 추천 레시피 전체 조회
function getRecipeData(pageNumber, success, fail) {
  local.get(`${url}/${pageNumber}`).then(success).catch(fail);
}

// 추천 레시피 자세히 보기 조회
function getRecipeDetailData(idx, success, fail) {
  local.get(`${url}/detail/${idx}`).then(success).catch(fail);
}

export { getRecipeData, getRecipeDetailData };
