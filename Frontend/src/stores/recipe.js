import { ref, computed } from "vue";
import { defineStore } from "pinia";
import axios from "axios";
import { useRouter } from "vue-router";

export const useRecipeStore = defineStore(
  "recipe",
  () => {
    // 더미 데이터
    const recipes = ref([
      {
        id: 1,
        제목: "맛있는 초콜릿 케이크",
        "사진 파일명": "food1.png",
        작성자: "뤼튼1",
        성분: {
          밀가루: "200g",
          설탕: "150g",
          "코코아 파우더": "50g",
          "베이킹 파우더": "10g",
          소금: "약간",
          우유: "200ml",
          버터: "100g",
          달걀: "2개",
          "바닐라 추출물": "1작은 숟가락",
        },
        조리법: [
          "1. 밀가루, 설탕, 코코아 파우더, 베이킹 파우더, 소금을 섞어주세요.",
          "2. 다른 그릇에 우유, 버터, 달걀, 바닐라 추출물을 섞고, 거품기로 잘 풀어주세요.",
          "3. 1단계에서 만든 건강한 재료를 2단계에 섞고, 부드러운 반죽을 만드세요.",
          "4. 반죽을 케이크 팬에 부어주세요.",
          "5. 180도로 예열된 오븐에서 25-30분 동안 구워주세요.",
          "6. 완전히 식혀서 케이크를 장식하고, 맛있게 즐기세요!",
        ],
      },
      {
        id: 2,
        제목: "상큼한 과일 샐러드",
        "사진 파일명": "food2.png",
        작성자: "뤼튼2",
        성분: {
          사과: "2개",
          바나나: "2개",
          딸기: "200g",
          포도: "150g",
          "오렌지 주스": "100ml",
          "레몬 주스": "1큰 숟가락",
          꿀: "2큰 숟가락",
        },
        조리법: [
          "1. 사과와 바나나를 껍질을 벗기고 썰어주세요.",
          "2. 딸기와 포도를 깨끗하게 씻은 후, 적절한 크기로 썬 후릇에 담아주세요.",
          "3. 오렌지 주스와 레몬 주스, 꿀을 잘 섞어 소스를 만들어주세요.",
          "4. 과일에 소스를 부어 섞어주세요.",
          "5. 상큼한 과일 샐러드를 그릇에 담아 맛있게 즐기세요!",
        ],
      },
      {
        id: 3,
        제목: "담백한 샐러드",
        "사진 파일명": "food3.png",
        작성자: "뤼튼3",
        성분: {
          apple: "1개",
          토마토: "2개",
          오이: "1개",
          당근: "1개",
          "올리브 오일": "2큰 숟가락",
          "레몬 주스": "1큰 숟가락",
          소금: "약간",
          후추: "약간",
        },
        조리법: [
          "1. 양상추, 토마토, 오이, 당근을 깨끗하게 씻어 썰어주세요.",
          "2. 그릇에 양상추, 토마토, 오이, 당근을 담고, 올리브 오일, 레몬 주스, 소금, 후추를 넣어주세요.",
          "3. 잘 섞어 담백한 샐러드를 맛있게 즐기세요!",
        ],
      },
      {
        id: 4,
        제목: "가볍고 건강한 샌드위치",
        "사진 파일명": "food4.png",
        작성자: "뤼튼4",
        성분: {
          식빵: "2장",
          "닭 가슴살": "100g",
          양파: "1/4개",
          토마토: "1/2개",
          양상추: "적당량",
          마요네즈: "1큰 숟가락",
          머스타드: "1작은 숟가락",
          소금: "약간",
          후추: "약간",
        },
        조리법: [
          "1. 닭 가슴살을 삶아서 잘게 썰어주세요.",
          "2. 양파와 토마토를 얇게 썰어주세요.",
          "3. 식빵에 마요네즈와 머스타드를 발라주세요.",
          "4. 양상추, 닭 가슴살, 양파, 토마토를 식빵에 올려주세요.",
          "5. 소금과 후추를 뿌리고, 다른 식빵으로 덮어주세요.",
          "6. 가볍고 건강한 샌드위치를 맛있게 즐기세요!",
        ],
      },
    ]);

    const API_URL = "http://127.0.0.1:8000";
    const userObj = ref(null);
    const router = useRouter();

    // 전체 레시피 조회
    const getAllProducts = () => {
      axios({
        method: "get",
        url: `${API_URL}/`,
        // headers: {
        //   Authorization: `Token ${token.value}`
        // }
      })
        .then((res) => {
          console.log(res);
          recipes.value = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    };

    // 상품 ID에 따라 상품 정보 빼오기
    const getProductById = function (productId) {
      let product = null;
      recipes.value.forEach((prod) => {
        if (prod.id === productId) {
          product = prod;
        }
      });

      return product;
    };

    const addCart = function (productId) {
      axios({
        method: "post",
        url: `${API_URL}/accounts/user/add_cart/`,
        data: {
          productId,
        },
        headers: {
          Authorization: `Token ${token.value}`,
        },
      })
        .then((response) => {
          userObj.value = response.data;
          console.log(userObj.value.financial_products);
        })
        .catch((error) => {
          console.log(error);
        });
    };

    const deleteCart = function (productId) {
      axios({
        method: "post",
        url: `${API_URL}/accounts/user/delete_cart/`,
        data: {
          productId,
        },
        headers: {
          Authorization: `Token ${token.value}`,
        },
      })
        .then((response) => {
          userObj.value = response.data;
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
        });
    };

    return {
      recipes,
      getAllProducts,
      API_URL,
      getProductById,
    };
  },
  { persist: true }
);
