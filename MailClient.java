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
    // Atributo que guarda el último mensaje que se ha descargado del servidor
    private MailItem lastMail;
    // Atributo que guarda el último mensaje de spam descargado
    private boolean spam;
    // Atributo que controla cuantos mensajes se han enviado
    private int countSent;
    // Atributo que controla cuantos mensajes se han recibido
    private int countReceived;
    // Atributo que controla cuantos mensajes son spam
    private int countSpam;
    // Atributo que guarda la dirección de la persona que nos manda el mensaje más largo y los caracteres de dicho mensaje
    private String longestMessageEmitter;
    // Atributo que guarda el cuerpo del mensaje recibido que más caracteres tiene
    private String longest;
    // Atributo que guarda el último mensaje spam
    private MailItem lastSpam;

    /**
     * Constructor for objects of class MailClient
     */
    public MailClient(MailServer server,String user)
    {
        // Definimos las variables del constructor y se asignan sus valores iniciales con parámetros.
        this.server = server;
        this.user = user;
        longest = "";
    }

    /**
     * Método que recupera del servidor el siguiente correo
     */
    public MailItem getNextMailItem(){
        // En este método se invoca el método para mostrar el siguiente mensaje almacenado en el objeto de la clase MailServer.
        MailItem nextItem = server.getNextMailItem(user);
        if (nextItem != null){
            if (nextItem.getMessage().length() > longest.length()){
                longest = nextItem.getMessage();
                longestMessageEmitter = nextItem.getFrom();
            }
            if (nextItem.getMessage().contains("trabajo")){
                spam = false;
                lastMail = nextItem;
            }
            else{
                if ((nextItem.getMessage().contains("regalo") || nextItem.getMessage().contains("promocion"))){
                    spam = true;
                    lastSpam = nextItem;
                    nextItem = null;
                    countSpam = countSpam + 1;
                }
                else{
                    spam = false;
                    lastMail = nextItem;
                }
            }
            countReceived = countReceived + 1;
        }
        return nextItem;
    }

    /**
     * Método que imprime el mensaje por pantalla
     */
    public void printNextMailItem(){
        // El método howManyMailItems nos devuelve un entero que indica el número de mensajes que tiene el usuario indicado por parámetro.
        // Si ese número es mayor que 0 se invoca el método getNextMailItem del objeto server sobre un objeto de la clase MailItem.
        // Se imprime el contenido del objeto de la clase MailItem.
        // Si es igual a 0, se indica por pantalla que no hay mensajes.
        MailItem correo = getNextMailItem();
        if (server.howManyMailItems(user) > 0){
            if (spam == false){
                correo.printMail();
                lastMail = correo;
            }
            else{
                System.out.println("Se ha recibido spam");
            }
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
        countSent = countSent + 1;
    }

    /**
     * Método que permite saber cuántos correos tiene en el servidor cada usuario sin descargarlos.
     */
    public void howManyMail(){
        System.out.println("Tienes " + server.howManyMailItems(user) + " correo/s");
    }

    /**
     * Método que recupera el siguiente correo del servidor y genera una respuesta automática.
     */
    public void getNextMailItemAutomaticRespond(){
        MailItem mail = getNextMailItem();
        if (spam == true){
            if (mail != null){
                String newSubject = "RE: " + mail.getSubject();
                String answer = "Estoy fuera de la oficina \n\n" + "Original message: " + mail.getMessage();
                MailItem autoRespond = new MailItem(user,mail.getFrom(),newSubject,answer);
                server.post(autoRespond);
                lastMail = mail;
            }
        }
        countSent = countSent + 1;
    }

    /**
     * Método que muestra por pantalla el último correo descargado
     */
    public void printLast(){
        if (lastMail != null){
            lastMail.printMail();
        }
        else{
            System.out.println("No se ha recibido ningún mensaje");
        }
    }

    /**
     * Método que permite saber cuántos mensajes se han recibido, cuántos se han enviado, cuántos son spam,
     * el porcentaje de spam y el emisor del mensaje recibido más largo junto con los caracteres del 
     */
    public void showStats(){
        System.out.println("Recibidos: " + countReceived);
        System.out.println("Enviados: " + countSent);
        System.out.println("Spam: " + countSpam);
        if (countSpam > 0){
            System.out.println("Porcentaje de spam: " + (countSpam/countReceived)*100 + "%");
        }
        else{
            System.out.println("Porcentaje de spam: 0%");
        }
        System.out.println("Destinatario: " + longestMessageEmitter + ", longitud: " + longest.length());
    }
    
    /**
     * Método para obtener los datos del último mensaje de spam recibido
     */
    public void showInfoLastSpam(){
        if (countSpam > 0){
            lastSpam.printMail();
        }
        else{
            System.out.println("No se ha recibido spam");
        }
    }
}
