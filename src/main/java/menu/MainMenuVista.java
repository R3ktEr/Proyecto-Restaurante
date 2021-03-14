package menu;

public class MainMenuVista {
	
	private static MainMenuVista myMainMenuVista;
	
	private MainMenuVista() {}

	public static MainMenuVista getMyMainMenuVista()
	{
		if(myMainMenuVista==null)
		{
			myMainMenuVista=new MainMenuVista();
		}
		
		return myMainMenuVista;
	}
	
	public void options()
	{
		System.out.println("\n*****************************************");	
		System.out.println("*\t      Menu principal            *");
		System.out.println("*****************************************");
		System.out.println("* 1.- Clientes                          *");
		System.out.println("* 2.- Nuevo pedido                      *");
		System.out.println("* 3.- Cambiar pedido                    *");
		System.out.println("* 4.- Eliminar pedido                   *");
		System.out.println("* 5.- Mostrar pedidos                   *");
		System.out.println("* 6.- Ver pedidos no pagados            *");
		System.out.println("* 7.- Ver pedidos pendientes de entrega *");
		System.out.println("* 8.- Mostrar caja de hoy               *");
		System.out.println("* 9.- Mostrar caja de este mes          *");
		System.out.println("* 10.- Mostrar caja desde el inicio     *");
		System.out.println("*                                       *");
		System.out.println("* 11.- Salir y Guardar                  *");
		System.out.println("*****************************************");
		System.out.print("\nSeleccione una opcion: ");
	}
	
	public void clientNotFound() {
		System.out.println("Cliente no encontrado!");
	}
	
	public void clientList() {
		System.out.println("\n\tLista de clientes");
	}
	
	public void selectClient() {
		System.out.print("\nSelecciona un cliente: ");
	}
	
	public void askIfDelivered() {
		System.out.print("¿Confirmar la entrega del pedido? S/N: ");
	}
	
	public void askIfNotDelivered() {
		System.out.print("¿Cambiar la entrega del pedido a \"No Entregado\"? S/N: ");
	}
	
	public void selectOrder() {
		System.out.print("\nSeleccione el pedido: ");
	}
	
	public void orderDelivered() {
		System.out.println("Estado del pedido cambiado a \"Entregado\"");
	}
	
	public void orderNotDelivered() {
		System.out.println("Estado del pedido cambiado a \"No Entregado\"");
	}
	
	public void noOrdersFound() {
		System.out.println("\nError: El cliente seleccionado no tiene pedidos");
	}
	
	public void noOrders() {
		System.out.println("\nNo hay pedidos");
	}
	
	public void changeOrder() {
		System.out.println("\n*****************************************");
		System.out.println("*            Cambiar Pedido             *");
		System.out.println("*****************************************");
		System.out.println("* 1.- Buscar pedido por cliente         *");
		System.out.println("* 2.- Buscar pedido por fecha           *");
		System.out.println("* 3.- Buscar pedido por cliente y fecha *");
		System.out.println("*                                       *");
		System.out.println("* 4.-Salir                              *");
		System.out.println("*****************************************");
		System.out.print("\nSeleccione una opcion: ");
	}
	
	public void deleteOrder() {
		System.out.println("\n******************************************");
		System.out.println("*             Borrar Pedido              *");
		System.out.println("******************************************");
		System.out.println("* 1.- Buscar pedido por cliente          *");
		System.out.println("* 2.- Buscar pedido por fecha            *");
		System.out.println("* 3.- Buscar pedido por cliente y fecha  *");
		System.out.println("*                                        *");
		System.out.println("* 4.-Salir                               *");
		System.out.println("******************************************");
		System.out.print("\nSeleccione una opcion: ");
	}
	
	public void showOrder() {
		System.out.println("\n******************************************");
		System.out.println("*             Mostrar Pedidos            *");
		System.out.println("******************************************");
		System.out.println("* 1.- Mostrar todos los pedidos          *");
		System.out.println("* 2.- Mostrar pedido por cliente         *");
		System.out.println("* 3.- Mostrar pedido por fecha           *");
		System.out.println("* 4.- Mostrar pedido por cliente y fecha *");
		System.out.println("*                                        *");
		System.out.println("* 5.-Salir                               *");
		System.out.println("******************************************");
		System.out.print("\nSeleccione una opcion: ");
	}
	
	public void noOrdersByDate() {
		System.out.println("\nNo existe ningun pedido que coincida con esta fecha!");
	}
	
	public void insertDate() {
		System.out.print("\nIntroduce una fecha (Formato: DD-MM-AAAA): ");
	}
	
	public void noOrdersByClientAndDate() {
		System.out.println("\nNo existe ningun pedido que coincida con este cliente y esta fecha!");
	}
	
	public void deleteOrderConfirmation() {
		System.out.print("\n¿Estas seguro de que quieres borrar este pedido? S/N: ");
	}
	
	public void orderDeleted() {
		System.out.println("\nPedido borrado!");
	}
	
	public void showCashToday() {
		System.out.print("\nIngresos de hoy: ");
	}
	
	public void showCashThisMonth() {
		System.out.print("\nIngresos de este mes: ");
	}
	
	public void showCashTotal() {
		System.out.print("\nIngresos totales: ");
	}
	
	public void ordersPendingDeliver() {
		System.out.println("\n\tPedidos pendientes de envio");
	}
}
