package optional;

import org.junit.Test;

import java.util.Optional;

/**
 * 测试 Optional
 * @author hlam
 * @date 2023/1/15
 */
public class TestOptional {
    @Test
    public void test1() {
        Entity entity = new Entity();
        entity = null;
//        Optional<Entity> optional = Optional.of(entity);
        Optional<Entity> optional = Optional.ofNullable(entity);
        System.out.println(optional.orElse(new Entity("a")));
    }
}

class Entity {
    private String name;

    @Override
    public String toString() {
        return "Entity{" +
                "name='" + name + '\'' +
                '}';
    }

    public Entity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Entity(String name) {
        this.name = name;
    }
}
