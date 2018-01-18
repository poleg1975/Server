import java.net.*;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class Server {
   public static void main(String[] ar)    {
     int port = 6666; // случайный порт (может быть любое число от 1025 до 65535)
       try {
         ServerSocket ss = new ServerSocket(port); // создаем сокет сервера и привязываем его к вышеуказанному порту
         System.out.println("Waiting for a client...");

         Socket socket = ss.accept(); // заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером
         System.out.println("Got a client!");
         System.out.println();

 // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиенту. 
         InputStream sin = socket.getInputStream();
         OutputStream sout = socket.getOutputStream();

 // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
         DataInputStream in = new DataInputStream(sin);
         DataOutputStream out = new DataOutputStream(sout);

		 Calendar calendar = Calendar.getInstance();
         String line = null;
         while(true) {
           line = in.readUTF(); // ожидаем пока клиент пришлет строку текста.
           System.out.println("Client responce: " + line);
           out.writeUTF(line + " " + new SimpleDateFormat("d.MM.YYYY").format(calendar.getTime())); // отсылаем клиенту обратно ту самую строку текста.
           out.flush(); // заставляем поток закончить передачу данных.
           System.out.println("Waiting for the next line...");
           System.out.println();
         }
      } catch(Exception x) { x.printStackTrace(); }
   }
}