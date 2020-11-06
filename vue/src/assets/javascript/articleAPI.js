import axios from 'axios'

export default() => {
    return axios.create({
        method: 'get',
        baseURL: 'unmask.hrithviksood.me/',
        headers: {
            'Accept': 'application/json',
        },
    })
}