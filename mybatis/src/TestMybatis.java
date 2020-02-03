import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pojo.Category;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMybatis {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();


//        insertCategory(session);
//        deleteCategory(session);
//        updateCategory(session);

//        listAll(session);
//        getCategory(session);
//        getLikeAll(session);
        listCategoryByIdAndName(session);

        session.commit();
        session.close();
    }

    //查询
    private static void listAll(SqlSession session) {
        List<Category> cs = session.selectList("listCategory");
        for (Category c : cs) {
            System.out.println(c.getName());
        }
    }

    //增加
    private static void insertCategory(SqlSession session) {
        Category c = new Category();
        c.setName("新增的Category");
        session.insert("addCategory", c); //关联xml文件中的sql语句id，c为操作的值

    }

    //删除
    private static void deleteCategory(SqlSession session) {
        Category c = new Category();
        c.setId(6);
        session.delete("deleteCategory", c);  //xml文件中的deleteCategory需要id，把c传过去
    }

    //获取记录
    private static void getCategory(SqlSession session) {
        Category c = session.selectOne("getCategory", 2);
        System.out.println(c.getName());
    }

    //修改
    private static void updateCategory(SqlSession session) {
        Category c = session.selectOne("getCategory", 2); //先根据id获取对象
        c.setName("修改了Category的名称"); //修改对象名称
        session.update("updateCategory", c); //再用修改好名称的对象来更新
    }

    //模糊查询
    private static void getLikeAll(SqlSession session) {
        List<Category> cs = session.selectList("listCategoryByName", "改");
        for (Category c : cs) {
            System.out.println(c.getName());
        }
    }

    //多条件模糊查询
    private static void listCategoryByIdAndName(SqlSession session) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", 1);
        params.put("name", "cat");
        List<Category> cs = session.selectList("listCategoryByIdAndName", params);
        for (Category c : cs) {
            System.out.println(c.getName());
        }
    }
}
