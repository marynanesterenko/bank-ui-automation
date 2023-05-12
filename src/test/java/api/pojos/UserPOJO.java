package api.pojos;
// our goal is not to hard-code the body in the RESTtest Class
// that is why we are designing this Class, so that we can later create an Object of it

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// there are LomBok annotation
// when we run this Class - these annotations will create the methods and annotations
// if we had manually created the getters and setters manually- our class would be too long
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserPOJO {

    private transient int id;
    @Expose
    private String name;
    @Expose
    private String email;
    @Expose
    private String gender;
    @Expose
    private String status;

    // one of the characteristics of POJO Class is that it may or may not have the default Constructor
    public UserPOJO (String name, String email, String gender, String status) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserPOJO{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", gender='").append(gender).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
