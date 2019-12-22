import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Hilo implements Runnable {

	Socket clienteConectado;
	String cadena;
	PrintWriter fsalida;
	BufferedReader fentrada;

	public Hilo (Socket clienteConectado) {
		
		this.clienteConectado = clienteConectado;
		this.fsalida = null;
	}

	@Override
	public void run() {
		
		// Controlamos si el cliente quiere salir o no
		boolean salir = false;
		
		// Controlamos que algo pueda salir mal
		try {
			
			// Flujo de entrada del cliente
			fentrada = new BufferedReader(new InputStreamReader(this.clienteConectado.getInputStream()));
			
			// Mostramos que se ha conectado un cliente
			System.out.println("Cliente conectado.");
			
			while ((cadena = fentrada.readLine()) != null && !salir) {
				for (int i = 0; i < Servidor.arraySalidaClientes.length; i++) {
					if (Servidor.arraySalidaClientes[i] != null) {
						Servidor.arraySalidaClientes[i].println(cadena);
					}
				}
				System.out.println(cadena);
				if (cadena.equals("exit")) { 
					fentrada.close();
					fsalida.close();
					salir = true;
				}
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}