import anatations.NotNull;
import anatations.PrimaryKey;
import anatations.Unique;
import anatations.Varchar;

import java.util.Objects;

public class Staff {
    @PrimaryKey
    private long id;

    @Varchar(MaxLength = 256)
    @NotNull
    private String name;

    @NotNull
    @Unique
    private String email;

    public Staff() {
    }

    Staff(long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return id == staff.id && Objects.equals(name, staff.name) && Objects.equals(email, staff.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }
}
