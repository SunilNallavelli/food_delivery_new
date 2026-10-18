package com.online.food.deligates;

import com.online.food.dto.UserRegDto;
import com.online.food.serviceI.ServiceInterface;
import com.online.food.serviceImpl.ServiceImplementation;
import com.online.food.vo.UserRegisterVo;

public class UserRegistrationDeligates {

	public static boolean parse(UserRegisterVo userRegVo) {
		int pincode = 0;
		UserRegDto regDto = null;
		ServiceInterface servIntf = null;
		boolean flag1=false;
		pincode = Integer.parseInt(userRegVo.getPincode());
		regDto = new UserRegDto();
		regDto.setfName(userRegVo.getfName());
		regDto.setlName(userRegVo.getlName());
		regDto.setEmail(userRegVo.getEmail());
		regDto.setPass(userRegVo.getPass());
		regDto.setDob(userRegVo.getDob());
		regDto.setMno(userRegVo.getMno());
		regDto.setFlatNum(userRegVo.getFlatNum());
		regDto.setApartmentNum(userRegVo.getApartmentNum());
		regDto.setLandmark(userRegVo.getLandmark());
		regDto.setPincode(pincode);
		regDto.setCity(userRegVo.getCity());
		regDto.setState(userRegVo.getState());
		regDto.setCountry(userRegVo.getCountry());
		/* regDto.setRole(userRegVo.getRole()); */
		servIntf = new ServiceImplementation();
		flag1 = servIntf.register(regDto);
		System.out.println("1");
		
		return flag1;
	}

}
