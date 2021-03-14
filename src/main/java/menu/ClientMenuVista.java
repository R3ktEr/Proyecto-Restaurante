package menu;

public class ClientMenuVista {

	private static ClientMenuVista myClientMenuVista;
	
	private ClientMenuVista() {}
	
	public static ClientMenuVista getMyClientMenuVista()
	{
		if(myClientMenuVista==null)
		{
			myClientMenuVista=new ClientMenuVista();
		}
		
		return myClientMenuVista;
	}
	
	public void options(){
		System.out.println("\n************************");
		System.out.println("*     Menu Clientes    *");
		System.out.println("************************");
		System.out.println("* 1.- Crear Cliente    *");
		System.out.println("* 2.- Editar Cliente   *");
		System.out.println("* 3.- Borrar Cliente   *");
		System.out.println("* 4.- Mostrar Clientes *");
		System.out.println("*                      *");
		System.out.println("* 5.- Salir            *");
		System.out.println("************************");
		System.out.print("\nSeleccione una opcion: ");
	}
	
	public void newClient() {	
		System.out.println("\n\tNuevo Cliente");
	}
	
	public void newName(){
		System.out.print("Introduzca un nombre: ");
	}
	
	public void newDNI(){
		System.out.print("Introduzca un DNI (Formato: 12345678-A): ");
	}
	
	public void newAge() {
		System.out.print("Introduzca la edad: ");
	}
	
	public void newAddress() {
		System.out.print("Introduzca una direccion: ");
	}
	
	public void errorCreatingClient() {
		System.out.println("Error al crear el cliente!");
	}
	
	public void editClient() {
		System.out.println("\n***************************");
		System.out.println("*      Editar Cliente     *");
		System.out.println("***************************");
		System.out.println("* 1.- Cambiar nombre      *");
		System.out.println("* 2.- Cambiar DNI         *");
		System.out.println("* 3.- Cambiar edad        *");
		System.out.println("* 4.- Editar direcciones  *");
		System.out.println("*                         *");
		System.out.println("* 5.- Salir               *");
		System.out.println("***************************");
		System.out.print("\nElija una opcion: ");
	}
	
	public void insertNewName() {
		System.out.print("\nIntroduzca el nuevo nombre del cliente: ");
	}
	
	public void insertNewDNI() {
		System.out.print("\nIntroduzca el nuevo DNI del cliente (Formato: 12345678-A): ");
	}
	
	public void clientCreatedSuccessfuly() {
		System.out.println("\nCliente creado correctamente!");
	}
	
	public void clientDoesExit() {
		System.out.println("Error: El cliente ya existe");
	}
	
	public void insertNewAge() {
		System.out.print("\nIntroduzca la nueva edad del cliente: ");
	}
	
	public void selectAddress() {
		System.out.print("\nSeleccione la direccion a cambiar: ");
	}
	
	public void selectDropAddress() {
		System.out.print("\nSeleccione la direccion a borrar: ");
	}
	
	public void addressDroppedSuccessfuly() {
		System.out.println("\nDireccion borrada correctamente!");
	}
	
	public void editAddresses() {
		System.out.println("\n***********************************");
		System.out.println("*         Menu Direcciones        *");
		System.out.println("***********************************");
		System.out.println("* 1.- Cambiar direccion existente *");
		System.out.println("* 2.- Añadir nueva direccion      *");
		System.out.println("* 3.- Borrar direccion            *");
		System.out.println("*                                 *");
		System.out.println("* 4.- Salir                       *");
		System.out.println("***********************************");
		System.out.print("\nSeleccione una opcion: ");
	}
	
	public void insertNewAddress() {
		System.out.print("\nIntroduzca la nueva direccion: ");
	}
	
	public void updateClientSuccessful() {
		System.out.println("\nCliente actualizado correctamente!");
	}
	
	public void clientDroppedSuccesfuly() {
		System.out.println("\nCliente borrado satisfactoriamente!");
	}
	
	public void clientDoNotExist() {
		System.out.println("Error: El cliente introducido no existe");
	}
	
	public void clientHasNoAddress() {
		System.out.println("\nEl cliente seleccionado no tiene direcciones");
	}
	
	public void noClients() {
		System.out.println("\nNo hay ningun cliente en la base de datos!");
	}
	
	public void ordersWillBeDroppedWarning() {
		System.out.print("\nADVERTENCIA: Los pedidos relacionados con este cliente tambien se borraran.\n\nContinuar? S/N: ");
	}
}
