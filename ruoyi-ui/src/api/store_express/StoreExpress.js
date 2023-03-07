import request from '@/utils/request'

// 查询店铺-快递费用列表
export function listStoreExpress(query) {
  return request({
    url: '/store_express/StoreExpress/list',
    method: 'get',
    params: query
  })
}

// 查询店铺-快递费用详细
export function getStoreExpress(id) {
  return request({
    url: '/store_express/StoreExpress/' + id,
    method: 'get'
  })
}

// 新增店铺-快递费用
export function addStoreExpress(data) {
  return request({
    url: '/store_express/StoreExpress',
    method: 'post',
    data: data
  })
}

// 修改店铺-快递费用
export function updateStoreExpress(data) {
  return request({
    url: '/store_express/StoreExpress',
    method: 'put',
    data: data
  })
}

// 删除店铺-快递费用
export function delStoreExpress(id) {
  return request({
    url: '/store_express/StoreExpress/' + id,
    method: 'delete'
  })
}
