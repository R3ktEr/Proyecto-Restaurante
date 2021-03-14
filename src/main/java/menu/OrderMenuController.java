package menu;

import java.util.ArrayList;

import clients.Client;
import orders.Order;
import orders.OrderLine;
import utils.Number;
import utils.Utils;

public class OrderMenuController implements IOrderMenuController{
	
	private static OrderMenuController myOrderMenuController;
	private OrderMenuVista orderMenuVista;
	private OrderMenuModel orderMenuModel;
	
	public OrderMenuController() {
		this.orderMenuVista=OrderMenuVista.getMyOrderMenuVista();
		this.orderMenuModel=OrderMenuModel.getMyOrderMenuModel();
	}
	
	public static OrderMenuController getMyOrderMenuController() {
		
		if(myOrderMenuController==null) {
			myOrderMenuController=new OrderMenuController();
		}
		
		return myOrderMenuController;
	}
	
	public boolean selectOption(Client c, boolean isNew, boolean changeByDate)
	{
		boolean exit=false;
		int select;
		
		if(!changeByDate) {
			if((MainMenuModel.getMainMenuModel().getRepositoryOrders().getOrdersByClient(c.getDni()).size()!=0)&&isNew==false) {
				orderMenuModel.changeOrder(c);
			}else {
				orderMenuModel.newOrder(c);			
			}			
		}else {
			c=orderMenuModel.getNewOrder().getClient();
		}
		
		do
		{
			orderMenuVista.options();
			select=Number.getNumberInRange(1, 13);
			
			switch(select)
			{
			case 1: addProduct(orderMenuModel.getNewOrder());
					break;
			case 2: editLine(orderMenuModel.getNewOrder());
					break;
			case 3: removeLine(orderMenuModel.getNewOrder());
					break;
			case 4: setAddress(orderMenuModel.getNewOrder());;
					break;
			case 5: showCart(orderMenuModel.getNewOrder());
					break;
			case 6: setOrderPaid(orderMenuModel.getNewOrder(),false);
					break;
			case 7: setOrderNoPaid(orderMenuModel.getNewOrder(),false);
					break;
			case 8: setOrderDelivered(orderMenuModel.getNewOrder());
					break;
			case 9: setOrderNotDelivered(orderMenuModel.getNewOrder());
					break;
			case 10: if(save(orderMenuModel.getNewOrder(),c)) {
						exit=true;				
					}
					break;
			case 11: if(savePaid(orderMenuModel.getNewOrder(), c)) {
					exit=true;				
					}
					break;
			case 12: if(saveNotPaid(orderMenuModel.getNewOrder(), c)) {
					exit=true;				
					}
					break;
			case 13: if(orderMenuModel.getNewOrder().isSaved()==false)
					{
						orderMenuVista.orderNotSavedWarning();
						if(Utils.getMyUtils().confirmation())
						{
							exit=true;							
						}
					}
					break;
			}
			
		}while(!exit);
		
		return true;
	}

	public boolean addProduct(Order newOrder) {
		
		boolean valid=false;
		int select;
		int cuantity;
		OrderLine ol=null;
		
		try {
			do {
				orderMenuVista.productList();
				MainMenuModel.getMainMenuModel().getRepositoryProducts().showProducts();
		
				orderMenuVista.selectProduct();
				select=Number.getPositiveNumber()-1;
				
				if(select<0||select>=MainMenuModel.getMainMenuModel().getRepositoryProducts().getAllProducts().size())
				{
					do {
						orderMenuVista.selectInListRange();
						select=Number.getPositiveNumber()-1;						
						valid=false;
					}while(select<0||select>=MainMenuModel.getMainMenuModel().getRepositoryProducts().getAllProducts().size());
				}
				
				orderMenuVista.selectCuantity();
				cuantity=Number.getPositiveNumber();
				
				ol=new OrderLine(MainMenuModel.getMainMenuModel().getRepositoryProducts().getAllProducts().get(select), cuantity);
				
				newOrder.getCart().getNoConfirmedOrders().add(ol);
				newOrder.setTotalProducts(newOrder.getTotalProducts()+cuantity);
				orderMenuVista.productsAddedSuccessfuly();
				
				orderMenuVista.selectAnotherProduct();
				valid=!Utils.getMyUtils().confirmation();
						
			}while(!valid);
			
			return true;
			
		}catch(Exception e) {
			System.out.println("Error al añadir productos a la cesta!");
			return false;
		}
		
	}

