import Applayout from "@/layout/Applayout";
const routes = [
    {
        path: '/history',
        component: Applayout,
        name: 'History',
        meta: {
            title: 'History',
            icon: 'menu-fold'
        },
        children: [
            {
                path: 'history',
                component: () => import('./List'),
                name: 'history',
                meta: {
                    title: 'history',
                }
            }
        ]
    }
]

export default routes;