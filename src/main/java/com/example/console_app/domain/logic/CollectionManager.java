package com.example.console_app.domain.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import com.example.console_app.api.input.classes.input_manager.InputManager;
import com.example.console_app.api.input_entities.exceptions.IncorrectInputException;
import com.example.console_app.api.input_entities.movie.MovieBuilderDirector;
import com.example.console_app.api.response.records.ConsoleAppResponse;
import com.example.console_app.api.response.interfaces.Response;
import com.example.console_app.domain.commands.exceptions.EmptyCollectionException;
import com.example.console_app.entities.classes.Movie;
import com.example.console_app.entities.enums.MpaaRating;
import com.example.console_app.repository.exceptions.KeyNotFoundException;
import com.example.console_app.repository.interfaces.Repository;
/**
 * Класс CollectionManager управляет коллекцией фильмов, предоставляя методы для работы с ней:
 * добавление, удаление, обновление, отображение и другие операции.
 * Также поддерживает сохранение и загрузку данных из файла.
 *
 * @author Добрышкин Владимир (vodobryshkin)
 * @version 1.0
 * @since 2025-25-02
 */
public class CollectionManager {
    private final Repository repository;
    private Date initializationDate;
    private final InputManager inputManager;
    private final MovieBuilderDirector movieBuilderDirector;

