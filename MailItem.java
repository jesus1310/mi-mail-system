/**
 * Write a description of class MailItem here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MailItem
{
    // Atributo que controla el emisor del mensaje
    private String from;
    // Atributo que controla el receptor del mensaje
    private String to;
    // Atributo que controla el mensaje
    private String message;

    /**
     * Constructor for objects of class MailItem
     */
    public MailItem(String de, String para, String mensaje)
    {
        from = de;
        to = para;
        message = mensaje;
    }
    
    /**
     * Método que devuelve la cadena almacenada en from
     */
    public String getFrom()
    {
        return from;
    }
    
    /**
     * Método que devuelve la cadena almacenada en from
     */
    public String getTo()
    {
        return to;
    }
    
    /**
     * Método que devuelve la cadena almacenada en from
     */
    public String getMessage()
    {
        return message;
    }
    
    /**
     * Método que imprime por pantalla los 3 atributos creados con sus valores fijados
     */
    public void printMail()
    {
        System.out.println ("From: " + from);
        System.out.println ("To: " + to);
        System.out.println ("Text: " + message);
    }
}
