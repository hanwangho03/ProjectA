<template>
  <div>
    <h1>Danh sách người dùng</h1>
    <ul>
      <li v-for="user in users" :key="user.id">
        <p><strong>ID:</strong> {{ user.id }}</p>
        <p><strong>Email:</strong> {{ user.email }}</p>
        <p><strong>Name:</strong> {{ user.name }}</p>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      users: [], // Mảng chứa danh sách người dùng
    };
  },
  mounted() {
    // Khi component được mounted, gọi API để lấy dữ liệu người dùng
    axios.get('http://localhost:8080/users')
        .then(response => {
          this.users = response.data; // Lưu dữ liệu vào biến users
        })
        .catch(error => {
          console.error('Đã có lỗi xảy ra khi lấy dữ liệu người dùng:', error);
        });
  },
};
</script>

<style scoped>
/* Các style cho component */
h1 {
  font-size: 2em;
  color: #333;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  background-color: #f4f4f4;
  margin: 10px 0;
  padding: 10px;
  border-radius: 5px;
}

p {
  margin: 5px 0;
}
</style>
