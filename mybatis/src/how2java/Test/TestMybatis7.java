package how2java.Test;

import how2java.mapper.CategoryMapper;
import how2java.pojo.Category;
import how2java.pojo.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

//一对多
public class TestMybatis7 {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        CategoryMapper mapper = session.getMapper(CategoryMapper.class);

        listAll(mapper);

        session.commit();
        session.close();
    }

    //查询所有
    private static void listAll(CategoryMapper mapper) {
        List<Category> cs = mapper.list();
        for (Category c : cs) {
            System.out.println(c);
//            List<Product> ps = c.getProducts();
//            for (Product p : ps) {
//                System.out.println("\t" + p);
//            }
        }
    }
}
