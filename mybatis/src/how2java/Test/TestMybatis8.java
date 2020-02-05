package how2java.Test;

import how2java.mapper.ProductMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import how2java.pojo.Product;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

//多对一
public class TestMybatis8 {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        ProductMapper mapper = session.getMapper(ProductMapper.class);

        List<Product> ps = mapper.list();
        for (Product p : ps) {
            System.out.println(p + "\t对应的分类是:\t" + p.getCategory().getName());
        }

        session.commit();
        session.close();
    }
}
