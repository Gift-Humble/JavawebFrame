package how2java.mapper;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import how2java.pojo.OrderItem;

import java.util.List;

//与Product建立多对一关系
public interface OrderItemMapper {

    @Select("select * from order_item_ where oid = #{oid}")
    @Results({
            @Result(property = "product", column = "pid", one = @One(select = "how2java.mapper.ProductMapper.get"))
    })
    public List<OrderItem> listByOrder(int oid);
}
