import java.net.*;
import java.io.*;

public class Cliente {

	public static void main (String[] args) throws Exception {

		// Puerto por el que nos conectamos
		String host = "localhost";
		int port = 9000;
		Socket client = new Socket(host, port);
				
		// Variables utilizadas para enviar y recibir mensajes
		String cadena, msj, nombreUsuario = "";
		
		// Flujo de entrada estándar
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// Flujo de salida al servidor
		PrintWriter fsortida = new PrintWriter(client.getOutputStream(), true);
		
		// Flujo de entrada del servidor
		BufferedReader fentrada = new BufferedReader(new InputStreamReader(client.getInputStream()));
		
		// Pedimos el nombre de usuario
		System.out.println("Introduce tu nombre de usuario: ");
		nombreUsuario = in.readLine();
		
		HiloCliente hiloCliente = new HiloCliente(fentrada);
        Thread t = new Thread(hiloCliente);
        t.start();
		
		// Variables utilizadas para enviar un mensaje
		System.out.println("Introduce un mensaje: ");
		cadena = in.readLine();

		while (cadena != null) {

			// Enviament cadena al servidor
			fsortida.println(nombreUsuario + ": " + cadena);
			
			// Seguimos pidiendo más mensajes
			cadena = in.readLine();	
		}

		fsortida.close();
		fentrada.close();
		System.out.println("Finaliza el envío...");
		in.close();
		client.close();	
	}	
}
