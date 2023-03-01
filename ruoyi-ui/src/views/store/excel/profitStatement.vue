<template>
  <div class="page">
    <div>预留空间</div>
    <div class="content">
      <div class="content-folder"></div>
    </div>
  </div>
</template>

<script>
import { test } from "@/api/store/excel";
export default {
  name: "profitStatement",
  data() {

    return {

    };
  },
  mounted() {

  },
  methods: {
    Attest() {
      test().then(res => {
        console.log(res);
      });
    }
  }
}
</script>

<style lang="scss" scoped>
.page {
  width: 100%;
  height: 100%;
  background-color: #fff;
  padding: 10px;
}

.content {
  display: flex;
  .content-folder{
    width: 50px;
    height: 50px;
    background-image: url('../../../assets/store/folder.png');
    background-size: 100% 100%;
    background-repeat: no-repeat;
  }
}
</style>