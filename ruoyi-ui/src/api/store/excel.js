import request from '@/utils/request'

// 查询参数列表
export function test (query) {
  return request({
    url: '/store/excel/test',
    method: 'get',
    params: query
  })
}