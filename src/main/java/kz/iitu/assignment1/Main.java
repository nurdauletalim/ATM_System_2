package kz.iitu.assignment1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        Bank bank = context.getBean("bank",Bank.class);

        Account acc = bank.getAccounts().get(1);
        bank.getBankService().menu(acc);
        ((ClassPathXmlApplicationContext) context).close();
    }
}