    /**
     * Конструктор класса CollectionManager.
     *
     * @param repository Репозиторий для хранения коллекции.
     * @param inputManager Менеджер ввода данных.
     */
    public CollectionManager(Repository repository, InputManager inputManager) {
        this.repository = repository;
        this.inputManager = inputManager;
        movieBuilderDirector = new MovieBuilderDirector(inputManager);
        
        File file = new File("src/main/resources/config_file");
        if (file.exists() && file.length() > 0) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String dateStr = reader.readLine();
                initializationDate = new Date(Long.parseLong(dateStr));
            } catch (IOException | NumberFormatException e) {
                initializationDate = new Date();
            }
        } else {
            initializationDate = new Date();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(String.valueOf(initializationDate.getTime()));
            } catch (IOException ignored) {
            }
        }
    }

    /**
     * Выводит информацию о коллекции: тип, тип элементов, дату инициализации и количество элементов.
     *
     * @throws KeyNotFoundException Если элемент не найден в коллекции.
     */
    public Response info() throws KeyNotFoundException {
        String message = "";

        message += "Тип коллекции: " + repository.toString() + "\n";
        message += "Тип элементов: " + ((repository.getSize() == 0) ? "не определен" : repository.getByIndex(0).getClass().getSimpleName()) + "\n";
        message += "Дата инициализации: " + initializationDate + "\n";
        message += "Количество элементов: " + repository.getSize() + "\n";

        return new ConsoleAppResponse(true, message);
    }

    /**
     * Добавляет новый элемент в коллекцию.
     *
     * @throws IncorrectInputException Если ввод данных некорректен.
     */
    public Response add() throws IncorrectInputException {
        String message = "Элемент успешно добавлен.";

        repository.add(movieBuilderDirector.inputMovie());
        
        return new ConsoleAppResponse(!Objects.equals(inputManager.toString(), "FileInput"), message);
    }

    /**
     * Сохраняет коллекцию в файл.
     *
     * @throws KeyNotFoundException Если элемент не найден в коллекции.
     */
    public Response save() throws KeyNotFoundException {
        String message = "Коллекция успешно сохранена.";
        repository.writeIntoFile();

        return new ConsoleAppResponse(!Objects.equals(inputManager.toString(), "FileInput"), message);
    }

    /**
     * Отображает все элементы коллекции.
     *
     * @throws KeyNotFoundException Если элемент не найден в коллекции.
     */
    public Response show() throws KeyNotFoundException {
        StringBuilder message = new StringBuilder();

        if (repository.getSize() == 0) {
            message = new StringBuilder("Коллекция пустая.");
        } else {
            for (int i = 0; i < repository.getSize(); i++) {
                message.append(repository.getByIndex(i).toString()).append("\n");
            }
        }

        return new ConsoleAppResponse(!Objects.equals(inputManager.toString(), "FileInput"), message.toString());
    }

    /**
     * Обновляет элемент коллекции по указанному идентификатору.
     *
     * @param id Идентификатор элемента для обновления.
     * @throws KeyNotFoundException Если элемент не найден в коллекции.
     * @throws IncorrectInputException Если ввод данных некорректен.
     */
    public Response update(int id) throws KeyNotFoundException, IncorrectInputException {
        String message = "Элемент был успешно заменён.";

        Movie movie = movieBuilderDirector.inputMovie();
        int nextId = movie.getId();
        movie.setId(id);
        Movie.setNextId(nextId);
        repository.update(id, movie);

        return new ConsoleAppResponse(!Objects.equals(inputManager.toString(), "FileInput"), message);
    }

    /**
     * Удаляет элемент коллекции по указанному идентификатору.
     *
     * @param id Идентификатор элемента для удаления.
     * @throws KeyNotFoundException Если элемент не найден в коллекции.
     */
    public Response remove_by_id(int id) throws KeyNotFoundException {
        String message = "Элемент был успешно удалён.";
        repository.delete(id);

        return new ConsoleAppResponse(!Objects.equals(inputManager.toString(), "FileInput"), message);
    }

    /**
     * Очищает коллекцию, удаляя все элементы.
     *
     * @throws KeyNotFoundException Если элемент не найден в коллекции.
     */
    public Response clear() throws KeyNotFoundException {
        String message = "Элемент был успешно удалён.";

        while (repository.getSize() > 0) {
            repository.delete(repository.getByIndex(0).getId());
        }

        return new ConsoleAppResponse(!Objects.equals(inputManager.toString(), "FileInput"), message);
    }

    /**
     * Удаляет элемент коллекции по указанному индексу.
     *
     * @param index Индекс элемента для удаления.
     * @throws KeyNotFoundException Если элемент не найден в коллекции.
     */
    public Response remove_at(int index) throws KeyNotFoundException {
        String message = "Элемент был успешно удалён.";
        repository.delete(repository.getByIndex(index).getId());

        return new ConsoleAppResponse(!Objects.equals(inputManager.toString(), "FileInput"), message);
    }

    /**
     * Удаляет первый элемент коллекции.
     *
     * @throws KeyNotFoundException Если элемент не найден в коллекции.
     * @throws EmptyCollectionException Если коллекция пуста.
     */
    public Response remove_first() throws KeyNotFoundException, EmptyCollectionException {
        String message = "Элемент был успешно удалён.";
        if (repository.getSize() == 0) {
            throw new EmptyCollectionException("Ошибка: выполнить действие не удалось, так как коллекция пустая. ");
        }
        repository.delete(repository.getByIndex(0).getId());

        return new ConsoleAppResponse(!Objects.equals(inputManager.toString(), "FileInput"), message);
    }

    /**
     * Удаляет все элементы коллекции, которые меньше указанного элемента.
     *
     * @throws KeyNotFoundException Если элемент не найден в коллекции.
     * @throws IncorrectInputException Если ввод данных некорректен.
     */
    public Response remove_lower() throws KeyNotFoundException, IncorrectInputException {
        Movie compMovie = movieBuilderDirector.inputMovie();
        int value = repository.getSize();
        ArrayList<Integer> arr = new ArrayList<>(); 

        for (int i = 0; i < repository.getSize(); i++) {
            if (compMovie.compareTo(repository.getByIndex(i)) > 0) {
                arr.add(repository.getByIndex(i).getId());
            }
        }

        for (Integer integer : arr) {
            repository.delete(integer);
        }

        String message = value - repository.getSize() + " элементов был успешно удалено.";

        return new ConsoleAppResponse(!Objects.equals(inputManager.toString(), "FileInput"), message);
    }

    /**
     * Находит и выводит элемент коллекции с максимальной датой создания.
     *
     * @throws KeyNotFoundException Если элемент не найден в коллекции.
     * @throws EmptyCollectionException Если коллекция пуста.
     */
    public Response max_by_creation_date() throws KeyNotFoundException, EmptyCollectionException {
        if (repository.getSize() == 0) {
            throw new EmptyCollectionException("Ошибка: выполнить действие не удалось, так как коллекция пустая. ");
        }
        Movie maxElement = repository.getByIndex(0);

        for (int i = 1; i < repository.getSize(); i++) {
            int comparison = maxElement.getCreationDate().compareTo(repository.getByIndex(i).getCreationDate());

            if (comparison <= 0) {
                maxElement = repository.getByIndex(i);
            }
        }
        String message = maxElement.toString();
        return new ConsoleAppResponse(!Objects.equals(inputManager.toString(), "FileInput"), message);
    }

    /**
     * Фильтрует и выводит элементы коллекции, имена которых начинаются с указанной строки.
     *
     * @param arg Строка для фильтрации.
     * @throws KeyNotFoundException Если элемент не найден в коллекции.
     */
    public Response filter_starts_with_name(String arg) throws KeyNotFoundException {
        StringBuilder message = new StringBuilder();

        for (int i = 0; i < repository.getSize(); i++) {
            if (repository.getByIndex(i).getName().startsWith(arg)) {
                message.append(repository.getByIndex(i)).append("\n");
            }
        }

        return new ConsoleAppResponse(true, message.toString());
    }

    /**
     * Выводит уникальные значения рейтинга MPAA из коллекции.
     *
     * @throws KeyNotFoundException Если элемент не найден в коллекции.
     */
    public Response print_unique_mpaa_rating() throws KeyNotFoundException {
        Set<MpaaRating> set = new HashSet<>();
        StringBuilder message = new StringBuilder();
        
        for (int i = 0; i < repository.getSize(); i++) {
            set.add(repository.getByIndex(i).getMpaaRating());
        }

        for (MpaaRating mpaaRating : set) {
            message.append(mpaaRating).append("\n");
        }

        return new ConsoleAppResponse(true, message.toString());
    }
}