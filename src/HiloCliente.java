import java.io.BufferedReader;
import java.io.IOException;

public class HiloCliente implements Runnable {

	private BufferedReader fentrada;

	public HiloCliente (BufferedReader fentrada) {
	
		this.fentrada = fentrada;
	}

	@Override
	public void run() {
		
		 try {
			 
			 // Leemos el mensaje
             String mensaje = fentrada.readLine();
             
             // Vamos leyendo todo lo que nos llegue
             while(true) {
            	 
            	 // Lo mostramos y lo leemos de nuevo
                 System.out.println(mensaje);
                 mensaje = fentrada.readLine();
             }
           
         // Excepción en caso de fallo
         } catch (IOException ex) {
        	 
         	ex.printStackTrace();
         }
	}
}