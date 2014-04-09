package nju.software.service;

import java.util.List;

import nju.software.dataobject.Logistics;
import nju.software.model.OrderInfo;

public interface LogisticsService {
	
	public List<OrderInfo>getReceiveSampleList();
	
	public OrderInfo getReceiveSampleDetail(Integer orderId);
	
	public boolean receiveSampleSubmit(long taskId,String result);
	
	
	 public List<OrderInfo> getSendClothesList();
		
		public OrderInfo getSendClothesDetail(Integer orderId);

		public void sendClothesSubmit(int orderId,long taskId,float logistics_cost);
	
	
	
	public List<OrderInfo>getWarehouseList();
	
	public OrderInfo getWarehouseDetail(Integer orderId);
	
	public boolean warehouseSubmit(long taskId,String result);
	
	
	public Logistics findByOrderId(String orderId);

	public boolean addLogistics(Logistics log);

	public boolean sendSampleSubmit(long taskId, long processId);

	public List<OrderInfo> getSendSampleList(int s_page, int s_number_per_page);

	public OrderInfo getSendSampleDetail(int orderId, long tid);
}
