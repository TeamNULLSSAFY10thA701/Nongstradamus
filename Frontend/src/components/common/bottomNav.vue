<template>
  <div
    class="fixed z-50 w-full h-16 max-w-lg -translate-x-1/2 bg-white border border-gray-200 bottom-0 left-1/2 dark:bg-gray-700 dark:border-gray-600"
  >
    <div class="grid h-full grid-cols-5 mx-auto gap-x-4">
      <span
        class="text-xs ml-8 w-10 inline-flex flex-col items-center justify-center mt-1 text-gray-500 dark:text-gray-400"
        ><div>추천</div>
        <div>레시피</div></span
      >
      <button
        data-tooltip-target="tooltip-wallet"
        type="button"
        class="col-start-2 inline-flex flex-col items-center justify-center px-5 hover:bg-gray-50 dark:hover:bg-gray-800 group"
      >
        <svg
          class="w-10 h-10 mb-1 mr-6 text-gray-500 dark:text-gray-400 group-hover:text-blue-600 dark:group-hover:text-blue-500"
          aria-hidden="true"
          xmlns="http://www.w3.org/2000/svg"
          fill="currentColor"
          viewBox="0 0 49.000000 46.000000"
        >
          <g
            transform="translate(0.000000,46.000000) scale(0.100000,-0.100000)"
            fill="#000000"
            stroke="none"
          >
            <path
              d="M297 354 c-26 -14 -28 -18 -22 -62 3 -26 8 -56 10 -68 4 -17 -3 -23
-36 -33 -35 -11 -43 -10 -58 3 -10 9 -23 16 -29 16 -6 0 -17 12 -24 27 -8 17
-21 28 -33 28 -29 0 -24 -26 9 -44 16 -9 29 -21 28 -26 0 -6 9 -22 20 -38 15
-20 28 -27 54 -27 33 0 34 -1 34 -40 0 -39 -1 -40 -32 -39 -31 0 -31 1 -10 10
15 5 22 16 20 26 -3 15 -16 19 -76 21 -45 2 -72 -1 -72 -8 0 -5 22 -10 51 -10
43 0 50 -3 47 -17 -2 -14 -15 -19 -56 -21 -51 -3 -52 -3 -58 28 -5 21 -12 30
-23 28 -26 -5 -25 -37 1 -47 l23 -10 -22 0 c-13 -1 -23 -5 -23 -11 0 -6 82
-10 225 -10 143 0 225 4 225 10 0 6 -40 10 -101 10 l-100 0 3 33 c3 32 3 32
58 32 53 0 55 -1 58 -28 4 -36 22 -25 22 14 0 16 5 29 10 29 6 0 10 -11 10
-25 0 -30 35 -35 45 -6 11 35 -11 68 -59 87 -32 13 -46 25 -47 39 0 18 -1 18
-10 -3 -13 -34 -53 -27 -57 11 -3 25 -1 27 33 27 29 0 37 5 45 26 16 40 12 51
-22 68 -17 9 -32 16 -32 16 -1 -1 -14 -8 -29 -16z m64 -14 c12 0 12 -47 -1
-55 -5 -3 -10 -1 -10 6 0 8 -4 8 -15 -1 -12 -10 -15 -10 -16 1 0 10 -2 11 -6
2 -8 -21 -20 -15 -26 13 -3 19 1 28 17 34 11 5 27 7 34 4 8 -2 18 -4 23 -4z
m-241 -101 c0 -6 -4 -7 -10 -4 -5 3 -10 11 -10 16 0 6 5 7 10 4 6 -3 10 -11
10 -16z m160 -86 c0 -19 -3 -24 -9 -15 -5 8 -22 12 -46 10 -26 -2 -39 1 -43
11 -3 10 0 12 12 8 9 -4 26 -2 38 3 38 15 48 11 48 -17z m65 17 c13 10 15 9
15 -9 0 -17 -6 -21 -30 -21 -24 0 -30 4 -30 21 0 18 2 19 15 9 12 -10 18 -10
30 0z m85 -5 c28 -15 42 -45 20 -45 -5 0 -10 4 -10 9 0 17 -42 24 -51 10 -6
-10 -9 -6 -9 14 0 31 9 33 50 12z"
            />
            <path
              d="M320 219 c0 -5 5 -7 10 -4 6 3 10 8 10 11 0 2 -4 4 -10 4 -5 0 -10
-5 -10 -11z"
            />
            <path
              d="M100 187 c1 -14 24 -47 34 -47 13 0 5 26 -14 43 -13 11 -20 13 -20 4z"
            />
            <path
              d="M60 177 c1 -14 24 -47 34 -47 13 0 5 26 -14 43 -13 11 -20 13 -20 4z"
            />
            <path
              d="M30 152 c0 -10 22 -32 32 -32 15 0 8 15 -12 28 -11 7 -20 9 -20 4z"
            />
            <path
              d="M300 91 c0 -23 28 -35 46 -20 24 20 16 39 -16 39 -23 0 -30 -5 -30
