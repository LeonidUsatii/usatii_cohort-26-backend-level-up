import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("SqlGenerator is works...")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
public class SqlGeneratorTest {
    private SqlGenerator sqlGenerator;

    @BeforeEach
    public void setUp() {

        sqlGenerator = new SqlGenerator();
    }

    @DisplayName("generateTable():")
    @Nested
    public class GenerateTable {
        @Test
        void return_string_from_fields_on_staff_class() {
            String expected = "CREATE TABLE Staff (\nid LONG PRIMARY KEY," +
                    "\nname VARCHAR(256) NOT NULL,\nemail NOT NULL UNIQUE);\n";
            String actual = sqlGenerator.generateTable(Staff.class);
            assertEquals(expected, actual);
        }

        @Test
        void return_string_from_fields_on_apartment_class() {
            String expected = "CREATE TABLE Apartment (\nid INT PRIMARY KEY," +
                    "\nname VARCHAR(256) NOT NULL,\npassword VARCHAR(256) NOT NULL UNIQUE," +
                    "\nemail NOT NULL UNIQUE);\n";
            String actual = sqlGenerator.generateTable(Apartment.class);
            assertEquals(expected, actual);
        }
    }

}
