package edu.school21.app;

import edu.school21.printer.Printer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new
                ClassPathXmlApplicationContext("context.xml");
        Printer printer = context.getBean(
                "printerWithPrefixToUpperErr", Printer.class);
        printer . print("Hello!") ;
        ((ConfigurableApplicationContext)context).close();
    }
}