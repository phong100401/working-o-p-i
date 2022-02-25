import Applayout from "@/layout/Applayout";
const routes = [
    {
        path: '/orders',
        component: Applayout,
        name: 'orders',
        meta: {
            title: 'Order',
            icon: 'menu-fold'
        },
        children: [
            {
                path: 'list',
                component: () => import('./List'),
                name: 'orderList',
                meta: {
                    title: 'Order List',
                }
            }
        ]
    }
]

export default routes;