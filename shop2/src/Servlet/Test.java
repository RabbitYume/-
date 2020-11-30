package Servlet;

import java.util.List;

import dao.impl.GoodsManagerImpl;
import dao.impl.SalesManagerImpl;
import dao.impl.StockManagerImpl;
import entity.Msg;
import entity.Sales;
import entity.Stock;
import service.IShopCarService;
import service.impl.SalesServiceImpl;
import service.impl.ShopCarServiceImpl;
import service.impl.StockServiceImpl;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IShopCarService shopCarService = new ShopCarServiceImpl();
		Msg msg= shopCarService.getNum(333333);
		System.out.println(msg.getObj());
	}

}
