import axios from "axios";

const URL_CART = "http://localhost:8081/api/v1/cart";
const access_token = "token p";
class CartService {

    getCart(){
        return axios.get(URL_CART+ `/?access_token=${access_token}`,{
            params: access_token,
        });
    }
    addToCart(body){
        return axios.post(URL_CART + "/add" + `?access_token=${access_token}`, body);
    }
    remove(id){
        return axios.delete(URL_CART + "/remove" + `?access_token=${access_token}` + `&productId=${id}`)
    }
    clear(){
        return axios.delete(URL_CART + "/clear"+ `?access_token=${access_token}`)
    }
    submitOrder(body){
        return axios.post(URL_CART + "/submitOrder" + `?access_token=${access_token}`,body)
    }

}

export default new CartService();