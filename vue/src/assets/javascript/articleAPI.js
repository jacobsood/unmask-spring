import axios from 'axios'

export default() => {
    return axios.create({
        method: 'get',
        baseURL: 'https://unmask.hrithviksood.me/',
        headers: {
            'Content-Type': 'application/json',
        },
    })
}