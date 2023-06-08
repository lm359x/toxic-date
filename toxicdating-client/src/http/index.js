import axios from 'axios';

// export const API_URL='http://api:8080/v1'
export const API_URL=process.env.REACT_APP_BACKEND_BASE_URI
export const USER_ID='2736e840-2289-4735-a667-b03a5c68f88e'

const api = axios.create({
    baseURL: API_URL
})

export default api;