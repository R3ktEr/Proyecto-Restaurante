package menu;

public class OrderMenuVista {

	private static OrderMenuVista myOrderMenuVista;
	
	private OrderMenuVista() {}
	
	public static OrderMenuVista getMyOrderMenuVista() {
		
		if(myOrderMenuVista==null) {
			myOrderMenuVista=new OrderMenuVista();
		}
		
		return myOrderMenuVista;
	}
	
	public void options()
	{
		System.out.println("\n************************************************");
		System.out.println("*               Menu de pedidos                *");
		System.out.println("************************************************");
		System.out.println("* 1.- Añadir producto a la cesta               *");
		System.out.println("* 2.- Editar linea de pedido                   *");
		System.out.println("* 3.- Borrar linea de pedido                   *");
		System.out.println("* 4.- Insertar direccion de destino del pedido *");
		System.out.println("* 5.- Mostrar productos de la cesta            *");
		System.out.println("* 6.- Marcar pedido como pagado                *");
		System.out.println("* 7.- Marcar pedido como no pagado             *");
		System.out.println("* 8.- Marcar pedido como entregado             *");
		System.out.println("* 9.- Marcar pedido como no entregado          *");
		System.out.println("*                                              *");
		System.out.println("* 10.- Guardar                                 *");
		System.out.println("* 11.- Guardar y pagar                         *");
		System.out.println("* 12.- Guardar sin pagar                       *");
		System.out.println("*                                              *");
		System.out.println("* 13.- Salir                                   *");
		System.out.println("************************************************");
		System.out.print("\nSeleccione una opcion: ");	
	}
	
	public void productList() {
		System.out.println("\n\tLista de productos");
	}
	
	public void selectProduct() {
		System.out.print("\nSeleccione un producto: ");
	}
	
	public void selectAnotherProduct() {
		System.out.print("¿Desea seleccionar otro producto? S/N: ");
	}
	
	public void selectInListRange() {
		System.out.print("Por favor, introduzca un numero perteneciente a un elemento de la lista: ");
	}
	
	public void selectCuantity() {
		System.out.print("Seleccione la cantidad: ");
	}
	
	public void productsAddedSuccessfuly() {
		System.out.println("\nProducto/s añadido/s correctamente!");
	}
	
	public void orderNotSavedWarning() {
		System.out.print("\n¿Desea salir sin guardar? S/N: ");
	}
	
	public void selectProductLine() {
		System.out.print("\nSeleccione una linea de productos: ");
	}
	
	public void changeCuantity() {
		System.out.print("\nIntroduzca la nueva cantidad de producto: ");
	}
	
	public void errorCuantityIsZero() {
		System.out.print("\nError! La nueva cantidad introducida no puede ser 0. Introduzca la cantidad de nuevo: ");
	}
	
	public void lineChangedSuccessfuly() {
		System.out.println("\nLinea cambiada correctamente!");
	}
	
	public void removeLineWarning() {
		System.out.print("\n¿Esta seguro de que quiere borrar esta linea? S/N: ");
	}
	
	public void lineRemovedSuccessfuly() {
		System.out.println("\nLinea borrada con exito!");
	}
	
	public void setAddress() {
		System.out.print("\nIntroduzca la nueva direccion del pedido: ");
	}
	
	public void selectAddress() {
		System.out.print("\nSeleccione una direccion: ");
	}
	
	public void addressSelected() {
		System.out.println("\nDireccion seleccionada correctamente!");
	}
	
	public void addressChangedSuccessfuly() {
		System.out.println("\nDireccion del pedido cambiada correctamente!");
	}
	
	public void noOrdersFound() {
		System.out.println("Error: La cesta del cliente esta vacia!");
	}
	
	public void orderSavedSuccessfuly() {
		System.out.println("\nPedido guardado correctamente!");
	}
	
	public void totalPrice(float total) {
		System.out.print("\nEl precio total del pedido es: "+total+" Euros\n");
	}
	
	public void setPaid() {
		System.out.print("\n¿Pagar? S/N: ");
	}
	
	public void setNoPaid() {
		System.out.print("\n¿Guardar pedido como \"No pagado\"? S/N: ");
	}
	
	public void orderPaid() {
		System.out.println("\nPedido pagado!");
	}
	
	public void emptyCart() {
		System.out.println("\nLa cesta esta vacía!");
	}
	
	public void createLinesFirst() {
		System.out.println("\nError: Se deben añadir productos a la cesta antes de modificar este campo");
	}
	
	public void setNoDelivered() {
		System.out.print("\n¿Cambiar pedido a \"No entregado\"? S/N: " );
	}
	
	public void setOrderDelivered() {
		System.out.print("\n¿Cambiar el pedido a \"Entregado\"? S/N: ");
	}
	
	public void orderDelivered() {
		System.out.println("\nPedido entregado!");
	}
	
	public void saveAndPay() {
		System.out.print("\n¿Guardar pedido y pagar? S/N: ");
	}
	
	public void saveWithoutPay() {
		System.out.print("\n¿Guardar pedido y pagar? S/N: ");
	}
	
	public void noProductsInOrder() {
		System.out.println("\nGuardado abortado! La cesta de productos esta vacia");
	}
	
	public void selectOrder() {
		System.out.print("\nSeleccione un pedido: ");
	}
	
	public void clientOrders() {
		System.out.println("\n\tPedidos del cliente");
	}
	
	public void orderHasNoAddress() {
		System.out.println("\nGuardado abortado! El pedido debe tener una direccion de entrega");
	}
	
	public void ordersByDate() {
		System.out.println("\n\tPedidos por fecha");
	}
	
	public void ordersByClientAndDate() {
		System.out.println("\n\tPedidos por cliente y fecha");
	}
	
	public void orderList() {
		System.out.println("\n\tLista de pedidos");
	}
}
