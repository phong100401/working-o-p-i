import Applayout from "@/layout/Applayout";
const routes = [
    {
        path: '/cart',
        component: Applayout,
        name: 'carts',
        meta: {
            title: 'Cart',
            icon: 'shopping-cart'
        },
        children: [
            {
                path: 'cartItem',
                component: () => import('./ShoppingCart'),
                name: 'carts',
                meta: {
                    title: 'Cart Item',
                }
            }
        ]
    }
]

export default routes;