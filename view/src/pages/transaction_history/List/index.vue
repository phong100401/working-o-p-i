<template>
  <a-table :pagination="false"  :columns="columns" :data-source="data">
    <a slot="name" slot-scope="text">{{ text }}</a>
    <span slot="customTitle"> Id</span>
    <span slot="status" slot-scope="status">
      <a-tag
          :key="status"
          :color="status === 'REJECT' ? 'volcano' : status === 'SUCCESS' ? 'geekblue' : 'green'"
      >
        {{ status.toUpperCase() }}
      </a-tag>
    </span>
    <span slot="payment" slot-scope="paymentType">
      <a-tag
          :key="paymentType"
          :color="paymentType === 'UNPAID' ? 'volcano' : paymentType === 'TRANSFER' ? 'geekblue' : 'green'"
      >
        {{ payment_status.toUpperCase() }}
      </a-tag>
    </span>
  </a-table>
</template>
<script>
import Transaction_history from "../../../service/Transaction_history";
const columns = [
  {
    dataIndex: 'id',
    key: 'id',
    slots: { title: 'customTitle' },
    scopedSlots: { customRender: 'id' },
  },
  {
    title: 'Order id',
    dataIndex: 'orderId',
    key: 'orderId',
  },
  {
    title: 'Amount',
    dataIndex: 'amount',
    key: 'amount',
  },

  {
    title: 'Payment Status',
    key: 'paymentType',
    dataIndex: 'paymentType',
    scopedSlots: { customRender: 'paymentType' },
  },
  {
    title: 'Status',
    key: 'status',
    dataIndex: 'status',
    scopedSlots: { customRender: 'status' },
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
    this.getHistory()
  },
  methods:{
    getHistory(){
      Transaction_history.getAll().then(rs=>{
        console.log(rs.data)
        this.data = rs.data
      })
    }
  }
};
</script>