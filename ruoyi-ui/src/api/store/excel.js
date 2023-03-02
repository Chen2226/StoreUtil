import request from '@/utils/request'


export function getPlatformStoreInfo (query) {
  return request({
    url: '/platform/platformList/list',
    method: 'get',
    params: query
  })
}