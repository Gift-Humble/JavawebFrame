package how2java.mapper;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import how2java.pojo.Order;

import java.util.List;

//与OrderItem建立一对多关系
public interface OrderMapper {

    @Select("select * from order_")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "orderItems", javaType = List.class, column = "id",
                    many = @Many(select = "how2java.mapper.OrderItemMapper.listByOrder"))
    })
    public List<Order> list();
}
