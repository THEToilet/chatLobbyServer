package jp.ac.shibaura.it.ie.usecases.image;

import jp.ac.shibaura.it.ie.usecases.core.InputData;

public class ImageListInputData implements InputData<ImageListOutputData> {
    private final String session;
    private final String categoryId;

    public ImageListInputData(String session, String categoryId) {
        this.session = session;
        this.categoryId = categoryId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getSession() {
        return session;
    }

}
