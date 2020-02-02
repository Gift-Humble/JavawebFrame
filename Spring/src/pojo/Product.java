package pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("p")
public class Product {
    private int id;
    private String name = "product 2";

    //    @Resource(name = "c")
    @Autowired
    private Category category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    //    @Autowired
    public void setCategory(Category category) {
        this.category = category;
    }
}
