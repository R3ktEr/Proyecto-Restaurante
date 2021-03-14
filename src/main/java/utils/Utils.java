package utils;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import clients.RepositoryClients;
import orders.RepositoryOrders;

public class Utils {
	
	private static Utils _myInstance;
	
	private Utils(){}
	
	public static Utils getMyUtils()
	{
		if(_myInstance==null)
		{
			_myInstance=new Utils();
		}
		
		return _myInstance;
	}

	public static void setUtils(Utils utils) {
		Utils._myInstance = utils;
	}

	public boolean saveClients(String url)
	{
		boolean result=false;
		
		try {
			JAXBContext jaxbContext= JAXBContext.newInstance(RepositoryClients.class);
			Marshaller marshaller=jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(RepositoryClients.getMyRepositoryClients(), new File(url));
			result=true;
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public boolean loadClients(String url)
	{
		boolean result=false;
		
		try {
			JAXBContext jaxbcontent=JAXBContext.newInstance(RepositoryClients.class);
			Unmarshaller unmarshaller=jaxbcontent.createUnmarshaller();
			RepositoryClients.getMyRepositoryClients().setClientList(((RepositoryClients) unmarshaller.unmarshal(new File(url))).getAllClients());
			result=true;
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	public boolean saveOrders(String url)
	{
		boolean result=false;
		
		try {
			JAXBContext jaxbContext= JAXBContext.newInstance(RepositoryOrders.class);
			Marshaller marshaller=jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(RepositoryOrders.getMyRepositoryOrders(), new File(url));
			result=true;
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	public boolean loadOrders(String url)
	{
		boolean result=false;
		
		try {
			JAXBContext jaxbcontent=JAXBContext.newInstance(RepositoryOrders.class);
			Unmarshaller unmarshaller=jaxbcontent.createUnmarshaller();
			RepositoryOrders.getMyRepositoryOrders().setOrders(((RepositoryOrders) unmarshaller.unmarshal(new File(url))).getAllOrders());
			result=true;
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	public String getString()
	{
		String string="";
		
		@SuppressWarnings("resource")
		Scanner keyboard=new Scanner(System.in);
		
		do
		{
			string=keyboard.nextLine();
			
			if(string==null||string=="")
			{
				System.out.print("Por favor, introduzca una palabra o frase valida: ");
			}
			
		}while(string==null||string=="");
		
		return string;
	}
	
	public String getName()
	{
		String string="";
		
		@SuppressWarnings("resource")
		Scanner keyboard=new Scanner(System.in);
		
		do
		{
			string=keyboard.nextLine();
			
			if(string==null||string=="")
			{
				System.out.print("Por favor, introduzca una palabra o frase valida: ");
			}else if(!string.matches("(i?)\\D+")) {
				System.out.print("Los digitos no estan permitidos en el nombre! Vuelva a introducirlo: ");
			}
			
		}while(string==null||string==""||!string.matches("(i?)\\D+"));
		
		return string;
	}
	
	public String getDNI()
	{
		String dni="";
		boolean valid=false;
		
		@SuppressWarnings("resource")
		Scanner keyboard=new Scanner(System.in);
		
		do
		{
			dni=keyboard.nextLine();
			
			if(dni==null||!(dni.matches("\\d{8}-[A-Z]")))
			{
				System.out.print("Por favor, introduzca un DNI valido: ");
				valid=false;
			}else {
				valid=true;
			}
			
		}while(!valid);
		
		return dni;
	}
	
	public void promptEnterKey()
	{
	   @SuppressWarnings("resource")
	   Scanner scanner = new Scanner(System.in);
	   System.out.println("\nPulse Intro para continuar...");
	   scanner.nextLine();
	}
	
	public boolean confirmation()
	{
		@SuppressWarnings("resource")
		Scanner keyboard=new Scanner(System.in);
		String confirmation="";
		boolean conf=false;
		
		do
		{
			confirmation=keyboard.next();
			
			if(confirmation.charAt(0)=='s'||confirmation.charAt(0)=='n'||confirmation.charAt(0)=='S'||confirmation.charAt(0)=='N')
			{
				conf=true;						
			}
			else
			{
				System.out.print("Por favor, vuelva a introducir la confirmacion: ");	
			}
			
		}while(!conf);
		
		if(confirmation.equals("N")||confirmation.equals("n"))
		{
			conf=false;
		}
		
		return conf;
	}
	
	public LocalDate getLocalDate() {
		LocalDate d;
		DateTimeFormatter df=DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String date="";
		boolean valid=false;
		
		do {
			date=getString();
			if(date.matches("\\d{2}-\\d{2}-\\d{4}")) {
				valid=true;
			}else {
				System.out.print("Por favor, introduzca la fecha en el formato especificado: ");
			}
		}while(!valid);
		
		d=LocalDate.parse(date, df);
		
		return d;
	}
}
