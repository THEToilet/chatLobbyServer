package jp.ac.shibaura.it.ie.usecases.category.list;

import jp.ac.shibaura.it.ie.usecases.core.InputData;

public class CategoryListInputData implements InputData<CategoryListOutputData> {
    private final String session;

    public CategoryListInputData(String session) {
        this.session = session;
    }

    public String getSession() {
        return session;
    }
}
