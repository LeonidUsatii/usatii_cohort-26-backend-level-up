
import config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.UsersService;
import services.impl.UsersServiceImpl;


public class Main {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        UsersService usersService =applicationContext.getBean(UsersServiceImpl.class);
        System.out.println(usersService.getAllUsers());
   }
}
