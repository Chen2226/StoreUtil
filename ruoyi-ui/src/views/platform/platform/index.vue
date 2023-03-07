<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="名字" prop="name">
        <el-input v-model="queryParams.name" placeholder="请输入名字" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['platform:platform:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['platform:platform:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['platform:platform:remove']">删除</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['platform:platform:export']">导出</el-button>
      </el-col> -->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="platformList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="用户" align="center" prop="userName" />
      <el-table-column label="名字" align="center" prop="name" />
      <el-table-column label="平台扣费（%）" align="center" prop="platformDeduction" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['platform:platform:edit']">设置平台-店铺</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['platform:platform:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改平台对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="90px">
        <el-form-item label="名字" prop="name">
          <el-input v-model="form.name" placeholder="请输入名字" />
        </el-form-item>
        <el-form-item label="平台扣费(%)" prop="platformDeduction">
          <el-input v-model="form.platformDeduction" placeholder="请输入平台扣费" />
        </el-form-item>
        <el-divider content-position="center">店铺信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddChenStore">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteChenStore">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="chenStoreList" :row-class-name="rowChenStoreIndex"
          @selection-change="handleChenStoreSelectionChange" ref="chenStore">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50" />

          <!-- <el-table-column label="用户id" prop="uid" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.uid" placeholder="请输入用户id" :disabled="true" />
            </template>
          </el-table-column> -->
          <el-table-column label="店铺名字" prop="name" width="350">
            <template slot-scope="scope">
              <el-input v-model="scope.row.name" placeholder="请输入店铺名字" />
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listPlatform, getPlatform, delPlatform, addPlatform, updatePlatform } from "@/api/platform/platform";

export default {
  name: "Platform",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedChenStore: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 平台表格数据
      platformList: [],
      // 店铺表格数据
      chenStoreList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        uid: this.$store.state.user.user_id,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询平台列表 */
    getList() {
      this.loading = true;
      listPlatform(this.queryParams).then(response => {
        this.platformList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        uid: this.$store.state.user.user_id,
        name: null,
        createTime: null,
        updateTime: null,
      };
      this.chenStoreList = [];
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加平台";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getPlatform(id).then(response => {
        this.form = response.data;
        this.chenStoreList = response.data.chenStoreList;
        this.open = true;
        this.title = "修改平台";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.chenStoreList = this.chenStoreList;
          if (this.form.id != null) {
            updatePlatform(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPlatform(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除平台编号为"' + ids + '"的数据项？').then(function () {
        return delPlatform(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 店铺序号 */
    rowChenStoreIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 店铺添加按钮操作 */
    handleAddChenStore() {
      let obj = {};
      obj.uid = this.$store.state.user.user_id;
      obj.name = "";
      this.chenStoreList.push(obj);
    },
    /** 店铺删除按钮操作 */
    handleDeleteChenStore() {
      if (this.checkedChenStore.length == 0) {
        this.$modal.msgError("请先选择要删除的店铺数据");
      } else {
        const chenStoreList = this.chenStoreList;
        const checkedChenStore = this.checkedChenStore;
        this.chenStoreList = chenStoreList.filter(function (item) {
          return checkedChenStore.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleChenStoreSelectionChange(selection) {
      this.checkedChenStore = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('platform/platform/export', {
        ...this.queryParams
      }, `platform_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
