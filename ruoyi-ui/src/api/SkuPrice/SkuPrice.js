import request from '@/utils/request'

// 查询sku价格对照列表
export function listSkuPrice(query) {
  return request({
    url: '/SkuPrice/SkuPrice/list',
    method: 'get',
    params: query
  })
}

// 查询sku价格对照详细
export function getSkuPrice(id) {
  return request({
    url: '/SkuPrice/SkuPrice/' + id,
    method: 'get'
  })
}

// 新增sku价格对照
export function addSkuPrice(data) {
  return request({
    url: '/SkuPrice/SkuPrice',
    method: 'post',
    data: data
  })
}

// 修改sku价格对照
export function updateSkuPrice(data) {
  return request({
    url: '/SkuPrice/SkuPrice',
    method: 'put',
    data: data
  })
}

// 删除sku价格对照
export function delSkuPrice(id) {
  return request({
    url: '/SkuPrice/SkuPrice/' + id,
    method: 'delete'
  })
}
