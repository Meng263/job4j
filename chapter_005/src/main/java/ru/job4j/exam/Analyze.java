package ru.job4j.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Определение разницы между начальным состояние списка и измененным.
 */
public class Analyze {
    /**
     * Метод анализирует изменинеие в списке
     * Возвращает экземляр класса Info, который содержит информацию о кол-ве
     * добавленных элементах, измененных элементах, удаленных элементах
     * @param previous начальный список
     * @param current измененнный список
     * @return экземляр класса Info
     */
    public Info diff(List<User> previous, List<User> current) {
        int added = 0, changed = 0, deleted = 0;
        List<User> change = new ArrayList<>(current);
        for (User userPrevious : previous) {
            boolean isDeleted = true;
            for (User userCurrent : current) {
                if (userCurrent.id == userPrevious.id) {
                    change.remove(userCurrent);
                    isDeleted = false;
                    if (!userCurrent.equals(userPrevious)) {
                        changed++;
                    }
                }
            }
            if (isDeleted) {
                deleted++;
            }
        }
        added = change.size();
        return new Info(added, changed, deleted);
    }

    public static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id
                    && Objects.equals(name, user.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    public static class Info {
        private int added;
        private int changed;
        private int deleted;

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }

        public int getAdded() {
            return added;
        }

        public int getChanged() {
            return changed;
        }

        public int getDeleted() {
            return deleted;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Info info = (Info) o;
            return added == info.added
                    && changed == info.changed
                    && deleted == info.deleted;
        }

        @Override
        public int hashCode() {
            return Objects.hash(added, changed, deleted);
        }
    }
}