import axios from "axios";

const URL_CATEGORIES = "http://localhost:8081/api/v1/categories";

class CategoryService {

    getAll(){
        return axios.get(URL_CATEGORIES, {
        });
    }


}

export default new CategoryService();