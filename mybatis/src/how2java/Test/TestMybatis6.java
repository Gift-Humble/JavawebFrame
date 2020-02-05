package how2java.Test;

import how2java.mapper.CategoryMapper;
import how2java.pojo.Category;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

//注解方式配置CRUD测试类
public class TestMybatis6 {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        CategoryMapper mapper = session.getMapper(CategoryMapper.class);

        //CRUD操作

//        update(com.mapper);
        listAll(mapper);
//        get(com.mapper);
//        delete(com.mapper);
//        add(com.mapper);

        session.commit();
        session.close();
    }

    //修改
    private static void update(CategoryMapper mapper) {
        Category c = mapper.get(2);
        c.setName("修改了的Category名称");
        mapper.update(c);
        listAll(mapper);
    }

    //删除
    private static void delete(CategoryMapper mapper) {
        mapper.delete(2);
        listAll(mapper);
    }

    //增加
    private static void add(CategoryMapper mapper) {
        Category c = new Category();
        c.setName("新增加的Category");
        mapper.add(c);
        listAll(mapper);
    }

    //获取一个
    private static void get(CategoryMapper mapper) {
        Category c = mapper.get(2);
        System.out.println(c.getName());
    }

    //查询所有
    private static void listAll(CategoryMapper mapper) {
        List<Category> cs = mapper.list();
        for (Category c : cs) {
            System.out.println(c.getName());
        }
    }
}
