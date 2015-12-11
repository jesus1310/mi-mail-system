
/**
 * Write a description of class test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class test
{
    /**
     *  
     */
    public void test1()
    {
        MailServer gmail = new MailServer();
        MailClient cliente1 = new MailClient(gmail, "pepe");
        MailClient cliente2 = new MailClient(gmail, "maria");
        
        cliente1.sendMailItem("maria","Asunto","Hola");
        cliente2.getNextMailItemAutomaticRespond();
        cliente1.printNextMailItem();
        cliente2.getNextMailItemAutomaticRespond();
        cliente1.printNextMailItem();
    }
}