	public boolean editLine(Order newOrder) {
		
		int select;
		int cuantity;
		int productsVariation;
		OrderLine ol;
		
		if(newOrder.getCart().showProductInCart()==false)
		{
			return false;
		}
		
		orderMenuVista.selectProductLine();
		
		try {
			do {
				select=Number.getPositiveNumber()-1;
				
				if(select<0||select>=newOrder.getCart().getNoConfirmedOrders().size()) {
					orderMenuVista.selectInListRange();
				}else {
					ol=newOrder.getCart().getNoConfirmedOrders().get(select);
					orderMenuVista.changeCuantity();
					productsVariation=ol.getCuantity();
					do {
						cuantity=Number.getPositiveNumber();
						
						if(cuantity==0) {
							orderMenuVista.errorCuantityIsZero();
						}						
					}while(cuantity==0);
					
					if(cuantity>productsVariation) {
						productsVariation=cuantity-productsVariation;
						newOrder.setTotalProducts(newOrder.getTotalProducts()+productsVariation);
					}else if(cuantity<productsVariation) {
						productsVariation=productsVariation-cuantity;
						newOrder.setTotalProducts(newOrder.getTotalProducts()-productsVariation);
					}
					
					ol.setCuantity(cuantity);
					newOrder.getCart().getNoConfirmedOrders().set(select, ol);
					orderMenuVista.lineChangedSuccessfuly();
					Utils.getMyUtils().promptEnterKey();
				}
			}while(select<0||select>=newOrder.getCart().getNoConfirmedOrders().size());		
			
			
			return true;
		}catch(Exception e) {
			System.out.println("Error al editar la linea!");
			return false;
		}
	}

	public boolean removeLine(Order newOrder) {
		
		int select;
		boolean exit=false;
		
		try {
			if(newOrder.getCart().showProductInCart()==false)
			{
				return false;
			}
			
			orderMenuVista.selectProductLine();
			
			do {
				select=Number.getPositiveNumber()-1;
				
				if(select<0||select>=newOrder.getCart().getNoConfirmedOrders().size()) {
					orderMenuVista.selectInListRange();
				}else {
					orderMenuVista.removeLineWarning();
					
					if(Utils.getMyUtils().confirmation())
					{
						newOrder.setTotalProducts(newOrder.getTotalProducts()-newOrder.getCart().getNoConfirmedOrders().get(select).getCuantity());
						newOrder.getCart().getNoConfirmedOrders().remove(select);
						orderMenuVista.lineRemovedSuccessfuly();
						Utils.getMyUtils().promptEnterKey();
						return true;
					}
					else
					{
						exit=true;
					}
				}				
			}while((select<0||select>=newOrder.getCart().getNoConfirmedOrders().size())&&!exit);
			
			return false;
		} catch (Exception e) {
			System.out.println("Error al borrar la linea!");
			return false;
		}
	}

	public boolean setAddress(Order newOrder) {
		
		int select;
		
		if(newOrder.getCart().getNoConfirmedOrders().size()!=0) {
			try {
				newOrder.getClient().showAddress();
				orderMenuVista.selectAddress();
				select=Number.getNumberInRange(1, newOrder.getClient().getAddress().size())-1;
				newOrder.setAddress(newOrder.getClient().getAddress().get(select));
				orderMenuVista.addressSelected();
				Utils.getMyUtils().promptEnterKey();
				return true;
			} catch (Exception e) {
				System.out.println("Error al cambiar la direccion del pedido!");
				return false;
			}			
		}else {
			orderMenuVista.createLinesFirst();
			return false;
		}
	}

