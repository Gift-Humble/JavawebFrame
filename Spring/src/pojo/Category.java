package pojo;

import org.springframework.stereotype.Component;

@Component("c")
public class Category {
    private int id;
    private String name = "category 2";

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
}
