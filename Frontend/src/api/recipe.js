import { localAxios, localSessionAxios } from "@/util/http-commons";
const url = `https://i10a701.p.ssafy.io/api/recipe`;

const local = localAxios();

// 추천 레시피 조회
function getRecipe(success, fail) {
  local.get(`${url}`).then(success).catch(fail);
}

// 추천 레시피 자세히 보기 조회
function getDetailRecipe(success, fail) {
  local.get(`${url}/${title}`).then(success).catch(fail);
}
export { getRecipe, getDetailRecipe };
