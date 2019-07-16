package test.setAndGetTest;

import java.io.Serializable;

/**
 * Created by liaura_ljl on 2018/1/18.
 */
public class Cat implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
