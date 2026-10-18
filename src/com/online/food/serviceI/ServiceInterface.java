package com.online.food.serviceI;

import com.online.food.dto.OrdersDto;
import com.online.food.dto.UserLoginDto;
import com.online.food.dto.UserRegDto;

public interface ServiceInterface {

	boolean register(UserRegDto regDto);

	String login(UserLoginDto userlogDto);

	int orderInsert(OrdersDto orderDto);



}
