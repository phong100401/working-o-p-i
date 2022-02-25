<template>
  <a-table :pagination="false"  :columns="columns" :data-source="data">
    <a slot="name" slot-scope="text">{{ text }}</a>
    <span slot="customTitle"> Id</span>
    <span slot="status" slot-scope="status">
      <a-tag
          :key="status"
          :color="status === 'REJECT' ? 'volcano' : status === 'CONFIRM' ? 'geekblue' : 'green'"
      >
        {{ status.toUpperCase() }}
      </a-tag>
    </span>
    <span slot="payment" slot-scope="payment_status">
      <a-tag
          :key="payment_status"
          :color="payment_status === 'UNPAID' ? 'volcano' : payment_status.length > 5 ? 'geekblue' : 'green'"
      >
        {{ payment_status.toUpperCase() }}
      </a-tag>
    </span>
  </a-table>
</template>
<script>
import OrderService from "@/service/OrderService";
const columns = [
  {
    dataIndex: 'id',
    key: 'id',
    slots: { title: 'customTitle' },
    scopedSlots: { customRender: 'id' },
  },
  {
    title: 'Ship name',
    dataIndex: 'shipName',
    key: 'shipName',
  },
  {
    title: 'Ship address',
    dataIndex: 'shipAddress',
    key: 'shipAddress',
  },
  {
    title: 'Customer id',
    dataIndex: 'customerId',
    key: 'customerId',
  },
  {
    title: 'Total price',
    dataIndex: 'totalPrice',
    key: 'totalPrice',
  },
  {
    title: 'Status',
    key: 'status',
    dataIndex: 'status',
    scopedSlots: { customRender: 'status' },
  },
  {
    title: 'Payment Status',
    key: 'payment_status',
    dataIndex: 'payment_status',
    scopedSlots: { customRender: 'payment' },
  }
];
export default {
  data() {
    return {
      columns,
      data: []
    };
  },
  created() {
    this.getOrders()
  },
  methods:{
    getOrders(){
      OrderService.getAll().then(rs=>{
        console.log(rs.data)
        this.data = rs.data.data
      })
    }
  }
};
</script>