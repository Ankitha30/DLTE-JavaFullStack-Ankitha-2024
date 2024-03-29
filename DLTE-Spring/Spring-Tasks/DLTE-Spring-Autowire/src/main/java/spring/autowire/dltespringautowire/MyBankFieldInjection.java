package spring.autowire.dltespringautowire;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.autowire.dltespringautowire.service.MyBank;

public class MyBankFieldInjection {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("spring.autowire.dltespringautowire");
        context.refresh();
        MyBank myBank = context.getBean(MyBank.class);
        System.out.println(myBank.execute().toString());
    }
}


/*
AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();:
This line is creating a new instance of AnnotationConfigApplicationContext,
 which is a Spring application context that accepts component classes as input.
In other words, it’s a container for all your beans (objects managed by Spring).

 context.scan("spring.autowire.dltespringautowire");:
  This line is instructing the AnnotationConfigApplicationContext to scan
  a specific package for any classes annotated with
  @Component, @Service, @Repository, or @Controller.
  Any classes found with these annotations will be registered as beans in the
  application context.


context.refresh();:
This line is refreshing the application context,
which triggers the creation of all singleton beans.
 It’s necessary to call this after scan()
 to ensure that all beans are properly created and initialized.


MyBank myBank = context.getBean(MyBank.class);:
 This line is retrieving an instance of MyBank from the application context.
  If MyBank is a Spring-managed bean
  (i.e., it’s annotated with @Component, @Service, @Repository, or @Controller),
   then Spring will return the managed instance of this bean.


System.out.println(myBank.execute().toString());:
This line is calling the execute() method on the myBank bean and
printing the result to the console.

 */