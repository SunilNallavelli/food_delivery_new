package com.online.food.daoI;


import com.online.food.bo.LoginBo;
import com.online.food.bo.OrdersBo;
import com.online.food.bo.RegistrationBo;

public interface DaoInterface {

	public abstract int insert(RegistrationBo regBo);

	public abstract String verify(LoginBo logBo);

	public abstract int orderInsert(OrdersBo bo);


	
	


}
