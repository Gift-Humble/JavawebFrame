package Test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pojo.Product;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

//多对一
public class TestMybatis3 {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        List<Product> ps = session.selectList("moreToOneListProduct");
        for (Product p : ps) {
            System.out.println(p + "对应的分类是 \t" + p.getCategory());
        }
        session.commit();
        session.close();
    }
}
