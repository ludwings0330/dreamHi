import axios from 'axios'

const instance = axios.create({
  baseURL: "https://openapi.naver.com/v1/search/news.json",
  params: {
    query: "영화"
  },
  headers: {
    "X-Naver-Client-Id": "G6cu_2RT6__edZyLqZ3r",
    "X-Naver-Client-Secret": "qjkJ7IdOEl"
  }
})

export default instance;