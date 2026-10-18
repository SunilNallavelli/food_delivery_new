package com.online.food.deligates;

import com.online.food.dto.UserLoginDto;
import com.online.food.serviceI.ServiceInterface;
import com.online.food.serviceImpl.ServiceImplementation;
import com.online.food.vo.UserLoginVo;

public class UserLoginDeligates {

	

	public static String authenticateUser(UserLoginVo userlogVo) {
		UserLoginDto userlogDto = new UserLoginDto();
		ServiceInterface servIntf = new ServiceImplementation();
		 
		
		userlogDto.setEmail(userlogVo.getEmail());
		userlogDto.setPassword(userlogVo.getPassword());
		
		String valid = servIntf.login(userlogDto);
		return valid;
	}

	
}