-19z"
            />
            <path
              d="M20 10 c0 -6 32 -10 75 -10 43 0 75 4 75 10 0 6 -32 10 -75 10 -43 0
-75 -4 -75 -10z"
            />
            <path
              d="M195 10 c-4 -6 43 -10 134 -10 87 0 141 4 141 10 0 6 -51 10 -134 10
-79 0 -137 -4 -141 -10z"
            />
          </g>
        </svg>
      </button>
      <div class="flex items-center justify-center col-start-3">
        <button
          data-tooltip-target="tooltip-new"
          type="button"
          class="inline-flex items-center justify-center w-14 h-14 font-medium bg-blue-600 rounded-full hover:bg-blue-700 group focus:ring-4 focus:ring-blue-300 focus:outline-none dark:focus:ring-blue-800"
        >
          <img
            src="@/assets/full_logo1.png"
            alt="로-고"
            width="200"
            height="200"
            class="text-white"
          />
          <span class="sr-only">New item</span>
        </button>
      </div>
      <button
        data-tooltip-target="tooltip-settings"
        type="button"
        class="col-start-4 inline-flex flex-col items-center justify-center px-5 hover:bg-gray-50 dark:hover:bg-gray-800 group"
      >
        <svg
          class="w-10 h-10 mt-1 ml-16 text-gray-500 dark:text-gray-400 group-hover:text-blue-600 dark:group-hover:text-blue-500"
          aria-hidden="true"
          xmlns="http://www.w3.org/2000/svg"
          fill="none"
          viewBox="0 0 45.000000 47.000000"
        >
          <g
            transform="translate(0.000000,47.000000) scale(0.100000,-0.100000)"
            fill="#000000"
            stroke="none"
          >
            <path
              d="M310 425 c0 -18 -2 -18 -34 -4 -36 17 -152 3 -160 -19 -3 -9 -17 -35
-31 -57 -16 -27 -24 -53 -23 -80 l1 -40 6 45 c8 53 39 110 61 110 9 0 23 9 33
20 27 31 133 26 120 -6 -3 -8 1 -14 11 -14 9 0 16 -8 16 -20 0 -11 5 -20 10
-20 15 0 12 26 -5 40 -13 11 -13 14 0 27 17 17 20 33 5 33 -5 0 -10 -7 -10
-15z m-160 -25 c0 -5 -4 -10 -10 -10 -5 0 -10 5 -10 10 0 6 5 10 10 10 6 0 10
-4 10 -10z"
            />
            <path
              d="M334 392 c1 -10 12 -33 24 -52 44 -70 20 -155 -57 -207 -68 -46 -156
-22 -208 56 -26 39 -30 34 -8 -9 8 -16 25 -36 37 -46 18 -13 19 -15 4 -10 -12
4 -23 0 -32 -11 -22 -30 -16 -43 29 -63 70 -32 175 -24 235 18 22 15 -6 64
-32 56 -20 -5 -20 -5 2 11 31 23 62 87 62 130 0 20 -8 52 -19 73 -10 20 -16
42 -14 48 2 6 -2 14 -10 17 -9 3 -14 -1 -13 -11z m-156 -287 c4 -4 -4 -5 -18
-1 -21 5 -20 3 5 -9 38 -18 82 -18 120 0 25 12 26 14 5 9 -14 -4 -22 -3 -18 1
16 16 69 12 78 -6 19 -34 -54 -63 -143 -57 -77 5 -123 29 -108 57 10 18 63 23
79 6z m70 -12 c-10 -2 -28 -2 -40 0 -13 2 -5 4 17 4 22 1 32 -1 23 -4z"
            />
            <path
              d="M201 338 c-10 -16 -10 -23 -2 -26 6 -2 11 -10 11 -18 0 -8 5 -14 10
-14 6 0 10 6 10 14 0 8 5 16 11 18 8 3 8 10 -2 26 -8 12 -16 22 -19 22 -3 0
-11 -10 -19 -22z m30 -21 c-13 -13 -26 -3 -16 12 3 6 11 8 17 5 6 -4 6 -10 -1
-17z"
            />
            <path
              d="M100 241 c12 -44 38 -78 68 -89 52 -19 62 -21 62 -12 0 6 -12 10 -26
10 -32 0 -94 59 -94 88 0 12 -4 23 -9 27 -6 3 -6 -6 -1 -24z"
            />
            <path
              d="M286 235 c-6 -17 9 -29 24 -20 14 9 12 22 -4 28 -8 3 -17 -1 -20 -8z"
            />
          </g>
        </svg>
        <span class="sr-only">Settings</span>
      </button>
    </div>
  </div>
</template>

<script setup></script>

<style scoped></style>
