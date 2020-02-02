package pojo;

import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

public class TestSpring {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"applicationContext.xml"}
        );
        Category c = (Category) context.getBean("c");
        Product p = (Product) context.getBean("p");
        System.out.println(c.getName());
        System.out.println(p.getName());
        System.out.println(p.getCategory().getName());
        ProductService s = (ProductService) context.getBean("s");
        s.doSomeService();
    }
}
