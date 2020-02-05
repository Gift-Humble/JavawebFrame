package how2java;

import how2java.mapper.CategoryMapper;
import how2java.pojo.Category;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//分页
public class TestMybatis11 {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        CategoryMapper mapper = session.getMapper(CategoryMapper.class);

        /*
        已插入100条数据
         */

        xmlWay(session);
//        annotationWay(mapper);

        session.commit();
        session.close();
    }

    //分页的xml配置方法
    private static void xmlWay(SqlSession session) {
        Map<String, Object> params = new HashMap<>();
        params.put("start", 0);
        params.put("count", 5);
        List<Category> cs = session.selectList("listCategoryPage", params);
        for (Category c : cs) {
            System.out.println(c);
        }
    }

    //分页的注解方法
    private static void annotationWay(CategoryMapper mapper) {
        List<Category> cs = mapper.listByPage(0, 5);
        for (Category c : cs) {
            System.out.println(c);
        }
    }
}
