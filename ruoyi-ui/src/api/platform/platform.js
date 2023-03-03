import request from '@/utils/request'

// 查询平台列表
export function listPlatform(query) {
  return request({
    url: '/platform/platform/list',
    method: 'get',
    params: query
  })
}

// 查询平台详细
export function getPlatform(id) {
  return request({
    url: '/platform/platform/' + id,
    method: 'get'
  })
}

// 新增平台

export function addPlatform(data) {
  return request({
    url: '/platform/platform',
    method: 'post',
    data: data
  })
}

// 修改平台

export function updatePlatform(data) {
  return request({
    url: '/platform/platform',
    method: 'put',
    data: data
  })
}

// 删除平台

export function delPlatform(id) {
  return request({
    url: '/platform/platform/' + id,
    method: 'delete'
  })
}
