package Test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pojo.Product;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMybatis5 {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

//        listProduct2(session);
//        updateProduct(session);
//        listAllOrLikeProduct(session);
//        listProduct3(session);
//        listProduct4(session);
        listProduct5(session);

        session.commit();
        session.close();
    }

    //查询所有或者模糊查询
    private static void listAllOrLikeProduct(SqlSession session) {
        System.out.println("查询所有");
        List<Product> ps = session.selectList("listProduct");
        for (Product p : ps) {
            System.out.println(p);
        }
        System.out.println("模糊查询");
        Map<String, Object> params = new HashMap<>();
        params.put("name", "a");
        List<Product> ps2 = session.selectList("listProduct", params);
        for (Product p : ps2) {
            System.out.println(p);
        }
    }

    //多条件查询
    private static void listProduct2(SqlSession session) {
        System.out.println("多条件查询");
        Map<String, Object> params = new HashMap<>();
        params.put("name", "a"); //是否添加模糊查询的条件
        params.put("price", "10");
        List<Product> ps2 = session.selectList("listProduct2", params);
        for (Product p : ps2) {
            System.out.println(p);
        }
    }

    //更新语句如果有数据就用该数据更新
    private static void updateProduct(SqlSession session) {
        Product p = new Product();
        p.setId(6);
        p.setName("product zz");
        p.setPrice(99.99f);
        session.update("updateProduct", p);
    }

    //提供了任何条件，就进行条件查询，否则就使用id>1这个条件。
    private static void listProduct3(SqlSession session) {
        Map<String, Object> params = new HashMap<>();
//        params.put("name", "a");//条件1：and name like concat('%',#{name},'%')
        params.put("price", "10");//条件2：and price > #{price}
        List<Product> ps = session.selectList("listProduct3", params);
        for (Product p : ps) {
            System.out.println(p);
        }
    }

    //foreach标签查询(相当于in)
    private static void listProduct4(SqlSession session) {
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(3);
        ids.add(5);
        List<Product> ps = session.selectList("listProduct4", ids);
        for (Product p : ps) {
            System.out.println(p);
        }
    }

    //bind标签模糊查询
    private static void listProduct5(SqlSession session) {
        Map<String, String> params = new HashMap();
        params.put("name", "product");

        List<Product> ps = session.selectList("listProduct5", params);
        for (Product p : ps) {
            System.out.println(p);
        }
    }
}
