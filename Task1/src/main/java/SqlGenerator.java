import anatations.NotNull;
import anatations.PrimaryKey;
import anatations.Unique;
import anatations.Varchar;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class SqlGenerator {
    public String generateTable(Class<?> tableClass) {

        final String CREATE_TABLE =  "CREATE TABLE ";
        StringBuilder sqlRequest = new StringBuilder(CREATE_TABLE);

        String nameOfTable = tableClass.getName();
        sqlRequest.append(nameOfTable).append(" ").append("(\n");

        Field[] fields = tableClass.getDeclaredFields();
        int sizeFields = fields.length;

        for (Field field : fields) {
            String name = field.getName();
            sqlRequest.append(name).append(" ");
            if (name.equals("id")) {
                String fieldType = ("" + field.getType()).toUpperCase();
                sqlRequest.append(fieldType).append(" ");
            }

            Annotation[] annotations = field.getAnnotations();
            int sizeAnnotations = annotations.length;
                for (Annotation annotation : annotations) {
                    String annotationStr = "" + annotation;
                    annotationStr = annotationStr.substring((annotationStr.indexOf('.') + 1),
                            annotationStr.indexOf('(')).toUpperCase();
                    if(annotationStr.equals("NOTNULL")) {
                        annotationStr = "NOT NULL";
                    }
                    if(annotationStr.equals("PRIMARYKEY")) {
                        annotationStr = "PRIMARY KEY";
                    }
                    if(annotationStr.equals("VARCHAR")) {
                        Varchar varchar = field.getDeclaredAnnotation(Varchar.class);
                        int maxLength = varchar.MaxLength();
                        annotationStr += "(" + maxLength + ")";
                    }
                    sqlRequest.append(annotationStr);

                    if (sizeAnnotations > 1) {
                        sqlRequest.append(" ");
                    }
                    sizeAnnotations--;
                }
                if (sizeFields > 1) {
                    sqlRequest.append(",");
                } else {
                    sqlRequest.append(");");
                }
                sqlRequest.append("\n");
            sizeFields--;
        }

        return sqlRequest.toString();
    }
}
