package com.online.food.serviceImpl;

import com.online.food.bo.LoginBo;
import com.online.food.bo.OrdersBo;
import com.online.food.bo.RegistrationBo;
import com.online.food.daoI.DaoInterface;
import com.online.food.daoImpl.DaoImpl;
import com.online.food.dto.OrdersDto;
import com.online.food.dto.UserLoginDto;
import com.online.food.dto.UserRegDto;
import com.online.food.serviceI.ServiceInterface;

public class ServiceImplementation implements ServiceInterface {
	
	public boolean register(UserRegDto regDto) {
		RegistrationBo regBo=null;
		DaoInterface iDaoReg=null;
		regBo=new RegistrationBo();
		regBo.setfName(regDto.getfName());
		regBo.setlName(regDto.getlName());
		regBo.setEmail(regDto.getEmail());
		regBo.setPass(regDto.getPass());
		regBo.setDob(regDto.getDob());
		regBo.setMno(regDto.getMno());
		regBo.setFlatNum(regDto.getFlatNum());
		regBo.setApartmentNum(regDto.getApartmentNum());
		regBo.setLandmark(regDto.getLandmark());
		regBo.setPincode(regDto.getPincode());
		regBo.setCity(regDto.getCity());
		regBo.setState(regDto.getState());
		regBo.setCountry(regDto.getCountry());
		/* regBo.setRole(regDto.getRole()); */
		iDaoReg = new DaoImpl();
		System.out.println("2");
		if(iDaoReg.insert(regBo)>0)
			return true;
		else 
			return false;
	}

	@Override
	public String login(UserLoginDto userlogDto) {
		LoginBo logBo = new LoginBo();
		DaoInterface iDaolog=new DaoImpl();
		logBo.setEmail(userlogDto.getEmail());
		logBo.setPassword(userlogDto.getPassword());
		System.out.println("2");
		if(iDaolog.verify(logBo).equals("user role")) {
			System.out.println("3");
			return "user role";
		}
		else if(iDaolog.verify(logBo).equals("admin role")) {
			System.out.println("3");
			return "admin role";
		}
		else
			return "invalid user credentials";
	}

	@Override
	public int orderInsert(OrdersDto orderDto) {
		DaoInterface iDaoOrderInsert=null;
		OrdersBo bo= new OrdersBo();
		bo.setName(orderDto.getName());
		bo.setQuantity(orderDto.getQuantity());
		bo.setEmail(orderDto.getEmail());
		
		iDaoOrderInsert= new DaoImpl();
		return iDaoOrderInsert.orderInsert(bo);
		
	}

	
	
}
