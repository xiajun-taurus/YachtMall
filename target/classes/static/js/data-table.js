function getIndex(id) {
  return function (value, row, index) {
    var pageSize = $(id).bootstrapTable('getOptions').pageSize;     //通过table的#id
    // 得到每页多少条
    var pageNumber = $(id).bootstrapTable('getOptions').pageNumber; //通过table的#id
    // 得到当前第几页
    return pageSize * (pageNumber - 1) + index + 1;    // 返回每条的序号： 每页条数 *（当前页 -
                                                       // 1 ）+ 序号
  }
}

function InitTable(id, url, columns) {
  $(id).bootstrapTable({
    url: url,         //请求后台的URL（*）
    method: 'post',                      //请求方式（*）
    toolbar: '#toolbar',                //工具按钮用哪个容器，即包含这个组件的id
                                        // 或者classname，用户写一个#toolbar的容器会自动包进去
    toolbarAlign: 'left',
    height: 650,                //表头固定
    rowStyle: function () {       //行样式
      return {
        css: {
          height: '50px'
        }
      }
    },
    cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
    pagination: true,                   //是否显示分页（*）
    queryParams: function (params) {
      return {
        current: params.offset / params.limit + 1, //当前页码=偏移量/size + 1，
        size: params.limit //一页多少条
      };
    },
    responseHandler: function (res) {
      return {
        rows: res.result.records,
        total: res.result.total,
        offset: res.result.current
      }
    },
    // search: true,        //是否显示表格搜索
    sortName: 'lastEditTime',
    sortOrder: 'asc',
    pagination: true,
    sidePagination: 'server',          //分页方式：client客户端分页，server服务端分页（*）
    pageSize: 10,                       //每页的记录行数（*）
    pageList: [10, 20, 50, 100],        //可供选择的每页的行数（*）
    showRefresh: true, //是否显示刷新按钮
    showToggle: true, //是否显示详细视图和列表视图的切换按钮
    showColumns: true,
    uniqueId: "id",                     //每一行的唯一标识，一般为主键列
    undefinedText: '未知',
    icons: {
      refresh: 'fa-refresh',
      toggleOff: 'fa-toggle-off',
      toggleOn: 'fa-toggle-on',
      columns: 'fa-th-list'
    },
    onLoadError: function () {
      alert('数据加载失败');
    },
    columns: columns
  });
}

