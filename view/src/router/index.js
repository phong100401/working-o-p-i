import Vue from 'vue'
import VueRouter from 'vue-router'
import {productsRoute} from "@/pages/product";
import {cartRoute} from "@/pages/cart";
import {orderRoute} from "@/pages/order";
import {transaction_history} from "@/pages/transaction_history";


Vue.use(VueRouter)

const routes = [
    ...productsRoute,
    ...cartRoute,
    ...orderRoute,
    ...transaction_history

]

const router = new VueRouter({
    mode: 'history',
    routes
})

router.beforeEach((to, from, next) => {
    document.title = to.meta.title;
    next();
})

export default router;