
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
    public MailClient(MailServer nuevoServidor,String nuevoUsuario)
    {
        server = nuevoServidor;
        user = nuevoUsuario;
    }
    
    /**
     * Método que recupera del servidor el siguiente correo
     */
    public MailItem getNextMailItem(){
        return server.getNextMailItem(user);
    }
    
    /**
     * Método que imprime el mensaje por pantalla
     */
    public void printNextMailItem(){
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
    public void sendMailItem(String para,String mensaje){
        MailItem email = new MailItem(user,para,mensaje);
        server.post(email);
    }
}
