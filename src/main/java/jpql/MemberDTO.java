package jpql;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {


    public MemberDTO(int age, String username) {
        this.username = username;
        this.age = age;
    }

    private String username;
    private int age;
}
