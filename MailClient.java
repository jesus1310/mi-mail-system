/**
 * Write a description of class MailClient here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MailClient
{
    // Artibuto que controla un objeto de la clase MailServer
    private MailServer server;
    // Atributo que controla una cadena que contiene los datos del usuario
    private String user;
    
    /**
     * Constructor for objects of class MailClient
     */
    public MailClient(MailServer server,String user)
    {
        // Definimos las variables del constructor y se asignan sus valores iniciales con parámetros.
        this.server = server;
        this.user = user;
    }
    
    /**
     * Método que recupera del servidor el siguiente correo
     */
    public MailItem getNextMailItem(){
        // En este método se invoca el método para mostrar el siguiente mensaje almacenado en el objeto de la clase MailServer.
        return server.getNextMailItem(user);
    }
    
    /**
     * Método que imprime el mensaje por pantalla
     */
    public void printNextMailItem(){
        // El método howManyMailItems nos devuelve un entero que indica el número de mensajes que tiene el usuario indicado por parámetro.
        // Si ese número es mayor que 0 se invoca el método getNextMailItem del objeto server sobre un objeto de la clase MailItem.
        // Se imprime el contenido del objeto de la clase MailItem.
        // Si es igual a 0, se indica por pantalla que no hay mensajes.
        if (server.howManyMailItems(user) > 0){
            MailItem correo = server.getNextMailItem(user);
            correo.printMail();
        }
        else{
            System.out.println("No hay mensajes");
        }
    }
    
    /**
     * Método que permite crear y enviar un mensaje
     */
    public void sendMailItem(String to,String subject,String message){
        // Se crea un objeto de la clase MailItem en el que se indicará a quién va dirigido y el mensaje, y se añade al server con el método post.
        MailItem email = new MailItem(user,to,subject,message);
        server.post(email);
    }
    
    /**
     * Método que permite saber cuántos correos tiene en el servidor cada usuario sin descargarlos.
     */
    public void howManyMail(){
        System.out.println("Tienes " + server.howManyMailItems(user) + " correo/s");
    }
}
