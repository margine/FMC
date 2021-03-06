<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<table class="table table-striped table-bordered detail">

	<tr>
		<td class="title" rowspan="2" style="width: 8%;">业务信息</td>
		<td class="title">业务编号</td>
		<td class="title" colspan="2">接单时间</td>
		<td class="title">接单业务员</td>
		<td class="title" style="color:red;">订单来源</td>
		<td class="title">翻单</td>
	</tr>
	<tr>
		<td>${orderInfo.orderId}</td>
		<td colspan="2">${fn:substring(orderInfo.order.orderTime,0,19)}</td>
		<td>${orderInfo.employee.employeeName}</td>
		<td>${orderInfo.order.orderSource }</td>
		<td>${orderInfo.order.reorder==0?"否":"是" }</td>
	</tr>
	<tr>
		<td class="title" rowspan="3">客户信息</td>
		<td class="title">客户编号</td>
		<td class="title">姓名</td>
		<td class="title">公司</td>
		<td class="title">传真</td>
		<td class="title">手机1</td>
		<td class="title">手机2</td>
	</tr>
	<tr>
		<td>${orderInfo.order.customerId }</td>
		<td>${orderInfo.order.customerName }</td>
		<td>${orderInfo.order.customerCompany }</td>
		<td>${orderInfo.order.customerCompanyFax}</td>
		<td>${orderInfo.order.customerPhone1}</td>
		<td>${orderInfo.order.customerPhone2}</td>
	</tr>
	<tr>
		<td class="title">公司地址</td>
		<td colspan="5">${orderInfo.order.customerCompanyAddress}</td>
	</tr>
	<tr>
		<td class="title" rowspan="3">款式信息</td>
		<td class="title">款式名称</td>
		<td class="title">款式性别</td>
		<td class="title">款式季节</td>
		<td class="title">面料类型</td>
		<td class="title">特殊工艺</td>
		<td class="title">其他说明</td>
	</tr>
	<tr>
		<td>${orderInfo.order.styleName }</td>
		<td>${orderInfo.order.styleSex }</td>
		<td>${orderInfo.order.styleSeason}</td>
		<td>${orderInfo.order.fabricType}</td>
		<td>${orderInfo.order.specialProcess}</td>
		<td>${orderInfo.order.otherRequirements}</td>
	</tr>
	
	<tr>
		<td class="title">参考链接</td>
		<td colspan="5" style="word-wrap:break-word;word-break:break-all;"><a href="${orderInfo.order.referenceUrl}"  target="_blank">${orderInfo.order.referenceUrl}</a></td>
	</tr>
	<tr>
		<td class="title" rowspan="2">专员信息</td>
		<td class="title">接单业务员</td>
		<td class="title" colspan="2">业务员邮箱</td>
		<td class="title">业务员手机</td>
		<td class="title">业务员办公电话</td>
		<td class="title">业务员QQ</td>
	 
 	</tr>
	<tr >
		<td>${orderInfo.employee.employeeName}</td>
		<td colspan="2">${orderInfo.employee.email}</td>
		<td>${orderInfo.employee.phone1}</td>
		<td>${orderInfo.employee.jobPhone}</td>
		<td>${orderInfo.employee.qq}</td>
  	</tr>

</table>