	public boolean setOrderPaid(Order newOrder, boolean withinSave) {
		
		
		if(newOrder.getCart().getNoConfirmedOrders().size()!=0) {
			orderMenuVista.totalPrice((float)newOrder.calculateTotal());
			orderMenuVista.setPaid();
			if(Utils.getMyUtils().confirmation()) {
				newOrder.setPaid(true);
				orderMenuVista.orderPaid();
				if(!withinSave) {
					Utils.getMyUtils().promptEnterKey();					
				}
				return true;
			}else {
				return false;
			}			
		}else {
			orderMenuVista.createLinesFirst();
			Utils.getMyUtils().promptEnterKey();
			return false;
		}
	}
	
	public boolean setOrderNoPaid(Order newOrder, boolean withinSave) {
		
		
		if(newOrder.getCart().getNoConfirmedOrders().size()!=0) {
			orderMenuVista.setNoPaid();
			if(Utils.getMyUtils().confirmation()) {
				newOrder.setPaid(false);
				if(!withinSave) {
					Utils.getMyUtils().promptEnterKey();					
				}
				return true;
			}else {
				return false;
			}			
		}else{
			orderMenuVista.createLinesFirst();
			Utils.getMyUtils().promptEnterKey();
			return false;
		}
	}
	
	public void showCart(Order newOrder) {
		
		if((newOrder.getCart().getNoConfirmedOrders()==null)||(newOrder.getCart().getNoConfirmedOrders().size()==0)) {
			orderMenuVista.emptyCart();
			Utils.getMyUtils().promptEnterKey();
		}else {
			newOrder.showOrder();
			Utils.getMyUtils().promptEnterKey();
		}
	}
	
	public boolean setOrderDelivered(Order newOrder) {
		
		if(newOrder.getCart().getNoConfirmedOrders().size()!=0) {
			orderMenuVista.setOrderDelivered();
			if(Utils.getMyUtils().confirmation()) {
				newOrder.setDelivered(true);
				orderMenuVista.orderDelivered();
				Utils.getMyUtils().promptEnterKey();
				return true;
			}else {
				return false;
			}
		}else {
			orderMenuVista.createLinesFirst();
			return false;
		}
	}
	
	public boolean setOrderNotDelivered(Order newOrder) {
		
		if(newOrder.getCart().getNoConfirmedOrders().size()!=0) {
			orderMenuVista.setNoDelivered();
			if(Utils.getMyUtils().confirmation()) {
				newOrder.setDelivered(false);
				Utils.getMyUtils().promptEnterKey();
				return true;
			}else {
				return false;
			}
		}else {
			orderMenuVista.createLinesFirst();
			return false;
		}
	}
	
	public boolean save(Order newOrder, Client c) {
		//Guardar el pedido en el repositorio de pedidos
		
		ArrayList<Order> orders;
		
		try {
			if((MainMenuModel.getMainMenuModel().getRepositoryOrders().getAllOrders()!=null)) {
				if(newOrder.getCart().getNoConfirmedOrders().size()!=0) {
					if(!(newOrder.getAddress()==null||newOrder.getAddress()=="")) {
						orders=MainMenuModel.getMainMenuModel().getRepositoryOrders().getAllOrders();
						newOrder.calculateTotal();
						
						for (int i = 0; i < orders.size(); i++) {
							if(orders.get(i).equals(newOrder)) {  //Si existia previamente, lo sobreescribe
								orders.set(i, newOrder);
								orderMenuVista.orderSavedSuccessfuly();
								Utils.getMyUtils().promptEnterKey();
								return true;	
							}
						}
						//Si no existia previamente, simplemente lo añade
						orders.add(newOrder);
						c.setOrders(c.getOrders()+1);
						orderMenuVista.orderSavedSuccessfuly();
						Utils.getMyUtils().promptEnterKey();
						return true;															
					}else {
						orderMenuVista.orderHasNoAddress();
						Utils.getMyUtils().promptEnterKey();
					}
				}else {
					orderMenuVista.noProductsInOrder();
					Utils.getMyUtils().promptEnterKey();
				}
			}
			
			return false;
			
		}catch(Exception e) {
			System.out.println("Error al guardar el pedido!");
			return false;
		}
	}
	
