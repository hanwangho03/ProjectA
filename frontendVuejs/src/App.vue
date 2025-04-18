<template>
  <div id="app">
    <UserList />
  </div>
</template>

<script>
import UserList from './components/UserList.vue';

export default {
  name: 'App',
  components: {
    UserList
  }
};
</script>

<style>
/* Các style chung cho ứng dụng */
body {
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
}

#app {
  padding: 20px;
}
</style>
