<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="店铺名字" prop="name">
        <el-input v-model="queryParams.name" placeholder="请输入店铺名字" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="平台" prop="platformId">
        <el-select v-model="queryParams.platformId" placeholder="请选择平台" clearable @change="handleQuery">
          <el-option v-for="item in platformList" :key="item.id" :label="item.name" :value="item.id" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <!-- <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['store_express:StoreExpress:add']">新增</el-button>
      </el-col> -->
      <!-- <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['store_express:StoreExpress:edit']">修改</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="addStoreExpress"
          v-hasPermi="['store_express:StoreExpress:addStoreExpress']">一键设置店铺快递</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['store_express:StoreExpress:remove']">删除</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['store_express:StoreExpress:export']">导出</el-button>
      </el-col> -->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="StoreExpressList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <!-- <el-table-column label="用户id" align="center" prop="uid" /> -->
      <el-table-column label="店铺名字" align="center" prop="name" />
      <!-- <el-table-column label="平台id" align="center" prop="platformId" /> -->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['store_express:StoreExpress:edit']">设置店铺快递费用</el-button>
          <!-- <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['store_express:StoreExpress:remove']">删除</el-button> -->
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 一键设置店铺快递 -->
    <el-dialog :title="一键设置店铺快递" :visible.sync="openSet" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-divider content-position="center">快递信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddChenStoreExpress">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini"
              @click="handleDeleteChenStoreExpress">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="chenStoreExpressList" :row-class-name="rowChenStoreExpressIndex"
          @selection-change="handleChenStoreExpressSelectionChange" ref="chenStoreExpress">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50" />
          <el-table-column label="快递名" prop="name" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.name" placeholder="请输入快递名" />
            </template>
          </el-table-column>
          <el-table-column label="单个包裹价格" prop="price" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.price" placeholder="请输入单个包裹价格" />
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="">确 定</el-button>
        <el-button @click="">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 添加或修改店铺-快递费用对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <!-- <el-form-item label="用户id" prop="uid">
          <el-input v-model="form.uid" placeholder="请输入用户id" />
        </el-form-item> -->
        <el-form-item label="店铺名字" prop="name">
          <el-input v-model="form.name" placeholder="请输入店铺名字" />
        </el-form-item>
        <!-- <el-form-item label="平台id" prop="platformId">
          <el-input v-model="form.platformId" placeholder="请输入平台id" />
        </el-form-item> -->
        <el-divider content-position="center">快递信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddChenStoreExpress">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini"
              @click="handleDeleteChenStoreExpress">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="chenStoreExpressList" :row-class-name="rowChenStoreExpressIndex"
          @selection-change="handleChenStoreExpressSelectionChange" ref="chenStoreExpress">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50" />
          <el-table-column label="快递名" prop="name" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.name" placeholder="请输入快递名" />
            </template>
          </el-table-column>
          <el-table-column label="单个包裹价格" prop="price" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.price" placeholder="请输入单个包裹价格" />
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
import { listStoreExpress, getStoreExpress, delStoreExpress, addStoreExpress, updateStoreExpress } from "@/api/store_express/StoreExpress";
import { listPlatform } from "@/api/platform/platform";
export default {
  name: "StoreExpress",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedChenStoreExpress: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 店铺-快递费用表格数据
      StoreExpressList: [],
      //平台列表
      platformList: [],
      // 快递表格数据
      chenStoreExpressList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        uid: this.$store.state.user.user_id,
        name: null,
      },
      // 是否显示一键设置快递弹出层
      openSet: false,
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    this.getPlatform();
  },
  methods: {
    /**
     * openSet显示弹出层
     */
    addStoreExpress() {
      this.openSet = true;
    },
    /**
     * 查询平台
     */
    getPlatform() {
      listPlatform({
        pageNum: 1,
        pageSize: 1000,
        uid: this.$store.state.user.user_id,
      }).then(response => {
        this.platformList = response.rows;
      });
    },
    /** 查询店铺-快递费用列表 */
    getList() {
      this.loading = true;
      listStoreExpress(this.queryParams).then(response => {
        this.StoreExpressList = response.rows;
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
        updateTime: null
      };
      this.chenStoreExpressList = [];
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
      this.title = "添加店铺-快递费用";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getStoreExpress(id).then(response => {
        this.form = response.data;
        this.chenStoreExpressList = response.data.chenStoreExpressList;
        this.open = true;
        this.title = "修改店铺-快递费用";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.chenStoreExpressList = this.chenStoreExpressList;
          if (this.form.id != null) {
            updateStoreExpress(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addStoreExpress(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除店铺-快递费用编号为"' + ids + '"的数据项？').then(function () {
        return delStoreExpress(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 快递序号 */
    rowChenStoreExpressIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 快递添加按钮操作 */
    handleAddChenStoreExpress() {
      let obj = {};
      obj.name = "";
      obj.price = "";
      this.chenStoreExpressList.push(obj);
    },
    /** 快递删除按钮操作 */
    handleDeleteChenStoreExpress() {
      if (this.checkedChenStoreExpress.length == 0) {
        this.$modal.msgError("请先选择要删除的快递数据");
      } else {
        const chenStoreExpressList = this.chenStoreExpressList;
        const checkedChenStoreExpress = this.checkedChenStoreExpress;
        this.chenStoreExpressList = chenStoreExpressList.filter(function (item) {
          return checkedChenStoreExpress.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleChenStoreExpressSelectionChange(selection) {
      this.checkedChenStoreExpress = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('store_express/StoreExpress/export', {
        ...this.queryParams
      }, `StoreExpress_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
