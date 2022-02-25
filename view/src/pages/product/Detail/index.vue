<template>
  <div>
    <a-form :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }" :datasrc="data">
      <a-form-item label="Product Name">
        <a-input disabled v-model="data.name"/>
      </a-form-item>
      <a-form-item label="Category">
        <a-input disabled v-model="data.category.name"/>
      </a-form-item>
      <a-form-item label="Price">
        <a-input disabled v-model="data.price"/>
      </a-form-item>
      <a-form-item label="Quantity">
        <a-input disabled v-model="data.quantity"/>
      </a-form-item>
      <a-form-item label="Thumbnail">
        <a-input disabled v-model="data.thumbnail"/>
      </a-form-item>
    </a-form>
    <a-button type="primary" html-type="submit" @click="backBtn()">
      Back
    </a-button>
  </div>
</template>

<script>
import ProductService from "@/service/ProductService";


export default {
  data() {
    return {
      data: {},
      params: undefined,
      form:{
        name: undefined,
        price: undefined,

      }
    }
  },
  created() {
    this.params = this.$router.currentRoute.params.id
    this.getDetail()
  },
  methods: {
    getDetail() {
      ProductService.detail(this.params).then(
          res => {
            this.data = res.data.data
          }
      )
    },
    backBtn(){
      this.$router.push("/products/list")
    }
  }
}
</script>

<style scoped>

</style>