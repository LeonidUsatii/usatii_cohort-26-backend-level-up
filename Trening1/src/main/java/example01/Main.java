package example01;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
//        example01.PasswordValidator validator =
//                new example01.PasswordValidator(3);
//        System.out.println(validator.validate("Hello"));
        //Class<?> aClass = PasswordValidator.class;
        //printBaseInfo(List.class);
       // printFieldsOfClass(PasswordValidator.class);
        Class<?> aClass = Class.forName("example01.PasswordValidator");
        Constructor<?> constructor = aClass.getConstructor(String.class, int.class);
        Object object = constructor.newInstance("Hello", 2);
        Field field = aClass.getDeclaredField("minLength");
        field.setAccessible(true);
        field.set(object, 150);
        int i = 0;
    }

    public static void printBaseInfo(Class<?> aClass) {
        System.out.println(aClass.getName() + " "
                + aClass.getTypeName() + " " + aClass.getSimpleName()
                + " " + aClass.getPackageName());
    }

    public static void printFieldsOfClass(Class<?> aClass) {
        Field[] fields = aClass.getDeclaredFields();

        for (Field field : fields) {
            System.out.println(field.getName());
        }
    }
}
