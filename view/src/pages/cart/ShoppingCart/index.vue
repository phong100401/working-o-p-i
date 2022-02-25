<template>
  <div>
    <a-card>
      <a-table :columns="columns" :pagination="false" rowKey="id" :data-source="cartItems">
        <span slot="thumbnail" slot-scope="text">
             <img :src="text" style="width: 100%">
         </span>
        <div slot="action" slot-scope="text,record">
          <a class="button" @click="confirmRemove(record.productId)">
            <a-icon type="delete"/>
          </a>
        </div>
      </a-table>
      <div>
        <a-form :form="form"  :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }" >
          <a-form-item label="Total Price">
            <a-input disabled v-model="cartData.totalPrice"/>
          </a-form-item>
          <a-form-item   label="Ship name">
           <a-input v-model="form.shipName"/>
          </a-form-item>
          <a-form-item label="Ship Address">
           <a-input v-model="form.shipAddress"/>
          </a-form-item>
          <a-form-item :wrapper-col="{ span: 12, offset: 5 }">
            <a-button type="primary" html-type="submit" @click.stop.prevent="submitOrder">
              Submit Cart
            </a-button>
          </a-form-item>
        </a-form>
      </div>
    </a-card>
  </div>
</template>
<script>
import CartService from "@/service/CartService";

const columns = [
  {
    title: 'ID',
    dataIndex: 'id',
    key: 'id',
  },
  {
    title: 'Product Name',
    dataIndex: 'productName',
    key: 'productName',
  },
  {
    title: 'Image',
    dataIndex: 'thumbnail',
    key: 'thumbnail',
    ellipsis: true,
    scopedSlots: {customRender: 'thumbnail'}
  },
  {
    title: 'Unit price',
    dataIndex: 'unitPrice',
    key: 'unitPrice',
  },
  {
    title: 'Amount',
    dataIndex: 'quantity',
    key: 'quantity',
    ellipsis: true,
  },
  {
    title: 'Action',
    dataIndex: 'action',
    key: 'action',
    ellipsis: true,
    scopedSlots: {customRender: 'action'},
  }

];

export default {
  data() {
    return {
      cartData: undefined,
      cartItems: [],
      columns,
      params:{

      },
      form:{
        shipName: undefined,
        shipAddress: undefined
      }
    };
  },
  created() {
      this.getCart()
  },
  methods: {
    getCart(){
      CartService.getCart().then(
          rs =>{
            console.log(rs.data.data)
            this.cartData = rs.data.data
            this.cartItems = rs.data.data.items;
          }
      )
    },
    confirmRemove(id){
      this.$confirm({
        title: 'Do you want remove this product',
        onOk: () => {
          this.removeProduct(id)
          window.location.reload();
        },
        onCancel() {
          console.log("Cancel")
        }
      })
    },
    removeProduct(id){
      CartService.remove(id).then(
          rs =>{
            console.log(rs.data)
          }
      )
      this.getCart()
    },
    submitOrder(){
      CartService.submitOrder(this.form).then(()=>{
            console.log("Đặt đươc dồi")
            window.location.reload();
      }
      )
    }

  }
};
</script>
<style scoped>
.product__search-form{
  display: grid;
  gap: 2rem;
  grid-template-columns: repeat(auto-fit,minmax(200px,1fr));
}
</style>