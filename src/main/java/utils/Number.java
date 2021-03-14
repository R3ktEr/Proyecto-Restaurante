package utils;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;

/**
 * La clase Number provee de una serie de metodos para la obtencion de numeros por teclado con control de errores
 * 
 * @author Cristian
 *
 */
public class Number
{
   
    private static Scanner teclado=new Scanner(System.in);;
    private static int inumber=-1;
    private static double dnumber=-1;
    private static boolean error=false;
    
    /**
     * El metodo getPositiveNumber pide por teclado un numero entero positivo con control de errores
     * @return devuelve un numero entero positivo
     */
    public static int getPositiveNumber()
    {
        do
        {
            try
            {
                error=false;
                
                do
                {	
                    inumber=teclado.nextInt();
                
                    
                    if(inumber<0)
                    {
                        System.out.print("Por favor, introduzca un numero positivo: ");
                    }
                    
                }while(inumber<0);
            }
            catch(InputMismatchException e)
            {
                System.out.print("Por favor, introduzca un caracter valido: ");
                teclado.nextLine();
                error=true;
            }
            catch(Exception e)
            {
                System.out.print("Error: "+e.getMessage());
                teclado.nextLine();
                error=true;
            }
            
        }while(error);
    
    return inumber;
    
    }
    
    /**
     * El metodo getPositiveNumber pide por teclado un numero entero dentro del rango establecido con control de errores
     * @return devuelve un numero entero dentro del rango establecido
     */
    public static int getNumberInRange(int minValue, int maxValue)
    {
        do
        {
            try
            {
                error=false;
                
                do
                {	
                    inumber=teclado.nextInt();
                
                    
	                if(inumber<minValue||inumber>maxValue)
	                {
	                	System.out.print("Por favor, introduzca un numero dentro del rango establecido: ");
	                }
                    
                }while((inumber<minValue||inumber>maxValue));
            }
            catch(InputMismatchException e)
            {
                System.out.print("Por favor, introduzca un caracter valido: ");
                teclado.nextLine();
                error=true;
            }
            catch(Exception e)
            {
                System.out.print("Error: "+e.getMessage());
                teclado.nextLine();
                error=true;
            }
            
        }while(error);
    
    return inumber;
    
    }

    /**
     * El metodo getPositiveDecimalNumber pide por teclado un numero decimal positivo con control de errores
     * @return devuelve un numero decimal positivo
     */
    public static double getPositiveDecimalNumber()
    {
        do
        {
            try
            {
                error=false;
                
                do
                {	
                    dnumber=teclado.nextDouble();
                
                    
                    if(dnumber<0)
                    {
                        System.out.print("Por favor, introduzca un numero positivo: ");
                    }
                    
                }while(dnumber<0);
            }
            catch(InputMismatchException e)
            {
                System.out.print("Por favor, introduzca un caracter valido: ");
                teclado.nextLine();
                error=true;
            }
            catch(Exception e)
            {
                System.out.print("Error: "+e.getMessage());
                teclado.nextLine();
                error=true;
            }
            
        }while(error);
    
    return dnumber;
    
    }
    
	public static int getRandomInt(int fnumber)  //Devuelve un numero random aleatorio entre 0 y el numero especificado
	{
		Random r=new Random();
		int nrand=0;
		
		nrand=r.nextInt(fnumber);
		
		//nrand=Math.floor(Math.random()*10+1);
		
		return nrand;
	}
}
