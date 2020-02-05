package how2java;

import how2java.mapper.CategoryMapper;
import how2java.pojo.Category;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestMybatis10 {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        CategoryMapper1 mapper = session.getMapper(CategoryMapper1.class);

//        add(com.mapper);
//        delete(com.mapper);
//        get(com.mapper);
//        update(com.mapper);
        listAll(mapper);

        session.commit();
        session.close();

    }

    private static void update(CategoryMapper1 mapper) {
        Category c = mapper.get(1);
        c.setName("修改了的Category名稱");
        mapper.update(c);
        listAll(mapper);
    }

    private static void get(CategoryMapper1 mapper) {
        Category c = mapper.get(2);
        System.out.println(c.getName());
    }

    private static void delete(CategoryMapper1 mapper) {
        mapper.delete(2);
        listAll(mapper);
    }

    private static void add(CategoryMapper1 mapper) {
        Category c = new Category();
        c.setName("新增加的Category");
        mapper.add(c);
        listAll(mapper);
    }

    private static void listAll(CategoryMapper1 mapper) {
        List<Category> cs = mapper.list();
        for (Category c : cs) {
            System.out.println(c.getName());
        }
    }

}
