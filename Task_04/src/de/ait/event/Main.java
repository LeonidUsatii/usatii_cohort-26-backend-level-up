package de.ait.event;

import de.ait.event.config.ApplicationConfig;
import de.ait.event.services.EventsService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
       EventsService eventsService = context.getBean(EventsService.class);



        while (true)  {

            System.out.println("1. Добавить новое событие");
            System.out.println("2. Найти событие по дате");
            System.out.println("3. Обновить событие");
            System.out.println("4. Удалить событие");
            System.out.println("0. Выход.");

            int command = scanner.nextInt();
            scanner.nextLine();

            switch (command) {
                case 1 -> {
                    System.out.println("Добавляем новое событие");
                    System.out.println("Введите название события");
                    String title = scanner.nextLine();
                    System.out.println("Введите дату начала события");
                    String startDate = scanner.nextLine();
                    System.out.println("Введите дату окончания события");
                    String expirationDate = scanner.nextLine();
                    eventsService.addEvent(title, startDate, expirationDate);
                }
                case 2 -> {
                    System.out.println("Получить событие по дате");
                    System.out.println("Введите дату начала события:");
                    String startDate = scanner.nextLine();
                    eventsService.getEvent(startDate);
                }
                case 3 -> {
                    System.out.println("Обновить событие");
                    System.out.println("Введите id события:");
                    Long id = scanner.nextLong();
                    scanner.nextLine();
                    System.out.println("Введите дату начала события");
                    String startDate = scanner.nextLine();
                    System.out.println("Введите дату окончания события");
                    String expirationDate = scanner.nextLine();
                    eventsService.updateEvent(id, startDate, expirationDate);

                }
                case 4 -> {
                    System.out.println("Удалить событие");
                    System.out.println("Введите id события:");
                    Long id = scanner.nextLong();
                    scanner.nextLine();
                    eventsService.deleteEvent(id);
                }
                case 0 -> {
                    System.out.println("Выход");
                    System.exit(0);
                }
                default -> System.out.println("Команда не распознана");
            }
        }

    }




}