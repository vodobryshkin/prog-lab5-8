package com.example.commands.logic;

import com.example.repository.exceptions.KeyNotFoundException;
import com.example.repository.interfaces.Repository;
import domain.chat.classes.ServerAnswerBuffer;
import domain.chat.enums.AnswerStatus;
import entities.classes.Movie;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.stream.Collectors;

public class CollectionManager {
    private final Repository repository;

    public CollectionManager(Repository repository) {
        this.repository = repository;
    }

    public ServerAnswerBuffer add(String commandName, Movie movie) throws KeyNotFoundException {
        repository.add(movie);
        return new ServerAnswerBuffer(commandName, AnswerStatus.OK, "Элемент был добавлен в коллекцию.");
    }

    public ServerAnswerBuffer save() throws KeyNotFoundException {
        repository.writeIntoFile();
        return new ServerAnswerBuffer(null, AnswerStatus.OK, null);
    }

    public ServerAnswerBuffer exit() throws KeyNotFoundException {
        save();
        return new ServerAnswerBuffer("exit", AnswerStatus.EXIT, null);
    }

    public ServerAnswerBuffer removeById(int id) {
        try {
            int length = repository.returnAll().size();
            repository.returnAll().removeIf(movie -> movie.getId() == id);

            if (length == repository.returnAll().size()) {
                throw new KeyNotFoundException("");
            }
            return new ServerAnswerBuffer("removeById", AnswerStatus.OK, "Элемент был успешно удален.");
        } catch (KeyNotFoundException e) {
            return new ServerAnswerBuffer("removeById", AnswerStatus.ERROR, "Элемент не был удален.");
        }

    }

    public ServerAnswerBuffer show() throws KeyNotFoundException {
        String result = repository.returnAll().stream()
                .map(m -> m.toCsv() + "\n")
                .collect(Collectors.joining());
        return new ServerAnswerBuffer("show", AnswerStatus.OK, "\n" + result);
    }

    public ServerAnswerBuffer clear() {
        repository.clear();
        return new ServerAnswerBuffer("clear", AnswerStatus.OK, "Коллекция была успешно очищена.");
    }

    public ServerAnswerBuffer info() throws KeyNotFoundException {
        String info = "Тип коллекции: " + repository.returnAll().getClass().getSimpleName() + "\n" +
                "Дата инициализации: " + LocalDateTime.now() + "\n" +
                "Количество элементов: " + repository.returnAll().size();
        return new ServerAnswerBuffer("info", AnswerStatus.OK, info);
    }

    public ServerAnswerBuffer update(int id, Movie movie) throws KeyNotFoundException {
        if (!movie.getUserLogin().equals(repository.returnAll().get(id).getUserLogin())) {
            System.out.printf("%s %s\n", movie.getUserLogin(), repository.returnAll().get(id).getUserLogin());
            return new ServerAnswerBuffer("update", AnswerStatus.ERROR, "Недостаточно прав для этого действия.");
        }
        try {
            boolean updated = repository.returnAll().stream()
                    .filter(m -> m.getId() == id)
                    .findFirst()
                    .map(m -> {
                        int index = 0;
                        try {
                            index = repository.returnAll().indexOf(m);
                        } catch (KeyNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            movie.setId(repository.returnAll().get(index).getId());
                            repository.returnAll().set(index, movie);
                        } catch (KeyNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        return true;
                    })
                    .orElseThrow(() -> new KeyNotFoundException(""));

            return new ServerAnswerBuffer("update", AnswerStatus.OK, "Элемент с ID " + id + " был обновлен.");
        } catch (KeyNotFoundException e) {
            return new ServerAnswerBuffer("update", AnswerStatus.ERROR, "Элемент с ID " + id + " не найден.");
        }
    }

    public ServerAnswerBuffer removeAt(int index) throws KeyNotFoundException {
        try {
            if (index < 0 || index >= repository.returnAll().size()) {
                throw new IndexOutOfBoundsException();
            }
            Movie removed = repository.returnAll().remove(index);
            return new ServerAnswerBuffer("remove_at", AnswerStatus.OK,
                    "Удален элемент: " + removed);
        } catch (IndexOutOfBoundsException e) {
            return new ServerAnswerBuffer("remove_at", AnswerStatus.ERROR,
                    "Неверный индекс: " + index);
        }
    }

    public ServerAnswerBuffer removeFirst() throws KeyNotFoundException {
        if (repository.returnAll().isEmpty()) {
            return new ServerAnswerBuffer("remove_first", AnswerStatus.ERROR,
                    "Коллекция пуста");
        }
        Movie removed = repository.returnAll().remove(0);
        return new ServerAnswerBuffer("remove_first", AnswerStatus.OK,
                "Удален первый элемент: " + removed);
    }

    public ServerAnswerBuffer removeLower(Movie movie) throws KeyNotFoundException {
        int initialSize = repository.returnAll().size();
        repository.returnAll().removeIf(m -> m.compareTo(movie) < 0);
        int removedCount = initialSize - repository.returnAll().size();
        return new ServerAnswerBuffer("remove_lower", AnswerStatus.OK,
                "Удалено элементов: " + removedCount);
    }

    public ServerAnswerBuffer maxByCreationDate() throws KeyNotFoundException {
        return repository.returnAll().stream()
                .max(Comparator.comparing(Movie::getCreationDate))
                .map(movie -> new ServerAnswerBuffer("max_by_creation_date",
                        AnswerStatus.OK, "\n" + movie))
                .orElse(new ServerAnswerBuffer("max_by_creation_date",
                        AnswerStatus.ERROR, "Коллекция пуста."));
    }

    public ServerAnswerBuffer filterStartsWithName(String prefix) throws KeyNotFoundException {
        String result = repository.returnAll().stream()
                .filter(m -> m.getName().startsWith(prefix))
                .map(Movie::toString)
                .collect(Collectors.joining("\n"));

        return new ServerAnswerBuffer("filter_starts_with_name", AnswerStatus.OK,
                result.isEmpty() ? "Нет элементов с таким префиксом" : result);
    }

    public ServerAnswerBuffer printUniqueMpaaRating() throws KeyNotFoundException {
        String result = "\n" + repository.returnAll().stream()
                .map(Movie::getMpaaRating)
                .distinct()
                .map(Object::toString)
                .collect(Collectors.joining("\n"));

        return new ServerAnswerBuffer("print_unique_mpaa_rating", AnswerStatus.OK,
                result.isEmpty() ? "Нет элементов с рейтингом MPAA" : result);
    }
}
