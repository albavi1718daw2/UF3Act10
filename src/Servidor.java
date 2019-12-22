import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static PrintWriter arraySalidaClientes[];
	public static int clientes = 0;
	
	public static void main (String[] args) throws Exception {

		// Puerto por el cual nos conectaremos
		int numPort = 9000;
		ServerSocket servidor = new ServerSocket(numPort);
		
		// Indicamos el tamaño del array
		arraySalidaClientes = new PrintWriter[Integer.parseInt(args[0])];
		
		// Esperamos a que se conecten clientes
		System.out.println("Esperando clientes...");
		
		// Bucle para ir contando los clientes, mientras sea menor a lo recibido
		// por parámetros, sigue
		while (clientes < Integer.parseInt(args[0])) {

			// Conectamos al cliente
			Socket clienteConectado = servidor.accept();

			// Flujo de salida al cliente
			arraySalidaClientes[clientes] = new PrintWriter(clienteConectado.getOutputStream(), true);
			
			// Creamos el hilo
			Hilo hilo = new Hilo(clienteConectado);
			Thread hiloThread = new Thread(hilo);
			hiloThread.start();

			clientes++;
		}

		servidor.close();
	}
}
