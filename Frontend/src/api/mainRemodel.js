import { localAxios } from "@/util/http-commons";
const url = `${import.meta.env.VITE_APP_SERVER_URL}/api/main`;

const local = localAxios();

function getTodayInfo(success, fail) {
  local.get(`${url}/today`).then(success).catch(fail);
}

function getTomorrowInfo(success, fail) {
  local.get(`${url}/tomorrow`).then(success).catch(fail);
}

function getCardInfo(success, fail) {
  local.get(`${url}/card`).then(success).catch(fail);
}

export { getTodayInfo, getTomorrowInfo, getCardInfo };
