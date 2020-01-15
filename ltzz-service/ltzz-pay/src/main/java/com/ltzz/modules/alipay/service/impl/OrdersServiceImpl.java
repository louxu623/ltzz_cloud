package com.ltzz.modules.alipay.service.impl;

import com.ltzz.idworker.Sid;
import com.ltzz.modules.alipay.dao.FlowDao;
import com.ltzz.modules.alipay.dao.OrdersDao;
import com.ltzz.modules.alipay.entity.Flow;
import com.ltzz.modules.alipay.entity.Orders;
import com.ltzz.modules.alipay.service.OrdersService;
import com.sihai.utils.OrderStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrdersDao ordersDao;
	
	@Autowired
	private FlowDao flowDao;
	
	@Autowired
	private Sid sid;
	
	@Override
	public void saveOrder(Orders order) {
		ordersDao.insert(order);
	}

	@Override
	public Orders getOrderById(String orderId) {
		return ordersDao.selectByPrimaryKey(orderId);
	}

	@Override
	public void updateOrderStatus(String orderId, String alpayFlowNum, String paidAmount) {
		
		Orders order = getOrderById(orderId);
		if (order.getOrderStatus().equals(OrderStatusEnum.WAIT_PAY.key)) {
			order = new Orders();
			order.setId(orderId);
			order.setOrderStatus(OrderStatusEnum.PAID.key);
			order.setPaidTime(new Date());
			order.setPaidAmount(paidAmount);
			
			ordersDao.updateByPrimaryKeySelective(order);
			
			order = getOrderById(orderId);
			
			String flowId = sid.nextShort();
			Flow flow = new Flow();
			flow.setId(flowId);
			flow.setFlowNum(alpayFlowNum);
			flow.setBuyCounts(order.getBuyCounts());
			flow.setCreateTime(new Date());
			flow.setOrderNum(orderId);
			flow.setPaidAmount(paidAmount);
			flow.setPaidMethod(1);
			flow.setProductId(order.getProductId());
			
			flowDao.insertSelective(flow);
		}
		
	}

}
