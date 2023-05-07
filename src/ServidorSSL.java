import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ServidorSSL {

    public static void main(String[] args) {
        System.setProperty("javax.net.ssl.keyStore", "keystore.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "123456");
        // Crear una instancia de SSLServerSocketFactory
        SSLServerSocketFactory sslServerSocketFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();

        try {
            // Crear un SSLServerSocket en el puerto 12345
            SSLServerSocket sslServerSocket = (SSLServerSocket) sslServerSocketFactory.createServerSocket(12345);

            System.out.println("Servidor iniciado");

            while (true) {
                // Esperar a que un cliente se conecte
                SSLSocket sslSocket = (SSLSocket) sslServerSocket.accept();
                System.out.println("Cliente conectado desde " + sslSocket.getInetAddress());

                // Leer los datos enviados por el cliente
                BufferedReader in = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
                String line = in.readLine();
                System.out.println("Mensaje recibido del cliente: " + line);

                // Enviar una respuesta al cliente
                PrintWriter out = new PrintWriter(sslSocket.getOutputStream(), true);
                out.println("Respuesta del servidor: Hola cliente");

                // Cerrar los recursos
                in.close();
                out.close();
                sslSocket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
