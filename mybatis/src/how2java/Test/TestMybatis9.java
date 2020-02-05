package how2java.Test;

import how2java.mapper.OrderMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import how2java.pojo.Order;
import how2java.pojo.OrderItem;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMybatis9 {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        OrderMapper mapper = session.getMapper(OrderMapper.class);
        List<Order> os = mapper.list();
        for (Order o : os) {
            System.out.println(o.getCode());
            List<OrderItem> ois = o.getOrderItems();
            if (null != ois) {
                for (OrderItem oi : ois) {
                    System.out.format("\t%s\t%f\t%d%n",
                            oi.getProduct().getName(), oi.getProduct().getPrice(), oi.getNumber());
                }
            }
        }
    }


}
