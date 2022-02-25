import axios from "axios";

const URL_HISTORY = "http://localhost:8082/api/v1/history";

class Transaction_history {

    getAll(){
        return axios.get(URL_HISTORY);
    }


}

export default new Transaction_history();