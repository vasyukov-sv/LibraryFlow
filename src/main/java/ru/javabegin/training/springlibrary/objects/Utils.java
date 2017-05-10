package ru.javabegin.training.springlibrary.objects;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.javabegin.training.springlibrary.enums.SearchType;

import javax.faces.context.FacesContext;
import java.util.*;

@Component
public class Utils {

    private final MessageSource msg;
    private Map<String, SearchType> searchTypeMap = new HashMap<String, SearchType>();
    private SearchType selectedSearchType = SearchType.TITLE; //default
    private Character[] letters = new Character[]
            {'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У',
                    'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я'};

    @Autowired
    public Utils(MessageSource msg) {
        this.msg = msg;
    }

    public static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
        List<T> r = new ArrayList<T>(c.size());
        for (Object o : c)
            r.add(clazz.cast(o));
        return r;
    }

    public SearchType getSelectedSearchType() {
        return selectedSearchType;
    }

    public Map<String, SearchType> getSearchTypeMap() {
        searchTypeMap.clear();
        searchTypeMap.put(msg.getMessage("author_name", null, FacesContext.getCurrentInstance().getViewRoot().getLocale()), SearchType.AUTHOR);
        searchTypeMap.put(msg.getMessage("book_name", null, FacesContext.getCurrentInstance().getViewRoot().getLocale()), SearchType.TITLE);
        return searchTypeMap;
    }

    public void setSearchTypeMap(Map<String, SearchType> searchTypeMap) {
        this.searchTypeMap = searchTypeMap;
    }

    public Character[] getLetters() {
        return letters;
    }

}
