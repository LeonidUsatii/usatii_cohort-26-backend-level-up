public class Main {
    public static void main(String[] args) {
        SqlGenerator sqlGenerator = new SqlGenerator();
        String staffCreateTable = sqlGenerator.generateTable(Staff.class);
        System.out.println(staffCreateTable);
    }
}
