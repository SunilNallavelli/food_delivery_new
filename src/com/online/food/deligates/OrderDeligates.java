package com.online.food.deligates;

import com.online.food.dto.OrdersDto;
import com.online.food.serviceI.ServiceInterface;
import com.online.food.serviceImpl.ServiceImplementation;
import com.online.food.vo.OrdersPageVo;

public class OrderDeligates {

	public int parse(OrdersPageVo vo) {
		OrdersDto orderDto=new OrdersDto();
		orderDto.setName(vo.getName());
		orderDto.setEmail(vo.getEmail());
		int q=Integer.parseInt(vo.getQuantity());
		orderDto.setQuantity(q);
		ServiceInterface servIntf = new ServiceImplementation();
		return servIntf.orderInsert(orderDto);
		
	}
	
	

}
