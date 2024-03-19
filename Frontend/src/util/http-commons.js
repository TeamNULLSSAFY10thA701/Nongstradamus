import axios from "axios";

//환경변수파일에 존재하는 URL을 가져다 쓰기 위한 변수 설정.
const { VITE_VUE_API_URL } = import.meta.env;

function localAxios() {
  //axios 인스턴스를 생성.
  const instance = axios.create({
    baseURL: VITE_VUE_API_URL,
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
  });
  //인스턴스를 생성 후, 리턴.
  return instance;
}

//생성한 axios 인스턴스를 외부파일에서 사용하기 위한 export.
export { localAxios };
