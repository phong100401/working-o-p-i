<template>
  <a-form :form="form" :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }" >
    <a-form-item label="Product Name">
      <a-input v-model="form.name"/>
    </a-form-item>
    <a-form-item label="Price">
      <a-input v-model="form.price"/>
    </a-form-item>
    <a-form-item label="Category">
      <a-select v-model="form.categoryId">
        <a-select-option v-for="item in data" :key="item.id" :value="item.id">
          {{item.name}}
        </a-select-option>
      </a-select>
    </a-form-item>
    <a-form-item label="Quantity">
      <a-input v-model="form.quantity"/>
    </a-form-item>
    <a-form-item label="Thumbnail">
      <a-input v-model="form.thumbnail"/>
    </a-form-item>
    <a-form-item :wrapper-col="{ span: 12, offset: 5 }">
      <a-button type="primary" html-type="submit" @click.stop.prevent="handleSubmit">
        Submit
      </a-button>
    </a-form-item>
  </a-form>
</template>

<script>
import CategoryService from "@/service/CategoryService";
import ProductService from "@/service/ProductService";


export default {
  data() {
    return {
      data:[],
      form:{
        name: undefined,
        price: undefined,
        thumbnail: undefined,
        categoryId: undefined,
        quantity: undefined
      }

    };
  },
  created() {
      this.getCategorise()
  },
  methods: {
      getCategorise(){
          CategoryService.getAll().then(
              res => {
                console.log(res.data)
                this.data = res.data.data
              }
          )
      },
      handleSubmit(){
        ProductService.save(this.form).then(
              this.$router.push("/products/list")
        ).catch(error => {
          console.log(error)
        })
    }
  },

};
</script>

<style scoped>

</style>