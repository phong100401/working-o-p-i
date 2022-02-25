<template>
  <a-layout id="components-layout-demo-side" style="min-height: 100vh" >
    <a-layout-sider v-model="collapsed" collapsible style="background: darkslategray">
      <div class="logo" />
      <a-menu theme="dark" :default-selected-keys="['1']" mode="inline">
        <template v-for="menu in $router.options.routes">
          <a-menu-item :key="menu.name" v-if="!menu.children">
            <router-link :to="{name: menu.name}">
              <a-icon :type="menu.meta.icon" />
              <span>{{ menu.meta.title }}</span>
            </router-link>
          </a-menu-item>
          <a-sub-menu :key="menu.name" v-else >
            <span slot="title"><a-icon :type="menu.meta.icon" /><span>{{ menu.meta.title }}</span></span>
            <template v-for="child in menu.children">
              <a-menu-item :key="child.name" v-if="!child.meta || !child.meta.hidden">
                <router-link :to="{name: child.name}">
                  {{ child.meta.title }}
                </router-link>
              </a-menu-item>
            </template>
          </a-sub-menu>
        </template>
      </a-menu>
    </a-layout-sider>
    <a-layout>
      <a-layout-header  style="background: #fff; padding: 0 16px; text-align: right;">
      </a-layout-header>
      <a-layout-content style="margin: 16px">
        <router-view :key="$route.fullPath"></router-view>
      </a-layout-content>
      <a-layout-footer style="text-align: center">
        <div id="nav">
          <router-link to="/">Home</router-link> |
          <router-link to="/about">About</router-link>
        </div>
      </a-layout-footer>
    </a-layout>
  </a-layout>
</template>

<script>
export default {
  name: 'AppLayout',
  data() {
    return {
      collapsed: false,
    };
  }
};
</script>

<style>
#components-layout-demo-side .logo {
  height: 32px;
  background: rgba(255, 165, 0, 255);
  margin: 16px;
}

</style>
