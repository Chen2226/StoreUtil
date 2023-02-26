<template>
  <div class="custom-tree-container">
    <div class="block">
      <p>使用 scoped slot</p>
      <el-tree :data="data" show-checkbox node-key="id" default-expand-all :expand-on-click-node="false">
        <span class="custom-tree-node" slot-scope="{ node, data }">
          <span>{{ node.label }}</span>
          <span>
            <span @click="Attest()">预览</span>
            <span>删除</span>
          </span>
        </span>
      </el-tree>
    </div>
  </div>
</template>

<script>
import { test } from "@/api/store/excel";
export default {
  name: "profitStatement",
  data () {
    const data = [{
      id: 1,
      label: '一级 1',
      children: [{
        id: 4,
        label: '二级 1-1'
      }]
    }];
    return {
      data: JSON.parse(JSON.stringify(data)),
      data: JSON.parse(JSON.stringify(data))
    }
  },
  mounted () {
    
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

<style>
.custom-tree-node {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 14px;
  padding-right: 8px;
}
</style>