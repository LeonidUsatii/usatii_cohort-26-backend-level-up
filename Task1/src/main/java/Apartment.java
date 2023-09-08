import anatations.NotNull;
import anatations.PrimaryKey;
import anatations.Unique;
import anatations.Varchar;

public class Apartment {
    @PrimaryKey
    private int id;

    @Varchar(MaxLength = 256)
    @NotNull
    private String name;

    @Varchar(MaxLength = 256)
    @NotNull
    @Unique
    private String password;

    @NotNull
    @Unique
    private String email;
}