	public boolean savePaid(Order newOrder, Client c) {
		//Guardar el pedido en el repositorio de pedidos y pagar
		
		ArrayList<Order> orders;
		
		try {
			if((MainMenuModel.getMainMenuModel().getRepositoryOrders().getAllOrders()!=null)) {
				if(newOrder.getCart().getNoConfirmedOrders().size()!=0) {
					if(!(newOrder.getAddress()==null||newOrder.getAddress()=="")) {
						orders=MainMenuModel.getMainMenuModel().getRepositoryOrders().getAllOrders();
						newOrder.calculateTotal();
						showCart(newOrder);
						
						for (int i = 0; i < orders.size(); i++) {
							if(orders.get(i).equals(newOrder)) {  //Si existia previamente, lo sobreescribe
								if(setOrderPaid(newOrder,true)) { 
									orders.set(i, newOrder);
									orderMenuVista.orderSavedSuccessfuly();
									Utils.getMyUtils().promptEnterKey();
									return true;
								}else {
									return false;
								}
							}
						}
						
						if(setOrderPaid(newOrder,true)) {  //Si no existia previamente, simplemente lo añade
							orders.add(newOrder);
							c.setOrders(c.getOrders()+1);
							orderMenuVista.orderSavedSuccessfuly();
							Utils.getMyUtils().promptEnterKey();
							return true;
						}					
					}else {
						orderMenuVista.orderHasNoAddress();
						Utils.getMyUtils().promptEnterKey();
					}
				}else {
					orderMenuVista.noProductsInOrder();
					Utils.getMyUtils().promptEnterKey();
				}
			}
			
			return false;
			
		}catch(Exception e) {
			System.out.println("Error al guardar el pedido!");
			return false;
		}
	}

	public boolean saveNotPaid(Order newOrder, Client c) {
		//Guardar el pedido en el repositorio de pedidos y no pagar
		
		ArrayList<Order> orders;
		
		try {
			if((MainMenuModel.getMainMenuModel().getRepositoryOrders().getAllOrders()!=null)) {
				if(newOrder.getCart().getNoConfirmedOrders().size()!=0) {
					if(!(newOrder.getAddress()==null||newOrder.getAddress()=="")) {
						orders=MainMenuModel.getMainMenuModel().getRepositoryOrders().getAllOrders();
						newOrder.calculateTotal();
						
						for (int i = 0; i < orders.size(); i++) {
							if(orders.get(i).equals(newOrder)) {  //Si existia previamente, lo sobreescribe
								if(setOrderNoPaid(newOrder,true)) { 
									orders.set(i, newOrder);
									orderMenuVista.orderSavedSuccessfuly();
									Utils.getMyUtils().promptEnterKey();
									return true;
								}else {
									return false;
								}
							}
						}
						
						if(setOrderNoPaid(newOrder,true)) { //Si no existia previamente, simplemente lo añade
							orders.add(newOrder);
							c.setOrders(c.getOrders()+1);
							orderMenuVista.orderSavedSuccessfuly();
							Utils.getMyUtils().promptEnterKey();
							return true;
						}					
					}else {
						orderMenuVista.orderHasNoAddress();
						Utils.getMyUtils().promptEnterKey();
					}
				}else {
					orderMenuVista.noProductsInOrder();
					Utils.getMyUtils().promptEnterKey();
				}
			}
			
			return false;
			
		}catch(Exception e) {
			System.out.println("Error al guardar el pedido!");
			return false;
		}
	}
}
