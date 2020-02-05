package how2java.Test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import how2java.pojo.Order;
import how2java.pojo.OrderItem;
import how2java.pojo.Product;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMybatis4 {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

//        addOrderItem(session);
        deleteOrderItem(session);
        listOrder(session);

        session.commit();
        session.close();
    }

    //多对多查询
    private static void listOrder(SqlSession session) {
        List<Order> os = session.selectList("listOrder");
        for (Order o : os) {
            System.out.println(o.getCode());
            List<OrderItem> ois = o.getOrderItems();
            for (OrderItem oi : ois) {
                System.out.format("\t%s\t%f\t%d%n", oi.getProduct().getName(), oi.getProduct().getPrice(), oi.getNumber());
            }
        }
    }

    //建立关系
    private static void addOrderItem(SqlSession session) {
        Order o1 = session.selectOne("getOrder", 1);
        Product p6 = session.selectOne("getProduct", 6);
        OrderItem oi = new OrderItem();
        oi.setProduct(p6);
        oi.setOrder(o1);
        oi.setNumber(200);
        session.insert("addOrderItem", oi);
    }

    //删除关系
    private static void deleteOrderItem(SqlSession session) {
        Order o1 = session.selectOne("getOrder", 1);
        Product p6 = session.selectOne("getProduct", 6);
        OrderItem oi = new OrderItem();
        oi.setProduct(p6);
        oi.setOrder(o1);
        session.delete("deleteOrderItem", oi);
    }
}
