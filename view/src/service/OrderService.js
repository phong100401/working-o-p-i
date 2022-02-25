import axios from "axios";

const URL_ORDER = "http://localhost:8081/api/v1/orders";

class OrderService {

    getAll(){
        return axios.get(URL_ORDER);
    }


}

export default new OrderService();