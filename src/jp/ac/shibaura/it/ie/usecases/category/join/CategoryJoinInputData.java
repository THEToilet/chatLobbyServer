package jp.ac.shibaura.it.ie.usecases.category.join;

import jp.ac.shibaura.it.ie.usecases.core.InputData;

public class CategoryJoinInputData implements InputData<CategoryJoinOutputData> {
    private String session;
    private String userId;
    private String categoryId;

    public CategoryJoinInputData(String session, String userId, String categoryId) {
        this.session = session;
        this.userId = userId;
        this.categoryId = categoryId;
    }

    public String getSession() {
        return this.session;
    }

    public String getCategoryId() {
        return this.categoryId;
    }

    public String getUserId(){
        return this.userId;
    }
}
