<template>
  <div>
    <a-form :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }" :datasrc="form">
      <a-form-item label="Product Name">
        <a-input v-model="form.name"/>
      </a-form-item>
      <a-form-item label="Category">
        <a-select v-model="form.categoryId">
          <a-select-option v-for="item in categories" :key="item.id" :value="item.id">
            {{item.name}}
          </a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item label="Price">
        <a-input v-model="form.price"/>
      </a-form-item>
      <a-form-item label="Quantity">
        <a-input v-model="form.quantity"/>
      </a-form-item>
      <a-form-item label="Thumbnail">
        <a-input v-model="form.thumbnail"/>
      </a-form-item>
    </a-form>
    <a-button type="primary" html-type="submit" @click="editProduct">
      Change
    </a-button>
  </div>
</template>

<script>
import ProductService from "@/service/ProductService";
import CategoryService from "@/service/CategoryService";

export default {
  data() {
    return {
      categories:[],
      params: undefined,
      form:{
        name: undefined,
        price: undefined,
        quantity: undefined,
        thumbnail: undefined,
        categoryId: undefined
      }
    }
  },
  created() {
    this.params = this.$router.currentRoute.params.id
    this.getDetail()
    this.getCategorise()
  },
  methods: {
    getDetail() {
      ProductService.detail(this.params).then(
          res => {
            this.form = res.data.data
            console.log(res.data)
          }
      )
    },
    editProduct(){
      ProductService.edit(this.params,this.form).then(
          () =>{
            this.$router.push("/products/list")
          }
      )
    },
    getCategorise() {
      CategoryService.getAll().then(
          res => {
            this.categories = res.data.data
            console.log(res.data.data)
          }
      )
    }
  }
}
</script>

<style scoped>

</style>